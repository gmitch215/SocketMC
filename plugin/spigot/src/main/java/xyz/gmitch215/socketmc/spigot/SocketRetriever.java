package xyz.gmitch215.socketmc.spigot;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.DecoderException;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.gmitch215.socketmc.SocketMCNotInstalledException;
import xyz.gmitch215.socketmc.instruction.FailedInstructionException;
import xyz.gmitch215.socketmc.retriever.RetrieverType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * The retriever handler for a {@link SocketPlayer}.
 */
@SuppressWarnings("rawtypes")
public final class SocketRetriever {

    final SocketPlayer player;
    final Map<UUID, Consumer> bus = new HashMap<>();

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
    public Map<UUID, Consumer> getBus() {
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

    /**
     * Retrieves information from the client.
     * @param r The retriever type
     * @param callback The callback to be called when the information is retrieved
     * @param plugin The plugin to retrieve the information for
     * @return The identifier of the request, to be stored in {@link #getBus()}.
     * @param <T> The type of the retriever
     * @throws FailedInstructionException if the instruction fails to send
     */
    @NotNull
    public <T> UUID retrieve(@NotNull RetrieverType<T> r, @NotNull Consumer<@Nullable T> callback, @NotNull SocketPlugin plugin) throws FailedInstructionException {
        if (r == null) throw new IllegalArgumentException("Retriever type cannot be null");
        if (callback == null) throw new IllegalArgumentException("Callback cannot be null");
        if (plugin == null) throw new IllegalArgumentException("Plugin cannot be null");

        UUID id = UUID.randomUUID();
        bus.put(id, callback);

        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeVarInt(-4);
        buf.writeUUID(id);
        buf.writeByteArray(r.toByteArray());
        buf.writeByteArray(plugin.toByteArray());

        try {
            ChannelFuture future = player.channel.writeAndFlush(buf);
            future.await();
            if (!future.isSuccess()) {
                if (future.cause() instanceof DecoderException)
                    throw new SocketMCNotInstalledException("Player does not have SocketMC installed", future.cause());
                else
                    throw new FailedInstructionException("Failed to request retriever", future.cause());
            } else {
                // Successful Retriever Request
                ServerAuditLog.INSTANCE.logSent(r, plugin);
            }
        } catch (InterruptedException e) {
            throw new FailedInstructionException("Failed to request retriever", e);
        }

        return id;
    }


}
