package me.gamercoder215.socketmc.screen;

/**
 * Represents the background to display when opening the screen.
 */
public enum ScreenBackground {

    /**
     * The background is slightly blurred. Used in most screens.
     */
    DEFAULT,

    /**
     * No background is displayed, and the screen is transparent. Used in the Book and Quill editor.
     */
    TRANSPARENT,

    /**
     * Uses the panorama on the title screen.
     */
    PANORAMA,

    /**
     * Uses the menu background. Used in the options screen.
     */
    MENU

}
