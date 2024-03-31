package me.gamercoder215.socketmc.forge;

import me.gamercoder215.socketmc.SocketMC;
import me.gamercoder215.socketmc.mod.ModListener;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SocketMC.MOD_ID)
public final class ForgeSocketMC {

    public ForgeSocketMC() {
        SocketMC.getListeners().forEach(ModListener::onInitialize);
    }

}
