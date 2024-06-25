package xyz.gmitch215.socketmc.fabric.machines;

import xyz.gmitch215.socketmc.fabric.FabricUtil;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import xyz.gmitch215.socketmc.util.LifecycleMap;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.fabric.FabricSocketMC;

import java.util.function.Consumer;

@InstructionId(Instruction.DRAW_TEXT)
public final class DrawTextMachine implements Machine {

    public static final DrawTextMachine MACHINE = new DrawTextMachine();

    private DrawTextMachine() {}

    private static final LifecycleMap<Consumer<GuiGraphics>> lifecycle = new LifecycleMap<>();

    public static void frameTick(GuiGraphics graphics, DeltaTracker delta) {
        lifecycle.run();
        lifecycle.forEach(c -> c.accept(graphics));
    }

    @Override
    public void onInstruction(@NotNull Instruction instruction) {
        int x = instruction.parameter(0, Integer.class);
        int y = instruction.parameter(1, Integer.class);
        Component c = FabricUtil.fromJson(instruction.parameter(2, String.class));
        int color = instruction.parameter(3, Integer.class);
        boolean dropShadow = instruction.parameter(4, Boolean.class);
        long millis = instruction.parameter(5, Long.class);

        lifecycle.store(graphics -> graphics.drawString(FabricSocketMC.minecraft.font, c, x, y, color, dropShadow), millis);
    }

}

