package xyz.gmitch215.socketmc.machines;

import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;

import java.awt.*;
import java.net.URI;

@InstructionId(Instruction.OPEN_LINK)
public final class OpenLinkMachine implements Machine {

    public static final OpenLinkMachine MACHINE = new OpenLinkMachine();

    private OpenLinkMachine() {}

    @Override
    public void onInstruction(Instruction instruction) throws Exception {
        URI uri = instruction.parameter(0, URI.class);
        Desktop.getDesktop().browse(uri);
    }
}
