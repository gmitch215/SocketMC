package xyz.gmitch215.socketmc.forge.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Overlay;
import xyz.gmitch215.socketmc.forge.machines.DrawContextMachine;

public final class ForgeOverlay extends Overlay {

    private xyz.gmitch215.socketmc.screen.Overlay handle;

    public ForgeOverlay(xyz.gmitch215.socketmc.screen.Overlay handle) {
        this.handle = handle;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        DrawContextMachine.draw(guiGraphics, handle.getContext());
    }

    @Override
    public boolean isPauseScreen() {
        return handle.isPauseScreen();
    }
}
