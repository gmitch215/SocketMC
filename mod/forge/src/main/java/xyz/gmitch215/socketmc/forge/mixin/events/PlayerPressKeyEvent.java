package xyz.gmitch215.socketmc.forge.mixin.events;

import xyz.gmitch215.socketmc.forge.ForgeSocketMC;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(targets = "net.minecraft.client.KeyboardHandler")
public class PlayerPressKeyEvent {

    @Inject(method = "keyPress", at = @At("HEAD"))
    public void onKeyPress(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        ForgeSocketMC.sendEvent(0, Map.of("key", key, "flags", modifiers, "action", action));
    }

}
