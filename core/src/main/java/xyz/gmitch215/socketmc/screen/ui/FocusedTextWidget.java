package xyz.gmitch215.socketmc.screen.ui;

import xyz.gmitch215.socketmc.util.render.text.Text;
import xyz.gmitch215.socketmc.util.ElementBounds;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;

/**
 * <p>Represents a focusable text widget that displays a text message.</p>
 * <p>{@link #getWidth()} serves as the text's <strong>maximum</strong> width. {@link #getHeight()} is ignored.</p>
 */
public final class FocusedTextWidget extends AbstractTextWidget {

    /**
     * The default padding around text elements.
     */
    public static final int DEFAULT_PADDING = 4;

    @Serial
    private static final long serialVersionUID = -736862345234117201L;

    private boolean alwaysShowBorder;
    private int padding;

    /**
     * Constructs a new text widget with the default padding.
     * @param bounds the bounds
     * @param message the text message
     * @throws IllegalArgumentException if message is null
     */
    public FocusedTextWidget(@NotNull ElementBounds bounds, @NotNull Text message) throws IllegalArgumentException {
        this(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), message, false);
    }

    /**
     * Constructs a new text widget with the default padding.
     * @param bounds the bounds
     * @param message the text message
     * @param alwaysShowBorder whether to render a drop shadow
     * @throws IllegalArgumentException if message is null
     */
    public FocusedTextWidget(@NotNull ElementBounds bounds, @NotNull Text message, boolean alwaysShowBorder) throws IllegalArgumentException {
        this(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), message, alwaysShowBorder, DEFAULT_PADDING);
    }

    /**
     * Constructs a new text widget.
     * @param bounds the bounds
     * @param message the text message
     * @param alwaysShowBorder whether to render a drop shadow
     * @param padding the padding around the text
     * @throws IllegalArgumentException if padding is negative or message is null
     */
    public FocusedTextWidget(@NotNull ElementBounds bounds, @NotNull Text message, boolean alwaysShowBorder, int padding) throws IllegalArgumentException {
        this(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), message, alwaysShowBorder, padding);
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
    public FocusedTextWidget(int x, int y, int width, int height, @NotNull Text message) {
        this(x, y, width, height, message, false);
    }

    /**
     * Constructs a new text widget with the default padding.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param message the text message
     * @param alwaysShowBorder whether to render a drop shadow
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or message is null
     */
    public FocusedTextWidget(int x, int y, int width, int height, @NotNull Text message, boolean alwaysShowBorder) {
        this(x, y, width, height, message, alwaysShowBorder, DEFAULT_PADDING);
    }

    /**
     * Constructs a new text widget.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param message the text message
     * @param alwaysShowBorder whether to render a drop shadow
     * @param padding the padding around the text
     * @throws IllegalArgumentException if coordinates, dimensions, or padding are negative, or message is null
     */
    public FocusedTextWidget(int x, int y, int width, int height, @NotNull Text message, boolean alwaysShowBorder, int padding) throws IllegalArgumentException {
        super(x, y, width, height, message);
        if (padding < 0) throw new IllegalArgumentException("Padding must be non-negative");

        this.alwaysShowBorder = alwaysShowBorder;
        this.padding = padding;
    }

    /**
     * Constructs a new text widget.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param messageJSON the text message as a JSON string
     * @param alwaysShowBorder whether to render a drop shadow
     * @param padding the padding around the text
     * @throws IllegalArgumentException if coordinates, dimensions, or padding are negative, or message is null
     */
    public FocusedTextWidget(int x, int y, int width, int height, @NotNull String messageJSON, boolean alwaysShowBorder, int padding) throws IllegalArgumentException {
        super(x, y, width, height, messageJSON);
        if (padding < 0) throw new IllegalArgumentException("Padding must be non-negative");

        this.alwaysShowBorder = alwaysShowBorder;
        this.padding = padding;
    }

    /**
     * Gets whether this text widget should always show its border.
     * @return Whether to always show the border
     */
    public boolean isAlwaysShowBorder() {
        return alwaysShowBorder;
    }

    /**
     * Sets whether this text widget should always show its border.
     * @param alwaysShowBorder Whether to always show the border
     */
    public void setAlwaysShowBorder(boolean alwaysShowBorder) {
        this.alwaysShowBorder = alwaysShowBorder;
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
