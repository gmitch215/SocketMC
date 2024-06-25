package xyz.gmitch215.socketmc.fabric.screen;

import xyz.gmitch215.socketmc.SocketMC;
import xyz.gmitch215.socketmc.fabric.machines.FabricMachineFinder;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.machines.MachineFinder;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import static xyz.gmitch215.socketmc.fabric.screen.FabricScreenUtil.BUTTON_PRESS_EVENT;

public final class FabricSendInstructionButton extends Button {

    final Instruction instruction;

    public FabricSendInstructionButton(int x, int y, int width, int height, Component message, Instruction i) {
        super(x, y, width, height, message, b -> {
            try {
                MachineFinder.getMachine(FabricMachineFinder.MACHINES, i.getId()).onInstruction(i);
            } catch (Exception e) {
                SocketMC.print(e);
            }

            BUTTON_PRESS_EVENT.onPress(b);
        }, Button.DEFAULT_NARRATION);

        this.instruction = i;
    }
}
