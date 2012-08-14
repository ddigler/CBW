package config.python;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * @author bgonzalez
 */
public class TestJythonConfigLoader {
    private static enum TestEnum {
        A;
    }

    private JythonConfigSnapshot _cfg;

    /***/
    @Before
    public void initConfigSystem() {
        JythonConfigLoader configLoader = new JythonConfigLoader("test.imports");
        configLoader.reload();
        _cfg = configLoader.createConfigSnapshot();
    }

    /***/
    @Test
    public void testIntVal() {
        int b = _cfg.get("b", "A");
        assertEquals(1, b);
    }

    /***/
    @Test
    public void testStringVal() {
        String c = _cfg.get("c", "A");
        assertEquals("a", c);
    }

    /***/
    @Test
    public void testDoubleVal() {
        double d = _cfg.get("d", "A");
        assertEquals(2.0, d, 0.0);
    }

    /***/
    @Test
    public void testBooleanVal() {
        boolean l = _cfg.get("l", "B");
        assertTrue(l);
    }

    /***/
    @Test
    public void testListVal() {
        List<Integer> e = _cfg.<Integer> getList("e", "A");
        assertArrayEquals(e.toArray(), new Integer[] { 1, 2 });
    }

    /***/
    @Test
    public void testMapVal() {
        Map<Integer, List<Integer>> f = _cfg.<Integer, List<Integer>> getMap(
                "f", "A");
        assertTrue(f.containsKey(1));
        assertArrayEquals(f.get(1).toArray(), new Integer[] { 2, 3, 4 });
    }

    /***/
    @Test
    public void testMultipleQualifiers() {
        int j = _cfg.get("j", "A", "g", "h", "i");
        assertEquals(1, j);
    }

    /***/
    @Test
    public void testMultipleQualifiersDifferentOrder() {
        int j = _cfg.get("j", "g", "i", "A", "h");
        assertEquals(1, j);
    }

    /***/
    @Test
    public void testGlobalVariable() {
        List<Integer> test = _cfg.get("Test");
        assertArrayEquals(test.toArray(), new Integer[] { 5, 6, 7 });
    }

    /***/
    @Test
    public void testGlobalFallback() {
        List<Integer> test = _cfg.get("Test", "H", "K");
        assertArrayEquals(test.toArray(), new Integer[] { 5, 6, 7 });
    }

    /***/
    @Test
    public void testPartialFallback() {
        double k = _cfg.get("k", "B", "m");
        assertEquals(10.0, k, 0.0);
    }

    /***/
    @Test(expected = RuntimeException.class)
    public void testMissingKey() {
        _cfg.get("NotAKey", "None");
    }

    /***/
    @Test
    public void testObjectLookup() {
        int b = _cfg.get("b", TestEnum.A);
        assertEquals(1, b);
    }

    /***/
    @Test
    public void testPrimitiveCalls() {
        int b = _cfg.getInt("b", "A");
        assertEquals(1, b);

        String c = _cfg.getString("c", "A");
        assertEquals("a", c);

        double d = _cfg.getDouble("d", "A");
        assertEquals(2.0, d, 0.0);

        double b1 = _cfg.getDouble("b", "A");
        assertEquals(1.0, b1, 0.0);

        short b2 = _cfg.getShort("b", "A");
        assertEquals(1, b2);

        float b3 = _cfg.getFloat("b", "A");
        assertEquals(1.0, b3, 0.0);

        boolean l = _cfg.getBoolean("l", "B");
        assertTrue(l);
    }

    @Test
    public void testArrayConversion() {
        int[] e = _cfg.getIntArray("e", "A");
        assertArrayEquals(e, new int[] { 1, 2 });

        double[] e1 = _cfg.getDoubleArray("e", "A");
        assertArrayEquals(e1, new double[] { 1.0, 2.0 }, 0.0);
    }
}
