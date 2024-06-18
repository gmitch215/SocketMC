package me.gamercoder215.socketmc.fabric.mixin;

import me.gamercoder215.socketmc.fabric.screen.FabricScreenUtil;
import me.gamercoder215.socketmc.screen.Positionable;
import me.gamercoder215.socketmc.screen.ScreenWidget;
import me.gamercoder215.socketmc.util.ReflectionUtil;
import me.gamercoder215.socketmc.util.SerializableConsumer;
import net.minecraft.client.gui.components.AbstractWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Mixin(AbstractWidget.class)
public class AbstractWidgetMixin implements ScreenWidget {

    @Unique
    private final Set<SerializableConsumer<Positionable>> socketMC$clickListeners = new HashSet<>();

    @Inject(method = "onClick", at = @At("TAIL"))
    public void onClick(double mouseX, double mouseY, CallbackInfo ci) {
        Positionable widget = FabricScreenUtil.fromMinecraft((AbstractWidget) (Object) this);
        if (widget == null) return;

        socketMC$clickListeners.forEach(listener -> {
            listener.accept(widget);

            AbstractWidget applied = FabricScreenUtil.toMinecraft(widget);
            ReflectionUtil.apply((AbstractWidget) (Object) this, applied, AbstractWidget.class);
        });
    }

    @Override
    public void socketMC$addClickListeners(Collection<SerializableConsumer<Positionable>> listener) {
        socketMC$clickListeners.addAll(listener);
    }

    @Override
    public Set<SerializableConsumer<Positionable>> socketMC$getClickListeners() {
        return socketMC$clickListeners;
    }
}
