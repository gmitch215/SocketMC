package xyz.gmitch215.socketmc.screen;

import xyz.gmitch215.socketmc.screen.layout.LayoutElement;
import xyz.gmitch215.socketmc.screen.util.Tooltip;
import xyz.gmitch215.socketmc.util.ElementBounds;
import xyz.gmitch215.socketmc.util.SerializableConsumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Set;

/**
 * Represents an object that can be positioned and rendered on the screen.
 */
public interface Positionable extends LayoutElement, Serializable {

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

    /**
     * Checks if this object is in the same position as another object.
     * @param other the other object
     * @return true if the objects are in the same position
     */
    default boolean inSamePosition(@NotNull Positionable other) {
        return getX() == other.getX() && getY() == other.getY();
    }

    /**
     * Adds an on-click listener to this widget.
     * @param listener the listener
     * @see SerializableConsumer
     */
    void onClick(@NotNull SerializableConsumer<Positionable> listener);

    /**
     * Clears all listeners from this widget.
     */
    void clearListeners();

    /**
     * Gets an immutable copy of the listeners for this widget.
     * @return the listeners
     */
    @NotNull
    Set<SerializableConsumer<Positionable>> getListeners();

}
