package me.gamercoder215.socketmc.screen.ui;

import me.gamercoder215.socketmc.util.render.text.Text;
import me.gamercoder215.socketmc.util.ElementBounds;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;

/**
 * Represents a box for text to be entered.
 */
public final class EditTextWidget extends AbstractTextWidget {
    
    @Serial
    private static final long serialVersionUID = -5604215369554823639L;

    /**
     * Constructs a new edit text widget.
     * @param bounds the bounds
     * @param message the text message
     * @throws IllegalArgumentException if message is null
     */
    public EditTextWidget(@NotNull ElementBounds bounds, @NotNull Text message) throws IllegalArgumentException {
        super(bounds, message);
    }

    /**
     * Constructs a new edit text widget.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param message the text message
     * @throws IllegalArgumentException if message is null
     */
    public EditTextWidget(int x, int y, int width, int height, @NotNull Text message) throws IllegalArgumentException {
        super(x, y, width, height, message);
    }

    /**
     * Constructs a new edit text widget.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param messageJSON the text message in JSON format
     * @throws IllegalArgumentException if message is null
     */
    public EditTextWidget(int x, int y, int width, int height, @NotNull String messageJSON) throws IllegalArgumentException {
        super(x, y, width, height, messageJSON);
    }
}
