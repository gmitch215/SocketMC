package xyz.gmitch215.socketmc.events.input;

import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.events.SocketEvent;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.spigot.SocketPlayer;

/**
 * Called when a player clicks a button from an external message box, generated from {@link Instruction#EXTERNAL_WINDOW_MESSAGE_BOX}.
 */
public class AsyncPlayerClickExternalMessageBoxEvent extends SocketEvent {

    private final boolean isSuccess;

    /**
     * Creates a new AsyncPlayerClickExternalMessageBoxEvent.
     * @param isSuccess Whether the player exited the window with success
     * @param player The player associated with this event
     */
    public AsyncPlayerClickExternalMessageBoxEvent(boolean isSuccess, @NotNull SocketPlayer player) {
        super(player);

        this.isSuccess = isSuccess;
    }

    /**
     * Returns whether the player did not click on cancel.
     * @return Whether the window was closed with success
     */
    public boolean isSuccess() {
        return isSuccess;
    }
}
