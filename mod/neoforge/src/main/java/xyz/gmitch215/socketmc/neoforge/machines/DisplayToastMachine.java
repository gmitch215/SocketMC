package xyz.gmitch215.socketmc.neoforge.machines;

import net.minecraft.client.gui.components.toasts.Toast;
import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import xyz.gmitch215.socketmc.neoforge.screen.NeoForgeScreenUtil;

import static xyz.gmitch215.socketmc.neoforge.NeoForgeSocketMC.minecraft;


@InstructionId(Instruction.DISPLAY_TOAST)
public final class DisplayToastMachine implements Machine {

    public static final DisplayToastMachine MACHINE = new DisplayToastMachine();

    private DisplayToastMachine() {}

    @Override
    public void onInstruction(@NotNull Instruction instruction) throws Exception {
        xyz.gmitch215.socketmc.screen.Toast toast = instruction.firstParameter(xyz.gmitch215.socketmc.screen.Toast.class);
        Toast toast0 = NeoForgeScreenUtil.toMinecraft(toast);
        minecraft.execute(() -> minecraft.getToasts().addToast(toast0));
    }

}
