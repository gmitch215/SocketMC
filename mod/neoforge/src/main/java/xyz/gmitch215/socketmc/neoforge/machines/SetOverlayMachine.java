package xyz.gmitch215.socketmc.neoforge.machines;

import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import xyz.gmitch215.socketmc.neoforge.screen.NeoForgeOverlay;
import xyz.gmitch215.socketmc.screen.Overlay;

import static xyz.gmitch215.socketmc.neoforge.NeoForgeSocketMC.minecraft;

@InstructionId(Instruction.SET_OVERLAY)
public final class SetOverlayMachine implements Machine {

    public static final SetOverlayMachine MACHINE = new SetOverlayMachine();

    private SetOverlayMachine() {}

    @Override
    public void onInstruction(Instruction instruction) {
        Object overlay = instruction.firstParameter();

        if (overlay == null)
            minecraft.setOverlay(null);
        else
            minecraft.setOverlay(new NeoForgeOverlay((Overlay) overlay));
    }

}

