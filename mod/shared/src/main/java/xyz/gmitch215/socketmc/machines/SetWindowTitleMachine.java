package xyz.gmitch215.socketmc.machines;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;
import xyz.gmitch215.socketmc.SocketMC;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;

@InstructionId(Instruction.SET_WINDOW_TITLE)
public final class SetWindowTitleMachine implements Machine {

    public static final SetWindowTitleMachine MACHINE = new SetWindowTitleMachine();

    private SetWindowTitleMachine() {}


    @Override
    public void onInstruction(@NotNull Instruction instruction) throws Exception {
        String title = instruction.firstStringParameter();
        long pointer = SocketMC.INSTANCE.get().getWindowId();

        GLFW.glfwSetWindowTitle(pointer, title);
    }
}
