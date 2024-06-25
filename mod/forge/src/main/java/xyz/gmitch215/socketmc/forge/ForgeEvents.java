package xyz.gmitch215.socketmc.forge;

import xyz.gmitch215.socketmc.forge.machines.RenderingMachine;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ForgeEvents {

    @SubscribeEvent
    public void onDisconnect(PlayerEvent.PlayerLoggedOutEvent event) {
        ForgeSocketMC.eventsEnabled = false;
    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        // Machines
        RenderingMachine.tick();
    }

}
