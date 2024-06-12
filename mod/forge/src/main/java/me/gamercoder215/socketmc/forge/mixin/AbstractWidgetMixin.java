package me.gamercoder215.socketmc.forge.mixin;

import me.gamercoder215.socketmc.forge.screen.ForgeScreenUtil;
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
        Positionable widget = ForgeScreenUtil.fromMinecraft((AbstractWidget) (Object) this);
        socketMC$clickListeners.forEach(listener -> {
            listener.accept(widget);
            
            AbstractWidget applied = ForgeScreenUtil.toMinecraft(widget);
            ReflectionUtil.apply(applied, (AbstractWidget) (Object) this, AbstractWidget.class);
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
