package config.python;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.python.core.PyException;
import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.core.PyStringMap;
import org.python.core.PyTuple;
import org.python.util.PythonInterpreter;

import config.FileUpdateChecker;

/**
 * @author bgonzalez
 */
public final class JythonConfigLoader {

    private List<File> _pyFiles;
    private PythonInterpreter _pyEngine = new PythonInterpreter();

    /**
     * @param importsFilename imports file
     */
    public JythonConfigLoader(String importsFilename) {
        this(getFiles(importsFilename));
    }

    /**
     * @param files list of files to interpret
     */
    public JythonConfigLoader(List<File> files) {
        _pyFiles = files;
    }

    private static List<File> getFiles(String importsFilename) {
        File importsFile = new File(importsFilename);
        if (!importsFile.exists()) {
            throw new RuntimeException("Imports file not found: "
                    + importsFile.getAbsolutePath());
        }

        File importsFolder = importsFile.getParentFile();
        if (importsFolder == null) {
            importsFolder = new File(".");
        }

        try {
            List<File> files = new ArrayList<>();
            BufferedReader rdr = new BufferedReader(new FileReader(importsFile));
            String line = null;
            while ((line = rdr.readLine()) != null) {
                File pyFile = new File(importsFolder.getAbsolutePath()
                        + File.separator + line.trim());
                if (!pyFile.exists()) {
                    throw new RuntimeException("Configuration file not found: "
                            + pyFile.getAbsolutePath());
                }
                files.add(pyFile);
            }
            return files;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reloads the python files and reinterprets their content
     */
    public void reload() {
        _pyEngine.cleanup();
        try {
            for (File pyFile : _pyFiles) {
                _pyEngine.execfile(new FileInputStream(pyFile),
                        pyFile.getName());
            }
        } catch (FileNotFoundException | PyException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return configuration snapshot
     */
    public JythonConfigSnapshot createConfigSnapshot() {
        PyObject root = _pyEngine.get("root");
        Map<String, Object> locals = new HashMap<String, Object>();
        PyList items = ((PyStringMap) _pyEngine.getLocals()).items();
        for (int i = 0; i < items.__len__(); i++) {
            PyTuple item = (PyTuple) items.get(i);

            Object k = item.get(0);
            assert k instanceof String;
            String key = (String) k;
            Object v = item.get(1);
            if (!key.startsWith("_") && !"root".equals(key)) {
                locals.put((String) k, v);
            }
        }

        return new JythonConfigSnapshot(root, locals);
    }

    /**
     * Task that checks if any of the configuration files have changed, and if
     * so, reinterprets them
     * 
     * @return task
     */
    public Runnable getFileUpdateTask() {
        final FileUpdateChecker fileChecker = new FileUpdateChecker(_pyFiles);
        return new Runnable() {
            @Override
            public void run() {
                if (fileChecker.checkForUpdates()) {
                    reload();
                }
            }
        };
    }
    //
    // public static void main(String[] args) {
    // JythonConfigLoader configLoader = new JythonConfigLoader("test.imports");
    // configLoader.reload();
    //
    // ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
    // Future<?> result = pool.scheduleAtFixedRate(
    // configLoader.getFileUpdateTask(), 2, 2, TimeUnit.SECONDS);
    // try {
    // while (true) {
    // test(configLoader.createConfigSnapshot());
    // result.get();
    // }
    // } catch (InterruptedException | ExecutionException e) {
    // e.printStackTrace();
    // pool.shutdown();
    // }
    // }
}
