package me.gamercoder215.socketmc.fabric;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import me.gamercoder215.socketmc.SocketMC;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.FriendlyByteBuf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.logging.Logger;

@Environment(EnvType.CLIENT)
public final class FabricSocketMC implements ClientModInitializer {

    public static final Logger LOGGER = Logger.getLogger("SocketMC");
    public static boolean eventsEnabled = false;

    public static Minecraft minecraft;
    public static GuiGraphics graphics;

    @Override
    public void onInitializeClient() {
        minecraft = Minecraft.getInstance();
        graphics = new GuiGraphics(minecraft, minecraft.renderBuffers().bufferSource());
    }

    public static void print(Throwable t) {
        SocketMC.print(LOGGER, t);
    }

    public static void sendEvent(int id, Map<String, Object> params) {
        if (!eventsEnabled) return;

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream obj = new ObjectOutputStream(out);
            obj.writeObject(params);
            obj.close();

            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
            buf.writeVarInt(id);
            buf.writeBytes(out.toByteArray());

            ChannelFuture future = minecraft.player.connection.getConnection().channel.writeAndFlush(buf);
            if (!future.isSuccess())
                throw new IOException("Failed to send event", future.cause());
        } catch (IOException e) {
            print(e);
        }
    }

}
