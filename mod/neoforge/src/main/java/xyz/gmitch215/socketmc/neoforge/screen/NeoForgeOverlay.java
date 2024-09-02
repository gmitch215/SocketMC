package xyz.gmitch215.socketmc.neoforge.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Overlay;
import xyz.gmitch215.socketmc.neoforge.machines.DrawContextMachine;

public final class NeoForgeOverlay extends Overlay {

    private xyz.gmitch215.socketmc.screen.Overlay handle;

    public NeoForgeOverlay(xyz.gmitch215.socketmc.screen.Overlay handle) {
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
