package me.gamercoder215.socketmc.forge.screen;

import me.gamercoder215.socketmc.SocketMC;
import me.gamercoder215.socketmc.forge.machines.ForgeMachineFinder;
import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.machines.MachineFinder;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import static me.gamercoder215.socketmc.forge.screen.ForgeScreenUtil.BUTTON_PRESS_EVENT;

public final class ForgeSendInstructionButton extends Button {

    final Instruction instruction;

    public ForgeSendInstructionButton(int x, int y, int width, int height, Component message, Instruction i) {
        super(x, y, width, height, message, b -> {
            try {
                MachineFinder.getMachine(ForgeMachineFinder.MACHINES, i.getId()).onInstruction(i);
            } catch (Exception e) {
                SocketMC.print(e);
            }

            BUTTON_PRESS_EVENT.onPress(b);
        }, Button.DEFAULT_NARRATION);

        this.instruction = i;
    }
}
