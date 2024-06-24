package me.gamercoder215.socketmc.forge.machines;

import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.instruction.InstructionId;
import me.gamercoder215.socketmc.instruction.Machine;
import me.gamercoder215.socketmc.instruction.RenderInstruction;
import me.gamercoder215.socketmc.util.LifecycleMap;
import org.joml.Matrix4f;

import java.util.List;
import java.util.function.Function;

import static me.gamercoder215.socketmc.forge.ForgeSocketMC.minecraft;

@InstructionId(Instruction.RENDERER)
public final class RenderingMachine implements Machine {

    public static final RenderingMachine MACHINE = new RenderingMachine();

    private RenderingMachine() {}

    private static final LifecycleMap<Runnable> lifecycle = new LifecycleMap<>();

    public static void tick() {
        lifecycle.run();
        lifecycle.forEach(Runnable::run);
    }

    public static final List<List<Function<RenderInstruction, Runnable>>> RENDERERS = List.of(
            // GameRenderer
            List.of(
                    i -> {
                        Matrix4f matrix = i.data("matrix", Matrix4f.class);
                        return () -> minecraft.gameRenderer.renderItemInHand(
                                minecraft.gameRenderer.getMainCamera(),
                                minecraft.getTimer().getGameTimeDeltaPartialTick(true),
                                matrix
                        );
                    }
            )
    );

    @Override
    public void onInstruction(Instruction instruction) {
        RenderInstruction render = instruction.lastParameter(RenderInstruction.class);

        Runnable action = RENDERERS.get(render.getOrdinal()).get(render.getSubOrdinal()).apply(render);
        long millis = (long) render.data("time");

        lifecycle.store(action, millis);
    }

}
