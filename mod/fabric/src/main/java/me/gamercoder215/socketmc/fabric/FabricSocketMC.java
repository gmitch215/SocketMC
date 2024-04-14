package me.gamercoder215.socketmc.fabric;

import me.gamercoder215.socketmc.SocketMC;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

import java.util.logging.Logger;

@Environment(EnvType.CLIENT)
public final class FabricSocketMC implements ClientModInitializer {

    public static final Logger LOGGER = Logger.getLogger("SocketMC");

    public static Minecraft minecraft;
    public static GuiGraphics graphics;

    @Override
    public void onInitializeClient() {
        minecraft = Minecraft.getInstance();
        graphics = new GuiGraphics(minecraft, minecraft.renderBuffers().bufferSource());
    }

    public static void print(Throwable t) {
        SocketMC.print(LOGGER, t);
    }

}
