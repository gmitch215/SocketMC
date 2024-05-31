package me.gamercoder215.socketmc.fabric.screen;

import me.gamercoder215.socketmc.fabric.FabricSocketMC;
import me.gamercoder215.socketmc.fabric.machines.FabricMachineFinder;
import me.gamercoder215.socketmc.instruction.Instruction;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import static me.gamercoder215.socketmc.fabric.screen.FabricScreenUtil.BUTTON_PRESS_EVENT;

public final class FabricSendInstructionButton extends Button {

    final Instruction instruction;

    public FabricSendInstructionButton(int x, int y, int width, int height, Component message, Instruction i) {
        super(x, y, width, height, message, b -> {
            try {
                FabricMachineFinder.getMachine(i.getId()).onInstruction(i);
            } catch (Exception e) {
                FabricSocketMC.print(e);
            }

            BUTTON_PRESS_EVENT.onPress(b);
        }, Button.DEFAULT_NARRATION);

        this.instruction = i;
    }
}
