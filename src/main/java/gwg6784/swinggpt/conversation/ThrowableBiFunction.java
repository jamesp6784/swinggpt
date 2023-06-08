package gwg6784.swinggpt.conversation;

import java.util.function.BiFunction;

/**
 * Converts any Throwables into a RuntimeException for use with Futures
 */
@FunctionalInterface
public interface ThrowableBiFunction<T, U, R> extends BiFunction<T, U, R> {
    default R apply(T t, U u) {
        try {
            return applyThrowable(t, u);
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    R applyThrowable(T t, U u) throws Throwable;
}
