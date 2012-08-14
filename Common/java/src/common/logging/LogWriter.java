package common.logging;

/**
 * An interface for converting log messages to text. Implementers are expected
 * to output the text immediately or provide some way to retrieve the formatted
 * text.
 *
 * @author rstoner
 */
public interface LogWriter {
    /**
     * Format and output a log message.
     *
     * @param nanoTime Message timestamp
     * @param level Message level
     * @param msg Message text. If <code>args</code> is not <code>null</code>,
     *            <code>msg</code> will be used as a format string with
     *            <code>args</code> as the arguments
     * @param args Message arguments. May be null if there are no arguments
     * @param thrown Error object. May be <code>null</code> if there is no
     *            associated error object to be logged
     */
    void write(long nanoTime, Logger.Level level, String msg, Object[] args,
            Throwable thrown);
}
