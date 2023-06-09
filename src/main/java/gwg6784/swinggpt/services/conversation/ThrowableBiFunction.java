// Written by James P. (21154854)

package gwg6784.swinggpt.services.conversation;

import java.util.function.BiFunction;

/**
 * Converts any Throwables into a RuntimeException for use with Futures
 * @param <T> type 1
 * @param <U> type 2
 * @param <R> return type
 */
@FunctionalInterface
public interface ThrowableBiFunction<T, U, R> extends BiFunction<T, U, R> {
    @Override
    default R apply(T t, U u) {
        try {
            return applyThrowable(t, u);
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    R applyThrowable(T t, U u) throws Throwable;
}
