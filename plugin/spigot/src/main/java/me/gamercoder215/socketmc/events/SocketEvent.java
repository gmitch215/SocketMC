package me.gamercoder215.socketmc.events;

import me.gamercoder215.socketmc.spigot.SocketPlayer;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event fired by SocketMC.
 */
public abstract class SocketEvent extends PlayerEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final SocketPlayer socketPlayer;

    /**
     * Creates a new SocketEvent instance.
     * @param player The player associated with this event
     */
    public SocketEvent(@NotNull SocketPlayer player) {
        super(player.getPlayer());
        this.socketPlayer = player;
    }

    /**
     * Gets the SocketPlayer associated with this event.
     * @return SocketPlayer
     */
    @NotNull
    public SocketPlayer getSocketPlayer() {
        return socketPlayer;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * Gets the handler list for this event.
     * @return HandlerList
     */
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
