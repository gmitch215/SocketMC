package xyz.gmitch215.socketmc.neoforge.machines;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import xyz.gmitch215.socketmc.util.LifecycleMap;

import java.util.function.Consumer;

import static xyz.gmitch215.socketmc.neoforge.NeoForgeSocketMC.minecraft;

@InstructionId(Instruction.DRAW_TEXT)
public final class DrawTextMachine implements Machine {

    public static final DrawTextMachine MACHINE = new DrawTextMachine();

    private DrawTextMachine() {}

    public static final LifecycleMap<Consumer<GuiGraphics>> lifecycle = new LifecycleMap<>();

    public static void frameTick(GuiGraphics graphics, DeltaTracker delta) {
        lifecycle.run();
        lifecycle.forEach(c -> c.accept(graphics));
    }

    @Override
    public void onInstruction(@NotNull Instruction instruction) {
        int x = instruction.intParameter(0);
        int y = instruction.intParameter(1);
        Component c = Component.Serializer.fromJson(instruction.parameter(2, String.class), minecraft.level.registryAccess());
        int color = instruction.intParameter(3);
        boolean dropShadow = instruction.parameter(4, Boolean.class);
        long millis = instruction.parameter(5, Long.class);

        lifecycle.store(graphics -> graphics.drawString(minecraft.font, c, x, y, color, dropShadow), millis);
    }

}

