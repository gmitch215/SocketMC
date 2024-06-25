package xyz.gmitch215.socketmc.events.input;

import xyz.gmitch215.socketmc.spigot.SocketPlayer;
import xyz.gmitch215.socketmc.util.input.Action;
import xyz.gmitch215.socketmc.util.input.MouseButton;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a player clicks or releases a button their mouse.
 */
public class AsyncPlayerClickMouseEvent extends AsyncModifiedInputEvent {

    private final MouseButton button;
    private final Action action;

    /**
     * Constructs a new PlayerClickMouseEvent.
     * @param player The player that clicked their mouse.
     * @param button The button that was clicked.
     * @param action The action that was performed
     * @param flags Bitfield for flags of the mouse click
     */
    public AsyncPlayerClickMouseEvent(@NotNull SocketPlayer player, @NotNull MouseButton button, @NotNull Action action, int flags) {
        super(player, flags);

        this.button = button;
        this.action = action;
    }

    /**
     * Gets the button that was clicked.
     * @return The button that was clicked
     */
    @NotNull
    public MouseButton getButton() {
        return button;
    }

    /**
     * Gets the action that was performed.
     * @return The action that was performed
     */
    @NotNull
    public Action getAction() {
        return action;
    }
}
