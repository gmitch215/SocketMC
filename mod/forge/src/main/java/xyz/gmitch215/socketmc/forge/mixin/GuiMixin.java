package xyz.gmitch215.socketmc.forge.mixin;

import xyz.gmitch215.socketmc.forge.machines.*;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

    @Inject(method = "render", at = @At(value = "TAIL"))
    public void render(GuiGraphics drawContext, DeltaTracker tickDelta, CallbackInfo callbackInfo) {
        DrawTextMachine.frameTick(drawContext, tickDelta);
        DrawShapeMachine.frameTick(drawContext, tickDelta);
        DrawBufferMachine.frameTick(drawContext, tickDelta);
        DrawTextureMachine.frameTick(drawContext, tickDelta);
        DrawBeaconBeamMachine.frameTick(drawContext, tickDelta);
    }

}
