package me.gamercoder215.socketmc.forge;

import me.gamercoder215.socketmc.SocketMC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.fml.common.Mod;

import java.util.logging.Logger;

@Mod(SocketMC.MOD_ID)
public final class ForgeSocketMC {

    public static final Logger LOGGER = Logger.getLogger("SocketMC");

    public static Minecraft minecraft;
    public static GuiGraphics graphics;

    public ForgeSocketMC() {
        minecraft = Minecraft.getInstance();
        graphics = new GuiGraphics(minecraft, minecraft.renderBuffers().bufferSource());
    }

    public static void print(Throwable t) {
        SocketMC.print(LOGGER, t);
    }

}
