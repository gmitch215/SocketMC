package xyz.gmitch215.socketmc.fabric.machines;

import xyz.gmitch215.socketmc.SocketMC;
import xyz.gmitch215.socketmc.fabric.FabricSocketMC;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import org.jetbrains.annotations.NotNull;

@InstructionId(Instruction.PING)
public final class PingMachine implements Machine {

    public static final PingMachine MACHINE = new PingMachine();

    private PingMachine() {}

    @Override
    public void onInstruction(@NotNull Instruction instruction) {
        SocketMC.LOGGER.info("Received ping instruction");

        if (!FabricSocketMC.eventsEnabled)
            FabricSocketMC.eventsEnabled = true;
    }

}
