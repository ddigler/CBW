package config.python;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.python.core.PyClass;
import org.python.core.PyDictionary;
import org.python.core.PyFunction;
import org.python.core.PyInstance;
import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.core.PyTuple;

import common.config.ConfigSnapshot;

/**
 * Jython configuration snapshot
 * 
 * @author bgonzalez
 */
public final class JythonConfigSnapshot implements ConfigSnapshot {
    private Map<String, Object> _configMap = new HashMap<>();

    /**
     * @param root container for all holder objects
     * @param locals local variables in the python context
     */
    public JythonConfigSnapshot(PyObject root, Map<String, Object> locals) {
        inspect(root, new ArrayList<String>());
        for (Entry<String, Object> entry : locals.entrySet()) {
            if (!isHolder(entry.getValue()) && isAllowed(entry.getValue())) {
                _configMap.put(entry.getKey(), toJava(entry.getValue()));
            }
        }
    }

    /**
     * @param name key name
     * @param qualifiers additional qualifiers for the key
     * @return key value
     */
    @SuppressWarnings("unchecked")
    private <T> T get(String name, String... qualifiers) {
        for (int i = qualifiers.length; i >= 0; i--) {
            String k = key(name, qualifiers, i);
            T val = (T) _configMap.get(k);
            if (val != null) {
                return val;
            }
        }
        throw new RuntimeException("Configuration key not found: "
                + key(name, qualifiers, qualifiers.length));
    }

    /**
     * @param name key name
     * @param qualifiers additional qualifiers (toString() will be called to
     *            look them up)
     * @return key value
     */
    @Override
    public <T> T get(String name, Object... qualifiers) {
        String[] strQualifiers = new String[qualifiers.length];
        for (int i = 0; i < qualifiers.length; i++) {
            strQualifiers[i] = qualifiers[i].toString();
        }
        return get(name, strQualifiers);
    }

    private String key(String name, String[] tokens, int lastTokenIdx) {
        assert lastTokenIdx <= tokens.length;
        String key = "";
        for (int i = 0; i < lastTokenIdx; i++) {
            key += tokens[i] + ".";
        }
        return key + name;
    }

    private void inspect(Object holder, List<String> trail) {
        PyObject pyHolder = (PyObject) holder;
        PyObject nodes = pyHolder.__getattr__("nodes");
        assert nodes.getType() == PyDictionary.TYPE;

        PyList items = ((PyDictionary) nodes).items();
        for (int i = 0; i < items.__len__(); i++) {
            PyTuple item = (PyTuple) items.get(i);

            Object k = item.get(0);
            assert k instanceof String;
            List<String> path = new ArrayList<>(trail);
            path.add((String) k);
            Object v = item.get(1);
            if (isHolder(v)) {
                inspect(v, path);
            } else {
                save(v, path);
            }
        }
    }

    private Object toJava(Object v) {
        if (v instanceof PyObject) {
            if (v instanceof PyList) {
                List<Object> list = new ArrayList<>();
                PyList pyList = (PyList) v;
                for (int i = 0; i < pyList.__len__(); i++) {
                    list.add(toJava(pyList.get(i)));
                }
                return list;
            } else if (v instanceof PyTuple) {
                List<Object> list = new ArrayList<>();
                PyTuple pyList = (PyTuple) v;
                for (int i = 0; i < pyList.__len__(); i++) {
                    list.add(toJava(pyList.get(i)));
                }
                return list;
            } else if (v instanceof PyDictionary) {
                Map<Object, Object> map = new HashMap<>();
                PyDictionary pyDict = (PyDictionary) v;

                PyList items = pyDict.items();
                for (int i = 0; i < items.__len__(); i++) {
                    PyTuple item = (PyTuple) items.get(i);
                    map.put(toJava(item.get(0)), toJava(item.get(1)));
                }
                return map;
            } else {
                throw new RuntimeException(
                        "Cannot interpret python instance of " + v);
            }
        } else {
            return v;
        }
    }

    private void savePermutations(List<String> prefix, List<String> path,
            Object v) {
        if (path.isEmpty()) {
            String key = "";
            for (String p : prefix) {
                key = key + p + ".";
            }
            key = key.substring(0, key.length() - 1);
            if (_configMap.containsKey(key)) {
                throw new RuntimeException("This key was already defined: "
                        + key);
            }
            _configMap.put(key, v);
        } else {
            for (int i = 0; i < path.size(); i++) {
                List<String> newPrefix = new ArrayList<>(prefix);
                newPrefix.add(path.get(i));

                List<String> newPath = new ArrayList<>(path.subList(0, i));
                newPath.addAll(path.subList(i + 1, path.size()));
                savePermutations(newPrefix, newPath, v);
            }
        }
    }

    private void save(Object v, List<String> path) {
        savePermutations(new ArrayList<String>(), path, toJava(v));
    }

    private boolean isHolder(Object v) {
        if (v instanceof PyObject) {
            PyObject o = (PyObject) v;
            if (o.getType() == PyInstance.TYPE) {
                PyInstance instance = (PyInstance) o;
                String className = instance.fastGetClass()
                        .__getattr__("__name__").toString();
                if ("Holder".equals(className)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isAllowed(Object v) {
        if (v instanceof PyObject) {
            PyObject o = (PyObject) v;
            if (o.getType() == PyClass.TYPE || o.getType() == PyFunction.TYPE) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int i(String name, Object... qualifiers) {
        return ((Number) get(name, qualifiers)).intValue();
    }

    @Override
    public long l(String name, Object... qualifiers) {
        return ((Number) get(name, qualifiers)).longValue();
    }

    @Override
    public double d(String name, Object... qualifiers) {
        return ((Number) get(name, qualifiers)).doubleValue();
    }

    @Override
    public float f(String name, Object... qualifiers) {
        return ((Number) get(name, qualifiers)).floatValue();
    }

    @Override
    public short s(String name, Object qualifiers) {
        return ((Number) get(name, qualifiers)).shortValue();
    }

    @Override
    public boolean bool(String name, Object... qualifiers) {
        return (boolean) get(name, qualifiers);
    }

    @Override
    public String string(String name, Object... qualifiers) {
        return (String) get(name, qualifiers);
    }

    @Override
    public char c(String name, Object qualifiers) {
        return (char) ((Number) get(name, qualifiers)).byteValue();
    }

    @Override
    public byte b(String name, Object qualifiers) {
        return ((Number) get(name, qualifiers)).byteValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> list(String name, Object... qualifiers) {
        return (List<T>) get(name, qualifiers);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K, V> Map<K, V> map(String name, Object... qualifiers) {
        return (Map<K, V>) get(name, qualifiers);
    }

    @Override
    public int[] iArr(String name, Object... qualifiers) {
        List<Number> list = get(name, qualifiers);
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).intValue();
        }
        return array;
    }

    @Override
    public double[] dArr(String name, Object... qualifiers) {
        List<Number> list = get(name, qualifiers);
        double[] array = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).doubleValue();
        }
        return array;
    }

    @Override
    public float[] fArr(String name, Object... qualifiers) {
        List<Number> list = get(name, qualifiers);
        float[] array = new float[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).floatValue();
        }
        return array;
    }

    @Override
    public long[] lArr(String name, Object... qualifiers) {
        List<Number> list = get(name, qualifiers);
        long[] array = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).longValue();
        }
        return array;
    }

    @Override
    public short[] sArr(String name, Object... qualifiers) {
        List<Number> list = get(name, qualifiers);
        short[] array = new short[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).shortValue();
        }
        return array;
    }

    @Override
    public byte[] bArr(String name, Object... qualifiers) {
        List<Number> list = get(name, qualifiers);
        byte[] array = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).byteValue();
        }
        return array;
    }

    @Override
    public char[] cArr(String name, Object... qualifiers) {
        List<Number> list = get(name, qualifiers);
        char[] array = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = (char) list.get(i).byteValue();
        }
        return array;
    }

    @Override
    public boolean[] boolArr(String name, Object... qualifiers) {
        List<Boolean> list = get(name, qualifiers);
        boolean[] array = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    @Override
    public String[] stringArr(String name, Object... qualifiers) {
        List<String> list = get(name, qualifiers);
        return list.toArray(new String[0]);
    }
}
