package me.gamercoder215.socketmc.events.screen;

import me.gamercoder215.socketmc.screen.AbstractScreen;
import me.gamercoder215.socketmc.screen.ui.CheckboxButton;
import me.gamercoder215.socketmc.spigot.SocketPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that is called when a player toggles a checkbox on a screen.
 */
public class AsyncPlayerToggleCheckboxEvent extends AsyncPlayerClickButtonEvent {

    private final boolean checked;

    /**
     * Creates a new AsyncPlayerToggleCheckboxEvent instance.
     * @param element The element associated with this event
     * @param checked The new state of the checkbox
     * @param screen The screen associated with this event
     * @param player The player associated with this event
     */
    public AsyncPlayerToggleCheckboxEvent(@NotNull CheckboxButton element, boolean checked, @NotNull AbstractScreen screen, @NotNull SocketPlayer player) {
        super(element, screen, player);
        this.checked = checked;
    }

    /**
     * Gets the checkbox that was toggled.
     * @return The checkbox that was toggled
     */
    @Override
    public CheckboxButton getElement() {
        return (CheckboxButton) super.getElement();
    }

    /**
     * Gets the new state of the checkbox.
     * @return The new state of the checkbox
     */
    public boolean isChecked() {
        return checked;
    }
}
