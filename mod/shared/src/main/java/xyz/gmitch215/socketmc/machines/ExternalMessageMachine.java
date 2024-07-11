package xyz.gmitch215.socketmc.machines;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.util.tinyfd.TinyFileDialogs;
import xyz.gmitch215.socketmc.SocketMC;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;

import java.util.Map;

@InstructionId(Instruction.EXTERNAL_WINDOW_MESSAGE_BOX)
public final class ExternalMessageMachine implements Machine {

    public static final ExternalMessageMachine MACHINE = new ExternalMessageMachine();

    private ExternalMessageMachine() {}

    @Override
    public void onInstruction(@NotNull Instruction instruction) throws Exception {
        String title = instruction.firstStringParameter();
        String message = instruction.stringParameter(1);
        String dialogue = instruction.stringParameter(2);
        String icon = instruction.stringParameter(3);
        boolean defaultButton = instruction.booleanParameter(4);

        boolean success = TinyFileDialogs.tinyfd_messageBox(title, message, dialogue, icon, defaultButton);
        SocketMC.INSTANCE.get().sendSocketMCEvent(9, Map.of("success", success));
    }
}
