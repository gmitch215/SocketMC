package xyz.gmitch215.socketmc.neoforge.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import xyz.gmitch215.socketmc.neoforge.NeoForgeUtil;
import xyz.gmitch215.socketmc.neoforge.machines.DrawContextMachine;
import xyz.gmitch215.socketmc.screen.ui.CustomWidget;

public final class NeoForgeCustomWidget extends AbstractWidget {

    final CustomWidget handle;

    public NeoForgeCustomWidget(CustomWidget handle) {
        super(handle.getX(), handle.getY(), handle.getWidth(), handle.getHeight(), NeoForgeUtil.fromJson(handle.getMessageJSON()));

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
