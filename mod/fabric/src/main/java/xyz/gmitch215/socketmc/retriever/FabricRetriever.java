package xyz.gmitch215.socketmc.retriever;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import net.minecraft.network.FriendlyByteBuf;
import xyz.gmitch215.socketmc.util.InputType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static xyz.gmitch215.socketmc.fabric.FabricSocketMC.minecraft;
import static xyz.gmitch215.socketmc.retriever.Retriever.create;

public final class FabricRetriever {

    private FabricRetriever() {}

    public static final Set<ClientProperty<?>> PROPERTIES = Stream.concat(
            Retriever.PROPERTIES.stream(),
            Stream.of(
                    create(RetrieverType.CURRENT_WINDOW, () -> {
                        com.mojang.blaze3d.platform.Window window = minecraft.getWindow();

                        Window w = new Window();
                        w.id = window.getWindow();
                        w.fullscreen = window.isFullscreen();
                        w.x = window.getX();
                        w.y = window.getY();
                        w.width = window.getWidth();
                        w.height = window.getHeight();
                        w.screenWidth = window.getScreenWidth();
                        w.screenHeight = window.getScreenHeight();
                        w.guiScaledWidth = window.getGuiScaledWidth();
                        w.guiScaledHeight = window.getGuiScaledHeight();
                        w.guiScale = window.getGuiScale();
                        w.framerateLimit = window.getFramerateLimit();
                        w.platform = com.mojang.blaze3d.platform.Window.getPlatform();
                        w.refreshRate = window.getRefreshRate();

                        return w;
                    }),
                    create(RetrieverType.PAUSED, minecraft::isPaused),
                    create(RetrieverType.FPS, minecraft::getFps),
                    create(RetrieverType.GPU_UTILIZATION, minecraft::getGpuUtilization),
                    create(RetrieverType.LAUNCH_VERSION, minecraft::getLaunchedVersion),
                    create(RetrieverType.VERSION_TYPE, minecraft::getVersionType),
                    create(RetrieverType.LAST_INPUT_TYPE, () -> switch (minecraft.getLastInputType()) {
                        case KEYBOARD_ARROW -> InputType.KEYBOARD_ARROW;
                        case KEYBOARD_TAB -> InputType.KEYBOARD_TAB;
                        case MOUSE -> InputType.MOUSE;
                        case NONE -> InputType.NONE;
                    }),
                    create(RetrieverType.COMMAND_HISTORY, () -> minecraft.commandHistory().history().toArray(new String[0]))
            )
    ).collect(Collectors.toSet());

    public static void response(UUID id, Object value) throws Exception {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeVarInt(-5);
        buf.writeUUID(id);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream outw = new ObjectOutputStream(out);
        outw.writeObject(value);
        outw.close();

        buf.writeByteArray(out.toByteArray());

        ChannelFuture future = minecraft.player.connection.getConnection().channel.writeAndFlush(buf);
        future.await();
        if (!future.isSuccess())
            throw new IOException("Failed to send retriever response", future.cause());
    }
}
