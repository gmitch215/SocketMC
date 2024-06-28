package xyz.gmitch215.socketmc.events.screen;

import xyz.gmitch215.socketmc.screen.AbstractScreen;
import xyz.gmitch215.socketmc.spigot.SocketPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is called when a player changes screens. Note that not all screens can be intercepted.
 */
public class AsyncPlayerChangeScreenEvent extends AsyncPlayerScreenEvent {

    private final AbstractScreen newScreen;

    /**
     * Creates a new AsyncPlayerChangeScreenEvent instance.
     * @param oldScreen The screen the player is changing from
     * @param newScreen The screen the player is changing to
     * @param player The player associated with this event
     */
    public AsyncPlayerChangeScreenEvent(@NotNull AbstractScreen oldScreen, @NotNull AbstractScreen newScreen, @NotNull SocketPlayer player) {
        super(oldScreen, player);
        this.newScreen = newScreen;
    }

    /**
     * Gets the screen the player is changing to.
     * @return The screen the player is changing to
     */
    @NotNull
    public AbstractScreen getNewScreen() {
        return newScreen;
    }

}
