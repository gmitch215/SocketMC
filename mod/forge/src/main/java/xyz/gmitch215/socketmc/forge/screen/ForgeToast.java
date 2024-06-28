package xyz.gmitch215.socketmc.forge.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import xyz.gmitch215.socketmc.forge.machines.DrawContextMachine;
import xyz.gmitch215.socketmc.util.render.DrawingContext;

public final class ForgeToast implements Toast {

    final xyz.gmitch215.socketmc.screen.Toast handle;

    long lastChanged;
    boolean changed;

    public ForgeToast(xyz.gmitch215.socketmc.screen.Toast handle) {
        this.handle = handle;
    }

    @Override
    public Visibility render(GuiGraphics guiGraphics, ToastComponent toastComponent, long timeSinceLastVisible) {
        if (changed) {
            lastChanged = timeSinceLastVisible;
            changed = false;
        }

        DrawingContext context = handle.firstParameter(DrawingContext.class);
        DrawContextMachine.draw(guiGraphics, context);

        return (timeSinceLastVisible - lastChanged) >= handle.getDuration() * toastComponent.getNotificationDisplayTimeMultiplier() ? Visibility.HIDE : Visibility.SHOW;
    }

    @Override
    public int width() {
        return handle.getWidth();
    }

    @Override
    public int height() {
        return handle.getHeight();
    }
}
