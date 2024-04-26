package me.gamercoder215.socketmc.spigot;

import io.netty.channel.ChannelPipeline;
import me.gamercoder215.socketmc.events.input.PlayerClickMouseEvent;
import me.gamercoder215.socketmc.events.input.PlayerMoveMouseEvent;
import me.gamercoder215.socketmc.events.input.PlayerScrollMouseEvent;
import me.gamercoder215.socketmc.util.input.Action;
import me.gamercoder215.socketmc.events.input.PlayerPressKeyEvent;
import me.gamercoder215.socketmc.events.SocketEvent;
import me.gamercoder215.socketmc.util.input.Key;
import me.gamercoder215.socketmc.util.input.MouseButton;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

final class EventFactory {

    private EventFactory() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    // Packet Injectors

    static void addPacketInjector(SocketPlayer p) {
        ChannelPipeline pipeline = p.getChannel().pipeline();

        if (pipeline.get(SocketPlayer.PACKET_INJECTOR_ID) != null) return;
        pipeline.addBefore("decoder", SocketPlayer.PACKET_INJECTOR_ID, new PacketReceiver(p));
    }

    // Event Factory

    private static final List<BiFunction<SocketPlayer, Map<String, Object>, SocketEvent>> factory = List.of(
            // PlayerPressKeyEvent
            (p, params) -> {
                int key = (int) params.get("key");
                int flags = (int) params.get("flags");
                int action = (int) params.get("action");

                return new PlayerPressKeyEvent(p, Key.fromCode(key), Action.values()[action], flags);
            },
            // PlayerMoveMouseEvent
            (p, params) -> {
                double x = (double) params.get("x");
                double y = (double) params.get("y");

                return new PlayerMoveMouseEvent(p, x, y);
            },
            // PlayerScrollMouseEvent
            (p, params) -> {
                double x = (double) params.get("x");
                double y = (double) params.get("y");

                return new PlayerScrollMouseEvent(p, x, y);
            },
            // PlayerClickMouseEvent
            (p, params) -> {
                int button = (int) params.get("button");
                int action = (int) params.get("action");
                int flags = (int) params.get("mods");

                return new PlayerClickMouseEvent(p, MouseButton.values()[button], Action.values()[action], flags);
            }
    );

    static void call(SocketPlayer player, int id, Map<String, Object> params) {
        SocketEvent e = factory.get(id).apply(player, params);
        Bukkit.getPluginManager().callEvent(e);
    }
}