package xyz.gmitch215.socketmc.spigot;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.DecoderException;
import xyz.gmitch215.socketmc.SocketMCNotInstalledException;
import xyz.gmitch215.socketmc.instruction.FailedInstructionException;
import xyz.gmitch215.socketmc.instruction.Instruction;
import net.minecraft.network.Connection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;
import org.bukkit.craftbukkit.v1_21_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.net.URISyntaxException;

/**
 * Represents a player connected to the server with SocketMC installed.
 */
public final class SocketPlayer {

    /**
     * The ID of the packet injector.
     */
    public static final String PACKET_INJECTOR_ID = "socketmc:packet_injector";

    private final SocketRetriever retriever = new SocketRetriever(this);

    final Player player;
    final Channel channel;

    /**
     * Creates a new SocketPlayer instance.
     * @param p The player to create the instance for.
     * @throws SocketMCNotInstalledException if the player does not have SocketMC installed
     */
    public SocketPlayer(@NotNull Player p) throws SocketMCNotInstalledException {
        this.player = p;

        try {
            Field connection = ServerCommonPacketListenerImpl.class.getDeclaredField("e");
            connection.setAccessible(true);
            this.channel = ((Connection) connection.get(((CraftPlayer) p).getHandle().connection)).channel;
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException("Failed to get connection field", e);
        }

        EventFactory.addPacketInjector(this);
        ping();
    }

    /**
     * Gets the player associated with this SocketPlayer instance.
     * @return Player
     */
    @NotNull
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the netty channel connecting the player to the server.
     * @return Network Channel
     */
    @NotNull
    public Channel getChannel() {
        return channel;
    }

    /**
     * Sends a {@link Instruction#PING} instruction.
     */
    public void ping() {
        sendInstruction(Instruction.ping(), SocketPlugin.LIBRARY);
    }

    /**
     * Sends an instruction to the player.
     * @param i The instruction to send.
     * @param plugin The plugin sending the instruction.
     * @throws SocketMCNotInstalledException if the player does not have SocketMC installed
     */
    public void sendInstruction(@NotNull Instruction i, @NotNull Plugin plugin) throws SocketMCNotInstalledException {
        try {
            SocketPlugin sp = new SocketPlugin(
                    plugin.getDescription().getMain(),
                    plugin.getName(),
                    plugin.getDescription().getVersion(),
                    plugin.getDescription().getWebsite(),
                    plugin.getClass()
                            .getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI()
                            .getPath()
            );

            sendInstruction(i, sp);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Failed to get plugin jar file path", e);
        }
    }

    /**
     * Sends an instruction to the player.
     * @param i The instruction to send.
     * @param plugin The plugin sending the instruction.
     * @throws SocketMCNotInstalledException if the player does not have SocketMC installed
     * @see SocketPlugin
     */
    public void sendInstruction(@NotNull Instruction i, @NotNull SocketPlugin plugin) throws SocketMCNotInstalledException {
        if (i == null) throw new IllegalArgumentException("Instruction cannot be null");
        if (plugin == null) throw new IllegalArgumentException("Plugin cannot be null");

        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeVarInt(-2);
        buf.writeByteArray(i.toByteArray());
        buf.writeByteArray(plugin.toByteArray());

        try {
            ChannelFuture future = channel.writeAndFlush(buf);
            future.await();
            if (!future.isSuccess()) {
                if (future.cause() instanceof DecoderException)
                    throw new SocketMCNotInstalledException("Player does not have SocketMC installed", future.cause());
                else
                    throw new FailedInstructionException("Failed to send instruction", future.cause());
            } else {
                // Successful Instruction Send
                ServerAuditLog.INSTANCE.logSent(i, plugin);
            }
        } catch (InterruptedException e) {
            throw new FailedInstructionException("Failed to send instruction", e);
        }
    }

    /**
     * Gets the retriever for this SocketPlayer.
     * @return Socket Retriever
     */
    @NotNull
    public SocketRetriever getRetriever() {
        return retriever;
    }

    // Static Util

    /**
     * Registers the packet injector for the player.
     * Please note that a {@link Instruction#PING} instruction must be sent at least once in order for the client to start sending events.
     * @param p The player to register the events for.
     */
    public static void registerEvents(@NotNull Player p) {
        SocketPlayer sp = SocketPlayerRegistry.createIfAbsent(p);
        EventFactory.addPacketInjector(sp);
    }

}
