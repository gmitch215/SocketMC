package me.gamercoder215.socketmc.screen.ui;

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
     * Constructs a new button using the default dimesions.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @throws IllegalArgumentException if coordinates are negative
     */
    protected AbstractButton(int x, int y) throws IllegalArgumentException {
        this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Constructs a new button.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @throws IllegalArgumentException if coordinates or dimensions are negative
     */
    protected AbstractButton(int x, int y, int width, int height) throws IllegalArgumentException {
        super(x, y, width, height);
    }
}
