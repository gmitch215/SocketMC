package me.gamercoder215.socketmc.instruction;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a listener for an {@link Instruction}.
 */
public interface Machine {

    /**
     * Called when the mod receives an instruction.
     * @param instruction The instruction received.
     */
    void onInstruction(@NotNull Instruction instruction);

}
