package me.gamercoder215.socketmc.fabric.mod;

import net.minecraft.client.gui.screens.Screen;

import static me.gamercoder215.socketmc.fabric.mod.MainScreen.PERMISSIONS;

public final class ModPermissionsScreen extends Screen {

    private final Screen previousScreen;

    public ModPermissionsScreen(Screen previousScreen) {
        super(PERMISSIONS);

        this.previousScreen = previousScreen;
    }

    @Override
    public void onClose() {
        minecraft.setScreen(previousScreen);
    }

}
