package me.gamercoder215.socketmc.forge.machines;

import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.instruction.InstructionId;
import me.gamercoder215.socketmc.instruction.Machine;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;

import static me.gamercoder215.socketmc.forge.ForgeSocketMC.minecraft;

@InstructionId(Instruction.OPEN_BOOK_AND_QUILL)
public final class OpenBookAndQuillMachine implements Machine {

    public static final OpenBookAndQuillMachine MACHINE = new OpenBookAndQuillMachine();

    private OpenBookAndQuillMachine() {}

    @Override
    public void onInstruction(Instruction instruction) {
        minecraft.player.openItemGui(Items.WRITABLE_BOOK.getDefaultInstance(), InteractionHand.MAIN_HAND);
    }

}
