package xyz.gmitch215.socketmc.neoforge;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import net.minecraft.network.FriendlyByteBuf;
import xyz.gmitch215.socketmc.neoforge.machines.*;
import xyz.gmitch215.socketmc.retriever.ClientProperty;
import xyz.gmitch215.socketmc.retriever.Retriever;
import xyz.gmitch215.socketmc.retriever.RetrieverType;
import xyz.gmitch215.socketmc.retriever.Window;
import xyz.gmitch215.socketmc.util.Identifier;
import xyz.gmitch215.socketmc.util.InputType;
import xyz.gmitch215.socketmc.util.RenderingProperties;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static xyz.gmitch215.socketmc.neoforge.NeoForgeSocketMC.minecraft;
import static xyz.gmitch215.socketmc.retriever.Retriever.create;

public final class NeoForgeRetriever {

    private NeoForgeRetriever() {}

    public static final Set<ClientProperty<?>> PROPERTIES = Stream.concat(
            Retriever.PROPERTIES.stream(),
            Stream.of(
                    create(RetrieverType.CURRENT_WINDOW, () -> {
                        com.mojang.blaze3d.platform.Window window = minecraft.getWindow();

                        return new Window(
                                window.getWindow(),
                                window.isFullscreen(),
                                window.getX(),
                                window.getY(),
                                window.getWidth(),
                                window.getHeight(),
                                window.getScreenWidth(),
                                window.getScreenHeight(),
                                window.getGuiScaledWidth(),
                                window.getGuiScaledHeight(),
                                window.getGuiScale(),
                                window.getFramerateLimit(),
                                com.mojang.blaze3d.platform.Window.getPlatform(),
                                RenderingProperties.REFRESH_RATE.get()
                        );
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
                        default -> InputType.NONE;
                    }),
                    create(RetrieverType.COMMAND_HISTORY, () -> minecraft.commandHistory().history().toArray(new String[0])),
                    create(RetrieverType.DRAWN_CONTENTS, () -> Stream.of(
                            DrawTextMachine.lifecycle.getIdentifiers(),
                            DrawShapeMachine.lifecycle.getIdentifiers(),
                            DrawTextureMachine.lifecycle.getIdentifiers(),
                            DrawBufferMachine.lifecycle.getIdentifiers(),
                            DrawContextMachine.lifecycle.getIdentifiers(),
                            DrawItemStackMachine.lifecycle.getIdentifiers()
                    ).flatMap(Set::stream).distinct().toArray(Identifier[]::new))
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

        ChannelFuture future = minecraft.player.connection.getConnection().channel().writeAndFlush(buf);
        future.await();
        if (!future.isSuccess())
            throw new IOException("Failed to send retriever response", future.cause());
    }

}
