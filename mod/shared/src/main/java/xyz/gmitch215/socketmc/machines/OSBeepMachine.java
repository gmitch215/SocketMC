package xyz.gmitch215.socketmc.machines;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.util.tinyfd.TinyFileDialogs;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;

@InstructionId(Instruction.OS_BEEP)
public final class OSBeepMachine implements Machine {

    public static final OSBeepMachine MACHINE = new OSBeepMachine();

    private OSBeepMachine() {}

    @Override
    public void onInstruction(@NotNull Instruction instruction) throws Exception {
        TinyFileDialogs.tinyfd_beep();
    }
}
