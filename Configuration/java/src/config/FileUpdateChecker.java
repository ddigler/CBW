package config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Watches a list of files to see when they've been updated
 * 
 * @author benjamin
 */
public class FileUpdateChecker {
    private List<FileStatus> _files = new ArrayList<>();

    /**
     * @param files list of files to check
     */
    public FileUpdateChecker(List<File> files) {
        for (File f : files) {
            _files.add(new FileStatus(f));
        }
    }

    /**
     * Goes through the list of files and returns true if any of them have been
     * updated. If any file has been updated, the recorded modification time
     * will be updated as well.
     * 
     * @return true if any of the files have been updated
     */
    public boolean checkForUpdates() {
        boolean updated = false;
        for (FileStatus f : _files) {
            updated |= f.updated();
        }
        return updated;
    }

    private class FileStatus {
        private long _lastModificationTime;
        private File _file;

        FileStatus(File file) {
            _file = file;
            _lastModificationTime = file.lastModified();
        }

        boolean updated() {
            if (_file.lastModified() != _lastModificationTime) {
                _lastModificationTime = _file.lastModified();
                return true;
            } else {
                return false;
            }

        }
    }
}
