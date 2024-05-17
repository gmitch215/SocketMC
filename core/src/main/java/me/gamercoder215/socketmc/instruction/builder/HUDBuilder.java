package me.gamercoder215.socketmc.instruction.builder;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Represents a {@link InstructionBuilder} for displaying elements on the HUD.
 * @param <T> The type of the builder
 */
public interface HUDBuilder<T> extends InstructionBuilder {

    /**
     * Sets the X coordinate of the element.
     * @param x X coordinate
     * @return this class, for chaining
     */
    @NotNull
    T x(int x);

    /**
     * Sets the Y coordinate of the element.
     * @param y Y coordinate
     * @return this class, for chaining
     */
    @NotNull
    T y(int y);

    /**
     * Sets the color of the element using ARGB format.
     * @param argb Color in ARGB
     * @return this class, for chaining
     */
    @NotNull
    T argb(int argb);

    /**
     * Sets the color of the element using RGB format.
     * @param r Red
     * @param g Green
     * @param b Blue
     * @return this class, for chaining
     */
    @NotNull
    default T rgb(int r, int g, int b) {
        return argb(0xFF << 24 | r << 16 | g << 8 | b);
    }

    /**
     * Sets the color of the element using RGB format.
     * @param rgb RGB color
     * @return this class, for chaining
     */
    @NotNull
    default T rgb(int rgb) {
        return rgb((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
    }

    /**
     * Sets the color of the text using ARGB format.
     * @param a Alpha
     * @param r Red
     * @param g Green
     * @param b Blue
     * @return this class, for chaining
     */
    @NotNull
    default T argb(int a, int r, int g, int b) {
        return argb(a << 24 | r << 16 | g << 8 | b);
    }

    /**
     * Sets the color of the element.
     * @param color Color
     * @return this class, for chaining
     */
    @NotNull
    default T color(@NotNull Color color) {
        return argb(color.getRGB());
    }

    /**
     * Sets the color of the element.
     * @param color Color
     * @param alpha Alpha
     * @return this class, for chaining
     */
    @NotNull
    default T color(@NotNull Color color, int alpha) {
        return argb(alpha, color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Sets the duration the element should be displayed for.
     * @param millis Duration, in milliseconds
     * @return this class, for chaining
     */
    T duration(long millis);

    /**
     * Sets the duration the element should be displayed for.
     * @param millis Duration, in milliseconds
     * @return this class, for chaining
     */
    default T duration(int millis) {
        return duration((long) millis);
    }

    /**
     * Sets the duration the element should be displayed for.
     * @param duration Duration
     * @return this class, for chaining
     */
    default T duration(@NotNull Duration duration) {
        return duration(duration.toMillis());
    }

    /**
     * Sets the duration the element should be displayed for.
     * @param time Duration Amount
     * @param unit Duration Unit
     * @return this class, for chaining
     */
    default T duration(long time, @NotNull TimeUnit unit) {
        return duration(unit.toMillis(time));
    }

}
