package xyz.gmitch215.socketmc.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a NBT tag.
 */
@SuppressWarnings("unchecked")
public final class NBTTag implements Serializable {

    @Serial
    private static final long serialVersionUID = -8168895646288699502L;

    private final Map<String, Object> tag = new HashMap<>();

    /**
     * Constructs a new, empty NBTTag.
     */
    public NBTTag() {
    }

    /**
     * Constructs a new NBTTag.
     * @param tag The tag.
     */
    public NBTTag(@NotNull Map<String, Object> tag) {
        this.tag.putAll(tag);
    }

    /**
     * Gets an immutable copy of the full tag.
     * @return The tag.
     */
    @NotNull
    public Map<String, Object> getTag() {
        return Map.copyOf(tag);
    }

    /**
     * <p>Gets an object from the tag. If the object does not exist, null is returned.</p>
     * <p>This supports dot notation, for example, "key.subkey.subsubkey".</p>
     * @param key The key to get.
     * @return The object, or null if it does not exist.
     */
    @Nullable
    public Object get(@NotNull String key) {
        String[] keys = key.split("\\.");
        Map<String, Object> current = tag;

        for (int i = 0; i < keys.length - 1; i++) {
            if (!current.containsKey(keys[i])) return null;
            Object c = current.get(keys[i]);
            if (!(current instanceof Map)) return null;

            current = (Map<String, Object>) c;
        }

        return current.get(keys[keys.length - 1]);
    }

    /**
     * Sets an object in the tag.
     * @param key The key to set.
     * @param value The value to set. If null, will remove the key.
     */
    public void set(@NotNull String key, @Nullable Object value) {
        if (value == null) {
            remove(key);
            return;
        }

        String[] keys = key.split("\\.");
        Map<String, Object> current = tag;

        for (int i = 0; i < keys.length - 1; i++) {
            if (!current.containsKey(keys[i])) current.put(keys[i], new HashMap<String, Object>());
            Object c = current.get(keys[i]);
            if (!(current instanceof Map)) return;

            current = (Map<String, Object>) c;
        }

        current.put(keys[keys.length - 1], value);
    }

    /**
     * Removes an object from the tag.
     * @param key The key to remove.
     */
    public void remove(@NotNull String key) {
        String[] keys = key.split("\\.");
        Map<String, Object> current = tag;

        for (int i = 0; i < keys.length - 1; i++) {
            if (!current.containsKey(keys[i])) return;
            Object c = current.get(keys[i]);
            if (!(current instanceof Map)) return;

            current = (Map<String, Object>) c;
        }

        current.remove(keys[keys.length - 1]);
    }

    /**
     * <p>Gets an object from the tag. If the object does not exist, the default value is returned.</p>
     * <p>This supports dot notation, for example, "key.subkey.subsubkey".</p>
     * @param key The key to get.
     * @param def The default value.
     * @return The object, or the default value if it does not exist.
     */
    @Nullable
    public Object get(@NotNull String key, @Nullable Object def) {
        Object obj = get(key);
        return obj == null ? def : obj;
    }

    /**
     * Gets an object from the tag. If the object does not exist, null is returned.
     * @param key The key to get.
     * @param clazz The class of the object to cast to.
     * @param <T> The type of the object.
     * @return The object, or null if it does not exist.
     */
    @NotNull
    public <T> T getObject(@NotNull String key, @NotNull Class<T> clazz) {
        return clazz.cast(get(key));
    }

    /**
     * Gets an object from the tag. If the object does not exist, the default value is returned.
     * @param key The key to get.
     * @param clazz The class of the object to cast to.
     * @param def The default value.
     * @param <T> The type of the object.
     * @return The object, or the default value if it does not exist.
     */
    @NotNull
    public <T> T getObject(@NotNull String key, @NotNull Class<T> clazz, T def) {
        Object obj = get(key);
        return obj == null ? def : clazz.cast(obj);
    }

    /**
     * Gets a section from the tag. If the object does not exist, an empty map is returned.
     * @param key The key to get.
     * @return The section, or an empty map if it does not exist.
     */
    @NotNull
    public Map<String, Object> getSection(@NotNull String key) {
        return getObject(key, Map.class, new HashMap<>());
    }

    /**
     * Creates a section in the tag. If it exists, it will be overwritten.
     * @param key The key to create.
     */
    public void createSection(@NotNull String key) {
        set(key, new HashMap<>());
    }

    /**
     * Gets a string from the tag. If the object does not exist, an empty string is returned.
     * @param key The key to get.
     * @return The string, or an empty string if it does not exist.
     */
    @NotNull
    public String getString(@NotNull String key) {
        return getObject(key, String.class, "");
    }

    /**
     * Gets a string from the tag. If the object does not exist, the default value is returned.
     * @param key The key to get.
     * @param def The default value.
     * @return The string, or the default value if it does not exist.
     */
    @NotNull
    public String getString(@NotNull String key, @NotNull String def) {
        return getObject(key, String.class, def);
    }

    /**
     * Gets a boolean from the tag. If the object does not exist, false is returned.
     * @param key The key to get.
     * @return The boolean, or false if it does not exist.
     */
    public boolean getBoolean(@NotNull String key) {
        return getObject(key, Boolean.class, false);
    }

    /**
     * Gets a boolean from the tag. If the object does not exist, the default value is returned.
     * @param key The key to get.
     * @param def The default value.
     * @return The boolean, or the default value if it does not exist.
     */
    public boolean getBoolean(@NotNull String key, boolean def) {
        return getObject(key, Boolean.class, def);
    }

    /**
     * Gets a byte from the tag. If the object does not exist, 0 is returned.
     * @param key The key to get.
     * @return The byte, or 0 if it does not exist.
     */
    public byte getByte(@NotNull String key) {
        return getObject(key, Byte.class, (byte) 0);
    }

    /**
     * Gets a byte from the tag. If the object does not exist, the default value is returned.
     * @param key The key to get.
     * @param def The default value.
     * @return The byte, or the default value if it does not exist.
     */
    public byte getByte(@NotNull String key, byte def) {
        return getObject(key, Byte.class, def);
    }

    /**
     * Gets a short from the tag. If the object does not exist, 0 is returned.
     * @param key The key to get.
     * @return The short, or 0 if it does not exist.
     */
    public short getShort(@NotNull String key) {
        return getObject(key, Short.class, (short) 0);
    }

    /**
     * Gets a short from the tag. If the object does not exist, the default value is returned.
     * @param key The key to get.
     * @param def The default value.
     * @return The short, or the default value if it does not exist.
     */
    public short getShort(@NotNull String key, short def) {
        return getObject(key, Short.class, def);
    }

    /**
     * Gets an integer from the tag. If the object does not exist, 0 is returned.
     * @param key The key to get.
     * @return The integer, or 0 if it does not exist.
     */
    public int getInt(@NotNull String key) {
        return getObject(key, Integer.class, 0);
    }

    /**
     * Gets an integer from the tag. If the object does not exist, the default value is returned.
     * @param key The key to get.
     * @param def The default value.
     * @return The integer, or the default value if it does not exist.
     */
    public int getInt(@NotNull String key, int def) {
        return getObject(key, Integer.class, def);
    }

    /**
     * Gets a long from the tag. If the object does not exist, 0 is returned.
     * @param key The key to get.
     * @return The long, or 0 if it does not exist.
     */
    public long getLong(@NotNull String key) {
        return getObject(key, Long.class, 0L);
    }

    /**
     * Gets a long from the tag. If the object does not exist, the default value is returned.
     * @param key The key to get.
     * @param def The default value.
     * @return The long, or the default value if it does not exist.
     */
    public long getLong(@NotNull String key, long def) {
        return getObject(key, Long.class, def);
    }

    /**
     * Gets a float from the tag. If the object does not exist, 0 is returned.
     * @param key The key to get.
     * @return The float, or 0 if it does not exist.
     */
    public float getFloat(@NotNull String key) {
        return getObject(key, Float.class, 0.0f);
    }

    /**
     * Gets a float from the tag. If the object does not exist, the default value is returned.
     * @param key The key to get.
     * @param def The default value.
     * @return The float, or the default value if it does not exist.
     */
    public float getFloat(@NotNull String key, float def) {
        return getObject(key, Float.class, def);
    }

    /**
     * Gets a double from the tag. If the object does not exist, 0 is returned.
     * @param key The key to get.
     * @return The double, or 0 if it does not exist.
     */
    public double getDouble(@NotNull String key) {
        return getObject(key, Double.class, 0.0);
    }

    /**
     * Gets a double from the tag. If the object does not exist, the default value is returned.
     * @param key The key to get.
     * @param def The default value.
     * @return The double, or the default value if it does not exist.
     */
    public double getDouble(@NotNull String key, double def) {
        return getObject(key, Double.class, def);
    }

    /**
     * Gets a byte array from the tag. If the object does not exist, an empty byte array is returned.
     * @param key The key to get.
     * @return The byte array, or an empty byte array if it does not exist.
     */
    @NotNull
    public byte[] getByteArray(@NotNull String key) {
        return getObject(key, byte[].class, new byte[0]);
    }

    /**
     * Gets a byte array from the tag. If the object does not exist, the default value is returned.
     * @param key The key to get.
     * @param def The default value.
     * @return The byte array, or the default value if it does not exist.
     */
    @NotNull
    public byte[] getByteArray(@NotNull String key, @NotNull byte[] def) {
        return getObject(key, byte[].class, def);
    }

}
