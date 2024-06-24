package me.gamercoder215.socketmc.fabric.machines;

import me.gamercoder215.socketmc.fabric.FabricUtil;
import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.instruction.InstructionId;
import me.gamercoder215.socketmc.instruction.Machine;
import me.gamercoder215.socketmc.util.Identifier;
import me.gamercoder215.socketmc.util.LifecycleMap;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

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
        int x = instruction.parameter(0, Integer.class);
        int y = instruction.parameter(1, Integer.class);
        int width = instruction.parameter(2, Integer.class);
        int height = instruction.parameter(3, Integer.class);
        Identifier texture = instruction.parameter(4, Identifier.class);

        int u = instruction.parameter(5, Integer.class);
        int v = instruction.parameter(6, Integer.class);
        int regionWidth = instruction.parameter(7, Integer.class);
        int regionHeight = instruction.parameter(8, Integer.class);

        long millis = instruction.lastParameter(Long.class);

        ResourceLocation l = FabricUtil.toMinecraft(texture);
        int rw = regionWidth == -1 ? width : regionWidth;
        int rh = regionHeight == -1 ? height : regionHeight;

        lifecycle.store(graphics -> graphics.blit(l, x, y, width, height, u, v, rw, rh, 256, 256), millis);
    }

}
