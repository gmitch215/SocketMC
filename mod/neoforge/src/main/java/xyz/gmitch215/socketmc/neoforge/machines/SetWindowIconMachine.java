package xyz.gmitch215.socketmc.neoforge.machines;

import com.mojang.blaze3d.platform.MacosUtil;
import com.mojang.blaze3d.platform.NativeImage;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

import static xyz.gmitch215.socketmc.neoforge.NeoForgeSocketMC.minecraft;

@InstructionId(Instruction.SET_WINDOW_ICON)
public final class SetWindowIconMachine implements Machine {

    public static final SetWindowIconMachine MACHINE = new SetWindowIconMachine();

    private SetWindowIconMachine() {}

    @Override
    public void onInstruction(Instruction instruction) throws Exception {
        byte[] icon = instruction.parameter(0, byte[].class);

        int i = GLFW.glfwGetPlatform();
        switch (i) {
            case 393217, 393220 -> {
                try (MemoryStack stack = MemoryStack.stackPush()) {
                    GLFWImage.Buffer buffer = GLFWImage.malloc(1, stack);
                    try (NativeImage image = NativeImage.read(icon)) {
                        ByteBuffer bytes = MemoryUtil.memAlloc(image.getWidth() * image.getHeight() * 4);
                        bytes.asIntBuffer().put(image.getPixelsRGBA());

                        buffer.position(0);
                        buffer.width(image.getWidth());
                        buffer.height(image.getHeight());
                        buffer.pixels(bytes);

                        GLFW.glfwSetWindowIcon(minecraft.getWindow().getWindow(), buffer.position(0));
                        MemoryUtil.memFree(bytes);
                    }
                }
            }
            case 393218 -> MacosUtil.loadIcon(() -> new ByteArrayInputStream(icon));
        }
    }

}
