package me.gamercoder215.socketmc.config;

import me.gamercoder215.socketmc.instruction.Instruction;

/**
 * Represents the permissions for the SocketMC Mod.
 */
public enum ModPermission {

    /**
     * The permission for the mod to be enabled and work properly. Used in essential permissions, such as {@link Instruction#ping()}.
     */
    REQUIRED(true, false),

    /**
     * Permission to draw on the HUD.
     */
    DRAW_HUD(true),

    /**
     * Permission to open and close default and user-generated screens.
     */
    USE_SCREENS(true),

    /**
     * Permission to use the audio system.
     */
    USE_AUDIO(true),

    ;

    private final boolean changeable;
    private final boolean defaultValue;

    ModPermission(boolean defaultValue) {
        this(defaultValue, true);
    }

    ModPermission(boolean defaultValue, boolean changeable) {
        this.changeable = changeable;
        this.defaultValue = defaultValue;
    }

}
