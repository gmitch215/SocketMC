package me.gamercoder215.socketmc.events.screen;

import me.gamercoder215.socketmc.events.SocketEvent;
import me.gamercoder215.socketmc.screen.AbstractScreen;
import me.gamercoder215.socketmc.spigot.SocketPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is called when a player interacts with a screen.
 */
public abstract class AsyncPlayerScreenEvent extends SocketEvent {

    private final AbstractScreen screen;

    /**
     * Creates a new AsyncPlayerScreenEvent instance.
     * @param screen The screen associated with this event
     * @param player The player associated with this event
     */
    public AsyncPlayerScreenEvent(@NotNull AbstractScreen screen, @NotNull SocketPlayer player) {
        super(player);
        this.screen = screen;
    }

    /**
     * Gets the screen associated with this event.
     * @return The screen
     */
    @NotNull
    public AbstractScreen getScreen() {
        return screen;
    }
}
