package xyz.gmitch215.socketmc.forge;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import xyz.gmitch215.socketmc.SocketMC;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

@Mod("socketmc")
public final class ForgeSocketMC implements SocketMC {

    public static boolean eventsEnabled = false;

    public static Minecraft minecraft;

    public ForgeSocketMC() {
        minecraft = Minecraft.getInstance();
        GAME_DIRECTORY.set(minecraft.gameDirectory);
        INSTANCE.set(this);

        // Events
        MinecraftForge.EVENT_BUS.register(new ForgeEvents());
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
}
