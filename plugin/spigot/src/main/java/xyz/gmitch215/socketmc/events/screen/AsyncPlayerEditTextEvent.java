package xyz.gmitch215.socketmc.events.screen;

import xyz.gmitch215.socketmc.screen.AbstractScreen;
import xyz.gmitch215.socketmc.screen.ui.EditTextWidget;
import xyz.gmitch215.socketmc.spigot.SocketPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is fired when a player edits a {@link EditTextWidget}.
 */
public class AsyncPlayerEditTextEvent extends AsyncPlayerElementEvent {

    private final String text;

    /**
     * Constructs a new AsyncPlayerEditTextEvent.
     * @param element The element that the player edited.
     * @param text The text that the player entered.
     * @param screen The screen that the element is on.
     * @param player The player that edited the element.
     */
    public AsyncPlayerEditTextEvent(@NotNull EditTextWidget element, @NotNull String text, @NotNull AbstractScreen screen, @NotNull SocketPlayer player) {
        super(element, screen, player);
        this.text = text;
    }

    @Override
    public EditTextWidget getElement() {
        return (EditTextWidget) super.getElement();
    }

    /**
     * Gets the text that the player entered.
     * @return The text.
     */
    @NotNull
    public String getText() {
        return text;
    }

}
