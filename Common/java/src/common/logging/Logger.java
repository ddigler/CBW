package common.logging;

/**
 * Interface to the logging system.
 * 
 * @author rstoner
 */
public interface Logger {
    /**
     * An enum representing the various logging levels.
     * 
     * @author rstoner
     */
    enum Level {
        /** The type of a detailed message. */
        FINE,
        /** The type of a normal message. */
        INFO,
        /** The type of a warning message. */
        WARN,
        /** The type of an error message. */
        ERR;
    }

    /**
     * Log a detailed message without arguments.
     * 
     * @param msg The message
     */
    void fine(String msg);

    /**
     * Log a detailed message with arguments.
     * 
     * @param msg The message
     * @param args The arguments
     * @see String#format(String, Object...)
     */
    void fine(String msg, Object... args);

    /**
     * Log a normal message without arguments.
     * 
     * @param msg The message
     */
    void info(String msg);

    /**
     * Log a normal message with arguments.
     * 
     * @param msg The message
     * @param args The arguments
     * @see String#format(String, Object...)
     */
    void info(String msg, Object... args);

    /**
     * Log a warning message without arguments.
     * 
     * @param msg The message
     * @param args The arguments
     */
    void warn(String msg);

    /**
     * Log a warning message with arguments.
     * 
     * @param msg The message
     * @param args The arguments
     * @see String#format(String, Object...)
     */
    void warn(String msg, Object... args);

    /**
     * Log an error message without arguments.
     * 
     * @param msg The message
     * @param args The arguments
     */
    void err(String msg);

    /**
     * Log an error message with arguments.
     * 
     * @param msg The message
     * @param args The arguments
     * @see String#format(String, Object...)
     */
    void err(String msg, Object... args);

    /**
     * Log an error message without arguments and a {@link Throwable} which is
     * responsible for the error.
     * 
     * @param thrown The source of the error
     * @param msg The message
     */
    void err(Throwable thrown, String msg);

    /**
     * Log an error message with arguments and a {@link Throwable} which is
     * responsible for the error.
     * 
     * @param thrown The source of the error
     * @param msg The message
     * @param args The arguments
     * @see String#format(String, Object...)
     */
    void err(Throwable thrown, String msg, Object... args);

    /**
     * Log a message without arguments.
     * 
     * @param level The level to log at
     * @param msg The message
     * @param args The arguments
     */
    void log(Level level, String msg);

    /**
     * Log a message with arguments.
     * 
     * @param level The level to log at
     * @param msg The message
     * @param args The arguments
     * @see String#format(String, Object...)
     */
    void log(Level level, String msg, Object... args);
}
