// Written by James P. (21154854)

package gwg6784.swinggpt.services.conversation;

import java.util.function.Supplier;

/**
 * Converts any Throwables into a RuntimeException for use with Futures
 * 
 * @param <T> supplier item
 */
@FunctionalInterface
public interface ThrowableSupplier<T> extends Supplier<T> {
    @Override
    default T get() {
        try {
            return getThrowable();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    T getThrowable() throws Throwable;
}
