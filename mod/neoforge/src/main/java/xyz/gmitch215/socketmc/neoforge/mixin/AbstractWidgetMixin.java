package xyz.gmitch215.socketmc.neoforge.mixin;

import net.minecraft.client.gui.components.AbstractWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.gmitch215.socketmc.neoforge.screen.NeoForgeScreenUtil;
import xyz.gmitch215.socketmc.screen.Positionable;
import xyz.gmitch215.socketmc.screen.ScreenWidget;
import xyz.gmitch215.socketmc.util.ReflectionUtil;
import xyz.gmitch215.socketmc.util.SerializableConsumer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Mixin(AbstractWidget.class)
public class AbstractWidgetMixin implements ScreenWidget {

    @Unique
    private final Set<SerializableConsumer<Positionable>> socketMC$clickListeners = new HashSet<>();

    @Inject(method = "onClick", at = @At("TAIL"))
    public void onClick(double mouseX, double mouseY, CallbackInfo ci) {
        Positionable widget = NeoForgeScreenUtil.fromMinecraft((AbstractWidget) (Object) this);
        if (widget == null) return;

        socketMC$clickListeners.forEach(listener -> {
            listener.accept(widget);
            
            AbstractWidget applied = NeoForgeScreenUtil.toMinecraft(widget);
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
