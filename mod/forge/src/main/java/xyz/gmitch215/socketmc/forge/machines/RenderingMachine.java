package xyz.gmitch215.socketmc.forge.machines;

import net.minecraft.client.gui.GuiGraphics;
import xyz.gmitch215.socketmc.forge.ForgeUtil;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import xyz.gmitch215.socketmc.instruction.RenderInstruction;
import xyz.gmitch215.socketmc.util.LifecycleMap;
import org.joml.Matrix4f;
import xyz.gmitch215.socketmc.util.NBTTag;

import java.util.List;
import java.util.function.BiFunction;

import static xyz.gmitch215.socketmc.forge.ForgeSocketMC.minecraft;

@InstructionId(Instruction.RENDERER)
public final class RenderingMachine implements Machine {

    public static final RenderingMachine MACHINE = new RenderingMachine();

    private RenderingMachine() {}

    private static final LifecycleMap<Runnable> lifecycle = new LifecycleMap<>();
    private static final Runnable DONE = () -> {};

    public static void tick() {
        lifecycle.run();
        lifecycle.forEach(Runnable::run);
    }

    public static final List<List<BiFunction<RenderInstruction, Long, Runnable>>> RENDERERS = List.of(
            // GameRenderer
            List.of(
                    (i, time) -> {
                        Matrix4f matrix = i.data("matrix", Matrix4f.class);
                        return () -> minecraft.gameRenderer.renderItemInHand(
                                minecraft.gameRenderer.getMainCamera(),
                                minecraft.getTimer().getGameTimeDeltaPartialTick(true),
                                matrix
                        );
                    },
                    (i, time) -> {
                        float strength = i.data("strength", Float.class);
                        GuiGraphics graphics = new GuiGraphics(minecraft, minecraft.renderBuffers().bufferSource());
                        return () -> minecraft.gameRenderer.renderConfusionOverlay(graphics, strength);
                    },
                    (i, time) -> {
                        NBTTag item = i.data("item", NBTTag.class);
                        float offsetX = i.data("offsetX", Float.class);
                        float offsetY = i.data("offsetY", Float.class);

                        minecraft.gameRenderer.itemActivationItem = ForgeUtil.toItem(item);
                        minecraft.gameRenderer.itemActivationTicks = (int) (time / 50);
                        minecraft.gameRenderer.itemActivationOffX = offsetX;
                        minecraft.gameRenderer.itemActivationOffY = offsetY;

                        return DONE;
                    }
            ),
            // DebugRenderer
            List.of(
                    (i, time) -> {
                        minecraft.debugRenderer.renderChunkborder = i.data("enabled", Boolean.class);
                        return DONE;
                    }
            )
    );

    @Override
    public void onInstruction(Instruction instruction) {
        RenderInstruction render = instruction.lastParameter(RenderInstruction.class);
        long millis = (long) render.data("time");

        Runnable action = RENDERERS.get(render.getOrdinal()).get(render.getSubOrdinal()).apply(render, millis);

        lifecycle.store(action, millis);
    }

}
