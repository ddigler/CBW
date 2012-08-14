/**
 *
 */
package common.logging;

import static common.Constraints.notEmpty;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import common.logging.Logger.Level;

/**
 * A {@link LogWriter} implementation which writes log messages files. A new
 * file is generated whenever the log message timestamp passes midnight. The
 * files are named using the provided template, but are passed to
 * {@link String#format(String, Object...)} to give the filename the option of
 * containing the date.
 * <p>
 * Additionally, 2 files can optionally be created. One will contain all log
 * messages, and the other will only contain messages logged at
 * {@link Logger.Level#INFO} or above. In this case, the "info" file will use
 * the provided naming template, while the full log will be named with ".all"
 * before its filename suffix.
 *
 * @author rstoner
 */
public final class FileLogWriter implements LogWriter {
    private final String _nameTemplate;
    private final boolean _filter;

    private long _todayStart, _tommorowStart;
    private OutputStream _out;
    private StreamLogWriter _writer;
    private boolean _doFilter;

    /**
     * @param nameTemplate Template used to generate the file name. This
     *            template will be passed to
     *            {@link String#format(String, Object...)} with the log date to
     *            generate a new file whenever the log time passes midnight.
     * @param filter <code>true</code> to generate 2 seperate logs - one with
     *            all messages and one only with {@link Logger.Level#INFO} and
     *            above, the other with all messages. If <code>false</code>, as
     *            single file is created with all messages
     */
    public FileLogWriter(String nameTemplate, boolean filter) {
        _nameTemplate = notEmpty(nameTemplate);
        _filter = filter;

        _todayStart = Long.MAX_VALUE;
        _tommorowStart = Long.MIN_VALUE;
    }

    @Override
    public void write(long nanoTime, Level level, String msg, Object[] args,
            Throwable thrown) {
        _doFilter = level == Level.FINE;

        checkTime(nanoTime);

        _writer.write(nanoTime, level, msg, args, thrown);
    }

    private void checkTime(long nanoTime) {
        if (nanoTime >= _tommorowStart || nanoTime < _todayStart) {
            updateDate(nanoTime);
            try {
                updateWriter(nanoTime);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void updateWriter(long nanoTime) throws IOException,
            FileNotFoundException {
        if (_out != null) {
            _out.close();
        }

        if (_filter) {
            String unfilteredTmpl;
            int extIdx = _nameTemplate.lastIndexOf('.');
            if (extIdx >= 0) {
                unfilteredTmpl = _nameTemplate.substring(0, extIdx);
                unfilteredTmpl += ".all";
                unfilteredTmpl += _nameTemplate.substring(extIdx);
            } else {
                unfilteredTmpl = _nameTemplate + ".all";
            }

            String filtered = String.format(_nameTemplate,
                    NANOSECONDS.toMillis(nanoTime));
            String unfiltered = String.format(unfilteredTmpl,
                    NANOSECONDS.toMillis(nanoTime));
            _out = new FilterStream(filtered, unfiltered);
        } else {
            String allName = String.format(_nameTemplate,
                    NANOSECONDS.toMillis(nanoTime));
            _out = new FileOutputStream(allName, true);
        }

        _writer = new StreamLogWriter(_out);
    }

    private void updateDate(long nanoTime) {
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

    private final class FilterStream extends OutputStream {
        private final OutputStream _filtered, _all;

        public FilterStream(String filteredFile, String unfilteredFile)
                throws IOException {
            _filtered = new FileOutputStream(filteredFile, true);
            _all = new FileOutputStream(unfilteredFile, true);
        }

        @Override
        public void write(int b) throws IOException {
            _all.write(b);
            if (!_doFilter) {
                _filtered.write(b);
            }
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            _all.write(b, off, len);
            if (!_doFilter) {
                _filtered.write(b, off, len);
            }
        }

        @Override
        public void flush() throws IOException {
            _all.flush();
            _filtered.flush();
        }

        @Override
        public void close() throws IOException {
            _all.close();
            _filtered.close();
        }
    }
}
