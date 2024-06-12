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
     * <p>Permission to manage the GUI.</p>
     * <p>This permission includes everything used to manage what you see, confined to the game's operations. For example,
     * this permission includes drawing on the HUD, managing the player's perspective camera, and drawing objects in the world.</p>
     * <p>Everything included in {@link #USE_SCREENS} is ignored by this permission.</p>
     */
    USE_GUI(true),

    /**
     * Permission to open and close default and user-generated screens.
     */
    USE_SCREENS(true),

    /**
     * Permission to use the audio system.
     */
    USE_AUDIO(true),

    /**
     * Permission to open {@code http://} and {@code https://} links.
     */
    OPEN_LINKS(true),

    /**
     * <p>Permission to use external applications and integrations.</p>
     * <p>This permission also grants using non-web links speciifed by {@link #OPEN_LINKS}, such as {@code mailto:} links.</p>
     */
    EXTERNAL_APPLICATIONS(false)

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
