package xyz.gmitch215.socketmc.forge.mixin.events;

import xyz.gmitch215.socketmc.forge.ForgeSocketMC;
import xyz.gmitch215.socketmc.forge.screen.ForgeScreenUtil;
import xyz.gmitch215.socketmc.screen.AbstractScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

import static xyz.gmitch215.socketmc.forge.ForgeSocketMC.minecraft;

@Mixin(Minecraft.class)
public class PlayerChangeScreenEvent {

    @Inject(method = "setScreen", at = @At("HEAD"))
    public void onScreenChange(Screen screen, CallbackInfo ci) {
        if (!ForgeSocketMC.eventsEnabled) return;

        AbstractScreen oldScreen = ForgeScreenUtil.fromMinecraft(minecraft.screen);
        AbstractScreen newScreen = ForgeScreenUtil.fromMinecraft(screen);

        if (oldScreen != null && newScreen != null) {
             ForgeSocketMC.sendEvent(4, Map.of("old", oldScreen, "new", newScreen));
        }
    }

}
