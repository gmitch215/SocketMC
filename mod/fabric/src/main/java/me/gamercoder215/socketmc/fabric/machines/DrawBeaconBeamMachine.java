package me.gamercoder215.socketmc.fabric.machines;

import com.mojang.blaze3d.vertex.PoseStack;
import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.instruction.InstructionId;
import me.gamercoder215.socketmc.instruction.InstructionUtil;
import me.gamercoder215.socketmc.instruction.Machine;
import me.gamercoder215.socketmc.util.LifecycleMap;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;

import java.util.function.BiConsumer;

import static me.gamercoder215.socketmc.fabric.FabricSocketMC.minecraft;

@InstructionId(Instruction.DRAW_BEACON_BEAM)
public final class DrawBeaconBeamMachine implements Machine {

    public static final DrawBeaconBeamMachine MACHINE = new DrawBeaconBeamMachine();

    private DrawBeaconBeamMachine() {}

    private static final LifecycleMap<BiConsumer<GuiGraphics, Float>> lifecycle = new LifecycleMap<>();

    public static void frameTick(GuiGraphics graphics, float delta) {
        lifecycle.run();
        lifecycle.forEach(c -> c.accept(graphics, delta));
    }

    @Override
    public void onInstruction(Instruction instruction) {
        int x = instruction.parameter(0, Integer.class);
        int y = instruction.parameter(1, Integer.class);
        int z = instruction.parameter(2, Integer.class);
        int height = instruction.parameter(3, Integer.class);
        int color = instruction.parameter(4, Integer.class);
        int yOffset = instruction.parameter(5, Integer.class);
        float beamRadius = instruction.parameter(6, Float.class);
        float glowRadius = instruction.parameter(7, Float.class);
        long millis = instruction.lastParameter(Long.class);

        float[] colors0 = InstructionUtil.parseColor(color);

        PoseStack stack = new PoseStack();
        stack.pushPose();
        stack.translate(x, y, z);

        lifecycle.store((graphics, delta) -> BeaconRenderer.renderBeaconBeam(
                stack, graphics.bufferSource(), BeaconRenderer.BEAM_LOCATION, delta, 1.0F, minecraft.level.getGameTime(),
                yOffset, height, colors0, beamRadius, glowRadius
        ), millis);
    }
}
