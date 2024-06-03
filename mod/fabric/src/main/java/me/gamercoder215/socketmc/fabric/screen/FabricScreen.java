package me.gamercoder215.socketmc.fabric.screen;

import me.gamercoder215.socketmc.fabric.FabricUtil;
import me.gamercoder215.socketmc.screen.CustomScreen;
import me.gamercoder215.socketmc.screen.Positionable;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class FabricScreen extends Screen {

    final CustomScreen handle;

    @Nullable
    final Screen previousScreen;

    public FabricScreen(CustomScreen handle, Screen previousScreen) {
        super(FabricUtil.fromJson(handle.getTitleJSON()));
        this.handle = handle;
        this.previousScreen = previousScreen;
    }

    @Override
    public Component getNarrationMessage() {
        return FabricUtil.fromJson(handle.getNarrationMessageJSON());
    }

    @Override
    public void init() {
        List<Positionable> children = handle.getChildren();
        for (Positionable child: children)
            addRenderableWidget(FabricScreenUtil.toMinecraft(child));
    }

    @Override
    public void onClose() {
        minecraft.setScreen(previousScreen);
    }

    @Override
    public void renderBackground(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        switch (handle.getBackground()) {
            case MENU -> renderMenuBackground(graphics);
            case DEFAULT -> super.renderBackground(graphics, mouseX, mouseY, partialTick);
            case PANORAMA -> renderPanorama(graphics, partialTick);
            case TRANSPARENT -> renderTransparentBackground(graphics);
        }
    }

}
