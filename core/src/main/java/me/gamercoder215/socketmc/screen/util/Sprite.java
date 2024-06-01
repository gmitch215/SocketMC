package me.gamercoder215.socketmc.screen.util;

import me.gamercoder215.socketmc.screen.ui.AbstractButton;
import me.gamercoder215.socketmc.screen.ui.CheckboxButton;
import me.gamercoder215.socketmc.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a Sprite to be displayed on a Screen.
 */
public final class Sprite implements Serializable {

    // Built-In Sprites

    /**
     * Represents an {@link AbstractButton} without a specified sprite.
     */
    public static final Sprite BUTTON = new Sprite(
            "widget/button",
            "widget/button_disabled",
            "widget/button_highlighted",
            "widget/button_disabled"
    );

    /**
     * Represents a {@link CheckboxButton}.
     */
    public static final Sprite CHECKBOX = new Sprite(
            "widget/checkbox",
            "widget/checkbox_highlighted",
            "widget/checkbox_selected",
            "widget/checkbox_selected_highlighted"
    );

    /**
     * Represents the Crafting Table Recipe Book Button.
     */
    public static final Sprite RECIPE_BUTTON = new Sprite("recipe_book/button", "recipe_book/button_highlighted");

    /**
     * Represents the Cross Button used in the realms screen.
     */
    public static final Sprite CROSS_BUTTON_REALMS = new Sprite("widget/cross_button", "widget/cross_button_highlighted");

    /**
     * Represents the Tab Button for switch to a new screen.
     */
    public static final Sprite SCREEN_TAB_BUTTON = new Sprite(
            "widget/tab_selected",
            "widget/tab",
            "widget/tab_selected_highlighted",
            "widget/tab_highlighted"
    );

    // Implementation

    @Serial
    private static final long serialVersionUID = -2456183342398330796L;

    private Identifier enabled;
    private Identifier enabledHovered;
    private Identifier disabled;
    private Identifier disabledHovered;

    /**
     * Constructs a new Sprite using {@link Identifier#minecraft(String)}.
     * @param enabled Sprite to display when Enabled
     * @param disabled Sprite to display when Disabled
     */
    public Sprite(@NotNull String enabled, @NotNull String disabled) {
        this(Identifier.minecraft(enabled), Identifier.minecraft(disabled));
    }

    /**
     * Constructs a new Sprite using {@link Identifier#minecraft(String)}.
     * @param enabled Sprite to display when Enabled
     * @param enabledHovered Sprite to display when Enabled, and mouse is hovering
     * @param disabled Sprite to display when Disabled
     * @param disabledHovered Sprite to display when Disabled, and mouse is hovering
     */
    public Sprite(@NotNull String enabled, @NotNull String enabledHovered, @NotNull String disabled, @NotNull String disabledHovered) {
        this(Identifier.minecraft(enabled), Identifier.minecraft(enabledHovered), Identifier.minecraft(disabled), Identifier.minecraft(disabledHovered));
    }

    /**
     * Constructs a new Sprite.
     * @param enabled Sprite to display when Enabled
     * @param disabled Sprite to display when Disabled
     */
    public Sprite(@NotNull Identifier enabled, @NotNull Identifier disabled) {
        this(enabled, enabled, disabled, disabled);
    }

    /**
     * Constructs a new Sprite.
     * @param enabled Sprite to display when Enabled
     * @param enabledHovered Sprite to display when Enabled, and mouse is hovering
     * @param disabled Sprite to display when Disabled
     * @param disabledHovered Sprite to display when Disabled, and mouse is hovering
     * @throws IllegalArgumentException if any Identifier is null
     */
    public Sprite(@NotNull Identifier enabled, @NotNull Identifier enabledHovered, @NotNull Identifier disabled, @NotNull Identifier disabledHovered)
            throws IllegalArgumentException {
        if (enabled == null) throw new IllegalArgumentException("Enabled Identifier cannot be null");
        if (enabledHovered == null) throw new IllegalArgumentException("Enabled Focused Identifier cannot be null");
        if (disabled == null) throw new IllegalArgumentException("Disabled Identifier cannot be null");
        if (disabledHovered == null) throw new IllegalArgumentException("Disabled Focused Identifier cannot be null");

        this.enabled = enabled;
        this.enabledHovered = enabledHovered;
        this.disabled = disabled;
        this.disabledHovered = disabledHovered;
    }

    /**
     * Gets the Identifier for the Sprite when Enabled.
     * @return Sprite Enabled Identifier
     */
    @NotNull
    public Identifier getEnabled() {
        return enabled;
    }

    /**
     * Sets the Identifier for the Sprite when Enabled.
     * @param enabled Sprite Enabled Identifier
     * @throws IllegalArgumentException if enabled is null
     */
    public void setEnabled(@NotNull Identifier enabled) throws IllegalArgumentException {
        if (enabled == null) throw new IllegalArgumentException("Enabled Identifier cannot be null");
        this.enabled = enabled;
    }

    /**
     * Gets the Identifier for the Sprite when Enabled, and mouse is hovering.
     * @return Sprite Enabled Focused Identifier
     */
    @NotNull
    public Identifier getEnabledHovered() {
        return enabledHovered;
    }

    /**
     * Sets the Identifier for the Sprite when Enabled, and mouse is hovering.
     * @param enabledHovered Sprite Enabled Focused Identifier
     * @throws IllegalArgumentException if enabledFocused is null
     */
    public void setEnabledHovered(@NotNull Identifier enabledHovered) throws IllegalArgumentException {
        if (enabledHovered == null) throw new IllegalArgumentException("Enabled Hovered Identifier cannot be null");
        this.enabledHovered = enabledHovered;
    }

    /**
     * Gets the Identifier for the Sprite when Disabled.
     * @return Sprite Disabled Identifier
     */
    @NotNull
    public Identifier getDisabled() {
        return disabled;
    }

    /**
     * Sets the Identifier for the Sprite when Disabled.
     * @param disabled Sprite Disabled Identifier
     * @throws IllegalArgumentException if disabled is null
     */
    public void setDisabled(@NotNull Identifier disabled) throws IllegalArgumentException {
        if (disabled == null) throw new IllegalArgumentException("Disabled Identifier cannot be null");
        this.disabled = disabled;
    }

    /**
     * Gets the Identifier for the Sprite when Disabled, and mouse is hovering.
     * @return Sprite Disabled Focused Identifier
     */
    @NotNull
    public Identifier getDisabledHovered() {
        return disabledHovered;
    }

    /**
     * Sets the Identifier for the Sprite when Disabled, and mouse is hovering.
     * @param disabledHovered Sprite Disabled Focused Identifier
     * @throws IllegalArgumentException if disabledFocused is null
     */
    @NotNull
    public void setDisabledHovered(@NotNull Identifier disabledHovered) throws IllegalArgumentException {
        if (disabledHovered == null) throw new IllegalArgumentException("Disabled Hovered Identifier cannot be null");
        this.disabledHovered = disabledHovered;
    }
}
