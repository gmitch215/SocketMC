package me.gamercoder215.socketmc.fabric;

import me.gamercoder215.socketmc.SocketMC;
import me.gamercoder215.socketmc.mod.ModListener;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class FabricSocketMC implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        SocketMC.getListeners().forEach(ModListener::onInitialize);
    }

}
