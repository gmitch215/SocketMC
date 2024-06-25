package xyz.gmitch215.socketmc.machines;

import xyz.gmitch215.socketmc.SocketMC;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import org.jetbrains.annotations.NotNull;

@InstructionId(Instruction.LOG_MESSAGE)
public final class LogMessageMachine implements Machine {

    public static final LogMessageMachine MACHINE = new LogMessageMachine();

    private LogMessageMachine() {}

    @Override
    public void onInstruction(@NotNull Instruction instruction) {
        String message = instruction.parameter(0, String.class);
        SocketMC.LOGGER.info("[Log Instruction] {}", message);
    }

}
