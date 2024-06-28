package xyz.gmitch215.socketmc.forge.machines;

import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import xyz.gmitch215.socketmc.util.LifecycleMap;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

@InstructionId(Instruction.DRAW_SHAPE)
public final class DrawShapeMachine implements Machine {

    public static final DrawShapeMachine MACHINE = new DrawShapeMachine();

    private DrawShapeMachine() {}

    private static final LifecycleMap<Consumer<GuiGraphics>> lifecycle = new LifecycleMap<>();

    public static void frameTick(GuiGraphics graphics, DeltaTracker delta) {
        lifecycle.run();
        lifecycle.forEach(c -> c.accept(graphics));
    }

    private static void fill(int x, int y, Instruction i) {
        int width = i.intParameter(3);
        int height = i.intParameter(4);
        int color = i.intParameter(5);
        long millis = i.lastLongParameter();

        lifecycle.store(graphics -> graphics.fill(x, y, x + width, y + height, color), millis);
    }

    private static void gradient(int x, int y, Instruction i) {
        int width = i.intParameter(3);
        int height = i.intParameter(4);
        int z = i.intParameter(5);
        int color1 = i.intParameter(6);
        int color2 = i.intParameter(7);
        long millis = i.lastLongParameter();

        lifecycle.store(graphics -> graphics.fillGradient(x, y, x + width, y + height, z, color1, color2), millis);
    }

    private static void vline(int x, int y, Instruction i) {
        int height = i.intParameter(3);
        int color = i.intParameter(4);
        long millis = i.lastLongParameter();

        lifecycle.store(graphics -> graphics.vLine(x, y, y + height, color), millis);
    }

    private static void hline(int x, int y, Instruction i) {
        int width = i.intParameter(3);
        int color = i.intParameter(4);
        long millis = i.lastLongParameter();

        lifecycle.store(graphics -> graphics.hLine(x, x + width, y, color), millis);
    }

    @Override
    public void onInstruction(@NotNull Instruction instruction) {
        String id = instruction.parameter(0, String.class);
        int x = instruction.intParameter(1);
        int y = instruction.intParameter(2);

        switch (id) {
            case "fill" -> fill(x, y, instruction);
            case "gradient" -> gradient(x, y, instruction);
            case "line_v" -> vline(x, y, instruction);
            case "line_h" -> hline(x, y, instruction);
        }
    }
}
