package xyz.gmitch215.socketmc.util.input;

/**
 * Represents an action that was performed on a button.
 */
public enum Action {
    /**
     * This action is performed when a button is released.
     */
    RELEASE,
    /**
     * This action is performed when a button is pressed.
     */
    PRESS,
    /**
     * This action is repeated when a button is held down (keyboard only).
     */
    REPEAT
}
