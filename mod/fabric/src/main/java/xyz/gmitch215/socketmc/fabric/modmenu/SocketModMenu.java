package xyz.gmitch215.socketmc.fabric.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import xyz.gmitch215.socketmc.fabric.mod.MainScreen;

public final class SocketModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<MainScreen> getModConfigScreenFactory() { return MainScreen::new; }

}
