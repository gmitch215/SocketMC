package me.gamercoder215.socketmc.fabric.machines;

import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.instruction.InstructionId;
import me.gamercoder215.socketmc.instruction.Machine;
import org.jetbrains.annotations.NotNull;

import static me.gamercoder215.socketmc.fabric.FabricSocketMC.graphics;

@InstructionId(Instruction.DRAW_SHAPE)
public final class DrawShapeMachine implements Machine {

    private static void fill(int x, int y, Instruction i) {
        int width = i.parameter(3, Integer.class);
        int height = i.parameter(4, Integer.class);
        int color = i.parameter(5, Integer.class);

        graphics.fill(x, y, x + width, y + height, color);
    }

    private static void gradient(int x, int y, Instruction i) {
        int width = i.parameter(3, Integer.class);
        int height = i.parameter(4, Integer.class);
        int z = i.parameter(5, Integer.class);
        int color1 = i.parameter(6, Integer.class);
        int color2 = i.parameter(7, Integer.class);

        graphics.fillGradient(x, y, x + width, y + height, z, color1, color2);
    }

    private static void vline(int x, int y, Instruction i) {
        int height = i.parameter(3, Integer.class);
        int color = i.parameter(4, Integer.class);

        graphics.vLine(x, y, y + height, color);
    }

    private static void hline(int x, int y, Instruction i) {
        int width = i.parameter(3, Integer.class);
        int color = i.parameter(4, Integer.class);

        graphics.hLine(x, x + width, y, color);
    }

    @Override
    public void onInstruction(@NotNull Instruction instruction) {
        String id = instruction.parameter(0, String.class);
        int x = instruction.parameter(1, Integer.class);
        int y = instruction.parameter(2, Integer.class);

        switch (id) {
            case "fill" -> fill(x, y, instruction);
            case "gradient" -> gradient(x, y, instruction);
            case "line_v" -> vline(x, y, instruction);
            case "line_h" -> hline(x, y, instruction);
        }
    }
}
