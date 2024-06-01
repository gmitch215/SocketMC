package me.gamercoder215.socketmc.forge.machines;

import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.instruction.InstructionId;
import me.gamercoder215.socketmc.instruction.Machine;
import org.jetbrains.annotations.NotNull;

import static me.gamercoder215.socketmc.forge.ForgeSocketMC.minecraft;

@InstructionId(Instruction.CLOSE_SCREEN)
public final class CloseScreenMachine implements Machine {

    public static final CloseScreenMachine MACHINE = new CloseScreenMachine();

    private CloseScreenMachine() {}

    @Override
    public void onInstruction(@NotNull Instruction instruction) throws Exception {
        minecraft.execute(() -> minecraft.setScreen(null));
    }
}
