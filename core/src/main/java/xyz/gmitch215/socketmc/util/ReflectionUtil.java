package xyz.gmitch215.socketmc.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Reflection utilities used client-side.
 */
public final class ReflectionUtil {

    private ReflectionUtil() {}

    /**
     * Applies all fields from the apply object to the target object.
     * @param target Target object to apply to
     * @param apply Apply object to use
     * @param clazz Class of the objects
     * @param <T> Type of the objects
     * @throws IllegalArgumentException If the class or objects are null
     */
    public static <T> void apply(T target, T apply, Class<T> clazz) throws IllegalArgumentException {
        if (clazz == null) throw new IllegalArgumentException("Class cannot be null");
        if (target == null || apply == null) throw new IllegalArgumentException("Objects cannot be null");

        try {
            for (Field f : clazz.getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers())) continue;

                f.setAccessible(true);
                f.set(target, f.get(apply));
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
