/**
 *
 */
package common;

/**
 * Extension of {@link RuntimeException} which makes it clear the exception is
 * being generated as the result of a failed constraint.
 *
 * @author rstoner
 */
public final class ConstraintException extends RuntimeException {
    private static final long serialVersionUID = -8105325252851473191L;

    /**
     * Constructs a new runtime exception with {@code null} as its detail
     * message. The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public ConstraintException() {
        super();
    }

    /**
     * Constructs a new runtime exception with the specified detail message. The
     * cause is not initialized, and may subsequently be initialized by a call
     * to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for later
     *            retrieval by the {@link #getMessage()} method.
     */
    public ConstraintException(String message) {
        super(message);
    }
}
