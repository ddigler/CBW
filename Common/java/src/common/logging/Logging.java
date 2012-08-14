/**
 *
 */
package common.logging;

import common.io.MuteOutputStream;

/**
 * A collection of logging utilities.
 *
 * @author rstoner
 */
public final class Logging {
    /** Logger which directs its output to stdout. */
    public static final Logger STDOUT = new SyncLogger(new StreamLogWriter(
            System.out));
    /** Logger which directs its output to stderr. */
    public static final Logger STDERR = new SyncLogger(new StreamLogWriter(
            System.err));
    /**
     * Logger which swallows all output. However, log messages are still handled
     * and formatted to detect any logging issues.
     */
    public static final Logger MUTE = new SyncLogger(new StreamLogWriter(
            MuteOutputStream.INSTANCE));

    private Logging() {
    }
}
