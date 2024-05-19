package me.gamercoder215.socketmc.fabric;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import me.gamercoder215.socketmc.fabric.machines.DrawBufferMachine;
import me.gamercoder215.socketmc.fabric.machines.DrawShapeMachine;
import me.gamercoder215.socketmc.fabric.machines.DrawTextMachine;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

@Environment(EnvType.CLIENT)
public final class FabricSocketMC implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("SocketMC");
    public static boolean eventsEnabled = false;

    public static Minecraft minecraft;

    @Override
    public void onInitializeClient() {
        minecraft = Minecraft.getInstance();

        // Events
        FabricEvents events = new FabricEvents();
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> events.onDisconnect());

        // Events - Machines
        HudRenderCallback.EVENT.register((graphics, delta) -> {
            DrawTextMachine.frameTick(graphics, delta);
            DrawShapeMachine.frameTick(graphics, delta);
            DrawBufferMachine.frameTick(graphics, delta);
        });
    }

    public static void print(Throwable t) {
        LOGGER.error("[SocketMC] {}", t.getClass().getSimpleName());
        LOGGER.error("-----------");
        LOGGER.error(t.getMessage());
        for (StackTraceElement element : t.getStackTrace()) LOGGER.error("  {}", element.toString());

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
            buf.writeVarInt(-3);
            buf.writeVarInt(id);
            buf.writeByteArray(out.toByteArray());

            ChannelFuture future = minecraft.player.connection.getConnection().channel.writeAndFlush(buf);
            future.await();
            if (!future.isSuccess())
                throw new IOException("Failed to send event", future.cause());
        } catch (IOException | InterruptedException e) {
            print(e);
        }
    }

}
