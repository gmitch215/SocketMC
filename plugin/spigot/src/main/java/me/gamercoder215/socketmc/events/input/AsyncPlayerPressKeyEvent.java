package me.gamercoder215.socketmc.events.input;

import me.gamercoder215.socketmc.spigot.SocketPlayer;
import me.gamercoder215.socketmc.util.input.Action;
import me.gamercoder215.socketmc.util.input.Key;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a player presses or releases a key on a keyboard.
 */
public class AsyncPlayerPressKeyEvent extends AsyncModifiedInputEvent {

    private final Key key;
    private final int flags;
    private final Action action;

    /**
     * Constructs a new PlayerPressKeyEvent.
     * @param player The player that pressed the key
     * @param key The key that was pressed
     * @param flags Bitfield for flags of the key press
     * @param action The action of the key press
     */
    public AsyncPlayerPressKeyEvent(@NotNull SocketPlayer player, Key key, Action action, int flags) {
        super(player, flags);

        this.key = key;
        this.flags = flags;
        this.action = action;
    }

    /**
     * Gets the key that was pressed.
     * @return The key that was pressed
     */
    @NotNull
    public Key getKey() {
        return key;
    }

    /**
     * Gets the action on the key that was pressed.
     * @return The action on what was pressed
     */
    @NotNull
    public Action getAction() {
        return action;
    }

}
