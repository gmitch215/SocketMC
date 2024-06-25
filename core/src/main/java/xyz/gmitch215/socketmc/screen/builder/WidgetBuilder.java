package xyz.gmitch215.socketmc.screen.builder;

import xyz.gmitch215.socketmc.screen.ui.AbstractWidget;
import xyz.gmitch215.socketmc.util.ElementBounds;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a builder for creating an {@link AbstractWidget} type.
 * @param <B> This builder type.
 * @param <T> The type of widget to build.
 */
@SuppressWarnings("unchecked")
public abstract class WidgetBuilder<B extends WidgetBuilder<B, T>, T extends AbstractWidget> {

    int x, y, width, height;

    WidgetBuilder() {}

    /**
     * Sets the x position of the widget.
     * @param x The x position.
     * @return this class, for chaining
     * @throws IllegalArgumentException if x is negative
     */
    @NotNull
    public B x(int x) throws IllegalArgumentException {
        if (x < 0) throw new IllegalArgumentException("x cannot be negative");

        this.x = x;
        return (B) this;
    }

    /**
     * Sets the y position of the widget.
     * @param y The y position.
     * @return this class, for chaining
     * @throws IllegalArgumentException if y is negative
     */
    @NotNull
    public B y(int y) throws IllegalArgumentException {
        if (y < 0) throw new IllegalArgumentException("y cannot be negative");

        this.y = y;
        return (B) this;
    }

    /**
     * Sets the position of the widget.
     * @param x The x position.
     * @param y The y position.
     * @return this class, for chaining
     * @throws IllegalArgumentException if x or y is negative
     */
    @NotNull
    public B pos(int x, int y) throws IllegalArgumentException {
        if (x < 0) throw new IllegalArgumentException("x cannot be negative");
        if (y < 0) throw new IllegalArgumentException("y cannot be negative");

        this.x = x;
        this.y = y;
        return (B) this;
    }

    /**
     * Sets the width of the widget.
     * @param width The width.
     * @return this class, for chaining
     * @throws IllegalArgumentException if width is negative
     */
    @NotNull
    public B width(int width) throws IllegalArgumentException {
        if (width < 0) throw new IllegalArgumentException("width cannot be negative");

        this.width = width;
        return (B) this;
    }

    /**
     * Sets the height of the widget.
     * @param height The height.
     * @return this class, for chaining
     * @throws IllegalArgumentException if height is negative
     */
    @NotNull
    public B height(int height) throws IllegalArgumentException {
        if (height < 0) throw new IllegalArgumentException("height cannot be negative");

        this.height = height;
        return (B) this;
    }

    /**
     * Sets the size of the widget.
     * @param width The width.
     * @param height The height.
     * @return this class, for chaining
     * @throws IllegalArgumentException if width or height is negative
     */
    @NotNull
    public B size(int width, int height) throws IllegalArgumentException {
        if (width < 0) throw new IllegalArgumentException("width cannot be negative");
        if (height < 0) throw new IllegalArgumentException("height cannot be negative");

        this.width = width;
        this.height = height;
        return (B) this;
    }

    /**
     * Sets the bounds of the widget.
     * @param x The x position.
     * @param y The y position.
     * @param width The width.
     * @param height The height.
     * @return this class, for chaining
     * @throws IllegalArgumentException if x, y, width, or height is negative
     */
    @NotNull
    public B bounds(int x, int y, int width, int height) throws IllegalArgumentException {
        return pos(x, y).size(width, height);
    }

    /**
     * Sets the bounds of the widget.
     * @param bounds The bounds.
     * @return this class, for chaining
     * @throws IllegalArgumentException if bounds is null
     */
    @NotNull
    public B bounds(@NotNull ElementBounds bounds) throws IllegalArgumentException {
        if (bounds == null) throw new IllegalArgumentException("bounds cannot be null");
        return bounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
    }

    /**
     * Builds the widget.
     * @return The built widget.
     */
    @NotNull
    public abstract T build();

}
