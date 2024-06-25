package xyz.gmitch215.socketmc.screen.ui;

import xyz.gmitch215.socketmc.util.ElementBounds;
import xyz.gmitch215.socketmc.util.render.text.Text;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;

/**
 * Represents a text widget that displays a text message.
 */
public final class TextWidget extends AbstractTextWidget {

    @Serial
    private static final long serialVersionUID = -736862345234117201L;

    /**
     * Constructs a new text widget with the default padding.
     * @param bounds the bounds
     * @param message the text message
     * @throws IllegalArgumentException if message is null
     */
    public TextWidget(@NotNull ElementBounds bounds, @NotNull Text message) throws IllegalArgumentException {
        this(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), message);
    }

    /**
     * Constructs a new text widget using automatically-determined dimensions.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param message the text message
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or message is null
     */
    public TextWidget(int x, int y, @NotNull Text message) throws IllegalArgumentException {
        super(x, y, -1, -1, message);
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
    public TextWidget(int x, int y, int width, int height, @NotNull Text message) throws IllegalArgumentException {
        super(x, y, width, height, message);
    }

    /**
     * Constructs a new text widget.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param messageJSON the text message as a JSON string
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or message is null
     */
    public TextWidget(int x, int y, int width, int height, @NotNull String messageJSON) throws IllegalArgumentException {
        super(x, y, width, height, messageJSON);
    }
    
}
