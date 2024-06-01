package me.gamercoder215.socketmc.util.render;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents a Render buffer for drawing complex shapes.
 */
public final class RenderBuffer implements Serializable {

    @Serial
    private static final long serialVersionUID = 4168784210322281868L;

    private final DrawingMode mode;

    private final Map<Vertex, Integer> vertices = new HashMap<>();

    private RenderBuffer(DrawingMode mode) {
        this.mode = mode;
    }

    /**
     * Gets the drawing mode for the buffer.
     * @return The drawing mode.
     */
    @NotNull
    public DrawingMode getMode() {
        return mode;
    }

    /**
     * Gets all vertices in the buffer.
     * @return Buffer Vertices
     */
    @NotNull
    public Set<Vertex> getVertices() {
        return vertices.keySet();
    }

    /**
     * Gets the color of a vertex in the buffer in ARGB format.
     * @param vertex The vertex to get the color of.
     * @return The color of the vertex.
     */
    public int getColor(@NotNull Vertex vertex) {
        return vertices.get(vertex);
    }

    /**
     * Creats a new builder for a {@link RenderBuffer}.
     * @param mode The drawing mode for the buffer.
     * @return RenderBuffer Builder
     */
    @NotNull
    public static Builder builder(@NotNull DrawingMode mode) {
        return new Builder(mode);
    }

    /**
     * Represents a builder for a {@link RenderBuffer}.
     */
    public static final class Builder {
        final DrawingMode mode;
        final Map<Vertex, Integer> vertices = new HashMap<>();

        private Builder(DrawingMode mode) {
            this.mode = mode;
        }

        /**
         * Adds a vertex to the buffer with a specified color.
         * @param vertex The vertex to add.
         * @param color The color of the vertex.
         * @return this class, for chaining
         * @throws IllegalArgumentException If the vertex or color is null
         */
        @NotNull
        public Builder addVertex(@NotNull Vertex vertex, @NotNull Color color) throws IllegalArgumentException {
            if (vertex == null) throw new IllegalArgumentException("Vertex cannot be null");
            if (color == null) throw new IllegalArgumentException("Color cannot be null");

            vertices.put(vertex, color.getRGB() | 0xFF000000);
            return this;
        }

        /**
         * Adds a vertex to the buffer with a specified color and alpha value.
         * @param vertex The vertex to add.
         * @param color The color of the vertex.
         * @param alpha The alpha value of the vertex (0-255).
         * @return this class, for chaining
         * @throws IllegalArgumentException If the vertex or color is null, or if the alpha value is not between 0 and 255
         */
        @NotNull
        public Builder addVertex(@NotNull Vertex vertex, @NotNull Color color, int alpha) throws IllegalArgumentException {
            if (vertex == null) throw new IllegalArgumentException("Vertex cannot be null");
            if (color == null) throw new IllegalArgumentException("Color cannot be null");
            if (alpha < 0 || alpha > 255) throw new IllegalArgumentException("Alpha must be between 0 and 255");

            vertices.put(vertex, color.getRGB() | (alpha << 24));
            return this;
        }

        /**
         * Adds a vertex to the buffer with a specified color and alpha value.
         * @param vertex The vertex to add.
         * @param argb The color of the vertex in ARGB format.
         * @return this class, for chaining
         * @throws IllegalArgumentException If the vertex or color is null, or if the alpha value is not between 0 and 255
         */
        @NotNull
        public Builder addVertex(@NotNull Vertex vertex, int argb) throws IllegalArgumentException {
            if (vertex == null) throw new IllegalArgumentException("Vertex cannot be null");

            vertices.put(vertex, argb);
            return this;
        }

        /**
         * Builds the buffer.
         * @return Built {@link RenderBuffer}
         */
        @NotNull
        public RenderBuffer build() {
            RenderBuffer buffer = new RenderBuffer(mode);

            buffer.vertices.putAll(vertices);
            return buffer;
        }
    }

}
