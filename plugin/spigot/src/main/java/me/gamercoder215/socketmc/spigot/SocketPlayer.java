package me.gamercoder215.socketmc.spigot;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import me.gamercoder215.socketmc.SocketMCNotInstalledException;
import me.gamercoder215.socketmc.instruction.Instruction;
import net.minecraft.network.Connection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

/**
 * Represents a player connected to the server with SocketMC installed.
 */
public final class SocketPlayer {

    /**
     * The ID of the packet injector.
     */
    public static final String PACKET_INJECTOR_ID = "socketmc:packet_injector";

    private final Player player;
    private final Channel channel;

    /**
     * Creates a new SocketPlayer instance.
     * @param p The player to create the instance for.
     * @throws SocketMCNotInstalledException if the player does not have SocketMC installed
     */
    public SocketPlayer(@NotNull Player p) throws SocketMCNotInstalledException {
        this.player = p;

        try {
            Field connection = ServerCommonPacketListenerImpl.class.getDeclaredField("c");
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
        sendInstruction(Instruction.ping());
    }

    /**
     * Sends an instruction to the player.
     * @param i The instruction to send.
     * @throws SocketMCNotInstalledException if the player does not have SocketMC installed
     */
    public void sendInstruction(@NotNull Instruction i) throws SocketMCNotInstalledException {
        if (i == null) throw new IllegalArgumentException("Instruction cannot be null");

        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeVarInt(-2);
        buf.writeByteArray(i.toByteArray());

        ChannelFuture future = channel.writeAndFlush(buf);
        if (!future.isSuccess())
            throw new SocketMCNotInstalledException("SocketMC is not installed on the player's client");
    }

}
