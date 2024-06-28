package xyz.gmitch215.socketmc.fabric.screen;

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
}
