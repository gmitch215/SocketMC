package xyz.gmitch215.socketmc.fabric;

import com.mojang.blaze3d.platform.Window;

import static xyz.gmitch215.socketmc.fabric.FabricSocketMC.minecraft;
import static xyz.gmitch215.socketmc.util.RenderingProperties.REFRESH_RATE;

public final class FabricRendering {

    private FabricRendering() {}

    public static void frameTick() {
        // Set Rendering Properties
        Window window = minecraft.getWindow();

        REFRESH_RATE.set(window.getRefreshRate());
    }

}
