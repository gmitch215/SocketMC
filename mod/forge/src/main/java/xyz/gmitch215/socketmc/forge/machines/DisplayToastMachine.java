package xyz.gmitch215.socketmc.forge.machines;

import net.minecraft.client.gui.components.toasts.Toast;
import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.forge.screen.ForgeScreenUtil;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;

import static xyz.gmitch215.socketmc.forge.ForgeSocketMC.minecraft;


@InstructionId(Instruction.DISPLAY_TOAST)
public final class DisplayToastMachine implements Machine {

    public static final DisplayToastMachine MACHINE = new DisplayToastMachine();

    private DisplayToastMachine() {}

    @Override
    public void onInstruction(@NotNull Instruction instruction) throws Exception {
        xyz.gmitch215.socketmc.screen.Toast toast = instruction.firstParameter(xyz.gmitch215.socketmc.screen.Toast.class);
        Toast toast0 = ForgeScreenUtil.toMinecraft(toast);
        minecraft.execute(() -> minecraft.getToasts().addToast(toast0));
    }

}
