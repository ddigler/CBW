/**
 *
 */
package common;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Map;

/**
 * A collection of methods useful for validating that arguments fulfill required
 * specifications.
 *
 * @author rstoner
 */
public final class Constraints {
    private Constraints() {
    }

    /**
     * Validate that <code>val</code> is <code>true</code>.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static boolean tru(boolean val) {
        if (!val) {
            throw new ConstraintException("Value must be true");
        }
        return val;
    }

    /**
     * Call {@link #tru(boolean)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static boolean asserttru(boolean val) {
        assert tru(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is <code>false</code>.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static boolean fals(boolean val) {
        if (val) {
            throw new ConstraintException("Value must be false");
        }
        return val;
    }

    /**
     * Call {@link #fals(boolean)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static boolean assertfals(boolean val) {
        assert fals(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is null.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T> T isNull(T val) {
        if (val != null) {
            throw new ConstraintException("Value must be null");
        }
        return val;
    }

    /**
     * Call {@link #isNull(Object)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T> T assertisNull(T val) {
        assert isNull(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is not null.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T> T notNull(T val) {
        if (val == null) {
            throw new ConstraintException("Value must not be null");
        }
        return val;
    }

    /**
     * Call {@link #notNull(Object)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T> T assertnotNull(T val) {
        assert notNull(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> equals <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte eq(int expect, byte val) {
        if (expect != val) {
            throw new ConstraintException(String.format("%d must equal %d",
                    val, expect));
        }
        return val;
    }

    /**
     * Call {@link #eq(int, byte)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte asserteq(int expect, byte val) {
        assert eq(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> equals <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte eq0(byte val) {
        return eq(0, val);
    }

    /**
     * Call {@link #eq0(byte)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte asserteq0(byte val) {
        assert eq0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> does not equal <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte neq(int expect, byte val) {
        if (expect == val) {
            throw new ConstraintException(String.format("%d must not equal %d",
                    val, expect));
        }
        return val;
    }

    /**
     * Call {@link #neq(int, byte)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte assertneq(int expect, byte val) {
        assert neq(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> does not equal <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte neq0(byte val) {
        return neq(0, val);
    }

    /**
     * Call {@link #neq0(byte)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte assertneq0(byte val) {
        assert neq0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte lt(int expect, byte val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be less than %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #lt(int, byte)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte assertlt(int expect, byte val) {
        assert lt(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte lt0(byte val) {
        return lt(0, val);
    }

    /**
     * Call {@link #lt0(byte)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte assertlt0(byte val) {
        assert lt0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than or equal to
     * <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte lte(int expect, byte val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be less than or equal to %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #lte(int, byte)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte assertlte(int expect, byte val) {
        assert lte(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than or equal to <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte lte0(byte val) {
        return lte(0, val);
    }

    /**
     * Call {@link #lte0(byte)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte assertlte0(byte val) {
        assert lte0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte gt(int expect, byte val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be greater than %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #gt(int, byte)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte assertgt(int expect, byte val) {
        assert gt(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte gt0(byte val) {
        return gt(0, val);
    }

    /**
     * Call {@link #gt0(byte)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte assertgt0(byte val) {
        assert gt0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than or equal to
     * <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte gte(int expect, byte val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be greater than or equal to %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #gte(int, byte)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte assertgte(int expect, byte val) {
        assert gte(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than or equal to <code>0</code>
     * .
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte gte0(byte val) {
        return gte(0, val);
    }

    /**
     * Call {@link #gte0(byte)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte assertgte0(byte val) {
        assert gte0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is in the range (<code>low</code>,
     * <code>high</code>).
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte between(int low, int high, byte val) {
        if (low >= val || high <= val) {
            throw new ConstraintException(String.format(
                    "%d must be in range (%d, %d)", val, low, high));
        }
        return val;
    }

    /**
     * Call {@link #between(int, int, byte)} if assertions are enabled.
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte assertbetween(int low, int high, byte val) {
        assert between(low, high, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is in the range [<code>low</code>,
     * <code>high</code>].
     *
     * @param low Low limit (inclusive)
     * @param high High limit (inclusive)
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte in(int low, int high, byte val) {
        if (low > val || high < val) {
            throw new ConstraintException(String.format(
                    "%d must be in range [%d, %d]", val, low, high));
        }
        return val;
    }

    /**
     * Call {@link #in(int, int, byte)} if assertions are enabled.
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static byte assertin(int low, int high, byte val) {
        assert in(low, high, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> equals <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short eq(int expect, short val) {
        if (expect != val) {
            throw new ConstraintException(String.format("%d must equal %d",
                    val, expect));
        }
        return val;
    }

    /**
     * Call {@link #eq(int, short)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short asserteq(int expect, short val) {
        assert eq(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> equals <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short eq0(short val) {
        return eq(0, val);
    }

    /**
     * Call {@link #eq0(short)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short asserteq0(short val) {
        assert eq0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> does not equal <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short neq(int expect, short val) {
        if (expect == val) {
            throw new ConstraintException(String.format("%d must not equal %d",
                    val, expect));
        }
        return val;
    }

    /**
     * Call {@link #neq(int, short)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short assertneq(int expect, short val) {
        assert neq(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> does not equal <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short neq0(short val) {
        return neq(0, val);
    }

    /**
     * Call {@link #neq0(short)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short assertneq0(short val) {
        assert neq0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short lt(int expect, short val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be less than %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #lt(int, short)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short assertlt(int expect, short val) {
        assert lt(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short lt0(short val) {
        return lt(0, val);
    }

    /**
     * Call {@link #lt0(short)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short assertlt0(short val) {
        assert lt0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than or equal to
     * <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short lte(int expect, short val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be less than or equal to %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #lte(int, short)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short assertlte(int expect, short val) {
        assert lte(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than or equal to <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short lte0(short val) {
        return lte(0, val);
    }

    /**
     * Call {@link #lte0(short)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short assertlte0(short val) {
        assert lte0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short gt(int expect, short val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be greater than %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #gt(int, short)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short assertgt(int expect, short val) {
        assert gt(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short gt0(short val) {
        return gt(0, val);
    }

    /**
     * Call {@link #gt0(short)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short assertgt0(short val) {
        assert gt0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than or equal to
     * <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short gte(int expect, short val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be greater than or equal to %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #gte(int, short)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short assertgte(int expect, short val) {
        assert gte(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than or equal to <code>0</code>
     * .
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short gte0(short val) {
        return gte(0, val);
    }

    /**
     * Call {@link #gte0(short)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short assertgte0(short val) {
        assert gte0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is in the range (<code>low</code>,
     * <code>high</code>).
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short between(int low, int high, short val) {
        if (low >= val || high <= val) {
            throw new ConstraintException(String.format(
                    "%d must be in range (%d, %d)", val, low, high));
        }
        return val;
    }

    /**
     * Call {@link #between(int, int, short)} if assertions are enabled.
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short assertbetween(int low, int high, short val) {
        assert between(low, high, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is in the range [<code>low</code>,
     * <code>high</code>].
     *
     * @param low Low limit (inclusive)
     * @param high High limit (inclusive)
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short in(int low, int high, short val) {
        if (low > val || high < val) {
            throw new ConstraintException(String.format(
                    "%d must be in range [%d, %d]", val, low, high));
        }
        return val;
    }

    /**
     * Call {@link #in(int, int, short)} if assertions are enabled.
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static short assertin(int low, int high, short val) {
        assert in(low, high, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> equals <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char eq(int expect, char val) {
        if (expect != val) {
            throw new ConstraintException(String.format("%d must equal %d",
                    val, expect));
        }
        return val;
    }

    /**
     * Call {@link #eq(int, char)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char asserteq(int expect, char val) {
        assert eq(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> equals <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char eq0(char val) {
        return eq(0, val);
    }

    /**
     * Call {@link #eq0(char)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char asserteq0(char val) {
        assert eq0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> does not equal <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char neq(int expect, char val) {
        if (expect == val) {
            throw new ConstraintException(String.format("%d must not equal %d",
                    val, expect));
        }
        return val;
    }

    /**
     * Call {@link #neq(int, char)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char assertneq(int expect, char val) {
        assert neq(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> does not equal <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char neq0(char val) {
        return neq(0, val);
    }

    /**
     * Call {@link #neq0(char)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char assertneq0(char val) {
        assert neq0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char lt(int expect, char val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be less than %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #lt(int, char)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char assertlt(int expect, char val) {
        assert lt(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char lt0(char val) {
        return lt(0, val);
    }

    /**
     * Call {@link #lt0(char)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char assertlt0(char val) {
        assert lt0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than or equal to
     * <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char lte(int expect, char val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be less than or equal to %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #lte(int, char)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char assertlte(int expect, char val) {
        assert lte(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than or equal to <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char lte0(char val) {
        return lte(0, val);
    }

    /**
     * Call {@link #lte0(char)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char assertlte0(char val) {
        assert lte0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char gt(int expect, char val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be greater than %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #gt(int, char)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char assertgt(int expect, char val) {
        assert gt(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char gt0(char val) {
        return gt(0, val);
    }

    /**
     * Call {@link #gt0(char)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char assertgt0(char val) {
        assert gt0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than or equal to
     * <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char gte(int expect, char val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be greater than or equal to %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #gte(int, char)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char assertgte(int expect, char val) {
        assert gte(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than or equal to <code>0</code>
     * .
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char gte0(char val) {
        return gte(0, val);
    }

    /**
     * Call {@link #gte0(char)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char assertgte0(char val) {
        assert gte0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is in the range (<code>low</code>,
     * <code>high</code>).
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char between(int low, int high, char val) {
        if (low >= val || high <= val) {
            throw new ConstraintException(String.format(
                    "%d must be in range (%d, %d)", val, low, high));
        }
        return val;
    }

    /**
     * Call {@link #between(int, int, char)} if assertions are enabled.
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char assertbetween(int low, int high, char val) {
        assert between(low, high, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is in the range [<code>low</code>,
     * <code>high</code>].
     *
     * @param low Low limit (inclusive)
     * @param high High limit (inclusive)
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char in(int low, int high, char val) {
        if (low > val || high < val) {
            throw new ConstraintException(String.format(
                    "%d must be in range [%d, %d]", val, low, high));
        }
        return val;
    }

    /**
     * Call {@link #in(int, int, char)} if assertions are enabled.
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static char assertin(int low, int high, char val) {
        assert in(low, high, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> equals <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int eq(int expect, int val) {
        if (expect != val) {
            throw new ConstraintException(String.format("%d must equal %d",
                    val, expect));
        }
        return val;
    }

    /**
     * Call {@link #eq(int, int)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int asserteq(int expect, int val) {
        assert eq(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> equals <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int eq0(int val) {
        return eq(0, val);
    }

    /**
     * Call {@link #eq0(int)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int asserteq0(int val) {
        assert eq0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> does not equal <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int neq(int expect, int val) {
        if (expect == val) {
            throw new ConstraintException(String.format("%d must not equal %d",
                    val, expect));
        }
        return val;
    }

    /**
     * Call {@link #neq(int, int)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int assertneq(int expect, int val) {
        assert neq(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> does not equal <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int neq0(int val) {
        return neq(0, val);
    }

    /**
     * Call {@link #neq0(int)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int assertneq0(int val) {
        assert neq0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int lt(int expect, int val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be less than %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #lt(int, int)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int assertlt(int expect, int val) {
        assert lt(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int lt0(int val) {
        return lt(0, val);
    }

    /**
     * Call {@link #lt0(int)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int assertlt0(int val) {
        assert lt0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than or equal to
     * <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int lte(int expect, int val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be less than or equal to %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #lte(int, int)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int assertlte(int expect, int val) {
        assert lte(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than or equal to <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int lte0(int val) {
        return lte(0, val);
    }

    /**
     * Call {@link #lte0(int)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int assertlte0(int val) {
        assert lte0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int gt(int expect, int val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be greater than %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #gt(int, int)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int assertgt(int expect, int val) {
        assert gt(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int gt0(int val) {
        return gt(0, val);
    }

    /**
     * Call {@link #gt0(int)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int assertgt0(int val) {
        assert gt0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than or equal to
     * <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int gte(int expect, int val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be greater than or equal to %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #gte(int, int)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int assertgte(int expect, int val) {
        assert gte(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than or equal to <code>0</code>
     * .
     *
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int gte0(int val) {
        return gte(0, val);
    }

    /**
     * Call {@link #gte0(int)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int assertgte0(int val) {
        assert gte0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is in the range (<code>low</code>,
     * <code>high</code>).
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int between(int low, int high, int val) {
        if (low >= val || high <= val) {
            throw new ConstraintException(String.format(
                    "%d must be in range (%d, %d)", val, low, high));
        }
        return val;
    }

    /**
     * Call {@link #between(int, int, int)} if assertions are enabled.
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int assertbetween(int low, int high, int val) {
        assert between(low, high, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is in the range [<code>low</code>,
     * <code>high</code>].
     *
     * @param low Low limit (inclusive)
     * @param high High limit (inclusive)
     * @param val Actual value
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int in(int low, int high, int val) {
        if (low > val || high < val) {
            throw new ConstraintException(String.format(
                    "%d must be in range [%d, %d]", val, low, high));
        }
        return val;
    }

    /**
     * Call {@link #in(int, int, int)} if assertions are enabled.
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static int assertin(int low, int high, int val) {
        assert in(low, high, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> equals <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long eq(long expect, long val) {
        if (expect != val) {
            throw new ConstraintException(String.format("%d must equal %d",
                    val, expect));
        }
        return val;
    }

    /**
     * Call {@link #eq(long, long)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long asserteq(long expect, long val) {
        assert eq(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> equals <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long eq0(long val) {
        return eq(0, val);
    }

    /**
     * Call {@link #eq0(long)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long asserteq0(long val) {
        assert eq0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> does not equal <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long neq(long expect, long val) {
        if (expect == val) {
            throw new ConstraintException(String.format("%d must not equal %d",
                    val, expect));
        }
        return val;
    }

    /**
     * Call {@link #neq(long, long)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long assertneq(long expect, long val) {
        assert neq(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> does not equal <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long neq0(long val) {
        return neq(0, val);
    }

    /**
     * Call {@link #neq0(long)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long assertneq0(long val) {
        assert neq0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long lt(long expect, long val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be less than %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #lt(long, long)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long assertlt(long expect, long val) {
        assert lt(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long lt0(long val) {
        return lt(0, val);
    }

    /**
     * Call {@link #lt0(long)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long assertlt0(long val) {
        assert lt0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than or equal to
     * <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long lte(long expect, long val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be less than or equal to %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #lte(long, long)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long assertlte(long expect, long val) {
        assert lte(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than or equal to <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long lte0(long val) {
        return lte(0, val);
    }

    /**
     * Call {@link #lte0(long)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long assertlte0(long val) {
        assert lte0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long gt(long expect, long val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be greater than %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #gt(long, long)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long assertgt(long expect, long val) {
        assert gt(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long gt0(long val) {
        return gt(0, val);
    }

    /**
     * Call {@link #gt0(long)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long assertgt0(long val) {
        assert gt0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than or equal to
     * <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long gte(long expect, long val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be greater than or equal to %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #gte(long, long)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long assertgte(long expect, long val) {
        assert gte(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than or equal to <code>0</code>
     * .
     *
     * @param val Actual value
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long gte0(long val) {
        return gte(0, val);
    }

    /**
     * Call {@link #gte0(long)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long assertgte0(long val) {
        assert gte0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is in the range (<code>low</code>,
     * <code>high</code>).
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Actual value
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long between(long low, long high, long val) {
        if (low >= val || high <= val) {
            throw new ConstraintException(String.format(
                    "%d must be in range (%d, %d)", val, low, high));
        }
        return val;
    }

    /**
     * Call {@link #between(long, long, long)} if assertions are enabled.
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Value to validate
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long assertbetween(long low, long high, long val) {
        assert between(low, high, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is in the range [<code>low</code>,
     * <code>high</code>].
     *
     * @param low Low limit (inclusive)
     * @param high High limit (inclusive)
     * @param val Actual value
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long in(long low, long high, long val) {
        if (low > val || high < val) {
            throw new ConstraintException(String.format(
                    "%d must be in range [%d, %d]", val, low, high));
        }
        return val;
    }

    /**
     * Call {@link #in(long, long, long)} if assertions are enabled.
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Value to validate
     * @return <code>val</code> if the constralong is met
     * @throws ConstraintException if the constralong is not met
     */
    public static long assertin(long low, long high, long val) {
        assert in(low, high, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> equals <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float eq(float expect, float val) {
        if (expect != val) {
            throw new ConstraintException(String.format("%d must equal %d",
                    val, expect));
        }
        return val;
    }

    /**
     * Call {@link #eq(float, float)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float asserteq(float expect, float val) {
        assert eq(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> equals <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float eq0(float val) {
        return eq(0, val);
    }

    /**
     * Call {@link #eq0(float)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float asserteq0(float val) {
        assert eq0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> does not equal <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float neq(float expect, float val) {
        if (expect == val) {
            throw new ConstraintException(String.format("%d must not equal %d",
                    val, expect));
        }
        return val;
    }

    /**
     * Call {@link #neq(float, float)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float assertneq(float expect, float val) {
        assert neq(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> does not equal <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float neq0(float val) {
        return neq(0, val);
    }

    /**
     * Call {@link #neq0(float)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float assertneq0(float val) {
        assert neq0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float lt(float expect, float val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be less than %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #lt(float, float)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float assertlt(float expect, float val) {
        assert lt(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float lt0(float val) {
        return lt(0, val);
    }

    /**
     * Call {@link #lt0(float)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float assertlt0(float val) {
        assert lt0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than or equal to
     * <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float lte(float expect, float val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be less than or equal to %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #lte(float, float)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float assertlte(float expect, float val) {
        assert lte(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than or equal to <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float lte0(float val) {
        return lte(0, val);
    }

    /**
     * Call {@link #lte0(float)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float assertlte0(float val) {
        assert lte0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float gt(float expect, float val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be greater than %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #gt(float, float)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float assertgt(float expect, float val) {
        assert gt(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float gt0(float val) {
        return gt(0, val);
    }

    /**
     * Call {@link #gt0(float)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float assertgt0(float val) {
        assert gt0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than or equal to
     * <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float gte(float expect, float val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be greater than or equal to %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #gte(float, float)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float assertgte(float expect, float val) {
        assert gte(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than or equal to <code>0</code>
     * .
     *
     * @param val Actual value
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float gte0(float val) {
        return gte(0, val);
    }

    /**
     * Call {@link #gte0(float)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float assertgte0(float val) {
        assert gte0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is in the range (<code>low</code>,
     * <code>high</code>).
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Actual value
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float between(float low, float high, float val) {
        if (low >= val || high <= val) {
            throw new ConstraintException(String.format(
                    "%d must be in range (%d, %d)", val, low, high));
        }
        return val;
    }

    /**
     * Call {@link #between(float, float, float)} if assertions are enabled.
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Value to validate
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float assertbetween(float low, float high, float val) {
        assert between(low, high, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is in the range [<code>low</code>,
     * <code>high</code>].
     *
     * @param low Low limit (inclusive)
     * @param high High limit (inclusive)
     * @param val Actual value
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float in(float low, float high, float val) {
        if (low > val || high < val) {
            throw new ConstraintException(String.format(
                    "%d must be in range [%d, %d]", val, low, high));
        }
        return val;
    }

    /**
     * Call {@link #in(float, float, float)} if assertions are enabled.
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Value to validate
     * @return <code>val</code> if the constrafloat is met
     * @throws ConstraintException if the constrafloat is not met
     */
    public static float assertin(float low, float high, float val) {
        assert in(low, high, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> equals <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double eq(double expect, double val) {
        if (expect != val) {
            throw new ConstraintException(String.format("%d must equal %d",
                    val, expect));
        }
        return val;
    }

    /**
     * Call {@link #eq(double, double)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double asserteq(double expect, double val) {
        assert eq(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> equals <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double eq0(double val) {
        return eq(0, val);
    }

    /**
     * Call {@link #eq0(double)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double asserteq0(double val) {
        assert eq0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> does not equal <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double neq(double expect, double val) {
        if (expect == val) {
            throw new ConstraintException(String.format("%d must not equal %d",
                    val, expect));
        }
        return val;
    }

    /**
     * Call {@link #neq(double, double)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double assertneq(double expect, double val) {
        assert neq(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> does not equal <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double neq0(double val) {
        return neq(0, val);
    }

    /**
     * Call {@link #neq0(double)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double assertneq0(double val) {
        assert neq0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double lt(double expect, double val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be less than %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #lt(double, double)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double assertlt(double expect, double val) {
        assert lt(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double lt0(double val) {
        return lt(0, val);
    }

    /**
     * Call {@link #lt0(double)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double assertlt0(double val) {
        assert lt0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than or equal to
     * <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double lte(double expect, double val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be less than or equal to %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #lte(double, double)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double assertlte(double expect, double val) {
        assert lte(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is less than or equal to <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double lte0(double val) {
        return lte(0, val);
    }

    /**
     * Call {@link #lte0(double)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double assertlte0(double val) {
        assert lte0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double gt(double expect, double val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be greater than %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #gt(double, double)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double assertgt(double expect, double val) {
        assert gt(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than <code>0</code>.
     *
     * @param val Actual value
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double gt0(double val) {
        return gt(0, val);
    }

    /**
     * Call {@link #gt0(double)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double assertgt0(double val) {
        assert gt0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than or equal to
     * <code>expect</code>.
     *
     * @param expect Expected value
     * @param val Actual value
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double gte(double expect, double val) {
        if (expect > val) {
            throw new ConstraintException(String.format(
                    "%d must be greater than or equal to %d", val, expect));
        }
        return val;
    }

    /**
     * Call {@link #gte(double, double)} if assertions are enabled.
     *
     * @param expect Expected value
     * @param val Value to validate
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double assertgte(double expect, double val) {
        assert gte(expect, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is greater than or equal to <code>0</code>
     * .
     *
     * @param val Actual value
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double gte0(double val) {
        return gte(0, val);
    }

    /**
     * Call {@link #gte0(double)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double assertgte0(double val) {
        assert gte0(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is in the range (<code>low</code>,
     * <code>high</code>).
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Actual value
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double between(double low, double high, double val) {
        if (low >= val || high <= val) {
            throw new ConstraintException(String.format(
                    "%d must be in range (%d, %d)", val, low, high));
        }
        return val;
    }

    /**
     * Call {@link #between(double, double, double)} if assertions are enabled.
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Value to validate
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double assertbetween(double low, double high, double val) {
        assert between(low, high, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is in the range [<code>low</code>,
     * <code>high</code>].
     *
     * @param low Low limit (inclusive)
     * @param high High limit (inclusive)
     * @param val Actual value
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double in(double low, double high, double val) {
        if (low > val || high < val) {
            throw new ConstraintException(String.format(
                    "%d must be in range [%d, %d]", val, low, high));
        }
        return val;
    }

    /**
     * Call {@link #in(double, double, double)} if assertions are enabled.
     *
     * @param low Low limit (exclusive)
     * @param high High limit (exclusive)
     * @param val Value to validate
     * @return <code>val</code> if the constradouble is met
     * @throws ConstraintException if the constradouble is not met
     */
    public static double assertin(double low, double high, double val) {
        assert in(low, high, val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is not null but is empty.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends CharSequence> T isEmpty(T val) {
        notNull(val);
        if (val.length() == 0) {
            throw new ConstraintException("Value must be empty");
        }
        return val;
    }

    /**
     * Call {@link #isEmpty(CharSequence)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends CharSequence> T assertisEmpty(T val) {
        assert isEmpty(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is not null but is empty.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends Iterable<?>> T isEmpty(T val) {
        notNull(val);
        if (val.iterator().hasNext()) {
            throw new ConstraintException("Value must be empty");
        }
        return val;
    }

    /**
     * Call {@link #isEmpty(Iterable)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends Iterable<?>> T assertisEmpty(T val) {
        assert isEmpty(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is not null but is empty.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends Map<?, ?>> T isEmpty(T val) {
        notNull(val);
        if (!val.isEmpty()) {
            throw new ConstraintException("Value must be empty");
        }
        return val;
    }

    /**
     * Call {@link #isEmpty(Map)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends Map<?, ?>> T assertisEmpty(T val) {
        assert isEmpty(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is not null or empty.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends CharSequence> T notEmpty(T val) {
        notNull(val);
        if (val.length() == 0) {
            throw new ConstraintException("Value must not be empty");
        }
        return val;
    }

    /**
     * Call {@link #notEmpty(CharSequence)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends CharSequence> T assertnotEmpty(T val) {
        assert notEmpty(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is not null or empty.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends Iterable<?>> T notEmpty(T val) {
        notNull(val);
        if (!val.iterator().hasNext()) {
            throw new ConstraintException("Value must not be empty");
        }
        return val;
    }

    /**
     * Call {@link #notEmpty(Iterable)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends Iterable<?>> T assertnotEmpty(T val) {
        assert notEmpty(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is not null or empty.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends Map<?, ?>> T notEmpty(T val) {
        notNull(val);
        if (val.isEmpty()) {
            throw new ConstraintException("Value must not be empty");
        }
        return val;
    }

    /**
     * Call {@link #notEmpty(Map)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends Map<?, ?>> T assertnotEmpty(T val) {
        assert notEmpty(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is not null and contains no nulls.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends Iterable<?>> T noNulls(T val) {
        notNull(val);
        for (Object o : val) {
            if (o == null) {
                throw new ConstraintException("No nulls allowed");
            }
        }
        return val;
    }

    /**
     * Call {@link #noNulls(Iterable)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends Iterable<?>> T assertnoNulls(T val) {
        assert noNulls(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is not null and contains no null
     * {@link Map#values()}.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends Map<?, ?>> T noNulls(T val) {
        notNull(val);
        noNulls(val.values());
        return val;
    }

    /**
     * Call {@link #noNulls(Map)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends Map<?, ?>> T assertnoNulls(T val) {
        assert noNulls(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is not null, not empty, and contains no
     * nulls.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends Iterable<?>> T notEmptyNoNulls(T val) {
        notEmpty(val);
        return noNulls(val);
    }

    /**
     * Call {@link #notEmptyNoNulls(Iterable)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends Iterable<?>> T assertnotEmptyNoNulls(T val) {
        assert notEmptyNoNulls(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is not null, not empty, and contains no
     * null {@link Map#values()}.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends Map<?, ?>> T notEmptyNoNulls(T val) {
        notEmpty(val);
        noNulls(val.values());
        return val;
    }

    /**
     * Call {@link #notEmptyNoNulls(Map)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends Map<?, ?>> T assertnotEmptyNoNulls(T val) {
        assert noNulls(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is not null and either doesn't exist or
     * can be written to.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static File canWrite(File val) {
        notNull(val);
        if (val.exists() && !val.canWrite()) {
            throw new ConstraintException(String.format("%s must be writable"));
        }
        return val;
    }

    /**
     * Call {@link #canWrite(File)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static File assertcanWrite(File val) {
        assert canWrite(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is not null, exists, and can be read.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static File canRead(File val) {
        notNull(val);
        if (!val.canRead()) {
            throw new ConstraintException(String.format("%s must be writable"));
        }
        return val;
    }

    /**
     * Call {@link #canRead(File)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static File assertcanRead(File val) {
        assert canRead(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is not null, not the wildcard address, and
     * matches a local interface.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends InetAddress> T isLocal(T val) {
        notNull(val);

        if (val.isAnyLocalAddress()) {
            throw new ConstraintException("%s must not be the wildcard address");
        }

        NetworkInterface nic;
        try {
            nic = NetworkInterface.getByInetAddress(val);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        if (nic == null) {
            throw new ConstraintException(String.format(
                    "%s is not a local interface", val));
        }
        return val;
    }

    /**
     * Call {@link #isLocal(InetAddress)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends InetAddress> T assertisLocal(T val) {
        assert isLocal(val) == val;
        return val;
    }

    /**
     * Validate that <code>val</code> is not null and is a multicast group.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends InetAddress> T isMCast(T val) {
        notNull(val);
        if (!val.isMulticastAddress()) {
            throw new ConstraintException("%s is not a multicast address");
        }

        return val;
    }

    /**
     * Call {@link #isMCast(InetAddress)} if assertions are enabled.
     *
     * @param val Value to validate
     * @return <code>val</code> if the constraint is met
     * @throws ConstraintException if the constraint is not met
     */
    public static <T extends InetAddress> T assertisMCast(T val) {
        assert isMCast(val) == val;
        return val;
    }
}
