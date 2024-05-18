package me.gamercoder215.socketmc.util.render

import me.gamercoder215.socketmc.instruction.util.render.Vertex

/**
 * Creates a new [Vertex].
 * @param x The x coordinate.
 * @param y The y coordinate.
 * @param zIndex The z-index of the Vertex.
 * @return [Vertex] with the specified coordinates and z-index.
 */
fun Vertex(x: Int = 0, y: Int = 0, zIndex: Int = 0) = Vertex(x, y, zIndex)