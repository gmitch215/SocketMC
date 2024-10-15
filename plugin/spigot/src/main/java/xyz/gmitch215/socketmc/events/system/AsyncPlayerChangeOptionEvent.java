package xyz.gmitch215.socketmc.events.system;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.gmitch215.socketmc.events.SocketEvent;
import xyz.gmitch215.socketmc.spigot.SocketPlayer;

/**
 * Called when a player changes a game option.
 */
public class AsyncPlayerChangeOptionEvent extends SocketEvent {

    private final String key;
    private final Object oldValue;
    private final String oldValueString;
    private final Object newValue;
    private final String newValueString;

    /**
     * Creates a new AsyncPlayerChangeOptionEvent.
     * @param player The player associated with this event
     */
    public AsyncPlayerChangeOptionEvent(@NotNull String key, @Nullable Object oldValue, @NotNull String oldValueString, @Nullable Object newValue, @NotNull String newValueString, @NotNull SocketPlayer player) {
        super(player);

        if (key == null) throw new IllegalArgumentException("key cannot be null");
        if (oldValueString == null) throw new IllegalArgumentException("oldValueString cannot be null");
        if (newValueString == null) throw new IllegalArgumentException("newValueString cannot be null");

        this.key = key;
        this.oldValue = oldValue;
        this.oldValueString = oldValueString;
        this.newValue = newValue;
        this.newValueString = newValueString;
    }

    /**
     * Gets the key of the option that was changed.
     * @return Option Identifier Key
     */
    @NotNull
    public String getKey() {
        return key;
    }

    /**
     * Gets the old value of the option.
     * @return Old Value
     */
    @Nullable
    public Object getOldValue() {
        return oldValue;
    }

    /**
     * Gets the old value of the option as a string.
     * @return Old Value as a String
     */
    @NotNull
    public String getOldValueString() {
        return oldValueString;
    }

    /**
     * Gets the new value of the option.
     * @return New Value
     */
    @Nullable
    public Object getNewValue() {
        return newValue;
    }

    /**
     * Gets the new value of the option as a string.
     * @return New Value as a String
     */
    @NotNull
    public String getNewValueString() {
        return newValueString;
    }
}
