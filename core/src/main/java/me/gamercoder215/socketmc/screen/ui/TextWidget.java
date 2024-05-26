package me.gamercoder215.socketmc.screen.ui;

import me.gamercoder215.socketmc.instruction.util.Text;
import me.gamercoder215.socketmc.util.Sizeable;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;

/**
 * Represents an abstract widget on the screen.
 */
public final class TextWidget extends AbstractWidget {

    /**
     * The default padding around text elements.
     */
    public static final int DEFAULT_PADDING = 4;

    @Serial
    private static final long serialVersionUID = -8273686084189110401L;

    private boolean isDropShadow;
    private int padding;

    /**
     * Constructs a new text widget with the default padding.
     * @param sizeable the sizeable
     * @param message the text message
     * @throws IllegalArgumentException if message is null
     */
    public TextWidget(@NotNull Sizeable sizeable, @NotNull Text message) throws IllegalArgumentException {
        this(sizeable.getX(), sizeable.getY(), sizeable.getWidth(), sizeable.getHeight(), message, false);
    }

    /**
     * Constructs a new text widget with the default padding.
     * @param sizeable the sizeable
     * @param message the text message
     * @param isDropShadow whether to render a drop shadow
     * @throws IllegalArgumentException if message is null
     */
    public TextWidget(@NotNull Sizeable sizeable, @NotNull Text message, boolean isDropShadow) throws IllegalArgumentException {
        this(sizeable.getX(), sizeable.getY(), sizeable.getWidth(), sizeable.getHeight(), message, isDropShadow, DEFAULT_PADDING);
    }

    /**
     * Constructs a new text widget.
     * @param sizeable the sizeable
     * @param message the text message
     * @param isDropShadow whether to render a drop shadow
     * @param padding the padding around the text
     * @throws IllegalArgumentException if padding is negative or message is null
     */
    public TextWidget(@NotNull Sizeable sizeable, @NotNull Text message, boolean isDropShadow, int padding) throws IllegalArgumentException {
        this(sizeable.getX(), sizeable.getY(), sizeable.getWidth(), sizeable.getHeight(), message, isDropShadow, padding);
    }

    /**
     * Constructs a new text widget.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param message the text message
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or message is null
     */
    public TextWidget(int x, int y, int width, int height, @NotNull Text message) {
        this(x, y, width, height, message, false);
    }

    /**
     * Constructs a new text widget with the default padding.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param message the text message
     * @param isDropShadow whether to render a drop shadow
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or message is null
     */
    public TextWidget(int x, int y, int width, int height, @NotNull Text message, boolean isDropShadow) {
        this(x, y, width, height, message, isDropShadow, DEFAULT_PADDING);
    }

    /**
     * Constructs a new text widget.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param message the text message
     * @param isDropShadow whether to render a drop shadow
     * @param padding the padding around the text
     * @throws IllegalArgumentException if coordinates, dimensions, or padding are negative, or message is null
     */
    public TextWidget(int x, int y, int width, int height, @NotNull Text message, boolean isDropShadow, int padding) throws IllegalArgumentException {
        super(x, y, width, height, message);
        if (padding < 0) throw new IllegalArgumentException("Padding must be non-negative");

        this.isDropShadow = isDropShadow;
        this.padding = padding;
    }

    /**
     * Gets whether this text widget has a drop shadow.
     * @return Whether to render a drop shadow
     */
    public boolean isDropShadow() {
        return isDropShadow;
    }

    /**
     * Sets whether this text widget should have a drop shadow.
     * @param dropShadow Whether to render a drop shadow
     */
    public void setDropShadow(boolean dropShadow) {
        isDropShadow = dropShadow;
    }

    /**
     * Gets the padding around the text.
     * @return Text Padding
     */
    public int getPadding() {
        return padding;
    }

    /**
     * Sets the padding around the text.
     * @param padding Text Padding
     */
    public void setPadding(int padding) {
        this.padding = padding;
    }
}
