package me.gamercoder215.socketmc.fabric.mixin.events;

import me.gamercoder215.socketmc.fabric.FabricSocketMC;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(targets = "net.minecraft.client.MouseHandler")
public class PlayerMouseInputEvent {

    @Unique
    private int previousX = -1;

    @Unique
    private int previousY = -1;

    @Inject(method = "onMove", at = @At("HEAD"))
    private void onMove(long window, double x, double y, CallbackInfo ci){
        if (Math.abs(previousX - x) < 10 || Math.abs(previousY - y) < 10) return;

        previousX = (int) x;
        previousY = (int) y;
        FabricSocketMC.sendEvent(1, Map.of("x", x, "y", y));
    }

    @Inject(method = "onScroll", at = @At("HEAD"))
    private void onScroll(long window, double xOffset, double yOffset, CallbackInfo ci){
        FabricSocketMC.sendEvent(2, Map.of("x", xOffset, "y", yOffset));
    }

    @Inject(method = "onPress", at = @At("HEAD"))
    private void onPress(long window, int button, int action, int mods, CallbackInfo ci){
        FabricSocketMC.sendEvent(3, Map.of("button", button, "action", action, "mods", mods));
    }

}
