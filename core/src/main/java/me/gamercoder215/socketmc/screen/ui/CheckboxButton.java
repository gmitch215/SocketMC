package me.gamercoder215.socketmc.screen.ui;

import me.gamercoder215.socketmc.util.render.text.Text;
import me.gamercoder215.socketmc.util.ElementBounds;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;

/**
 * <p>Represents a Checkbox Button.</p>
 * <p>{@link #getWidth()} and {@link #getHeight()} are determined by the client, so their values are set to {@code -1}.</p>
 */
public final class CheckboxButton extends AbstractButton {

    @Serial
    private static final long serialVersionUID = 6203813575916182638L;

    /**
     * Represents the default spacing between checkbox and text.
     */
    public static final int SPACING = 4;

    /**
     * Represents the default padding of the checkbox.
     */
    public static final int BOX_PADDING = 8;

    /**
     * Constructs a new checkbox.
     * @param bounds the bounds
     * @param message the text message
     * @throws IllegalArgumentException if coordinates are negative, or message is null
     */
    public CheckboxButton(@NotNull ElementBounds bounds, @NotNull Text message) throws IllegalArgumentException {
        this(bounds.getX(), bounds.getY(), message);
    }

    /**
     * Constructs a new checkbox.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param message the text message
     * @throws IllegalArgumentException if coordinates are negative, or message is null
     */
    public CheckboxButton(int x, int y, @NotNull Text message) throws IllegalArgumentException {
        super(x, y, -1, -1, message);
    }
}
