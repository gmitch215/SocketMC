package xyz.gmitch215.socketmc.forge.screen;

import xyz.gmitch215.socketmc.util.render.GraphicsContext;

import static xyz.gmitch215.socketmc.forge.ForgeSocketMC.minecraft;

public final class ForgeGraphicsContext implements GraphicsContext {

    public static final ForgeGraphicsContext INSTANCE = new ForgeGraphicsContext();

    private ForgeGraphicsContext() {}

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
