package xyz.gmitch215.socketmc.fabric.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import xyz.gmitch215.socketmc.fabric.machines.DrawContextMachine;
import xyz.gmitch215.socketmc.util.render.DrawingContext;

public final class FabricToast implements Toast {

    final xyz.gmitch215.socketmc.screen.Toast handle;

    long lastChanged;
    boolean changed;

    public FabricToast(xyz.gmitch215.socketmc.screen.Toast handle) {
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

        return (timeSinceLastVisible - lastChanged) >= handle.getDuration() * toastComponent.getNotificationDisplayTimeMultiplier() ? Toast.Visibility.HIDE : Toast.Visibility.SHOW;
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
