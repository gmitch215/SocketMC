package xyz.gmitch215.socketmc.neoforge.machines;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import xyz.gmitch215.socketmc.util.LifecycleMap;

import java.util.function.BiConsumer;

import static xyz.gmitch215.socketmc.neoforge.NeoForgeSocketMC.minecraft;

@InstructionId(Instruction.DRAW_BEACON_BEAM)
public final class DrawBeaconBeamMachine implements Machine {

    public static final DrawBeaconBeamMachine MACHINE = new DrawBeaconBeamMachine();

    private DrawBeaconBeamMachine() {}

    private static final LifecycleMap<BiConsumer<GuiGraphics, DeltaTracker>> lifecycle = new LifecycleMap<>();

    public static void frameTick(GuiGraphics graphics, DeltaTracker delta) {
        lifecycle.run();
        lifecycle.forEach(c -> c.accept(graphics, delta));
    }

    @Override
    public void onInstruction(Instruction instruction) {
        int x = instruction.intParameter(0);
        int y = instruction.intParameter(1);
        int z = instruction.intParameter(2);
        int height = instruction.intParameter(3);
        int color = instruction.intParameter(4);
        int yOffset = instruction.intParameter(5);
        float beamRadius = instruction.floatParameter(6);
        float glowRadius = instruction.floatParameter(7);
        long millis = instruction.lastLongParameter();

        PoseStack stack = new PoseStack();
        stack.pushPose();
        stack.translate(x, y, z);

        lifecycle.store((graphics, delta) -> BeaconRenderer.renderBeaconBeam(
                stack, graphics.bufferSource(), BeaconRenderer.BEAM_LOCATION, delta.getGameTimeDeltaPartialTick(true), 1.0F, minecraft.level.getGameTime(),
                yOffset, height, color, beamRadius, glowRadius
        ), millis);
    }
}
