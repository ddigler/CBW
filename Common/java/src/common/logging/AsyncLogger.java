/**
 *
 */
package common.logging;

import static common.Constraints.notNull;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import common.Common;
import common.concurrent.SameThread;

/**
 * An asynchronous implementation of a {@link Logger}. Log messages are placed
 * on a queue for later writing. To perform the actual writing, {@link #run()}
 * must be called periodically.
 *
 * @author rstoner
 */
public final class AsyncLogger extends AbstractLogger implements Runnable {
    private final SameThread _threadTester = new SameThread();
    private final LogWriter _writer;
    private final Queue<Record> _queue;

    /**
     * @param writer The destination for log messages, which performs the actual
     *            writing.
     */
    public AsyncLogger(LogWriter writer) {
        _writer = notNull(writer);

        // TODO: Better queue
        _queue = new ConcurrentLinkedQueue<>();
    }

    @Override
    protected void doLog(Level level, Throwable thrown, String msg,
            Object[] args) {
        long time = Common.nanoTime();
        Record r = new Record(time, level, thrown, msg, args);
        // TODO: Deal with full queue
        _queue.add(r);
    }

    @Override
    public void run() {
        assert _threadTester.check();

        for (Record r = _queue.poll(); r != null; r = _queue.poll()) {
            _writer.write(r._time, r._level, r._msg, r._args, r._thrown);
        }
    }

    private static final class Record {
        private final long _time;
        private final Level _level;
        private final String _msg;
        private final Object[] _args;
        private final Throwable _thrown;

        public Record(long time, Level level, Throwable thrown, String msg,
                Object[] args) {
            _time = time;
            _level = level;
            _thrown = thrown;
            _msg = msg;
            _args = args;
        }
    }
}
