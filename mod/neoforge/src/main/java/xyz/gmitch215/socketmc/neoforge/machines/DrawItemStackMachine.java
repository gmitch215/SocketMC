package xyz.gmitch215.socketmc.neoforge.machines;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.neoforge.NeoForgeUtil;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import xyz.gmitch215.socketmc.util.LifecycleMap;
import xyz.gmitch215.socketmc.util.NBTTag;

import java.util.function.BiConsumer;

@InstructionId(Instruction.DRAW_ITEMSTACK)
public final class DrawItemStackMachine implements Machine {

    public static final DrawItemStackMachine MACHINE = new DrawItemStackMachine();

    private DrawItemStackMachine() {}

    public static final LifecycleMap<BiConsumer<GuiGraphics, DeltaTracker>> lifecycle = new LifecycleMap<>();

    public static void frameTick(GuiGraphics graphics, DeltaTracker delta) {
        lifecycle.run();
        lifecycle.forEach(c -> c.accept(graphics, delta));
    }

    @Override
    public void onInstruction(@NotNull Instruction instruction) throws Exception {
        NBTTag tag = instruction.firstParameter(NBTTag.class);
        int x = instruction.intParameter(1);
        int y = instruction.intParameter(2);
        int guiOffset = instruction.intParameter(3);
        int randomSeed = instruction.intParameter(4);
        long millis = instruction.lastLongParameter();

        ItemStack item = NeoForgeUtil.toItem(tag);

        lifecycle.store((graphics, delta) -> graphics.renderItem(item, x, y, guiOffset, randomSeed), millis);
    }
}
