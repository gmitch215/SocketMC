package me.gamercoder215.socketmc.forge;

import me.gamercoder215.socketmc.SocketMC;
import me.gamercoder215.socketmc.events.ModListener;
import net.minecraftforge.fml.common.Mod;

@Mod(SocketMC.MOD_ID)
public final class ForgeSocketMC {

    public ForgeSocketMC() {
        SocketMC.getListeners().forEach(ModListener::onInitialize);
    }

}
