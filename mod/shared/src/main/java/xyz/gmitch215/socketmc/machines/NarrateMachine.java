package xyz.gmitch215.socketmc.machines;

import com.mojang.text2speech.Narrator;
import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;

@InstructionId(Instruction.NARRATE)
public final class NarrateMachine implements Machine {

    public static final NarrateMachine MACHINE = new NarrateMachine();

    private NarrateMachine() {}

    @Override
    public void onInstruction(@NotNull Instruction instruction) throws Exception {
        String text = instruction.firstStringParameter();
        boolean interrupt = instruction.lastBooleanParameter();

        Narrator.getNarrator().say(text, interrupt);
    }

}
