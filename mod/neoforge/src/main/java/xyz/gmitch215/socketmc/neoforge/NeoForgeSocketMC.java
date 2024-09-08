package xyz.gmitch215.socketmc.neoforge;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.neoforged.fml.common.Mod;
import xyz.gmitch215.socketmc.SocketMC;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Mod("socketmc")
public final class NeoForgeSocketMC implements SocketMC {

    public static boolean eventsEnabled = false;

    public static Minecraft minecraft;

    public NeoForgeSocketMC() {
        minecraft = Minecraft.getInstance();
        GAME_DIRECTORY.set(minecraft.gameDirectory);
        INSTANCE.set(this);
    }

    public static void sendEvent(int id, Map<String, Object> params) {
        if (!eventsEnabled) return;
        if (minecraft.player == null) return;

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream obj = new ObjectOutputStream(out);
            obj.writeObject(params);
            obj.close();

            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
            buf.writeVarInt(-3);
            buf.writeVarInt(id);
            buf.writeByteArray(out.toByteArray());

            ChannelFuture future = minecraft.player.connection.getConnection().channel().writeAndFlush(buf);
            future.await();
            if (!future.isSuccess())
                throw new IOException("Failed to send event", future.cause());
        } catch (IOException | InterruptedException e) {
            SocketMC.print(e);
        }
    }

    // Implementation

    @Override
    public void sendSocketMCEvent(int id, Map<String, Object> params) {
        sendEvent(id, params);
    }

    @Override
    public long getWindowId() {
        return minecraft.getWindow().getWindow();
    }

    @Override
    public void showPlayers(List<UUID> players) {
        players.forEach(minecraft.getPlayerSocialManager()::showPlayer);
    }

    @Override
    public void hidePlayers(List<UUID> players) {
        players.forEach(minecraft.getPlayerSocialManager()::hidePlayer);
    }

    @Override
    public Set<UUID> getHiddenPlayers() {
        return minecraft.getPlayerSocialManager().getHiddenPlayers();
    }
}