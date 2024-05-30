package me.gamercoder215.socketmc.screen.ui;

import me.gamercoder215.socketmc.util.render.text.Text;
import me.gamercoder215.socketmc.util.Position;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;

/**
 * Represents a button that displays text.
 */
public final class TextButton extends AbstractTextButton {

    @Serial
    private static final long serialVersionUID = -7187903535622563653L;

    /**
     * Constructs a new text button using the default dimesions.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param message the text message
     * @throws IllegalArgumentException if coordinates are negative, or message is null
     */
    public TextButton(int x, int y, @NotNull Text message) throws IllegalArgumentException {
        this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, message);
    }

    /**
     * Constructs a new text button.
     * @param position the position to use
     * @param message the text message
     */
    public TextButton(@NotNull Position position, @NotNull Text message) {
        this(position.getX(), position.getY(), position.getWidth(), position.getHeight(), message);
    }

    /**
     * Constructs a new text button.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param message the text message
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or message is null
     */
    public TextButton(int x, int y, int width, int height, @NotNull Text message) throws IllegalArgumentException {
        super(x, y, width, height, message);
    }

    /**
     * Constructs a new text button.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param messageJSON the text message in JSON format
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or message is null
     */
    public TextButton(int x, int y, int width, int height, @NotNull String messageJSON) throws IllegalArgumentException {
        super(x, y, width, height, messageJSON);
    }
}
