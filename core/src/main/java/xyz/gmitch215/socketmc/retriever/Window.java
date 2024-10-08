package xyz.gmitch215.socketmc.retriever;

import java.io.Serial;
import java.io.Serializable;

/**
 * Utility class to represent the client's window information.
 */
public final class Window implements Serializable {

    @Serial
    private static final long serialVersionUID = -3424701056683784809L;

    long id;
    boolean fullscreen;
    int x;
    int y;
    int width;
    int height;
    int screenWidth;
    int screenHeight;
    int guiScaledWidth;
    int guiScaledHeight;
    double guiScale;
    int framerateLimit;
    String platform;
    int refreshRate;

    /**
     * Creates a new, empty window.
     */
    public Window() {}

    /**
     * Creates a new window with the specified parameters.
     * @param id Window ID
     * @param fullscreen Fullscreen Mode
     * @param x X Position
     * @param y Y Position
     * @param width Width
     * @param height Height
     * @param screenWidth Screen Width
     * @param screenHeight Screen Height
     * @param guiScaledWidth GUI Scaled Width
     * @param guiScaledHeight GUI Scaled Height
     * @param guiScale GUI Scale
     * @param framerateLimit Framerate Limit
     * @param platform Window Platform
     * @param refreshRate Refresh Rate
     */
    public Window(long id, boolean fullscreen, int x, int y, int width, int height, int screenWidth, int screenHeight, int guiScaledWidth, int guiScaledHeight, double guiScale, int framerateLimit, String platform, int refreshRate) {
        this.id = id;
        this.fullscreen = fullscreen;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.guiScaledWidth = guiScaledWidth;
        this.guiScaledHeight = guiScaledHeight;
        this.guiScale = guiScale;
        this.framerateLimit = framerateLimit;
        this.platform = platform;
        this.refreshRate = refreshRate;
    }

    /**
     * Gets the window ID.
     * @return Window ID
     */
    public long getId() {
        return id;
    }

    /**
     * Gets whether the window is in fullscreen mode.
     * @return True if the window is in fullscreen mode, false otherwise.
     */
    public boolean isFullscreen() {
        return fullscreen;
    }

    /**
     * Gets the x position of the window.
     * @return Window X Position
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y position of the window.
     * @return Window Y Position
     */
    public int getY() {
        return y;
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

    /**
     * Gets the width of the screen.
     * @return Screen Width
     */
    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     * Gets the height of the screen.
     * @return Screen Height
     */
    public int getScreenHeight() {
        return screenHeight;
    }

    /**
     * Gets the width of the window, scaled by the GUI.
     * @return GUI Scaled Width
     */
    public int getGuiScaledWidth() {
        return guiScaledWidth;
    }

    /**
     * Gets the height of the window, scaled by the GUI.
     * @return GUI Scaled Height
     */
    public int getGuiScaledHeight() {
        return guiScaledHeight;
    }

    /**
     * Gets the GUI scale of the window.
     * @return GUI Scale
     */
    public double getGuiScale() {
        return guiScale;
    }

    /**
     * Gets the framerate limit of the window.
     * @return Framerate Limit
     */
    public int getFramerateLimit() {
        return framerateLimit;
    }

    /**
     * <p>Gets the platform this window is currently using.</p>
     * <p>Some potential values are:</p>
     * <ul>
     *     <li>{@code win32} if on Windows</li>
     *     <li>{@code cocoa} if on macOS</li>
     *     <li>{@code x11} or {@code wayland} if on Unix</li>
     *     <li>{@code null} if inaccessible</li>
     *     <li>{@code <error>} if can't fetch the value</li>
     *     <li>{@code unknown} if not using a generally known windows API</li>
     * </ul>
     * @return Window Platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * Gets the refresh rate (in Hz) for the window.
     * @return Refresh Rate
     */
    public int getRefreshRate() {
        return refreshRate;
    }
}
