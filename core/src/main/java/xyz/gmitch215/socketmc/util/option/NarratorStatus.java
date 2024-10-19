package xyz.gmitch215.socketmc.util.option;

/**
 * Represents the status of the narrator.
 */
public enum NarratorStatus {

    /**
     * The narrator is off.
     */
    OFF,

    /**
     * The narrator speaks on everything.
     */
    ALL,

    /**
     * The narrator speaks on chat messages.
     */
    CHAT,

    /**
     * The narrator speaks on system messages.
     */
    SYSTEM

    ;

    /**
     * Gets the NarratorStatus by its ordinal.
     * @param ordinal the ordinal
     * @return NarratorStatus by ordinal
     */
    public static NarratorStatus byOrdinal(int ordinal) {
        return values()[ordinal];
    }

}
