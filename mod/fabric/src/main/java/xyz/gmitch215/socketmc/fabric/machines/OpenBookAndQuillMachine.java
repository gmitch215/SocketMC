package xyz.gmitch215.socketmc.fabric.machines;

import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;

import static xyz.gmitch215.socketmc.fabric.FabricSocketMC.minecraft;

@InstructionId(Instruction.OPEN_BOOK_AND_QUILL)
public final class OpenBookAndQuillMachine implements Machine {

    public static final OpenBookAndQuillMachine MACHINE = new OpenBookAndQuillMachine();

    private OpenBookAndQuillMachine() {}

    @Override
    public void onInstruction(Instruction instruction) {
        minecraft.player.openItemGui(Items.WRITABLE_BOOK.getDefaultInstance(), InteractionHand.MAIN_HAND);
    }

}
