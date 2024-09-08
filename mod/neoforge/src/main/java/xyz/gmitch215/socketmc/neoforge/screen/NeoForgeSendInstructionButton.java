package xyz.gmitch215.socketmc.neoforge.screen;

import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import xyz.gmitch215.socketmc.SocketMC;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.machines.MachineFinder;
import xyz.gmitch215.socketmc.neoforge.machines.NeoForgeMachineFinder;

import static xyz.gmitch215.socketmc.neoforge.screen.NeoForgeScreenUtil.BUTTON_PRESS_EVENT;

public final class NeoForgeSendInstructionButton extends Button {

    final Instruction instruction;

    public NeoForgeSendInstructionButton(int x, int y, int width, int height, Component message, Instruction i) {
        super(x, y, width, height, message, b -> {
            try {
                MachineFinder.getMachine(NeoForgeMachineFinder.MACHINES, i.getId()).onInstruction(i);
            } catch (Exception e) {
                SocketMC.print(e);
            }

            BUTTON_PRESS_EVENT.onPress(b);
        }, Button.DEFAULT_NARRATION);

        this.instruction = i;
    }
}
