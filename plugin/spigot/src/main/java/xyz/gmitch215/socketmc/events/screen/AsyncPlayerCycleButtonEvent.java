package xyz.gmitch215.socketmc.events.screen;

import xyz.gmitch215.socketmc.screen.AbstractScreen;
import xyz.gmitch215.socketmc.screen.ui.CycleButton;
import xyz.gmitch215.socketmc.spigot.SocketPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a player cycles through a {@link CycleButton} with multiple states.
 */
public class AsyncPlayerCycleButtonEvent extends AsyncPlayerClickButtonEvent {

    private final Object newValue;

    /**
     * Constructs a new AsyncPlayerCycleButtonEvent.
     * @param element The element that the player cycled.
     * @param newValue The new value that the player selected.
     * @param screen The screen that the element is on.
     * @param player The player that edited the element.
     * @param <T> The type of the element.
     */
    public <T> AsyncPlayerCycleButtonEvent(@NotNull CycleButton<T> element, @NotNull T newValue, @NotNull AbstractScreen screen, @NotNull SocketPlayer player) {
        super(element, screen, player);
        this.newValue = newValue;
    }

    @Override
    public CycleButton<?> getElement() {
        return (CycleButton<?>) super.getElement();
    }

    /**
     * Gets the new value that the player selected.
     * @return The new value.
     */
    @NotNull
    public Object getNewValue() {
        return newValue;
    }
}
