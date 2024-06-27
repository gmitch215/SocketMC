package xyz.gmitch215.socketmc.screen.layout;

import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.util.ElementBounds;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Represents a layout with multiple children, rendered in a frame-like layout.
 */
public final class FrameLayout extends AbstractLayout {

    @Serial
    private static final long serialVersionUID = -4567145354746673921L;

    private final List<ChildContainer> children = new ArrayList<>();
    private int minWidth;
    private int minHeight;

    /**
     * Creates a new FrameLayout at the origin with no size.
     */
    public FrameLayout() {
        this(0, 0, 0 ,0);
    }

    /**
     * Creates a new FrameLayout with the given bounds.
     * @param bounds The bounds of the layout.
     */
    public FrameLayout(@NotNull ElementBounds bounds) {
        this(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
    }

    /**
     * Creates a new FrameLayout at the origin with the given size.
     * @param width The width of the layout.
     * @param height The height of the layout.
     */
    public FrameLayout(int width, int height) {
        this(0, 0, width, height);
    }

    /**
     * Creates a new FrameLayout with the given position and size.
     * @param x The x position of the layout.
     * @param y The y position of the layout.
     * @param width The width of the layout.
     * @param height The height of the layout.
     */
    public FrameLayout(int x, int y, int width, int height) {
        super(x, y, width, height);
        setMinSize(width, height);
    }

    /**
     * Gets an immutable copy of all the children in this layout.
     * @return All FrameLayout Children
     */
    @NotNull
    public List<ChildContainer> getChildren() {
        return List.copyOf(children);
    }

    /**
     * Gets the minimum width of the layout.
     * @return The minimum width of the layout.
     */
    public int getMinWidth() {
        return minWidth;
    }

    /**
     * Sets the minimum width of the layout.
     * @param minWidth The minimum width of the layout.
     * @return this class, for chaining
     * @throws IllegalArgumentException if the minimum width is negative
     */
    @NotNull
    public FrameLayout setMinWidth(int minWidth) throws IllegalArgumentException {
        if (minWidth < 0) throw new IllegalArgumentException("Minimum width must be greater than or equal to 0");
        this.minWidth = minWidth;
        return this;
    }

    /**
     * Gets the minimum height of the layout.
     * @return The minimum height of the layout.
     */
    public int getMinHeight() {
        return minHeight;
    }

    /**
     * Sets the minimum height of the layout.
     * @param minHeight The minimum height of the layout.
     * @return this class, for chaining
     * @throws IllegalArgumentException if the minimum height is negative
     */
    @NotNull
    public FrameLayout setMinHeight(int minHeight) throws IllegalArgumentException {
        if (minHeight < 0) throw new IllegalArgumentException("Minimum height must be greater than or equal to 0");
        this.minHeight = minHeight;
        return this;
    }

    /**
     * Sets the minimum size of the layout.
     * @param minWidth The minimum width of the layout.
     * @param minHeight The minimum height of the layout.
     * @return this class, for chaining
     * @throws IllegalArgumentException if the minimum width or height is negative
     */
    @NotNull
    public FrameLayout setMinSize(int minWidth, int minHeight) throws IllegalArgumentException {
        setMinWidth(minWidth);
        setMinHeight(minHeight);
        return this;
    }

    @Override
    public <T extends LayoutElement> T addElement(@NotNull T element, @NotNull LayoutSettings settings) throws IllegalArgumentException {
        if (element == null) throw new IllegalArgumentException("Element cannot be null");
        if (settings == null) throw new IllegalArgumentException("Settings cannot be null");

        this.children.add(new ChildContainer(element, settings));
        return element;
    }

    @Override
    @NotNull
    public LayoutSettings createDefaultSettings() {
        return LayoutSettings.createCentered();
    }

    @Override
    public void visitChildren(@NotNull Consumer<LayoutElement> visitor) {
        children.forEach(container -> visitor.accept(container.getElement()));
    }

    @Override
    public void arrangeElements() {
        super.arrangeElements();
        int width = minWidth;
        int height = minHeight;

        for (ChildContainer container : children) {
            width = Math.max(width, container.getWidth());
            height = Math.max(height, container.getHeight());
        }

        for (ChildContainer container : children) {
            container.setHorizontalBounds(getX(), width);
            container.setVerticalBounds(getY(), height);
        }

        this.width = width;
        this.height = height;
    }
}
