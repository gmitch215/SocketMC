package me.gamercoder215.socketmc.screen;

import me.gamercoder215.socketmc.screen.util.Tooltip;
import me.gamercoder215.socketmc.util.ElementBounds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Represents an object that can be positioned and rendered on the screen.
 */
public interface Positionable extends Serializable {

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
     * Sets the position and size of this object to match a {@link ElementBounds}.
     * @param bounds the bounds to use
     */
    default void setSize(@NotNull ElementBounds bounds) {
        setX(bounds.getX());
        setY(bounds.getY());
        setWidth(bounds.getWidth());
        setHeight(bounds.getHeight());
    }

    /**
     * Gets the tooltip for this widget.
     * @return Widget Tooltip
     */
    @Nullable
    Tooltip getTooltip();

    /**
     * Sets the tooltip for this widget.
     * @param tooltip Widget Tooltip
     */
    void setTooltip(@Nullable Tooltip tooltip);

}
