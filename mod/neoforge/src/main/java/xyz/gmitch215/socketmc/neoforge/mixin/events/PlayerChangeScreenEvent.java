package xyz.gmitch215.socketmc.neoforge.mixin.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.gmitch215.socketmc.neoforge.NeoForgeSocketMC;
import xyz.gmitch215.socketmc.neoforge.screen.NeoForgeScreenUtil;
import xyz.gmitch215.socketmc.screen.AbstractScreen;

import java.util.Map;

import static xyz.gmitch215.socketmc.neoforge.NeoForgeSocketMC.minecraft;

@Mixin(Minecraft.class)
public class PlayerChangeScreenEvent {

    @Inject(method = "setScreen", at = @At("HEAD"))
    public void onScreenChange(Screen screen, CallbackInfo ci) {
        if (!NeoForgeSocketMC.eventsEnabled) return;

        AbstractScreen oldScreen = NeoForgeScreenUtil.fromMinecraft(minecraft.screen);
        AbstractScreen newScreen = NeoForgeScreenUtil.fromMinecraft(screen);

        if (oldScreen != null && newScreen != null) {
             NeoForgeSocketMC.sendEvent(4, Map.of("old", oldScreen, "new", newScreen));
        }
    }

}
