package xyz.gmitch215.socketmc.screen.ui;

import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.util.ElementBounds;
import xyz.gmitch215.socketmc.util.render.DrawingContext;
import xyz.gmitch215.socketmc.util.render.text.Text;

import java.io.Serial;

/**
 * Represents a custom widget, rendered through a {@link DrawingContext}.
 */
public final class CustomWidget extends AbstractTextWidget {

    @Serial
    private static final long serialVersionUID = -2701808155424298543L;

    private final DrawingContext context;

    /**
     * Creates a new {@link CustomWidget}.
     * @param bounds the bounds
     * @param message the text message
     * @param context the drawing context
     * @throws IllegalArgumentException if bounds, message, or context is null
     */
    public CustomWidget(@NotNull ElementBounds bounds, @NotNull Text message, @NotNull DrawingContext context) throws IllegalArgumentException {
        super(bounds, message);

        if (context == null) throw new IllegalArgumentException("Drawing context cannot be null");
        this.context = context;
    }

    /**
     * Creates a new {@link CustomWidget}.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param message the text message
     * @param context the drawing context
     * @throws IllegalArgumentException if the bounds are invalid, or if the text or drawing context is null
     */
    public CustomWidget(int x, int y, int width, int height, @NotNull Text message, @NotNull DrawingContext context) throws IllegalArgumentException {
        super(x, y, width, height, message);

        if (context == null) throw new IllegalArgumentException("Drawing context cannot be null");
        this.context = context;
    }

    /**
     * Creates a new {@link CustomWidget}.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param messageJSON the text message in JSON format
     * @param context the drawing context
     * @throws IllegalArgumentException if the bounds are invalid, or if the text or drawing context is null
     */
    public CustomWidget(int x, int y, int width, int height, @NotNull String messageJSON, @NotNull DrawingContext context) throws IllegalArgumentException {
        super(x, y, width, height, messageJSON);

        if (context == null) throw new IllegalArgumentException("Drawing context cannot be null");
        this.context = context;
    }

    /**
     * Gets the drawing context of the widget.
     * @return the drawing context
     */
    @NotNull
    public DrawingContext getContext() {
        return context;
    }
}
