package common.config;

import java.util.List;
import java.util.Map;

/**
 * Configuration snapshot
 * 
 * @author bgonzalez
 */
public interface ConfigSnapshot {
    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value
     */
    public <T> T get(String name, Object... qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as an int
     */
    public int i(String name, Object... qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as a long
     */
    public long l(String name, Object... qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as a double
     */
    public double d(String name, Object... qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as a float
     */
    public float f(String name, Object... qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as short
     */
    public short s(String name, Object qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as char
     */
    public char c(String name, Object qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as byte
     */
    public byte b(String name, Object qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as a boolean
     */
    public boolean bool(String name, Object... qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as a string
     */
    public String string(String name, Object... qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as a list of T
     */
    public <T> List<T> list(String name, Object... qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as a map of K,V
     */
    public <K, V> Map<K, V> map(String name, Object... qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as an array of ints
     */
    public int[] iArr(String name, Object... qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as an array of doubles
     */
    public double[] dArr(String name, Object... qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as an array of floats
     */
    public float[] fArr(String name, Object... qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as an array of longs
     */
    public long[] lArr(String name, Object... qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as an array of shorts
     */
    public short[] sArr(String name, Object... qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as an array of bytes
     */
    public byte[] bArr(String name, Object... qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as an array of chars
     */
    public char[] cArr(String name, Object... qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as an array of booleans
     */
    public boolean[] boolArr(String name, Object... qualifiers);

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value as an array of strings
     */
    public String[] stringArr(String name, Object... qualifiers);

}
