package me.gamercoder215.socketmc.instruction.util;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a text element to be displayed on the client's screen, built by a {@link String}. This class will automatically strip color codes from  {@link #getText()}.
 */
public final class BukkitText extends Text {

    private String text;
    private int color = 0xFFFFFFF;

    /**
     * Constructs a new, empty text element.
     */
    public BukkitText() {}

    /**
     * Constructs a new text element.
     * @param text Text Content
     */
    public BukkitText(@NotNull String text) {
        this(text, ChatColor.WHITE);
    }

    /**
     * Constructs a new text element.
     * @param text Text Content
     * @param color ChatColor value
     */
    public BukkitText(@NotNull String text, @NotNull ChatColor color) throws IllegalArgumentException {
        this(text, color.asBungee().getColor().getRGB() | 0xFF000000);
    }

    /**
     * Constructs a new text element.
     * @param text Text Content
     * @param color Color as an ARGB integer
     * @throws IllegalArgumentException if the text is null
     */
    public BukkitText(@NotNull String text, int color) throws IllegalArgumentException {
        if (text == null) throw new IllegalArgumentException("Text cannot be null");

        this.text = ChatColor.stripColor(text);
        this.color = color;
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

        this.text = ChatColor.stripColor(text);
    }

    /**
     * Sets the color for this text element.
     * @param color Color
     * @throws IllegalArgumentException if the color is null
     */
    public void setColor(@NotNull ChatColor color) throws IllegalArgumentException {
        if (color == null) throw new IllegalArgumentException("Color cannot be null");

        this.color = color.asBungee().getColor().getRGB() | getAlpha();
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
    public int getColor() {
        return color;
    }

    @Override
    public String toJSON() {
        TextComponent component = new TextComponent(text);
        return ComponentSerializer.toString(component);
    }


}
