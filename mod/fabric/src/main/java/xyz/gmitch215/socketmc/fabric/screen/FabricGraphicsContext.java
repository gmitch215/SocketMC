package xyz.gmitch215.socketmc.fabric.screen;

import com.mojang.blaze3d.platform.InputConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.gmitch215.socketmc.util.InputType;
import xyz.gmitch215.socketmc.util.input.Key;
import xyz.gmitch215.socketmc.util.render.GraphicsContext;

import static xyz.gmitch215.socketmc.fabric.FabricSocketMC.minecraft;

public final class FabricGraphicsContext implements GraphicsContext {

    public static final FabricGraphicsContext INSTANCE = new FabricGraphicsContext();

    private FabricGraphicsContext() {}

    @Override
    public int getFps() {
        return minecraft.getFps();
    }

    @Override
    public int getScreenWidth() {
        return minecraft.getWindow().getScreenWidth();
    }

    @Override
    public int getScreenHeight() {
        return minecraft.getWindow().getScreenHeight();
    }

    @Override
    public int getMouseX() {
        return (int) (getRawMouseX() * minecraft.getWindow().getGuiScaledWidth() / minecraft.getWindow().getScreenWidth());
    }

    @Override
    public int getMouseY() {
        return (int) (getRawMouseY() * minecraft.getWindow().getGuiScaledHeight() / minecraft.getWindow().getScreenHeight());
    }

    @Override
    public double getRawMouseX() {
        return minecraft.mouseHandler.xpos();
    }

    @Override
    public double getRawMouseY() {
        return minecraft.mouseHandler.ypos();
    }

    @Override
    public float getPartialTicks() {
        return minecraft.getTimer().getGameTimeDeltaTicks();
    }

    @Override
    @NotNull
    public String getClipboard() {
        return minecraft.keyboardHandler.getClipboard();
    }

    @Override
    @Nullable
    public InputType getLastInputType() {
        return switch (minecraft.getLastInputType()) {
            case MOUSE -> InputType.MOUSE;
            case KEYBOARD_TAB -> InputType.KEYBOARD_TAB;
            case KEYBOARD_ARROW -> InputType.KEYBOARD_ARROW;
            default -> InputType.NONE;
        };
    }

    @Override
    public boolean isKeyDown(@Nullable Key key) {
        if (key == null) return false;
        return InputConstants.isKeyDown(minecraft.getWindow().getWindow(), key.getCode());
    }
}
