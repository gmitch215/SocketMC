package xyz.gmitch215.socketmc.fabric.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import xyz.gmitch215.socketmc.fabric.FabricUtil;
import xyz.gmitch215.socketmc.fabric.machines.DrawContextMachine;
import xyz.gmitch215.socketmc.screen.ui.CustomButton;

import static xyz.gmitch215.socketmc.fabric.screen.FabricScreenUtil.BUTTON_PRESS_EVENT;

public final class FabricCustomButton extends Button {

    final CustomButton handle;

    public FabricCustomButton(CustomButton handle) {
        super(handle.getX(), handle.getY(), handle.getWidth(), handle.getHeight(), FabricUtil.fromJson(handle.getMessageJSON()), BUTTON_PRESS_EVENT, DEFAULT_NARRATION);

        this.handle = handle;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (handle.isRenderDefault())
            super.renderWidget(guiGraphics, mouseX, mouseY, partialTick);

        DrawContextMachine.draw(guiGraphics, handle.getContext());
    }

}
