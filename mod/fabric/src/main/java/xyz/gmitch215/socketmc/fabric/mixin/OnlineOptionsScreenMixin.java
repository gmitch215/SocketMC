package xyz.gmitch215.socketmc.fabric.mixin;

import xyz.gmitch215.socketmc.fabric.mod.MainScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.options.OnlineOptionsScreen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OnlineOptionsScreen.class)
public class OnlineOptionsScreenMixin extends OptionsSubScreen {

    protected OnlineOptionsScreenMixin() {
        super(null, null, null);
    }

    @Inject(method = "init", at = @At("TAIL"))
    public void init(CallbackInfo ci) {
        Button socketMC = Button.builder(Component.literal("\uD83D\uDD0C"), button -> minecraft.setScreen(new MainScreen(this)))
                .pos(this.width - 105, this.height - 26)
                .size(20, 20)
                .build();

        socketMC.setTooltip(Tooltip.create(MainScreen.TITLE));
        addRenderableWidget(socketMC);
    }

    @Override
    public void addOptions() {}
}
