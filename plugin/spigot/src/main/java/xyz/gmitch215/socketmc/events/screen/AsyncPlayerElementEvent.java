package xyz.gmitch215.socketmc.events.screen;

import xyz.gmitch215.socketmc.screen.AbstractScreen;
import xyz.gmitch215.socketmc.screen.ui.AbstractWidget;
import xyz.gmitch215.socketmc.spigot.SocketPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is called when a player interacts with an element on a screen.
 */
public abstract class AsyncPlayerElementEvent extends AsyncPlayerScreenEvent {

    private final AbstractWidget element;

    /**
     * Creates a new AsyncPlayerElementEvent instance.
     * @param element The element associated with this event
     * @param screen The screen associated with this event
     * @param player The player associated with this event
     */
    public AsyncPlayerElementEvent(@NotNull AbstractWidget element, @NotNull AbstractScreen screen, @NotNull SocketPlayer player) {
        super(screen, player);
        this.element = element;
    }

    /**
     * Gets the element associated with this event.
     * @return The element
     */
    @NotNull
    public AbstractWidget getElement() {
        return element;
    }

}
