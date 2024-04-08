package me.gamercoder215.socketmc.forge;

import me.gamercoder215.socketmc.SocketMC;
import net.minecraftforge.fml.common.Mod;

import java.util.logging.Logger;

@Mod(SocketMC.MOD_ID)
public final class ForgeSocketMC {

    public static final Logger LOGGER = Logger.getLogger("SocketMC");

    public ForgeSocketMC() {

    }

    public static void print(Throwable t) {
        LOGGER.severe(t.getClass().getSimpleName());
        LOGGER.severe("-----------");
        LOGGER.severe(t.getMessage());
        for (StackTraceElement element : t.getStackTrace()) LOGGER.severe(element.toString());

        if (t.getCause() != null) {
            LOGGER.severe("Caused by:");
            print(t.getCause());
        }
    }

}
