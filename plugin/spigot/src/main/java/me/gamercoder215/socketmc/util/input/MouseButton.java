package me.gamercoder215.socketmc.util.input;

/**
 * Represents a button on a mouse.
 */
public enum MouseButton {
    /**
     * The left button on the mouse.
     */
    LEFT(0),
    /**
     * The right button on the mouse.
     */
    RIGHT(1),
    /**
     * The middle button on the mouse.
     */
    MIDDLE(2),
    /**
     * The fourth button on the mouse.
     */
    BUTTON_4(3),
    /**
     * The fifth button on the mouse.
     */
    BUTTON_5(4),
    /**
     * The sixth button on the mouse.
     */
    BUTTON_6(5),
    /**
     * The seventh button on the mouse.
     */
    BUTTON_7(6),
    /**
     * The eighth button on the mouse.
     */
    BUTTON_8(7),
    ;

    private final int code;

    MouseButton(int code) {
        this.code = code;
    }

    MouseButton(MouseButton button) {
        this.code = button.code;
    }

    /**
     * Gets the code of the button.
     * @return The code of the button.
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets the MouseButton from the code.
     * @param code The code of the button.
     * @return The MouseButton from the code.
     */
    public static MouseButton fromCode(int code) {
        for (MouseButton button : values())
            if (button.code == code)
                return button;

        return null;
    }

}
