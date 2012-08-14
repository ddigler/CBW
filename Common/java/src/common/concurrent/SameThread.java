/**
 *
 */
package common.concurrent;

import static common.Constraints.notNull;

/**
 * A utility class for checking that the current thread is the expected thread.
 *
 * @author rstoner
 */
public final class SameThread {
    private volatile Thread _expected;

    /**
     * Start in an uninitialized state. The first call to
     * {@link #expected(Thread)} or {@link #check()} will set the expected
     * thread.
     */
    public SameThread() {
    }

    /**
     * Set the expected thread. Calls to {@link #expected(Thread)} are not
     * allowed. Calls to {@link #check()} must occur from the
     * <code>expected</code> thread.
     *
     * @param expected The expected thread
     */
    public SameThread(Thread expected) {
        expected(expected);
    }

    /**
     * Set this expected thread. This method may only be called once, and cannot
     * be called if the {@link #SameThread(Thread)} constructor was used.
     *
     * @param expected The expected thread
     */
    public void expected(Thread expected) {
        notNull(expected);

        synchronized (this) {
            if (_expected != null) {
                throw new IllegalStateException(
                        "Expected thread already set to " + expected);
            }
            _expected = expected;
        }
    }

    /**
     * @return The expected thread, or <code>null</code> if it hasn't bee set
     */
    public Thread expected() {
        return _expected;
    }

    /**
     * Check that this call is being made from the expected thread. If the
     * {@link #SameThread(Thread)} constructor was not used, and
     * {@link #expected(Thread)} has not been called, then the first call to
     * this method functions as if calling {@link #expected(Thread)}.
     *
     * @return true if this call is occurring in the expected thread.
     */
    public boolean check() {
        Thread current = Thread.currentThread();
        if (_expected != current) {
            synchronized (this) {
                if (_expected != null) {
                    return false;
                } else {
                    _expected = current;
                }
            }
        }

        return true;
    }
}
