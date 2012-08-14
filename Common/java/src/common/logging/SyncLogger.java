/**
 *
 */
package common.logging;

import static common.Constraints.notNull;

import common.Common;

/**
 * A synchronous implementation of a {@link Logger}. Messages are written to the
 * log in the same thread performing the logging.
 * <p>
 * Because any thread may write to the log, logging is internally synchronized
 * in this class. Therefore, threads may be blocked while logging. If this is
 * unacceptable, you must use an asynchronous logger.
 *
 * @author rstoner
 */
public final class SyncLogger extends AbstractLogger {
    private final LogWriter _writer;

    /**
     * @param writer The destination for log messages, which performs the actual
     *            writing.
     */
    public SyncLogger(LogWriter writer) {
        _writer = notNull(writer);
    }

    @Override
    protected void doLog(Level level, Throwable thrown, String msg,
            Object[] args) {
        long time = Common.nanoTime();
        synchronized (_writer) {
            _writer.write(time, level, msg, args, thrown);
        }
    }
}
