package me.gamercoder215.socketmc.machines;

import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.instruction.InstructionId;
import me.gamercoder215.socketmc.instruction.Machine;

import java.awt.*;
import java.net.URI;

@InstructionId(Instruction.MAILTO)
public final class MailtoMachine implements Machine {

    public static final MailtoMachine MACHINE = new MailtoMachine();

    private MailtoMachine() {}

    @Override
    public void onInstruction(Instruction instruction) throws Exception {
        URI uri = instruction.parameter(0, URI.class);
        Desktop.getDesktop().mail(uri);
    }
}
