package me.gamercoder215.socketmc.instruction.builder;

import me.gamercoder215.socketmc.instruction.Instruction;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.Duration;

/**
 * Builder for {@link Instruction#DRAW_TEXT}.
 */
public final class TextBuilder implements HUDBuilder<TextBuilder> {

    int x, y, argb;
    String text;
    boolean dropShadow;
    long millis;

    TextBuilder() {}

    @Override
    public TextBuilder x(int x) {
        this.x = x;
        return this;
    }

    @Override
    public TextBuilder y(int y) {
        this.y = y;
        return this;
    }

    @Override
    public TextBuilder argb(int color) {
        this.argb = color;
        return this;
    }

    @Override
    public TextBuilder duration(long millis) {
        this.millis = millis;
        return this;
    }

    /**
     * Sets the text to be displayed.
     * @param text Text
     * @return this class, for chaining
     */
    @NotNull
    public TextBuilder text(@NotNull String text) {
        this.text = text;
        return this;
    }

    /**
     * Sets whether the text should have a drop shadow.
     * @param dropShadow Drop shadow
     * @return this class, for chaining
     */
    @NotNull
    public TextBuilder dropShadow(boolean dropShadow) {
        this.dropShadow = dropShadow;
        return this;
    }

    @Override
    @NotNull
    public Instruction build() {
        return Instruction.drawText(x, y, text, argb, dropShadow, millis);
    }

}
