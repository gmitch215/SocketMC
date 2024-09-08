package xyz.gmitch215.socketmc.config;

import xyz.gmitch215.socketmc.instruction.Instruction;
import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.retriever.RetrieverType;

/**
 * Represents the permissions for the SocketMC Mod.
 */
public enum ModPermission {

    /**
     * The permission for the mod to be enabled and work properly. Used in essential permissions, such as {@link Instruction#ping()}, and retrievers that cannot be disabled, such as {@link RetrieverType#PLUGIN_PERMISSIONS}.
     */
    REQUIRED(true, false),

    /**
     * <p>Permission to manage the GUI.</p>
     * <p>This permission includes everything used to manage what you see, confined to the game's operations. For example,
     * this permission includes drawing on the HUD, managing the player's perspective camera, and drawing objects in the world.</p>
     * <p>Everything included in {@link #USE_SCREENS} is ignored by this permission.</p>
     * <p>This permission is also used for retrievers that require input, such as {@link RetrieverType#LAST_INPUT_TYPE}.</p>
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
    EXTERNAL_APPLICATIONS(false),

    /**
     * <p>Permission to read various system properties, such as memory usage.</p>
     * <p>{@link RetrieverType#OPERATING_SYSTEM} is not included, which is marked with {@link #REQUIRED}.</p>
     */
    READ_SYSTEM_PROPERTIES(false),

    /**
     * Permission to read various GUI properties, such as window information and the game's paused state.
     */
    READ_GUI_PROPERTIES(true),

    /**
     * Permission to read various client game properties, such as command history and in-game information.
     */
    READ_GAME_PROPERTIES(true),

    /**
     * Permission to change various soft game preferences, such as social interactions.
     */
    CHANGE_GAME_PREFERENCES(false),

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

    /**
     * Gets whether the permission can be changed by the user.
     * @return whether the permission can be changed by the user
     */
    public boolean isChangeable() {
        return changeable;
    }

    /**
     * Returns the default value of the permission.
     * @return the default value of the permission
     */
    public boolean getDefaultValue() {
        return defaultValue;
    }

    /**
     * Returns the translation key for the permission.
     * @return the key for the permission
     */
    @NotNull
    public String getKey() {
        return "gui.socketmc.permission." + name().toLowerCase();
    }

    /**
     * Returns the translation key for the permission's tooltip.
     * @return the tooltip for the permission
     */
    @NotNull
    public String getTooltip() {
        return "gui.socketmc.permission." + name().toLowerCase() + ".tooltip";
    }

}
