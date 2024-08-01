package xyz.gmitch215.socketmc.fabric;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import xyz.gmitch215.socketmc.SocketMC;
import xyz.gmitch215.socketmc.fabric.machines.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

@Environment(EnvType.CLIENT)
public final class FabricSocketMC implements SocketMC, ClientModInitializer {

    public static boolean eventsEnabled = false;

    public static Minecraft minecraft;

    @Override
    public void onInitializeClient() {
        minecraft = Minecraft.getInstance();
        GAME_DIRECTORY.set(minecraft.gameDirectory);
        INSTANCE.set(this);

        // Events
        FabricEvents events = new FabricEvents();
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> events.onDisconnect());
        ServerTickEvents.END_SERVER_TICK.register(client -> events.tick());

        // Events - Machines
        HudRenderCallback.EVENT.register((graphics, delta) -> {
            DrawTextMachine.frameTick(graphics, delta);
            DrawShapeMachine.frameTick(graphics, delta);
            DrawBufferMachine.frameTick(graphics, delta);
            DrawTextureMachine.frameTick(graphics, delta);
            DrawBeaconBeamMachine.frameTick(graphics, delta);
            DrawContextMachine.frameTick(graphics, delta);
            DrawItemStackMachine.frameTick(graphics, delta);
        });
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
}
