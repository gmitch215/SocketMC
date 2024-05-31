package me.gamercoder215.socketmc.forge.screen;

import me.gamercoder215.socketmc.forge.ForgeUtil;
import me.gamercoder215.socketmc.screen.CustomScreen;
import me.gamercoder215.socketmc.screen.Positionable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class ForgeScreen extends Screen {

    final CustomScreen handle;

    @Nullable
    final Screen previousScreen;

    public ForgeScreen(CustomScreen handle, Screen previousScreen) {
        super(ForgeUtil.fromJson(handle.getTitleJSON()));

        this.handle = handle;
        this.previousScreen = previousScreen;
    }

    @Override
    public Component getNarrationMessage() {
        return ForgeUtil.fromJson(handle.getNarrationMessageJSON());
    }

    @Override
    public void init() {
        List<Positionable> children = handle.getChildren();
        for (Positionable child: children)
            addRenderableWidget(ForgeScreenUtil.convert(child));
    }

    @Override
    public void onClose() {
        minecraft.setScreen(previousScreen);
    }

}
