package xyz.gmitch215.socketmc.screen.ui;

import xyz.gmitch215.socketmc.screen.Positionable;
import xyz.gmitch215.socketmc.screen.util.Tooltip;
import xyz.gmitch215.socketmc.util.ElementBounds;
import xyz.gmitch215.socketmc.util.SerializableConsumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serial;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Represents a Widget on the screen.
 * <strong>This class is not legal for implementation!</strong>
 */
public abstract class AbstractWidget implements Positionable {

    /**
     * The default padding around text elements.
     */
    public static final int DEFAULT_PADDING = 4;

    @Serial
    private static final long serialVersionUID = -8273686084189110401L;

    /**
     * The x-coordinate of this widget.
     */
    protected int x;

    /**
     * The y-coordinate of this widget.
     */
    protected int y;

    /**
     * The width of this widget.
     */
    protected int width;

    /**
     * The height of this widget.
     */
    protected int height;

    /**
     * The tooltip for this widget.
     */
    @Nullable
    protected Tooltip tooltip;

    private final Set<SerializableConsumer<Positionable>> onClickListeners = new HashSet<>();

    /**
     * Constructs a new widget.
     * @param bounds the bounds
     */
    protected AbstractWidget(@NotNull ElementBounds bounds) {
        this(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
    }

    /**
     * Constructs a new widget.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @throws IllegalArgumentException if coordinates or dimensions are negative
     */
    protected AbstractWidget(int x, int y, int width, int height) throws IllegalArgumentException {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates must be non-negative");
        if (width < 0 || height < 0) throw new IllegalArgumentException("Width and height must be non-negative");

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public final int getX() {
        return x;
    }

    @Override
    public final void setX(int x) {
        this.x = x;
    }

    @Override
    public final int getY() {
        return y;
    }

    @Override
    public final void setY(int y) {
        this.y = y;
    }

    @Override
    public final int getWidth() {
        return width;
    }

    @Override
    public final void setWidth(int width) {
        this.width = width;
    }

    @Override
    public final int getHeight() {
        return height;
    }

    @Override
    public final void setHeight(int height) {
        this.height = height;
    }

    @Override
    public final Tooltip getTooltip() {
        return tooltip;
    }

    @Override
    public final void setTooltip(@Nullable Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    @Override
    public final void visitWidgets(@NotNull Consumer<AbstractWidget> visitor) {
        visitor.accept(this);
    }

    @Override
    public void onClick(@NotNull SerializableConsumer<Positionable> listener) {
        onClickListeners.add(listener);
    }

    @Override
    public void clearListeners() {
        onClickListeners.clear();
    }

    @Override
    public Set<SerializableConsumer<Positionable>> getListeners() {
        return Set.copyOf(onClickListeners);
    }

    @Override
    public final String toString() {
        return getClass().getSimpleName() + "@ [" + x + ", " + y + "] (" + width + "px * " + height + "px)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof AbstractWidget that)) return false;

        return x == that.x && y == that.y && width == that.width && height == that.height;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(x, y, width, height);
    }
}
