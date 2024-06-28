package xyz.gmitch215.socketmc.forge.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import xyz.gmitch215.socketmc.forge.ForgeUtil;
import xyz.gmitch215.socketmc.forge.machines.DrawContextMachine;
import xyz.gmitch215.socketmc.screen.ui.CustomWidget;

public final class ForgeCustomWidget extends AbstractWidget {

    final CustomWidget handle;

    public ForgeCustomWidget(CustomWidget handle) {
        super(handle.getX(), handle.getY(), handle.getWidth(), handle.getHeight(), ForgeUtil.fromJson(handle.getMessageJSON()));

        this.handle = handle;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        DrawContextMachine.draw(guiGraphics, handle.getContext());
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.TITLE, createNarrationMessage());
    }
}
