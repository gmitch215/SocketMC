package me.gamercoder215.socketmc.instruction.builder;

import me.gamercoder215.socketmc.instruction.Instruction;
import org.jetbrains.annotations.NotNull;

/**
 * Represents utility builders for creating {@link Instruction} classes.
 */
@FunctionalInterface
public interface InstructionBuilder {

    // Abstraction

    /**
     * Builds the instruction.
     * @return Constructed Instruction for this Builder
     */
    @NotNull
    Instruction build();

    // Creators

    /**
     * Creates a new builder for {@link Instruction#DRAW_TEXT}.
     * @return Instruction Builder
     */
    @NotNull
    static TextBuilder text() {
        return new TextBuilder();
    }

    /**
     * Creates a new builder for {@link Instruction#DRAW_SHAPE}.
     * @return Instruction Builder
     */
    @NotNull
    static RectangleBuilder rect() {
        return new RectangleBuilder();
    }

    /**
     * Creates a new builder for {@link Instruction#DRAW_TEXTURE}.
     * @return Instruction Builder
     */
    @NotNull
    static TextureBuilder texture() {
        return new TextureBuilder();
    }

}
