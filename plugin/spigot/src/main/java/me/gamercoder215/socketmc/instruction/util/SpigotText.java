package me.gamercoder215.socketmc.instruction.util;

import me.gamercoder215.socketmc.util.render.text.Text;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a text element to be displayed on the client's screen, built by a {@link BaseComponent}.
 */
public final class SpigotText extends Text {

    /**
     * The text content for this text element.
     */
    protected BaseComponent component = new TextComponent("");

    /**
     * The alpha value for the color in this text element.
     */
    protected int alpha;

    /**
     * Constructs a new, empty text element.
     */
    public SpigotText() {}

    /**
     * Constructs a new text element.
     * @param component Component for Text
     */
    public SpigotText(@NotNull BaseComponent component) {
        this(component, 0xFF);
    }

    /**
     * Constructs a new text element.
     * @param component Component for Text
     * @param alpha Color Alpha Value
     * @throws IllegalArgumentException if component is null or the alpha value is not between 0 and 255
     */
    public SpigotText(@NotNull BaseComponent component, int alpha) throws IllegalArgumentException {
        if (component == null) throw new IllegalArgumentException("Component cannot be null");
        if (alpha < 0 || alpha > 255) throw new IllegalArgumentException("Alpha must be between 0 and 255");

        this.component = component;
        this.alpha = alpha;
    }

    /**
     * Gets the text content for this text element.
     * @return Text Content
     */
    @NotNull
    public BaseComponent getComponent() {
        return component;
    }

    /**
     * Sets the text content for this text element.
     * @param component Text Component
     * @throws IllegalArgumentException if the text is null
     */
    public void setComponent(@NotNull BaseComponent component) throws IllegalArgumentException {
        if (component == null) throw new IllegalArgumentException("Component cannot be null");
        this.component = component;
    }

    /**
     * Gets the alpha value for the color in this text element.
     * @return Alpha Value
     */
    public int getAlpha() {
        return alpha >>> 24;
    }

    /**
     * Sets the alpha value for the color in this text element.
     * @param alpha Alpha Value
     * @throws IllegalArgumentException if the alpha value is not between 0 and 255
     */
    public void setAlpha(int alpha) throws IllegalArgumentException {
        if (alpha < 0 || alpha > 255) throw new IllegalArgumentException("Alpha must be between 0 and 255");
        this.alpha = alpha;
    }

    @Override
    public int getColor() {
        return component.getColor().getColor().getRGB() | getAlpha();
    }

    @Override
    public String toJSON() {
        return ComponentSerializer.toString(component);
    }

    /**
     * Constructs a new text element with the given text content.
     * @param component Text Component
     * @return Text Element
     */
    @NotNull
    public static SpigotText from(@NotNull BaseComponent component) {
        return new SpigotText(component);
    }

}
