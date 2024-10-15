package xyz.gmitch215.socketmc.util.option;

import org.jetbrains.annotations.NotNull;

/**
 * Represents the visibility of chat messages.
 */
public enum ChatVisibility {

    /**
     * All chat messages are visible.
     */
    FULL,

    /**
     * Only chat messages from players that are in the same team are visible.
     */
    SYSTEM,

    /**
     * No chat messages are visible.
     */
    HIDDEN

    ;

    /**
     * Gets the ChatVisibility by its ordinal.
     * @param ordinal
     * @return ChatVisibility by ordinal
     */
    @NotNull
    public static ChatVisibility byOrdinal(int ordinal) {
        return values()[ordinal];
    }

}
