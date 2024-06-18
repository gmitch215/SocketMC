package me.gamercoder215.socketmc.fabric;

import me.gamercoder215.socketmc.fabric.machines.RenderingMachine;

public final class FabricEvents {

    public void onDisconnect() {
        FabricSocketMC.eventsEnabled = false;
    }

    public void tick() {
        // Machines
        RenderingMachine.tick();
    }

}
