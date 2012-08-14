/**
 *
 */
package common;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * A collection of useful methods.
 *
 * @author rstoner
 */
public final class Common {
    private Common() {
    }

    /**
     * Get the current time with millisecond resolution. This is similar in
     * concept to {@link System#currentTimeMillis()}.
     *
     * @return Milliseconds since the epoch
     */
    public static long milliTime() {
        return NANOSECONDS.toMillis(nanoTime());
    }

    /**
     * Get the current time with nanosecond resolution. This is similar in
     * concept to <code>{@link System#currentTimeMillis()} * 1000 </code>,
     * except if possible nanosecond resolution is used.
     *
     * @return Nanoseconds since the epoch
     */
    public static long nanoTime() {
        // TODO: Native
        return MILLISECONDS.toNanos(System.currentTimeMillis());
    }
}
