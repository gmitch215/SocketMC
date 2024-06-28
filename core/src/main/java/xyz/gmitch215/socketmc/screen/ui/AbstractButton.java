package xyz.gmitch215.socketmc.screen.ui;

import xyz.gmitch215.socketmc.util.ElementBounds;
import xyz.gmitch215.socketmc.util.render.text.Text;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;

/**
 * Represents a Button on the screen.
 */
public abstract class AbstractButton extends AbstractTextWidget {

    @Serial
    private static final long serialVersionUID = -6994558317982834270L;

    /**
     * The default width of a button.
     */
    public static final int DEFAULT_WIDTH = 150;

    /**
     * The default width of a small button.
     */
    public static final int SMALL_WIDTH = 120;

    /**
     * The default width of a big button.
     */
    public static final int BIG_WIDTH = 200;

    /**
     * The default height of a button.
     */
    public static final int DEFAULT_HEIGHT = 20;

    /**
     * The default width and height spacing between buttons.
     */
    public static final int DEFAULT_SPACING = 8;

    /**
     * Constructs a new button using the specified bounds.
     * @param bounds the bounds
     * @param message the text message
     * @throws IllegalArgumentException if bounds or message is null
     */
    protected AbstractButton(@NotNull ElementBounds bounds, @NotNull Text message) throws IllegalArgumentException {
        super(bounds, message);
    }

    /**
     * Constructs a new button using the default dimesions.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param message the text message
     * @throws IllegalArgumentException if coordinates are negative, or message is null
     */
    protected AbstractButton(int x, int y, @NotNull Text message) throws IllegalArgumentException {
        this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, message);
    }

    /**
     * Constructs a new button.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param message the text message
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or message is null
     */
    protected AbstractButton(int x, int y, int width, int height, @NotNull Text message) throws IllegalArgumentException {
        super(x, y, width, height, message);
    }

    /**
     * Constructs a new button.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param messageJSON the text message in JSON format
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or message is null
     */
    protected AbstractButton(int x, int y, int width, int height, @NotNull String messageJSON) throws IllegalArgumentException {
        super(x, y, width, height, messageJSON);
    }
}
