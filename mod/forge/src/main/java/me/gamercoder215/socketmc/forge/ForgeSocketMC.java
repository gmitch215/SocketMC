package me.gamercoder215.socketmc.forge;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

@Mod("socketmc")
public final class ForgeSocketMC {

    public static final Logger LOGGER = LoggerFactory.getLogger("SocketMC");
    public static boolean eventsEnabled = false;

    public static Minecraft minecraft;

    public ForgeSocketMC() {
        minecraft = Minecraft.getInstance();

        // Events
        MinecraftForge.EVENT_BUS.register(new ForgeEvents());
    }

    public static void print(Throwable t) {
        LOGGER.error("[SocketMC] {}", t.getClass().getSimpleName());
        LOGGER.error("-----------");
        LOGGER.error(t.getMessage());
        for (StackTraceElement element : t.getStackTrace()) LOGGER.error(element.toString());

        if (t.getCause() != null) {
            LOGGER.error("Caused by:");
            print(t.getCause());
        }
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
            buf.writeVarInt(id);
            buf.writeByteArray(out.toByteArray());

            ChannelFuture future = minecraft.player.connection.getConnection().channel().writeAndFlush(buf);
            if (!future.isSuccess())
                throw new IOException("Failed to send event", future.cause());
        } catch (IOException e) {
            print(e);
        }
    }

}
