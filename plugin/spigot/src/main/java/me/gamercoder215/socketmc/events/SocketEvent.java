package me.gamercoder215.socketmc.events;

import me.gamercoder215.socketmc.spigot.SocketPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event fired by SocketMC. All SocketMC events are fired asynchronously.
 */
public abstract class SocketEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private final SocketPlayer socketPlayer;

    /**
     * Creates a new SocketEvent instance.
     * @param player The player associated with this event
     */
    public SocketEvent(@NotNull SocketPlayer player) {
        super(true);
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

    /**
     * Gets the player associated with this event.
     * @return Player
     */
    @NotNull
    public Player getPlayer() {
        return socketPlayer.getPlayer();
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

    @Override
    public String toString() {
        return getEventName() + "{" + getPlayer().getName() + "}";
    }
}
