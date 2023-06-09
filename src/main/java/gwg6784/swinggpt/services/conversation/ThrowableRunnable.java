// Written by James P. (21154854)

package gwg6784.swinggpt.services.conversation;

/**
 * Converts any Throwables into a RuntimeException for use with Futures
 */
@FunctionalInterface
public interface ThrowableRunnable extends Runnable {
    @Override
    default void run() {
        try {
            runThrowable();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    void runThrowable() throws Throwable;
}
