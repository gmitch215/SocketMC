package me.gamercoder215.socketmc.forge.machines;

import me.gamercoder215.socketmc.forge.ForgeSocketMC;
import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.instruction.InstructionId;
import me.gamercoder215.socketmc.instruction.Machine;
import org.jetbrains.annotations.NotNull;

@InstructionId(Instruction.LOG_MESSAGE)
public final class LogMessageMachine implements Machine {

    public static final LogMessageMachine MACHINE = new LogMessageMachine();

    private LogMessageMachine() {}

    @Override
    public void onInstruction(@NotNull Instruction instruction) {
        String message = instruction.parameter(0, String.class);
        ForgeSocketMC.LOGGER.info("[Log Instruction] {}", message);
    }

}
