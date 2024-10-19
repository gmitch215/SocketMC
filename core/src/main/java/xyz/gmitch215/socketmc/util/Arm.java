package xyz.gmitch215.socketmc.util;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a player's arm.
 */
public enum Arm {

    /**
     * Represents the left arm.
     */
    LEFT,

    /**
     * Represents the right arm.
     */
    RIGHT;

    /**
     * Returns the opposite arm.
     * @return the opposite arm
     */
    @NotNull
    public Arm opposite() {
        return this == LEFT ? RIGHT : LEFT;
    }

    /**
     * Gets the Arm by its ordinal.
     * @param ordinal
     * @return Arm by ordinal
     */
    @NotNull
    public static Arm byOrdinal(int ordinal) {
        return values()[ordinal];
    }



}
