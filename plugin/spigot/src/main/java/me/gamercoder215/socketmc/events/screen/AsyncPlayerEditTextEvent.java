package me.gamercoder215.socketmc.events.screen;

import me.gamercoder215.socketmc.screen.AbstractScreen;
import me.gamercoder215.socketmc.screen.ui.EditTextWidget;
import me.gamercoder215.socketmc.spigot.SocketPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is fired when a player edits a {@link EditTextWidget}.
 */
public class AsyncPlayerEditTextEvent extends AsyncPlayerElementEvent {

    private final String text;

    public AsyncPlayerEditTextEvent(@NotNull EditTextWidget element, @NotNull String text, @NotNull AbstractScreen screen, @NotNull SocketPlayer player) {
        super(element, screen, player);
        this.text = text;
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
