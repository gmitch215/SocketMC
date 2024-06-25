package xyz.gmitch215.socketmc.util;

/**
 * Represents a type of input that the client has used.
 */
public enum InputType {

    /**
     * No input was used.
     */
    NONE,

    /**
     * The mouse was used.
     */
    MOUSE,

    /**
     * An arrow key was used.
     */
    KEYBOARD_ARROW,

    /**
     * The tab key was used.
     */
    KEYBOARD_TAB

    ;

    /**
     * Checks if the input type is a keyboard input.
     * @return true if the input type is a keyboard input, false otherwise
     */
    public boolean isKeyboard() {
        return this == KEYBOARD_ARROW || this == KEYBOARD_TAB;
    }

    /**
     * Checks if the input type is a mouse input.
     * @return true if the input type is a mouse input, false otherwise
     */
    public boolean isMouse() {
        return this == MOUSE;
    }

}
