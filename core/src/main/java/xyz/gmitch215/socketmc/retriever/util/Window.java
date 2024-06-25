package xyz.gmitch215.socketmc.retriever.util;

import java.io.Serial;
import java.io.Serializable;

/**
 * Utility class to represent the client's window information.
 */
public final class Window implements Serializable {

    @Serial
    private static final long serialVersionUID = -3424701056683784809L;

    private final int id;
    private final int width;
    private final int height;

    /**
     * Constructs a new window instance.
     * @param id The window ID
     * @param width The width of the window
     * @param height The height of the window
     */
    public Window(int id, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the window ID.
     * @return Window ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the width of the window.
     * @return Window Width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the window.
     * @return Window Height
     */
    public int getHeight() {
        return height;
    }

}
