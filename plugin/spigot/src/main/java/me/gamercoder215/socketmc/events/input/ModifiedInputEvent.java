package me.gamercoder215.socketmc.events.input;

import me.gamercoder215.socketmc.events.SocketEvent;
import me.gamercoder215.socketmc.spigot.SocketPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an input event that can be modified using a keyboard modifier (Shift, Ctrl, Caps Lock, etc.)
 */
public abstract class ModifiedInputEvent extends SocketEvent {

    private final int flags;

    /**
     * Constructs a new ModifiedInputEvent.
     * @param flags The flags that were active when the event occurred.
     */
    public ModifiedInputEvent(@NotNull SocketPlayer player, int flags) {
        super(player);
        this.flags = flags;
    }

    /**
     * Gets all of the flags on the key press.
     * @return Bitfield for flags of the key press
     */
    public int getFlags() {
        return flags;
    }

    /**
     * Checks if a shift key was pressed.
     * @return True if a shift key was pressed during the key press
     */
    public boolean isShiftPressed() {
        return (flags & 0x01) != 0;
    }

    /**
     * Checks if a control key was pressed.
     * @return True if a control key was pressed during the key press
     */
    public boolean isCtrlPressed() {
        return (flags & 0x02) != 0;
    }

    /**
     * Checks if an alt key was pressed.
     * @return True if an alt key was pressed during the key press
     */
    public boolean isAltPressed() {
        return (flags & 0x04) != 0;
    }

    /**
     * Checks if a super key was pressed.
     * @return True if a super key was pressed during the key press
     */
    public boolean isSuperPressed() {
        return (flags & 0x08) != 0;
    }

    /**
     * Checks if caps lock is enabled.
     * @return True if the caps lock key was enabled during the key press
     */
    public boolean isCapsLockPressed() {
        return (flags & 0x10) != 0;
    }

    /**
     * Checks if num lock is enabled.
     * @return True if the num lock key was enabled during the key press
     */
    public boolean isNumLockPressed() {
        return (flags & 0x20) != 0;
    }

}
