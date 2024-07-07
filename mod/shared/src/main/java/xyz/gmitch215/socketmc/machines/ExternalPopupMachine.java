package xyz.gmitch215.socketmc.machines;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.util.tinyfd.TinyFileDialogs;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;

@InstructionId(Instruction.EXTERNAL_WINDOW_POPUP)
public final class ExternalPopupMachine implements Machine {

    public static final ExternalPopupMachine MACHINE = new ExternalPopupMachine();

    private ExternalPopupMachine() {}

    @Override
    public void onInstruction(@NotNull Instruction instruction) {
        String title = instruction.firstStringParameter();
        String message = instruction.stringParameter(1);
        String icon = instruction.lastStringParameter();

        TinyFileDialogs.tinyfd_notifyPopup(title, message, icon);
    }
}
