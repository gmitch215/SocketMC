package me.gamercoder215.socketmc.events.screen;

import me.gamercoder215.socketmc.screen.AbstractScreen;
import me.gamercoder215.socketmc.screen.ui.AbstractButton;
import me.gamercoder215.socketmc.spigot.SocketPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is called when a player clicks on a button on a screen.
 */
public class AsyncPlayerClickButtonEvent extends AsyncPlayerElementEvent {

    /**
     * Creates a new AsyncPlayerClickElementEvent instance.
     * @param element The element associated with this event
     * @param screen The screen associated with this event
     * @param player The player associated with this event
     */
    public AsyncPlayerClickButtonEvent(@NotNull AbstractButton element, @NotNull AbstractScreen screen, @NotNull SocketPlayer player) {
        super(element, screen, player);
    }

    /**
     * Gets the button that was clicked.
     * @return The button that was clicked
     */
    @NotNull
    public AbstractButton getElement() {
        return (AbstractButton) super.getElement();
    }

}
