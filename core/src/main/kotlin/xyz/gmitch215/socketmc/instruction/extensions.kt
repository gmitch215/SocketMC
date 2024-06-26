package xyz.gmitch215.socketmc.instruction

import xyz.gmitch215.socketmc.instruction.Instruction
import xyz.gmitch215.socketmc.util.render.text.Text
import xyz.gmitch215.socketmc.screen.ui.AbstractButton
import xyz.gmitch215.socketmc.screen.ui.SendInstructionButton

// Instruction

/**
 * Creates a [SendInstructionButton] button with this instruction.
 * @param x The x position of the button
 * @param y The y position of the button
 * @param width The width of the button
 * @param height The height of the button
 * @param text The text of the button
 */
fun Instruction.createButton(
    x: Int,
    y: Int,
    width: Int = AbstractButton.DEFAULT_WIDTH,
    height: Int = AbstractButton.DEFAULT_HEIGHT,
    text: Text
) = SendInstructionButton(x, y, width, height, text, this)