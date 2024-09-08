package xyz.gmitch215.socketmc.machines;

import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.SocketMC;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;

import java.util.UUID;

@InstructionId(Instruction.SHOW_PLAYERS)
public final class HidePlayersMachine implements Machine {

    public static final HidePlayersMachine MACHINE = new HidePlayersMachine();

    private HidePlayersMachine() {}

    @Override
    public void onInstruction(@NotNull Instruction instruction) throws Exception {
        SocketMC.INSTANCE.get().hidePlayers(instruction.getParameters(UUID.class));
    }
}
