package me.gamercoder215.socketmc.spigot;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thread-safe utility class for caching a {@link SocketPlayer} object.
 */
public final class SocketPlayerRegistry {

    private static final Map<UUID, SocketPlayer> registry = new ConcurrentHashMap<>();

    private SocketPlayerRegistry() {}

    /**
     * Gets a {@link SocketPlayer} object from the cache.
     * @param pid The UUID of the player to get the object for.
     * @return The {@link SocketPlayer} object, or null if the player is not in the cache.
     */
    @Nullable
    public static SocketPlayer get(@NotNull UUID pid) {
        return registry.get(pid);
    }

    /**
     * Gets a {@link SocketPlayer} object from the cache.
     * @param p The player to get the object for.
     * @return The {@link SocketPlayer} object, or null if the player is not in the cache.
     */
    @Nullable
    public static SocketPlayer get(@NotNull Player p) {
        return get(p.getUniqueId());
    }

    /**
     * Creates a new {@link SocketPlayer} object and caches it. This will remove any existing object for the player.
     * @param pid The UUID of the player to create the object for.
     * @return The new {@link SocketPlayer} object.
     */
    @NotNull
    public static SocketPlayer create(@NotNull UUID pid) {
        registry.remove(pid);

        Player p = Bukkit.getPlayer(pid);
        SocketPlayer sp = new SocketPlayer(p);
        registry.put(pid, sp);

        return sp;
    }

    /**
     * Creates a new {@link SocketPlayer} object and caches it. This will remove any existing object for the player.
     * @param p The player to create the object for.
     * @return The new {@link SocketPlayer} object.
     */
    @NotNull
    public static SocketPlayer create(@NotNull Player p) {
        return create(p.getUniqueId());
    }

    /**
     * Creates a new {@link SocketPlayer} object and caches it if one does not already exist.
     * @param pid The UUID of the player to create the object for.
     * @return The {@link SocketPlayer} object.
     */
    @NotNull
    public static SocketPlayer createIfAbsent(@NotNull UUID pid) {
        SocketPlayer sp = get(pid);
        if (sp == null) return create(pid);

        return sp;
    }

    /**
     * Creates a new {@link SocketPlayer} object and caches it if one does not already exist.
     * @param p The player to create the object for.
     * @return The {@link SocketPlayer} object.
     */
    @NotNull
    public static SocketPlayer createIfAbsent(@NotNull Player p) {
        return createIfAbsent(p.getUniqueId());
    }

    /**
     * Removes a {@link SocketPlayer} object from the cache.
     * @param pid The UUID of the player to remove the object for.
     */
    public static void remove(@NotNull UUID pid) {
        registry.remove(pid);
    }

    /**
     * Removes a {@link SocketPlayer} object from the cache.
     * @param p The player to remove the object for.
     */
    public static void remove(@NotNull Player p) {
        remove(p.getUniqueId());
    }

}
