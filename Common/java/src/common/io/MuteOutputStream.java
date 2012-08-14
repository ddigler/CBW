package common.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * {@link OutputStream} implementation which discards all data.
 *
 * @author rstoner
 */
public final class MuteOutputStream extends OutputStream {
    /** Singleton instance, if so desired. */
    public static MuteOutputStream INSTANCE = new MuteOutputStream();

    @Override
    public void write(int b) throws IOException {
        // no-op
    }

    @Override
    public void write(byte[] b) throws IOException {
        // no-op
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        // no-op
    }
}
