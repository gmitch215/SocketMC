package xyz.gmitch215.socketmc.neoforge.mixin.events;

import net.minecraft.client.NarratorStatus;
import net.minecraft.client.*;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.ChatVisiblity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.gmitch215.socketmc.neoforge.NeoForgeSocketMC;
import xyz.gmitch215.socketmc.util.Arm;
import xyz.gmitch215.socketmc.util.option.*;

import java.util.Map;
import java.util.function.Function;

@Mixin(OptionInstance.class)
public class PlayerChangeOptionEvent {

    @Final
    @Shadow
    public Component caption;

    @Shadow
    Object value;

    @Final
    @Shadow
    public Function<Object, Component> toString;

    @Inject(method = "set", at = @At("HEAD"))
    public void onOptionChange(Object newValue, CallbackInfo ci) {
        if (!NeoForgeSocketMC.eventsEnabled) return;

        String oldValueS = toString.apply(value).getString();
        String newValueS = toString.apply(newValue).getString();

        Object oldValue0 = socketMC$convertOptionValue(value);
        Object newValue0 = socketMC$convertOptionValue(newValue);

        String key = ((TranslatableContents) caption.getContents()).getKey();

        NeoForgeSocketMC.sendEvent(10, Map.of(
                "option", key,
                "old_value", oldValue0,
                "old_value_string", oldValueS,
                "new_value", newValue0,
                "new_value_string", newValueS
        ));
    }

    @Unique
    private static Object socketMC$convertOptionValue(Object value) {
        return switch (value) {
            case AttackIndicatorStatus a -> AttackIndicator.byOrdinal(a.getId());
            case ChatVisiblity a -> ChatVisibility.byOrdinal(a.getId());
            case PrioritizeChunkUpdates a -> ChunkUpdatePriority.byOrdinal(a.getId());
            case CloudStatus a -> CloudRendering.byOrdinal(a.getId());
            case GraphicsStatus a -> GraphicsQuality.byOrdinal(a.getId());
            case NarratorStatus a -> xyz.gmitch215.socketmc.util.option.NarratorStatus.byOrdinal(a.getId());
            case ParticleStatus a -> ParticleRendering.byOrdinal(a.getId());
            case HumanoidArm a -> Arm.byOrdinal(a.getId());
            default -> value;
        };
    }

}
