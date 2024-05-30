package me.gamercoder215.socketmc.screen;

import me.gamercoder215.socketmc.util.Position;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an object that can be positioned and rendered on the screen.
 */
public interface Positionable extends Narratable {

    /**
     * Gets the x-coordinate of the object.
     * @return the x-coordinate
     */
    int getX();

    /**
     * Sets the x-coordinate of the object.
     * @param x the x-coordinate
     */
    void setX(int x);

    /**
     * Gets the y-coordinate of the object.
     * @return the y-coordinate
     */
    int getY();

    /**
     * Sets the y-coordinate of the object.
     * @param y the y-coordinate
     */
    void setY(int y);

    /**
     * Gets the width of the object.
     * @return the width
     */
    int getWidth();

    /**
     * Sets the width of the object.
     * @param width the width
     */
    void setWidth(int width);

    /**
     * Gets the height of the object.
     * @return the height
     */
    int getHeight();

    /**
     * Sets the height of the object.
     * @param height the height
     */
    void setHeight(int height);

    /**
     * Sets the position and size of this object to match a {@link Position}.
     * @param position the position to use
     */
    default void setSize(@NotNull Position position) {
        setX(position.getX());
        setY(position.getY());
        setWidth(position.getWidth());
        setHeight(position.getHeight());
    }

}
