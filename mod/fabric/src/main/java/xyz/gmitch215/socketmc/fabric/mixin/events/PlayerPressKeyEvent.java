package xyz.gmitch215.socketmc.fabric.mixin.events;

import net.minecraft.client.KeyboardHandler;
import xyz.gmitch215.socketmc.fabric.FabricSocketMC;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(KeyboardHandler.class)
public class PlayerPressKeyEvent {

    @Inject(method = "keyPress", at = @At("HEAD"))
    public void onKeyPress(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        FabricSocketMC.sendEvent(0, Map.of("key", key, "flags", modifiers, "action", action));
    }

}
