package xyz.gmitch215.socketmc.screen.util;

import xyz.gmitch215.socketmc.util.Identifier;

/**
 * Represents a collection of {@link Identifier} objects that represent sprites in the game.
 */
public final class Sprites {

    private Sprites() { throw new UnsupportedOperationException("This class cannot be instantiated"); }

    /**
     * Represents the sprite for the crosshair.
     */
    public static final Identifier CROSSHAIR = Identifier.minecraft("hud/crosshair");

    /**
     * Represents the sprite for the crosshair attack indicator when it is full.
     */
    public static final Identifier CROSSHAIR_ATTACK_INDICATOR_FULL = Identifier.minecraft("hud/crosshair_attack_indicator_full");

    /**
     * Represents the sprite for the background of the crosshair attack indicator.
     */
    public static final Identifier CROSSHAIR_ATTACK_INDICATOR_BACKGROUND = Identifier.minecraft("hud/crosshair_attack_indicator_background");

    /**
     * Represents the sprite for the progress of the crosshair attack indicator.
     */
    public static final Identifier CROSSHAIR_ATTACK_INDICATOR_PROGRESS = Identifier.minecraft("hud/crosshair_attack_indicator_progress");

    /**
     * Represents the sprite for the ambient effect background.
     */
    public static final Identifier EFFECT_BACKGROUND_AMBIENT = Identifier.minecraft("hud/effect_background_ambient");

    /**
     * Represents the sprite for the background of the effect.
     */
    public static final Identifier EFFECT_BACKGROUND = Identifier.minecraft("hud/effect_background");

    /**
     * Represents the sprite for the hotbar.
     */
    public static final Identifier HOTBAR = Identifier.minecraft("hud/hotbar");

    /**
     * Represents the sprite for the hotbar selection.
     */
    public static final Identifier HOTBAR_SELECTION = Identifier.minecraft("hud/hotbar_selection");

    /**
     * Represents the sprite for the hotbar offhand on the left.
     */
    public static final Identifier HOTBAR_OFFHAND_LEFT = Identifier.minecraft("hud/hotbar_offhand_left");

    /**
     * Represents the sprite for the hotbar offhand on the right.
     */
    public static final Identifier HOTBAR_OFFHAND_RIGHT = Identifier.minecraft("hud/hotbar_offhand_right");

    /**
     * Represents the sprite for the background of the hotbar attack indicator.
     */
    public static final Identifier HOTBAR_ATTACK_INDICATOR_BACKGROUND = Identifier.minecraft("hud/hotbar_attack_indicator_background");

    /**
     * Represents the sprite for the progress of the hotbar attack indicator.
     */
    public static final Identifier HOTBAR_ATTACK_INDICATOR_PROGRESS = Identifier.minecraft("hud/hotbar_attack_indicator_progress");

    /**
     * Represents the sprite for the background of the jumping bar.
     */
    public static final Identifier JUMP_BAR_BACKGROUND = Identifier.minecraft("hud/jump_bar_background");

    /**
     * Represents the sprite for the progress of the jumping bar.
     */
    public static final Identifier JUMP_BAR_COOLDOWN = Identifier.minecraft("hud/jump_bar_cooldown");

    /**
     * Represents the sprite for the progress of the jumping bar.
     */
    public static final Identifier JUMP_BAR_PROGRESS = Identifier.minecraft("hud/jump_bar_progress");

    /**
     * Represents the sprite for the background of the experience bar.
     */
    public static final Identifier EXPERIENCE_BAR_BACKGROUND = Identifier.minecraft("hud/experience_bar_background");

    /**
     * Represents the sprite for the progress of the experience bar.
     */
    public static final Identifier EXPERIENCE_BAR_PROGRESS = Identifier.minecraft("hud/experience_bar_progress");

    /**
     * Represents the sprite for the armor icon when it is empty.
     */
    public static final Identifier ARMOR_EMPTY = Identifier.minecraft("hud/armor_empty");

    /**
     * Represents the sprite for the armor icon when it is half full.
     */
    public static final Identifier ARMOR_HALF = Identifier.minecraft("hud/armor_half");

    /**
     * Represents the sprite for the armor icon when it is full.
     */
    public static final Identifier ARMOR_FULL = Identifier.minecraft("hud/armor_full");

    /**
     * Represents the sprite for the food icon when it is empty, and when you have the hunger effect.
     */
    public static final Identifier FOOD_EMPTY_HUNGER = Identifier.minecraft("hud/food_empty_hunger");

    /**
     * Represents the sprite for the food icon when it is half full, and when you have the hunger effect.
     */
    public static final Identifier FOOD_HALF_HUNGER = Identifier.minecraft("hud/food_half_hunger");

    /**
     * Represents the sprite for the food icon when it is full, and when you have the hunger effect.
     */
    public static final Identifier FOOD_FULL_HUNGER = Identifier.minecraft("hud/food_full_hunger");

    /**
     * Represents the sprite for the food icon when it is empty.
     */
    public static final Identifier FOOD_EMPTY = Identifier.minecraft("hud/food_empty");

    /**
     * Represents the sprite for the food icon when it is half full.
     */
    public static final Identifier FOOD_HALF = Identifier.minecraft("hud/food_half");

    /**
     * Represents the sprite for the food icon when it is full.
     */
    public static final Identifier FOOD_FULL = Identifier.minecraft("hud/food_full");

    /**
     * Represents the sprite for the air icon when underwater.
     */
    public static final Identifier AIR = Identifier.minecraft("hud/air");

    /**
     * Represents the sprite for the air icon when it is about to burst.
     */
    public static final Identifier AIR_BURSTING = Identifier.minecraft("hud/air_bursting");

    /**
     * Represents the sprite for the health icon when you are in a vehicle and it is empty.
     */
    public static final Identifier HEART_VEHICLE_CONTAINER = Identifier.minecraft("hud/heart/vehicle_container");

    /**
     * Represents the sprite for the health icon when you are in a vehicle and it is full.
     */
    public static final Identifier HEART_VEHICLE_FULL = Identifier.minecraft("hud/heart/vehicle_full");

    /**
     * Represents the sprite for the health icon when you are in a vehicle and it is half full.
     */
    public static final Identifier HEART_VEHICLE_HALF = Identifier.minecraft("hud/heart/vehicle_half");

    /**
     * Represents the sprite for the info icon used in notifications.
     */
    public static final Identifier INFO = Identifier.minecraft("icon/info");

    /**
     * Represents the sprite for the new realm icon.
     */
    public static final Identifier NEW_REALM = Identifier.minecraft("icon/new_realm");

    /**
     * Represents the sprite for the icon that indicates that a realm is expired.
     */
    public static final Identifier REALM_EXPIRED = Identifier.minecraft("realm_status/expired");

    /**
     * Represents the sprite for the icon that indicates that a realm is about to expire.
     */
    public static final Identifier REALM_EXPIRES_SOON = Identifier.minecraft("realm_status/expires_soon");

    /**
     * Represents the sprite for the icon that indicates that a realm is open.
     */
    public static final Identifier REALM_OPEN = Identifier.minecraft("realm_status/open");

    /**
     * Represents the sprite for the icon that indicates that a realm is closed.
     */
    public static final Identifier REALM_CLOSED = Identifier.minecraft("realm_status/closed");

    /**
     * Represents the sprite for the invite icon used in realms.
     */
    public static final Identifier INVITE = Identifier.minecraft("icon/invite");

    /**
     * Represents the sprite for the news icon used in realms.
     */
    public static final Identifier NEWS = Identifier.minecraft("icon/news");

    /**
     * Represents the sprite for the background used in system toasts.
     */
    public static final Identifier TOAST_BACKGROUND = Identifier.minecraft("toast/system");
    
}
