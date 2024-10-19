package xyz.gmitch215.socketmc.forge;

import com.mojang.blaze3d.platform.Window;

import static xyz.gmitch215.socketmc.forge.ForgeSocketMC.minecraft;
import static xyz.gmitch215.socketmc.util.RenderingProperties.REFRESH_RATE;

public final class ForgeRendering {

    private ForgeRendering() {}

    public static void frameTick() {
        // Set Rendering Properties
        Window window = minecraft.getWindow();

        REFRESH_RATE.set(window.getRefreshRate());
    }

}
