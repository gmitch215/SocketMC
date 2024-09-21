package xyz.gmitch215.socketmc.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * A map that contains objects for a certain amount of time.
 * @param <T> The type of object to store
 */
public class LifecycleMap<T> implements Iterable<T> {

    private final Map<T, Identifier> identifiers = new HashMap<>();
    private final Map<T, Long> origin = new HashMap<>();
    private final Map<T, Long> duration = new HashMap<>();

    /**
     * Constructs a new, empty LifecycleMap.
     */
    public LifecycleMap() {}

    /**
     * Constructs a new LifecycleMap with the same mappings as the specified map.
     * @param map The map whose mappings are to be placed in this map
     */
    public LifecycleMap(@Nullable LifecycleMap<T> map) {
        if (map != null) {
            origin.putAll(map.origin);
            duration.putAll(map.duration);
        }
    }

    /**
     * Runs the map, removing any expired values.
     */
    public void run() {
        Iterator<T> it = origin.keySet().iterator();
        while (it.hasNext()) {
            T key = it.next();
            if (getRemainingTime(key) <= 0) {
                it.remove();
                duration.remove(key);
            }
        }
    }

    /**
     * Associates the specified value with the specified key in this map.
     * @param id The identifier for the key
     * @param key The key with which the specified value is to be associated
     * @param startMillis The start time of the value, in milliseconds
     * @param durationMillis The duration of the value, in milliseconds
     * @return The identifier of the key
     */
    @NotNull
    public Identifier put(@NotNull Identifier id, @NotNull T key, long startMillis, long durationMillis) {
        if (id == null) throw new IllegalArgumentException("Identifier cannot be null");
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        identifiers.put(key, id);
        origin.put(key, startMillis);
        duration.put(key, durationMillis);

        return id;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * @param key The key with which the specified value is to be associated
     * @param startMillis The start time of the value, in milliseconds
     * @param durationMillis The duration of the value, in milliseconds
     * @return The identifier of the key
     */
    @NotNull
    public Identifier put(@NotNull T key, long startMillis, long durationMillis) {
        return put(Identifier.random(), key, startMillis, durationMillis);
    }

    /**
     * Stores the specified key in this map for the specified duration.
     * @param id The identifier for the key
     * @param key The key to store
     * @param millis The duration to store the key for, in milliseconds
     * @return The identifier of the key
     */
    @NotNull
    public Identifier store(@NotNull Identifier id, @NotNull T key, long millis) {
        return put(id, key, System.currentTimeMillis(), millis);
    }

    /**
     * Stores the specified key in this map for the specified duration.
     * @param key The key to store
     * @param millis The duration to store the key for, in milliseconds
     * @return The identifier of the key
     */
    @NotNull
    public Identifier store(@NotNull T key, long millis) {
        return store(Identifier.random(), key, millis);
    }

    /**
     * Stores the specified key in this map for the specified duration.
     * @param id The identifier for the key
     * @param key The key whose associated value is to be returned
     * @param time The current time, in milliseconds
     * @param unit The time unit of the duration
     * @return The identifier for the key
     */
    @NotNull
    public Identifier store(@NotNull Identifier id, @NotNull T key, long time, @NotNull TimeUnit unit) {
        if (unit == null) throw new IllegalArgumentException("TimeUnit cannot be null");
        return put(id, key, System.currentTimeMillis(), unit.toMillis(time));
    }

    /**
     * Stores the specified key in this map for the specified duration.
     * @param key The key whose associated value is to be returned
     * @param time The current time, in milliseconds
     * @param unit The time unit of the duration
     * @return The identifier for the key
     */
    @NotNull
    public Identifier store(@NotNull T key, long time, @NotNull TimeUnit unit) {
        return store(Identifier.random(), key, time, unit);
    }

    /**
     * Stores the specified key in this map for the specified duration.
     * @param id The identifier for the key
     * @param key The key whose associated value is to be returned
     * @param duration The duration to store the key for
     * @return The identifier for the key
     */
    @NotNull
    public Identifier store(@NotNull Identifier id, @NotNull T key, @NotNull Duration duration) {
        if (duration == null) throw new IllegalArgumentException("TimeUnit cannot be null");
        return put(id, key, System.currentTimeMillis(), duration.toMillis());
    }

    /**
     * Stores the specified key in this map for the specified duration.
     * @param key The key whose associated value is to be returned
     * @param duration The duration to store the key for
     * @return The identifier for the key
     */
    @NotNull
    public Identifier store(@NotNull T key, @NotNull Duration duration) {
        return store(Identifier.random(), key, duration);
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * @param key The key whose associated value is to be returned
     */
    public void remove(@NotNull T key) {
        identifiers.remove(key);
        origin.remove(key);
        duration.remove(key);
    }

    /**
     * Returns the start time of the specified key.
     * @param key The key whose start time is to be returned
     * @return The start time of the key, in milliseconds
     */
    public long getStart(@NotNull T key) {
        return origin.get(key);
    }

    /**
     * Returns the duration of the specified key.
     * @param key The key whose duration is to be returned
     * @return The duration of the key, in milliseconds
     */
    public long getDuration(@NotNull T key) {
        return duration.get(key);
    }

    /**
     * Returns the remaining time of the specified key.
     * @param key The key whose remaining time is to be returned
     * @return The remaining time of the key, in milliseconds, or -1 if the key is not present
     */
    public long getRemainingTime(@NotNull T key) {
        if (!origin.containsKey(key)) return -1;
        return (origin.get(key) + duration.get(key)) - System.currentTimeMillis();
    }

    /**
     * Returns the identifier of the specified key.
     * @param key The key whose identifier is to be returned
     * @return The identifier of the key
     */
    @NotNull
    public Identifier getIdentifier(@NotNull T key) {
        return identifiers.get(key);
    }

    /**
     * Gets the contents of the LifecycleMap as a map of identifiers to keys.
     * @return The contents of the LifecycleMap
     */
    @NotNull
    public Map<Identifier, T> getContents() {
        Map<Identifier, T> contents = new HashMap<>();

        for (T key : origin.keySet())
            contents.put(identifiers.get(key), key);

        return contents;
    }

    /**
     * Gets the size of the map.
     * @return The size of the map
     */
    public int size() {
        return origin.size();
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     * @param key The key whose presence in this map is to be tested
     * @return True if this map contains a mapping for the specified key
     */
    public boolean containsKey(@NotNull T key) {
        return origin.containsKey(key);
    }

    /**
     * Returns true if this map contains a mapping for the specified identifier.
     * @param id The identifier whose presence in this map is to be tested
     * @return True if this map contains a mapping for the specified identifier
     */
    public boolean containsIdentifier(@NotNull Identifier id) {
        return identifiers.containsValue(id);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return origin.keySet().iterator();
    }
}
