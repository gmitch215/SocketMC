package xyz.gmitch215.socketmc.neoforge.machines;

import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import xyz.gmitch215.socketmc.neoforge.NeoForgeSocketMC;

@InstructionId(Instruction.CLOSE_SCREEN)
public final class CloseScreenMachine implements Machine {

    public static final CloseScreenMachine MACHINE = new CloseScreenMachine();

    private CloseScreenMachine() {}

    @Override
    public void onInstruction(@NotNull Instruction instruction) throws Exception {
        NeoForgeSocketMC.minecraft.execute(() -> NeoForgeSocketMC.minecraft.setScreen(null));
    }
}
