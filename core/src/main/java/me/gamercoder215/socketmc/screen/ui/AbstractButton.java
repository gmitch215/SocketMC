package me.gamercoder215.socketmc.screen.ui;

import me.gamercoder215.socketmc.instruction.util.Text;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;

/**
 * Represents a Button on the screen.
 */
public abstract class AbstractButton extends AbstractWidget {
    @Serial
    private static final long serialVersionUID = -6994558317982834270L;

    /**
     * The default width of a button.
     */
    public static final int DEFAULT_WIDTH = 150;

    /**
     * The default height of a button.
     */
    public static final int DEFAULT_HEIGHT = 20;

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
}
