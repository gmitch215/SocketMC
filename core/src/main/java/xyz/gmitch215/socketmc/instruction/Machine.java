package xyz.gmitch215.socketmc.instruction;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a listener for an {@link Instruction}.
 */
public interface Machine {

    /**
     * Called when the mod receives an instruction.
     * @param instruction The instruction received.
     * @throws Exception If an error occurs while processing the instruction.
     */
    void onInstruction(@NotNull Instruction instruction) throws Exception;

}
