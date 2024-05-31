package me.gamercoder215.socketmc.util.render.text;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Represents a text element with plain text.
 */
public class PlainText extends Text {

    /**
     * The text content for this text element.
     */
    protected String text;

    /**
     * The color for this text element.
     */
    protected int color = 0xFFFFFFFF;

    /**
     * Constructs a new, empty text element.
     */
    public PlainText() {}

    /**
     * Constructs a new text element.
     * @param text Text Content
     */
    public PlainText(@NotNull String text) {
        this.text = text;
    }

    /**
     * Constructs a new text element.
     * @param text Text Content
     * @param color Color
     */
    public PlainText(@NotNull String text, @NotNull Color color) {
        this(text, color.getRGB() | 0xFF000000);
    }

    /**
     * Constructs a new text element.
     * @param text Text Content
     * @param color Color as an ARGB integer
     */
    public PlainText(@NotNull String text, int color) {
        this.color = color;
        this.text = text;
    }

    /**
     * Gets the text content for this text element.
     * @return Text Content
     */
    @NotNull
    public String getText() {
        return text;
    }

    /**
     * Sets the text content for this text element.
     * @param text Text Content
     * @throws IllegalArgumentException if the text is null
     */
    public void setText(@NotNull String text) throws IllegalArgumentException {
        if (text == null) throw new IllegalArgumentException("Text cannot be null");
        this.text = text;
    }

    @Override
    public int getColor() {
        return color;
    }

    /**
     * Sets the color for this text element.
     * @param color Color as an ARGB integer
     */
    public void setColor(@NotNull int color) {
        this.color = color;
    }

    /**
     * Gets the alpha value for the color in this text element.
     * @return Color Alpha
     */
    public int getAlpha() {
        return this.color >>> 24;
    }

    /**
     * Sets the alpha value for the color in this text element.
     * @param alpha Color Alpha
     */
    public void setAlpha(int alpha) {
        if (alpha < 0 || alpha > 0xFF) throw new IllegalArgumentException("Alpha must be between 0 and 255");
        this.color = (this.color & 0xFFFFFF) | (alpha << 24);
    }

    @Override
    public String toJSON() {
        return "{\"text\":\"" + text + "\",\"color\":\"" + Integer.toHexString(color).substring(2) + "\"}";
    }

    /**
     * Creates a new text element with empty text content.
     * @return Empty Text Element
     */
    @NotNull
    public static PlainText empty() {
        return new PlainText("");
    }
}
