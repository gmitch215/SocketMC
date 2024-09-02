package xyz.gmitch215.socketmc.neoforge.machines;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import xyz.gmitch215.socketmc.neoforge.NeoForgeUtil;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import xyz.gmitch215.socketmc.util.Identifier;
import xyz.gmitch215.socketmc.util.LifecycleMap;

import java.util.function.Consumer;

@InstructionId(Instruction.DRAW_TEXTURE)
public final class DrawTextureMachine implements Machine {

    public static final DrawTextureMachine MACHINE = new DrawTextureMachine();

    private DrawTextureMachine() {}

    private static final LifecycleMap<Consumer<GuiGraphics>> lifecycle = new LifecycleMap<>();

    public static void frameTick(GuiGraphics graphics, DeltaTracker delta) {
        lifecycle.run();
        lifecycle.forEach(c -> c.accept(graphics));
    }

    @Override
    public void onInstruction(Instruction instruction) {
        int x = instruction.intParameter(0);
        int y = instruction.intParameter(1);
        int width = instruction.intParameter(2);
        int height = instruction.intParameter(3);
        Identifier texture = instruction.parameter(4, Identifier.class);

        int u = instruction.intParameter(5);
        int v = instruction.intParameter(6);
        int regionWidth = instruction.intParameter(7);
        int regionHeight = instruction.intParameter(8);

        long millis = instruction.lastLongParameter();

        ResourceLocation l = NeoForgeUtil.toMinecraft(texture);
        int rw = regionWidth == -1 ? width : regionWidth;
        int rh = regionHeight == -1 ? height : regionHeight;

        lifecycle.store(graphics -> graphics.blit(l, x, y, width, height, u, v, rw, rh, 256, 256), millis);
    }

}
