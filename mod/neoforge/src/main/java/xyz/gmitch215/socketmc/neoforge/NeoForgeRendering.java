package xyz.gmitch215.socketmc.neoforge;

import com.mojang.blaze3d.platform.Window;

import static xyz.gmitch215.socketmc.neoforge.NeoForgeSocketMC.minecraft;
import static xyz.gmitch215.socketmc.util.RenderingProperties.REFRESH_RATE;

public final class NeoForgeRendering {

    private NeoForgeRendering() {}

    public static void frameTick() {
        // Set Rendering Properties
        Window window = minecraft.getWindow();

        REFRESH_RATE.set(window.getRefreshRate());
    }

}
