package me.gamercoder215.socketmc.util.render.text;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;

/**
 * Represents a text element to be displayed on the client's screen, built by a {@link String}. This class will automatically strip color codes from  {@link #getText()}.
 */
public final class BukkitText extends PlainText {

    @Serial
    private static final long serialVersionUID = -4314124883799428436L;

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
     * Sets the text content for this text element.
     * @param text Text Content
     * @throws IllegalArgumentException if the text is null
     */
    @Override
    public void setText(@NotNull String text) throws IllegalArgumentException {
        super.setText(ChatColor.stripColor(text));
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

    @Override
    public String toJSON() {
        TextComponent component = new TextComponent(text);
        return ComponentSerializer.toString(component);
    }


}
