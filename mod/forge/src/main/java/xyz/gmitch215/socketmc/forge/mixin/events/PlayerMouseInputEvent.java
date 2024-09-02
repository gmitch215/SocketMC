package xyz.gmitch215.socketmc.forge.mixin.events;

import net.minecraft.client.MouseHandler;
import xyz.gmitch215.socketmc.forge.ForgeSocketMC;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(MouseHandler.class)
public class PlayerMouseInputEvent {

    @Inject(method = "onMove", at = @At("HEAD"))
    private void onMove(long window, double x, double y, CallbackInfo ci){
        ForgeSocketMC.sendEvent(1, Map.of("x", x, "y", y));
    }

    @Inject(method = "onScroll", at = @At("HEAD"))
    private void onScroll(long window, double xOffset, double yOffset, CallbackInfo ci){
        ForgeSocketMC.sendEvent(2, Map.of("x", xOffset, "y", yOffset));
    }

    @Inject(method = "onPress", at = @At("HEAD"))
    private void onPress(long window, int button, int action, int mods, CallbackInfo ci){
        ForgeSocketMC.sendEvent(3, Map.of("button", button, "action", action, "mods", mods));
    }

}
