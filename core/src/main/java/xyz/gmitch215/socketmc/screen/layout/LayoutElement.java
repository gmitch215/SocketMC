package xyz.gmitch215.socketmc.screen.layout;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.gmitch215.socketmc.screen.ui.AbstractWidget;
import xyz.gmitch215.socketmc.util.ElementBounds;

import java.util.function.Consumer;

/**
 * Represents an element that can be added to a layout.
 */
public interface LayoutElement {

    /**
     * Gets the x-coordinate of this object.
     * @return the x-coordinate
     */
    int getX();

    /**
     * Sets the x-coordinate of this object.
     * @param x the x-coordinate
     */
    void setX(int x);

    /**
     * Gets the y-coordinate of this object.
     * @return the y-coordinate
     */
    int getY();

    /**
     * Sets the y-coordinate of this object.
     * @param y the y-coordinate
     */
    void setY(int y);

    /**
     * Sets the position of this object.
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    default void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }

    /**
     * Gets the width of this object.
     * @return the width
     */
    int getWidth();

    /**
     * Sets the width of this object.
     * @param width the width
     */
    void setWidth(int width);

    /**
     * Gets the height of this object.
     * @return the height
     */
    int getHeight();

    /**
     * Sets the height of this object.
     * @param height the height
     */
    void setHeight(int height);

    /**
     * Sets the size of this object.
     * @param width the width
     * @param height the height
     */
    default void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }
    
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
     * Sets the position and size of this object.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     */
    default void setSize(int x, int y, int width, int height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    /**
     * Visits this object, applying a function to it.
     * @param visitor the visitor
     */
    void visitWidget(@Nullable Consumer<AbstractWidget> visitor);
}
