package xyz.gmitch215.socketmc.spigot;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * The retriever handler for a {@link SocketPlayer}.
 */
public final class SocketRetriever {

    private final SocketPlayer player;
    private final Map<UUID, Consumer<?>> bus = new HashMap<>();

    SocketRetriever(SocketPlayer player) {
        this.player = player;
    }

    /**
     * <p>Gets an immutable copy of this SocketRetriever's method bus.</p>
     * <p>Internally, the UUID generated with the retriever method is passed with the retriever type, and the consumer is not serialized and kept here.
     * Then, once the mod successfully retrieves the requested information, the consumer is called based on the UUID mapped to it. The consumer does
     * <strong>not</strong> need to be serializable.</p>
     * @return An immutable copy of this SocketRetriever's method bus
     */
    @NotNull
    public Map<UUID, Consumer<?>> getBus() {
        return Map.copyOf(bus);
    }

    /**
     * Gets the player associated with this retriever.
     * @return The player associated with this retriever
     */
    @NotNull
    public SocketPlayer getPlayer() {
        return player;
    }
}
