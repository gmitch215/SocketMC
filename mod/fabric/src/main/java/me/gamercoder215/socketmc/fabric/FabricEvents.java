package me.gamercoder215.socketmc.fabric;

public final class FabricEvents {

    public void onDisconnect() {
        FabricSocketMC.eventsEnabled = false;
    }

}
