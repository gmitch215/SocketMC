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

    public static final LifecycleMap<Consumer<GuiGraphics>> lifecycle = new LifecycleMap<>();

    public static void frameTick(GuiGraphics graphics, DeltaTracker delta) {
        lifecycle.run();
        lifecycle.forEach(c -> c.accept(graphics));
    }

    @Override
    public void onInstruction(@NotNull Instruction instruction) {
        int x = instruction.intParameter(0);
        int y = instruction.intParameter(1);
        Component c = FabricUtil.fromJson(instruction.stringParameter(2, "{}"));
        int color = instruction.intParameter(3);
        boolean dropShadow = instruction.booleanParameter(4, true);
        long millis = instruction.lastLongParameter();

        lifecycle.store(graphics -> graphics.drawString(FabricSocketMC.minecraft.font, c, x, y, color, dropShadow), millis);
    }

}

