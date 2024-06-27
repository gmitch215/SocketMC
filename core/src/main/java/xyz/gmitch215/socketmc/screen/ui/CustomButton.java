package xyz.gmitch215.socketmc.screen.ui;

import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.util.ElementBounds;
import xyz.gmitch215.socketmc.util.render.DrawingContext;
import xyz.gmitch215.socketmc.util.render.text.Text;

import java.io.Serial;

/**
 * Represents a custom button, rendered through a {@link DrawingContext}.
 */
public final class CustomButton extends AbstractButton {

    @Serial
    private static final long serialVersionUID = 7444823884325664668L;

    private final DrawingContext context;
    private boolean renderDefault;

    /**
     * Creates a new {@link CustomButton}.
     * @param bounds the bounds of the button
     * @param message the message of the button
     * @param context the drawing context
     * @throws IllegalArgumentException if the text or drawing context is null
     */
    public CustomButton(@NotNull ElementBounds bounds, @NotNull Text message, @NotNull DrawingContext context) throws IllegalArgumentException {
        this(bounds, message, context, true);
    }

    /**
     * Creates a new {@link CustomButton}.
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @param message the message of the button
     * @param context the drawing context
     * @throws IllegalArgumentException if the text or drawing context is null
     */
    public CustomButton(int x, int y, @NotNull Text message, @NotNull DrawingContext context) throws IllegalArgumentException {
        this(x, y, message, context, true);
    }

    /**
     * Creates a new {@link CustomButton}.
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @param width the width of the button
     * @param height the height of the button
     * @param message the message of the button
     * @param context the drawing context
     * @throws IllegalArgumentException if the bounds are invalid, or if the text or drawing context is null
     */
    public CustomButton(int x, int y, int width, int height, @NotNull Text message, @NotNull DrawingContext context) throws IllegalArgumentException {
        this(x, y, width, height, message, context, true);
    }

    /**
     * Creates a new {@link CustomButton}.
     * @param bounds the bounds of the button
     * @param message the message of the button
     * @param context the drawing context
     * @param renderDefault whether to render the default button
     * @throws IllegalArgumentException if the text or drawing context is null
     */
    public CustomButton(@NotNull ElementBounds bounds, @NotNull Text message, @NotNull DrawingContext context, boolean renderDefault) throws IllegalArgumentException {
        super(bounds, message);

        if (context == null) throw new IllegalArgumentException("Drawing context cannot be null");
        this.context = context;
        this.renderDefault = renderDefault;
    }

    /**
     * Creates a new {@link CustomButton}.
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @param message the message of the button
     * @param context the drawing context
     * @param renderDefault whether to render the default button
     * @throws IllegalArgumentException if the text or drawing context is null
     */
    public CustomButton(int x, int y, @NotNull Text message, @NotNull DrawingContext context, boolean renderDefault) throws IllegalArgumentException {
        super(x, y, message);

        if (context == null) throw new IllegalArgumentException("Drawing context cannot be null");
        this.context = context;
        this.renderDefault = renderDefault;
    }

    /**
     * Creates a new {@link CustomButton}.
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @param width the width of the button
     * @param height the height of the button
     * @param message the message of the button
     * @param context the drawing context
     * @param renderDefault whether to render the default button
     * @throws IllegalArgumentException if the bounds are invalid, or if the text or drawing context is null
     */
    public CustomButton(int x, int y, int width, int height, @NotNull Text message, @NotNull DrawingContext context, boolean renderDefault) throws IllegalArgumentException {
        super(x, y, width, height, message);

        if (context == null) throw new IllegalArgumentException("Drawing context cannot be null");
        this.context = context;
        this.renderDefault = renderDefault;
    }

    /**
     * Creates a new {@link CustomButton}.
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @param width the width of the button
     * @param height the height of the button
     * @param messageJSON the message of the button
     * @param context the drawing context
     * @param renderDefault whether to render the default button
     * @throws IllegalArgumentException if the bounds are invalid, or if the text or drawing context is null
     */
    public CustomButton(int x, int y, int width, int height, @NotNull String messageJSON, @NotNull DrawingContext context, boolean renderDefault) throws IllegalArgumentException {
        super(x, y, width, height, messageJSON);

        if (context == null) throw new IllegalArgumentException("Drawing context cannot be null");
        this.context = context;
        this.renderDefault = renderDefault;
    }

    /**
     * Gets the drawing context of the button.
     * @return the drawing context
     */
    @NotNull
    public DrawingContext getContext() {
        return context;
    }

    /**
     * Gets whether the default button setup should be rendered.
     * @return Whether to render the default button
     */
    public boolean isRenderDefault() {
        return renderDefault;
    }

    /**
     * Sets whether to render the default button.
     * @param renderDefault whether to render the default button
     */
    public void setRenderDefault(boolean renderDefault) {
        this.renderDefault = renderDefault;
    }
}
