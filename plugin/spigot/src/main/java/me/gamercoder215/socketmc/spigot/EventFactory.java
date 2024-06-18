package me.gamercoder215.socketmc.spigot;

import io.netty.channel.ChannelPipeline;
import me.gamercoder215.socketmc.events.SocketEvent;
import me.gamercoder215.socketmc.events.input.AsyncPlayerClickMouseEvent;
import me.gamercoder215.socketmc.events.input.AsyncPlayerMoveMouseEvent;
import me.gamercoder215.socketmc.events.input.AsyncPlayerPressKeyEvent;
import me.gamercoder215.socketmc.events.input.AsyncPlayerScrollMouseEvent;
import me.gamercoder215.socketmc.events.screen.*;
import me.gamercoder215.socketmc.screen.AbstractScreen;
import me.gamercoder215.socketmc.screen.ui.AbstractButton;
import me.gamercoder215.socketmc.screen.ui.CheckboxButton;
import me.gamercoder215.socketmc.screen.ui.CycleButton;
import me.gamercoder215.socketmc.screen.ui.EditTextWidget;
import me.gamercoder215.socketmc.util.input.Action;
import me.gamercoder215.socketmc.util.input.Key;
import me.gamercoder215.socketmc.util.input.MouseButton;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

@SuppressWarnings("unchecked")
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
            // PlayerPressKeyEvent - 0
            (p, params) -> {
                int key = (int) params.get("key");
                int flags = (int) params.get("flags");
                int action = (int) params.get("action");

                return new AsyncPlayerPressKeyEvent(p, Key.fromCode(key), Action.values()[action], flags);
            },
            // PlayerMoveMouseEvent - 1
            (p, params) -> {
                double x = (double) params.get("x");
                double y = (double) params.get("y");

                return new AsyncPlayerMoveMouseEvent(p, x, y);
            },
            // PlayerScrollMouseEvent - 2
            (p, params) -> {
                double x = (double) params.get("x");
                double y = (double) params.get("y");

                return new AsyncPlayerScrollMouseEvent(p, x, y);
            },
            // PlayerClickMouseEvent - 3
            (p, params) -> {
                int button = (int) params.get("button");
                int action = (int) params.get("action");
                int flags = (int) params.get("mods");

                return new AsyncPlayerClickMouseEvent(p, MouseButton.values()[button], Action.values()[action], flags);
            },
            // PlayerChangeScreenEvent - 4
            (p, params) -> {
                AbstractScreen oldScreen = (AbstractScreen) params.get("old");
                AbstractScreen newScreen = (AbstractScreen) params.get("new");

                return new AsyncPlayerChangeScreenEvent(oldScreen, newScreen, p);
            },
            // PlayerClickButtonEvent - 5
            (p, params) -> {
                AbstractButton button = (AbstractButton) params.get("button");
                AbstractScreen screen = (AbstractScreen) params.get("screen");

                return new AsyncPlayerClickButtonEvent(button, screen, p);
            },
            // PlayerToggleCheckboxEvent - 6
            (p, params) -> {
                CheckboxButton button = (CheckboxButton) params.get("button");
                AbstractScreen screen = (AbstractScreen) params.get("screen");
                boolean state = (boolean) params.get("state");

                return new AsyncPlayerToggleCheckboxEvent(button, state, screen, p);
            },
            // PlayerEditTextEvent - 7
            (p, params) -> {
                EditTextWidget widget = (EditTextWidget) params.get("widget");
                AbstractScreen screen = (AbstractScreen) params.get("screen");
                String text = (String) params.get("text");

                return new AsyncPlayerEditTextEvent(widget, text, screen, p);
            },
            // PlayerCycleButtonEvent - 8
            (p, params) -> {
                CycleButton<Object> button = (CycleButton<Object>) params.get("button");
                AbstractScreen screen = (AbstractScreen) params.get("screen");
                Object value = params.get("value");

                return new AsyncPlayerCycleButtonEvent(button, value, screen, p);
            }
    );

    static void call(SocketPlayer player, int id, Map<String, Object> params) {
        SocketEvent e = factory.get(id).apply(player, params);
        CompletableFuture.runAsync(() -> Bukkit.getPluginManager().callEvent(e));
    }
}
