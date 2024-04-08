package me.gamercoder215.socketmc.forge.machines;

import me.gamercoder215.socketmc.forge.ForgeSocketMC;
import me.gamercoder215.socketmc.instruction.InstructionId;
import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.instruction.Machine;
import org.jetbrains.annotations.NotNull;

@InstructionId(Instruction.PING)
public final class PingMachine implements Machine {

    public static final PingMachine MACHINE = new PingMachine();

    private PingMachine() {}

    @Override
    public void onInstruction(@NotNull Instruction instruction) {
        ForgeSocketMC.LOGGER.info("Received ping instruction");
    }

}
