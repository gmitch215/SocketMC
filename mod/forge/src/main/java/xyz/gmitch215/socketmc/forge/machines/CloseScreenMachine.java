package xyz.gmitch215.socketmc.forge.machines;

import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.forge.ForgeSocketMC;

@InstructionId(Instruction.CLOSE_SCREEN)
public final class CloseScreenMachine implements Machine {

    public static final CloseScreenMachine MACHINE = new CloseScreenMachine();

    private CloseScreenMachine() {}

    @Override
    public void onInstruction(@NotNull Instruction instruction) throws Exception {
        ForgeSocketMC.minecraft.execute(() -> ForgeSocketMC.minecraft.setScreen(null));
    }
}
