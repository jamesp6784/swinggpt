package gwg6784.swinggpt.services;

import java.util.HashMap;
import java.util.Map;

public class Services {
    private static final Map<Class<?>, Object> services = new HashMap<>();

    public static <T> void add(T item) {
        services.put(item.getClass(), item);
    }

    public static <T> T get(Class<T> clazz) {
        return clazz.cast(services.get(clazz));
    }
}
