package xyz.gmitch215.socketmc.util.option;

import org.jetbrains.annotations.NotNull;

/**
 * Represents the status of the attack indicator.
 */
public enum AttackIndicator {

    /**
     * The attack indicator is disabled.
     */
    OFF,

    /**
     * The attack indicator is displayed in the crosshair.
     */
    CROSSHAIR,

    /**
     * The attack indicator is displayed in the hotbar.
     */
    HOTBAR

    ;

    /**
     * Gets the AttackIndicator by its ordinal.
     * @param ordinal
     * @return AttackIndicator by ordinal
     */
    @NotNull
    public static AttackIndicator byOrdinal(int ordinal) {
        return values()[ordinal];
    }

}
