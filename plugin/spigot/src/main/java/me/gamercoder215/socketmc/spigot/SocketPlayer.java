package me.gamercoder215.socketmc.spigot;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import me.gamercoder215.socketmc.SocketMCNotInstalledException;
import me.gamercoder215.socketmc.instruction.Instruction;
import net.minecraft.network.Connection;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

/**
 * Represents a player connected to the server with SocketMC installed.
 */
public final class SocketPlayer {

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
     */
    public void sendInstruction(@NotNull Instruction i) {
        if (i == null) throw new IllegalArgumentException("Instruction cannot be null");

        ByteBuf buf = Unpooled.wrappedBuffer(i.toByteArray());
        channel.writeAndFlush(buf);
    }

}