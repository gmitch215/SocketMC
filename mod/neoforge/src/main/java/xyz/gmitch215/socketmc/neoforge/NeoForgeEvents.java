package xyz.gmitch215.socketmc.neoforge;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import xyz.gmitch215.socketmc.neoforge.machines.RenderingMachine;

public final class NeoForgeEvents {

    @SubscribeEvent
    public void onDisconnect(PlayerEvent.PlayerLoggedOutEvent event) {
        NeoForgeSocketMC.eventsEnabled = false;
    }

    @SubscribeEvent
    public void tick(ClientTickEvent.Post event) {
        // Machines
        RenderingMachine.tick();
    }

}
