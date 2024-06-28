package xyz.gmitch215.socketmc.fabric;

import xyz.gmitch215.socketmc.fabric.machines.RenderingMachine;

public final class FabricEvents {

    public void onDisconnect() {
        FabricSocketMC.eventsEnabled = false;
    }

    public void tick() {
        // Machines
        RenderingMachine.tick();
    }

}
