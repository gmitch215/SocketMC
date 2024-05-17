package me.gamercoder215.socketmc.instruction.builder;

import me.gamercoder215.socketmc.instruction.Instruction;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Builder for {@link Instruction#DRAW_SHAPE}. Excludes {@link Instruction#drawGradientRect}.
 */
public final class RectangleBuilder implements HUDBuilder<RectangleBuilder> {

    int x, y, width = 1, height = 1, argb;
    long millis;

    RectangleBuilder() {}

    @Override
    @NotNull
    public RectangleBuilder x(int x) {
        this.x = x;
        return this;
    }

    @Override
    @NotNull
    public RectangleBuilder y(int y) {
        this.y = y;
        return this;
    }

    @Override
    @NotNull
    public RectangleBuilder argb(int argb) {
        this.argb = argb;
        return this;
    }

    @Override
    public RectangleBuilder duration(long millis) {
        this.millis = millis;
        return this;
    }

    /**
     * Sets the width of the shape.
     * @param width Width
     * @return this class, for chaining
     */
    @NotNull
    public RectangleBuilder width(int width) {
        this.width = width;
        return this;
    }

    /**
     * Sets the height of the shape.
     * @param height Height
     * @return this class, for chaining
     */
    @NotNull
    public RectangleBuilder height(int height) {
        this.height = height;
        return this;
    }

    /**
     * Sets the dimensions of the shape.
     * @param width Width
     * @param height Height
     * @return this class, for chaining
     */
    @NotNull
    public RectangleBuilder dimensions(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    @Override
    @NotNull
    public Instruction build() {
        Color color = new Color(argb);
        int alpha = (argb >> 24) & 0xFF;

        if (width == 1 && height != -1) {
            return Instruction.drawVerticalLine(x, y, height, color, alpha, millis);
        } else if (height == 1 && width != -1) {
            return Instruction.drawHorizontalLine(x, y, width, color, alpha, millis);
        } else {
            return Instruction.drawRect(x, y, width, height, color, alpha, millis);
        }
    }
}
