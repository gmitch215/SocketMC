package me.gamercoder215.socketmc.instruction.util.render;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Utility class for representing a vertex in 2D space, with a Z index.
 */
public final class Vertex implements Serializable {

    @Serial
    private static final long serialVersionUID = -3611231838349785190L;

    /**
     * Represents a Vertex at the origin with a Z index of 0.
     */
    public static final Vertex ZERO = new Vertex(0, 0, 0);

    /**
     * Represents a Vertex at the origin with the lowest possible Z index.
     */
    public static final Vertex ZERO_BACK = new Vertex(0, 0, Integer.MIN_VALUE);

    private int x;
    private int y;
    private int z;

    /**
     * Creates a new vertex at the origin.
     */
    public Vertex() {
        this(0, 0, 0);
    }

    /**
     * Creates a new vertex.
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    public Vertex(int x, int y) {
        this(x, y, 0);
    }

    /**
     * Creates a new vertex.
     * @param x The X coordinate.
     * @param y The Y coordinate.
     * @param zIndex The Z index on the screen.
     */
    public Vertex(int x, int y, int zIndex) {
        this.x = x;
        this.y = y;
        this.z = zIndex;
    }

    /**
     * Gets the X coordinate.
     * @return The X coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X coordinate.
     * @param x The X coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the Y coordinate.
     * @return The Y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y coordinate.
     * @param y The Y coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the Z index on the screen.
     *
     * Z index is used to determine the order in which shapes are drawn. Shapes with a higher Z index are drawn on top of shapes with a lower Z index.
     * @return The Z index.
     */
    public int getZ() {
        return z;
    }

    /**
     * Sets the Z index.
     * @param zIndex The Z index.
     * @see #getZ()
     */
    public void setZ(int zIndex) {
        this.z = zIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex vertex)) return false;
        return x == vertex.x && y == vertex.y && z == vertex.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
