package xyz.gmitch215.socketmc.fabric.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import xyz.gmitch215.socketmc.fabric.FabricUtil;
import xyz.gmitch215.socketmc.screen.CustomScreen;
import xyz.gmitch215.socketmc.screen.Positionable;
import xyz.gmitch215.socketmc.screen.layout.Layout;

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
        for (Positionable child : children)
            addRenderableWidget(FabricScreenUtil.toMinecraft(child));

        if (handle.getLayout() != null)
            repositionElements();
    }

    @Override
    public void repositionElements() {
        if (handle.getLayout() != null) {
            Layout layout = handle.getLayout();
            if (layout.isFullscreen()) {
                layout.setWidth(width);
                layout.setHeight(height);
            }

            layout.arrangeElements();
        }

        super.repositionElements();
    }

    @Override
    public void onClose() {
        minecraft.setScreen(previousScreen);
    }

    @Override
    public void renderBackground(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        switch (handle.getBackground()) {
            case DEFAULT -> super.renderBackground(graphics, mouseX, mouseY, partialTick);
            case PANORAMA -> {
                renderPanorama(graphics, partialTick);
                renderBlurredBackground(partialTick);
                renderMenuBackground(graphics);
            }
            case TRANSPARENT -> renderTransparentBackground(graphics);
        }
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return handle.isCloseableOnEscape();
    }

}
