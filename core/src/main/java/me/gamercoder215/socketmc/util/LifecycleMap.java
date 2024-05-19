package me.gamercoder215.socketmc.util;

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
        for (T key : origin.keySet()) {
            if (getRemainingTime(key) <= 0) {
                origin.remove(key);
                duration.remove(key);
            }
        }
    }

    /**
     * Associates the specified value with the specified key in this map.
     * @param key The key with which the specified value is to be associated
     * @param startMillis The start time of the value, in milliseconds
     * @param durationMillis The duration of the value, in milliseconds
     */
    public void put(@NotNull T key, long startMillis, long durationMillis) {
        origin.put(key, startMillis);
        duration.put(key, durationMillis);
    }

    /**
     * Stores the specified key in this map for the specified duration.
     * @param key The key to store
     * @param millis The duration to store the key for, in milliseconds
     */
    public void store(@NotNull T key, long millis) {
        put(key, System.currentTimeMillis(), millis);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * @param key The key whose associated value is to be returned
     * @param time The current time, in milliseconds
     * @param unit The time unit of the duration
     */
    public void store(@NotNull T key, long time, @NotNull TimeUnit unit) {
        if (unit == null) throw new IllegalArgumentException("TimeUnit cannot be null");
        put(key, System.currentTimeMillis(), unit.toMillis(time));
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * @param key The key whose associated value is to be returned
     * @param duration The duration to store the key for
     */
    public void store(@NotNull T key, @NotNull Duration duration) {
        if (duration == null) throw new IllegalArgumentException("TimeUnit cannot be null");
        put(key, System.currentTimeMillis(), duration.toMillis());
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * @param key The key whose associated value is to be returned
     */
    public void remove(@NotNull T key) {
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

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return origin.keySet().iterator();
    }
}
