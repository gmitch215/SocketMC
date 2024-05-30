package me.gamercoder215.socketmc.screen.ui;

import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.util.render.text.Text;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;

/**
 * Represents a button that sends an instruction when clicked.
 */
public final class SendInstructionButton extends AbstractTextButton {

    @Serial
    private static final long serialVersionUID = 6366512250407097760L;

    private Instruction instruction;

    /**
     * Constructs a new button using the default dimensions.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param message the text message
     * @param instruction the instruction to send
     * @throws IllegalArgumentException if coordinates are negative, or message/instruction is null
     */
    public SendInstructionButton(int x, int y, @NotNull Text message, @NotNull Instruction instruction) throws IllegalArgumentException {
        super(x, y, message);

        if (instruction == null) throw new IllegalArgumentException("Instruction cannot be null");
        this.instruction = instruction;
    }

    /**
     * Constructs a new button.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param message the text message
     * @param instruction the instruction to send
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or message/instruction is null
     */
    public SendInstructionButton(int x, int y, int width, int height, @NotNull Text message, @NotNull Instruction instruction) throws IllegalArgumentException {
        super(x, y, width, height, message);

        if (instruction == null) throw new IllegalArgumentException("Instruction cannot be null");
        this.instruction = instruction;
    }

    /**
     * Gets the instruction to send when this button is clicked.
     * @return Instruction
     */
    @NotNull
    public Instruction getInstruction() {
        return instruction;
    }

    /**
     * Sets the instruction to send when this button is clicked.
     * @param instruction the instruction
     * @throws IllegalArgumentException if instruction is null
     */
    public void setInstruction(@NotNull Instruction instruction) throws IllegalArgumentException {
        if (instruction == null) throw new IllegalArgumentException("Instruction cannot be null");
        this.instruction = instruction;
    }
}
