package me.gamercoder215.socketmc.instruction.builder

import me.gamercoder215.socketmc.instruction.Instruction

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