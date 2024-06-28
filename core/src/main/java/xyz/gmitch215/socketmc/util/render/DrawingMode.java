package xyz.gmitch215.socketmc.util.render;

/**
 * Represents the drawing mode for a shape.
 */
public enum DrawingMode {

    /**
     * Each element is made up of two vertices, forming a line.
     */
    LINES,

    /**
     * The first element requires two vertices, and each subsequent element requires one more vertex. This forms a continuous line that bends at different places.
     */
    LINE_STRIP,

    /**
     * Each element requires three vertices, forming a triangle.
     */
    TRIANGLES,

    /**
     * The first element requires three vertices forming the first triangle. Each additional vertex forms a new triangle with the last two vertices.
     */
    TRIANGLE_STRIP,

    /**
     * The first element requires three vertices forming the first triangle. Each additional vertex forms a new triangle with the first vertex and the last vertex.
     */
    TRIANGLE_FAN,

    /**
     * Each element requires four vertices, forming a quadrilateral.
     */
    QUADRILATERALS

}
