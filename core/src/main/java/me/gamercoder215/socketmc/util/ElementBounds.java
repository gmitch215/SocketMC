package me.gamercoder215.socketmc.util;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Utility class for 2D sizeable and placeable objects.
 */
public final class ElementBounds implements Serializable {

    @Serial
    private static final long serialVersionUID = 1168470204360680611L;

    private int x, y, width, height;

    /**
     * Constructs new bounds at (0, 0).
     * @param width the width
     * @param height the height
     */
    public ElementBounds(int width, int height) {
        this(0, 0, width, height);
    }

    /**
     * Constructs new bounds.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @throws IllegalArgumentException if coordinates or dimensions are negative
     */
    public ElementBounds(int x, int y, int width, int height) throws IllegalArgumentException {
        if (width < 0 || height < 0) throw new IllegalArgumentException("Width and height must be non-negative");
        if (x < 0 || y < 0) throw new IllegalArgumentException("X and Y coordinates must be non-negative");

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the x-coordinate of the bounds.
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the bounds.
     * @param x the x-coordinate
     */
    public void setX(int x) {
        if (x < 0) throw new IllegalArgumentException("X coordinate must be non-negative");
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the bounds.
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the bounds.
     * @param y the y-coordinate
     */
    public void setY(int y) {
        if (y < 0) throw new IllegalArgumentException("Y coordinate must be non-negative");
        this.y = y;
    }

    /**
     * Translates the object by the given amount.
     * @param x the x-translation
     * @param y the y-translation
     */
    public void translate(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }

    /**
     * Gets the width of the bounds.
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the bounds.
     * @param width the width
     */
    public void setWidth(int width) {
        if (width < 0) throw new IllegalArgumentException("Width must be non-negative");
        this.width = width;
    }

    /**
     * Gets the height of the bounds.
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the bounds.
     * @param height the height
     */
    public void setHeight(int height) {
        if (height < 0) throw new IllegalArgumentException("Height must be non-negative");
        this.height = height;
    }

    /**
     * Scales the object by the given factor.
     * @param factor the scaling factor
     */
    public void scale(double factor) {
        setWidth((int) (getWidth() * factor));
        setHeight((int) (getHeight() * factor));
    }

    /**
     * Scales the object by the given factor.
     * @param factor the scaling factor
     */
    public void scale(int factor) {
        scale((double) factor);
    }

    /**
     * Checks if the object contains the given point.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return true if the point is contained, false otherwise
     */
    public boolean contains(int x, int y) {
        return x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight();
    }

    /**
     * Gets the x-coordinate of the center of the bounds.
     * @return the x-coordinate of the center
     */
    public int getCenterX() {
        return getX() + getWidth() / 2;
    }

    /**
     * Gets the y-coordinate of the center of the bounds.
     * @return the y-coordinate of the center
     */
    public int getCenterY() {
        return getY() + getHeight() / 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElementBounds bounds)) return false;
        return x == bounds.x && y == bounds.y && width == bounds.width && height == bounds.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height);
    }

    @Override
    public String toString() {
        return "ElementBounds{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
