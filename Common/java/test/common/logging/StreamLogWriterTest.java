package common.logging;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.Assert.assertEquals;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import common.logging.Logger.Level;

/**
 * Tests for {@link StreamLogWriter}.
 *
 * @author rstoner
 */
public class StreamLogWriterTest {

    private Collector _result;
    private StreamLogWriter _writer;

    /***/
    @Before
    public void before() {
        _result = new Collector();
        _writer = new StreamLogWriter(_result);
    }

    /***/
    @Test
    public void fineSimple() {
        testSimple(Level.FINE);
    }

    /***/
    @Test
    public void fineFormatted() {
        testFormat(Level.FINE);
    }

    /***/
    @Test
    public void infoSimple() {
        testSimple(Level.INFO);
    }

    /***/
    @Test
    public void infoFormatted() {
        testFormat(Level.INFO);
    }

    /***/
    @Test
    public void warnSimple() {
        testSimple(Level.WARN);
    }

    /***/
    @Test
    public void warnFormatted() {
        testFormat(Level.WARN);
    }

    /***/
    @Test
    public void erSimple() {
        testSimple(Level.ERR);
    }

    /***/
    @Test
    public void errFormatted() {
        testFormat(Level.ERR);
    }

    /***/
    @Test
    public void errSimpleThrown() {
        RuntimeException thrown = new RuntimeException("I'm an error");
        String time = "01:02:03:456789012";
        String msg = "Testing";
        String expected = expected(time, Level.ERR, msg, null, thrown);

        _writer.write(toNanos(time), Level.ERR, msg, null, thrown);
        assertEquals(expected, _result.toString());
    }

    private void testSimple(Level lvl) {
        String time = "01:02:03:456789012";
        String msg = "Testing";
        String expected = expected(time, lvl, msg, null, null);

        _writer.write(toNanos(time), lvl, msg, null, null);
        assertEquals(expected, _result.toString());
    }

    private void testFormat(Level lvl) {
        String time = "01:02:03:456789012";
        String msg = "Testing %s %d %s";
        Object[] args = { "with", 1, "arg" };
        String expected = expected(time, lvl, msg, args, null);

        _writer.write(toNanos(time), lvl, msg, args, null);
        assertEquals(expected, _result.toString());
    }

    private static String expected(String time, Level level, String msg,
            Object[] args, Throwable t) {
        StringBuilder buf = new StringBuilder(128);
        buf.append(time).append(' ');
        switch (level) {
        case FINE:
            buf.append("FINE");
            break;
        case INFO:
            buf.append("INFO");
            break;
        case WARN:
            buf.append("WARN");
            break;
        case ERR:
            buf.append("ERR");
            break;
        default:
            throw new IllegalArgumentException(level.toString());
        }
        buf.append(": ");

        buf.append(String.format(msg, args)).append('\n');

        if (t != null) {
            buf.append(t.getClass().getName()).append(": ");
            buf.append(t.getMessage()).append('\n');

            buf.append(time).append(" FINE: ");
            StringWriter stackTrace = new StringWriter();
            t.printStackTrace(new PrintWriter(stackTrace));
            buf.append(stackTrace.toString());
        }

        return buf.toString();
    }

    private static long toNanos(String time) {
        String[] parts = time.split(":");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(parts[1]));
        cal.set(Calendar.SECOND, Integer.parseInt(parts[2]));
        cal.set(Calendar.MILLISECOND, 0);
        int nanos = Integer.parseInt(parts[3]);
        return MILLISECONDS.toNanos(cal.getTimeInMillis()) + nanos;
    }

    private static final class Collector extends OutputStream {
        private final StringBuilder _buf = new StringBuilder();

        @Override
        public void write(int b) {
            _buf.append((char) b);
        }

        @Override
        public String toString() {
            return _buf.toString();
        }
    }
}
