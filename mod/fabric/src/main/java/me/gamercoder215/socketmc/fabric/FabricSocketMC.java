package me.gamercoder215.socketmc.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.logging.Logger;

@Environment(EnvType.CLIENT)
public final class FabricSocketMC implements ClientModInitializer {

    public static final Logger LOGGER = Logger.getLogger("SocketMC");

    @Override
    public void onInitializeClient() {

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
