package common.logging;

/**
 * A base class for {@link Logger} implementations. The primary purpose of this
 * class to to take all the methods of the {@link Logger} interface and union
 * them into one common method (
 * {@link #doLog(common.logging.Logger.Level, Throwable, String, Object[])})
 * which subclasses must implement.
 * <p>
 * Additionally, some argument validation is performed if assertions are
 * enabled.
 *
 * @author rstoner
 */
public abstract class AbstractLogger implements Logger {
    @Override
    public void fine(String msg) {
        assert msg != null;

        log(Level.FINE, msg);
    }

    @Override
    public void fine(String msg, Object... args) {
        assert msg != null;
        assert validFormat(msg, args);

        log(Level.FINE, msg, args);
    }

    @Override
    public void info(String msg) {
        assert msg != null;

        log(Level.INFO, msg);
    }

    @Override
    public void info(String msg, Object... args) {
        assert msg != null;
        assert validFormat(msg, args);

        log(Level.INFO, msg, args);
    }

    @Override
    public void warn(String msg) {
        assert msg != null;

        log(Level.WARN, msg);
    }

    @Override
    public void warn(String msg, Object... args) {
        assert msg != null;
        assert validFormat(msg, args);

        log(Level.WARN, msg, args);
    }

    @Override
    public void err(String msg) {
        assert msg != null;

        log(Level.ERR, msg);
    }

    @Override
    public void err(String msg, Object... args) {
        assert msg != null;
        assert validFormat(msg, args);

        log(Level.ERR, msg, args);
    }

    @Override
    public void err(Throwable thrown, String msg) {
        assert thrown != null;
        assert msg != null;

        doLog(Level.ERR, thrown, msg, null);
    }

    @Override
    public void err(Throwable thrown, String msg, Object... args) {
        assert thrown != null;
        assert msg != null;
        assert validFormat(msg, args);

        doLog(Level.ERR, thrown, msg, args);
    }

    @Override
    public void log(Level level, String msg) {
        assert level != null;
        assert msg != null;

        doLog(level, null, msg, null);
    }

    @Override
    public void log(Level level, String msg, Object... args) {
        assert level != null;
        assert msg != null;
        assert validFormat(msg, args);

        doLog(level, null, msg, args);
    }

    /**
     * Subclasses must do the actual log request handling in this method. This
     * method is basically just a union of all the methods of {@link Logger} so
     * implementations only have to implement a single method.
     *
     * @param level The level to log at. Cannot be <code>null</code>
     * @param thrown The source of an error. May be <code>null</code>
     * @param msg The message
     * @param args The arguments to the message
     */
    protected abstract void doLog(Level level, Throwable thrown, String msg,
            Object[] args);

    private static boolean validFormat(String msg, Object[] args) {
        assert args != null;
        String.format(msg, args);
        return true;
    }
}
