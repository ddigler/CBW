/**
 *
 */
package common.logging;

import static common.Constraints.notNull;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Formatter;

import common.logging.Logger.Level;

/**
 * A utility for formatting log messages in the following way:<br>
 * HH:MM:SS:nnnnnnnnn LEVEL: msg<br>
 * if a {@link Throwable} is provided, it will be logged on a new line as:<br>
 * HH:MM:SS:nnnnnnnnn FINE: throwable-output
 * <p>
 * This class is not thread safe.
 *
 * @author rstoner
 */
public final class StreamLogWriter implements LogWriter {
    private static final byte[][] LEVEL_BUFS;
    static {
        Level[] lvls = Logger.Level.values();
        LEVEL_BUFS = new byte[lvls.length][];
        for (int i = 0; i < lvls.length; ++i) {
            LEVEL_BUFS[i] = String.format(" %s: ", lvls[i]).getBytes();
        }
    }

    private final Buffer _out;
    private final Formatter _fmtr;

    private long _todayStart;
    private long _tommorowStart;

    /**
     * @param out Formatted log messages will be written to here
     */
    public StreamLogWriter(OutputStream out) {
        _out = new Buffer(out);
        _fmtr = new Formatter((Appendable) _out);
        _todayStart = Long.MAX_VALUE;
        _tommorowStart = Long.MIN_VALUE;
    }

    @Override
    public void write(long nanoTime, Logger.Level level, String msg,
            Object[] args, Throwable thrown) {
        assert level != null;
        assert msg != null;

        doHeader(nanoTime, level);

        if (args != null) {
            _fmtr.format(msg, args);
        } else {
            _out.append(msg);
        }

        if (thrown != null) {
            _out.write('\n');

            _out.append(thrown.getClass().getName()).append(':').append(' ');
            _out.append(thrown.getMessage()).append('\n');

            doHeader(nanoTime, Level.FINE);
            thrown.printStackTrace(new PrintStream(_out));
            // Remove trailing \n
            --_out._pos;
        }

        doFooter();
    }

    private void doHeader(long nanoTime, Logger.Level level) {
        writeTimestamp(nanoTime);
        _out.write(LEVEL_BUFS[level.ordinal()]);
    }

    private void doFooter() {
        _out.write('\n');
        _fmtr.flush();
    }

    /**
     * Timestamp format is HH:MM:SS:nnnnnnnnn
     *
     * @param nanoTime nanoseconds since the epoch
     */
    private void writeTimestamp(long nanoTime) {
        if (nanoTime >= _tommorowStart || nanoTime < _todayStart) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(NANOSECONDS.toMillis(nanoTime));
            cal.setLenient(true);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            _todayStart = MILLISECONDS.toNanos(cal.getTimeInMillis());

            cal.add(Calendar.DATE, 1);
            _tommorowStart = MILLISECONDS.toNanos(cal.getTimeInMillis());
        }

        long afterMidnight = nanoTime - _todayStart;
        int nanos = (int) (afterMidnight % 1000000000);
        afterMidnight /= 1000000000;
        int secs = (int) (afterMidnight % 60);
        afterMidnight /= 60;
        int mins = (int) (afterMidnight % 60);
        afterMidnight /= 60;
        int hours = (int) afterMidnight;
        assert hours < 24;

        _out.appendNumber(hours, 2);
        _out.write(':');
        _out.appendNumber(mins, 2);
        _out.write(':');
        _out.appendNumber(secs, 2);
        _out.write(':');
        _out.appendNumber(nanos, 9);
    }

    /**
     * This class servers a couple of purposes. Primarily, it buffers everything
     * until a call to {@link #flush()}. This guarantees that log lines are not
     * broken up in the file if multiple threads/programs are writing
     * concurrently to the same file (which they really shouldn't do anyway).
     * <p>
     * Furthermore, there are some optimizations in here which hopefully make
     * logging fast.
     *
     * @author rstoner
     */
    private static final class Buffer extends OutputStream implements
            Appendable {
        private final OutputStream _out;

        private byte[] _buf = new byte[128];
        private int _pos;

        public Buffer(OutputStream out) {
            _out = notNull(out);
        }

        @Override
        public void write(int b) {
            ensureCapacity(1);
            _buf[_pos++] = (byte) b;
        }

        @Override
        public void write(byte[] b) {
            write(b, 0, b.length);
        }

        @Override
        public void write(byte[] b, int off, int len) {
            ensureCapacity(len);
            System.arraycopy(b, off, _buf, _pos, len);
            _pos += len;
        }

        @Override
        public Buffer append(CharSequence csq) {
            return append(csq, 0, csq.length());
        }

        @SuppressWarnings("deprecation")
        @Override
        public Buffer append(CharSequence csq, int start, int end) {
            ensureCapacity(end - start);

            if (csq instanceof String) {
                String str = (String) csq;
                str.getBytes(start, start + end, _buf, _pos);
                _pos += end;
            } else {
                for (int i = start; i < end; ++i) {
                    assert (csq.charAt(i) & 0xff00) == 0;
                    _buf[_pos++] = (byte) csq.charAt(i);
                }
            }

            return this;
        }

        @Override
        public Buffer append(char c) {
            assert (c & 0xff00) == 0;

            write(c);
            return this;
        }

        /**
         * Append a number as a string.
         *
         * @param val The number
         * @param digits The number of digits to use. The string representation
         *            will be left-padded with "0" if necessary.
         */
        public void appendNumber(int val, int digits) {
            ensureCapacity(digits);

            for (int i = digits - 1; i >= 0; --i) {
                _buf[_pos + i] = (byte) ((val % 10) + '0');
                val /= 10;
            }
            _pos += digits;
        }

        @Override
        public void flush() throws IOException {
            if (_pos > 0) {
                _out.write(_buf, 0, _pos);
                _pos = 0;
            }

            _out.flush();
        }

        @Override
        public void close() throws IOException {
            flush();
            _out.close();
        }

        private void ensureCapacity(int more) {
            if (_pos + more > _buf.length) {
                int newSize = Math.max(_buf.length * 2, _pos + more);
                byte[] newBuf = new byte[newSize];
                System.arraycopy(_buf, 0, newBuf, 0, _pos);
                _buf = newBuf;
            }
        }
    }
}
