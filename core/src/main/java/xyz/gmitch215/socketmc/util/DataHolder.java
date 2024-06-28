package xyz.gmitch215.socketmc.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Map;

/**
 * Represents an object that holds an immutable map of data.
 */
public interface DataHolder {

    /**
     * Gets an immutable copy for the data for this object.
     * @return Screen Data
     */
    @NotNull
    @Unmodifiable
    Map<String, Object> getData();

    /**
     * Gets a specific piece of data from this object.
     * @param key Data Key
     * @return Data Value
     * @throws IllegalArgumentException if key is null
     */
    @Nullable
    default Object data(@NotNull String key) throws IllegalArgumentException {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        return getData().get(key);
    }

    /**
     * Gets a specific piece of data from this object.
     * @param key Data Key
     * @param def Default Value
     * @return Data Value
     * @throws IllegalArgumentException if key or default value are null
     */
    @NotNull
    default Object data(@NotNull String key, @NotNull Object def) throws IllegalArgumentException {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (def == null) throw new IllegalArgumentException("Default value cannot be null");

        return getData().getOrDefault(key, def);
    }

    /**
     * Gets a specific piece of data from this object.
     * @param key Data Key
     * @param clazz Data Class
     * @return Data Value
     * @param <T> Data Type
     * @throws IllegalArgumentException if key or class are null
     */
    @Nullable
    default <T> T data(@NotNull String key, @NotNull Class<T> clazz) throws IllegalArgumentException {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (clazz == null) throw new IllegalArgumentException("Class cannot be null");

        return clazz.cast(data(key));
    }

    /**
     * Gets a specific piece of data from this object.
     * @param key Data Key
     * @param clazz Data Class
     * @param def Default Value
     * @return Data Value
     * @param <T> Data Type
     * @throws IllegalArgumentException if key, class, or default value are null
     */
    @NotNull
    default <T> T data(@NotNull String key, @NotNull Class<T> clazz, @NotNull T def) throws IllegalArgumentException {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (clazz == null) throw new IllegalArgumentException("Class cannot be null");
        if (def == null) throw new IllegalArgumentException("Default value cannot be null");

        return clazz.cast(data(key, def));
    }
    
}
