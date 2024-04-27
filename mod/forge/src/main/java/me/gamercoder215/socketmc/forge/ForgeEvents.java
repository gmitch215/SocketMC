package me.gamercoder215.socketmc.forge;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ForgeEvents {

    @SubscribeEvent
    public void onDisconnect(PlayerEvent.PlayerLoggedOutEvent event) {
        ForgeSocketMC.eventsEnabled = false;
    }

}
