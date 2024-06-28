package xyz.gmitch215.socketmc.util.render;

/**
 * Represents the context of what is currently happening on the screen.
 */
public interface GraphicsContext {

    /**
     * Gets the current frames per second.
     * @return the fps
     */
    int getFps();

    /**
     * Gets the current width of the screen.
     * @return the width
     */
    int getScreenWidth();

    /**
     * Gets the current height of the screen.
     * @return the height
     */
    int getScreenHeight();

    /**
     * Gets the current x-coordinate of the mouse relative to Minecraft.
     * @return the x-coordinate
     */
    int getMouseX();

    /**
     * Gets the current y-coordinate of the mouse relative to Minecraft.
     * @return the y-coordinate
     */
    int getMouseY();

    /**
     * Gets the current x-coordinate of the mouse relative to the computer.
     * @return the x-coordinate
     */
    double getRawMouseX();

    /**
     * Gets the current y-coordinate of the mouse relative to the computer.
     * @return the y-coordinate
     */
    double getRawMouseY();

    /**
     * Gets the current time since the last frame, in seconds.
     * @return the partial tick
     */
    float getPartialTicks();

}
