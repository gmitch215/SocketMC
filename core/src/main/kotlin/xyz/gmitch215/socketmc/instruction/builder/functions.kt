package xyz.gmitch215.socketmc.instruction.builder

import xyz.gmitch215.socketmc.instruction.Instruction
import xyz.gmitch215.socketmc.instruction.builder.InstructionBuilder
import xyz.gmitch215.socketmc.instruction.builder.RectangleBuilder
import xyz.gmitch215.socketmc.instruction.builder.TextBuilder

/**
 * Creates a new [TextBuilder].
 * @return [TextBuilder] for [Instruction.DRAW_TEXT].
 */
fun createTextBuilder(): TextBuilder = InstructionBuilder.text()

/**
 * Creates a new [RectangleBuilder].
 * @return [RectangleBuilder] for [Instruction.DRAW_SHAPE].
 */
fun createRectangleBuilder(): RectangleBuilder = InstructionBuilder.rect()