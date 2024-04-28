package me.gamercoder215.socketmc.forge.machines;

import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.instruction.InstructionId;
import me.gamercoder215.socketmc.instruction.Machine;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import static me.gamercoder215.socketmc.forge.ForgeSocketMC.minecraft;

@InstructionId(Instruction.DRAW_TEXT)
public final class DrawTextMachine implements Machine {

    public static final DrawTextMachine MACHINE = new DrawTextMachine();

    private DrawTextMachine() {}

    @Override
    public void onInstruction(@NotNull Instruction instruction) {
        int x = instruction.parameter(0, Integer.class);
        int y = instruction.parameter(1, Integer.class);
        Component c = Component.Serializer.fromJson(instruction.parameter(2, String.class));
        int color = instruction.parameter(3, Integer.class);
        boolean dropShadow = instruction.parameter(4, Boolean.class);

        GuiGraphics graphics = new GuiGraphics(minecraft, minecraft.renderBuffers().bufferSource());
        graphics.drawString(minecraft.font, c, x, y, color, dropShadow);
    }

}

