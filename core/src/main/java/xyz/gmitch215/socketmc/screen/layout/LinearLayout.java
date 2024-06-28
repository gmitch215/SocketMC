package xyz.gmitch215.socketmc.screen.layout;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.util.function.Consumer;

/**
 * Represents a layout in a straight line, internally wrapped around a {@link GridLayout}.
 */
public final class LinearLayout implements Layout {

    @Serial
    private static final long serialVersionUID = -1445731247085305193L;

    private final GridLayout wrapped;
    private final Orientation orientation;
    private int nextChildIndex = 0;

    private LinearLayout(Orientation orientation) {
        this(0, 0, orientation);
    }

    /**
     * Creates a new LinearLayout with the specified orientation.
     * @param width The width of the layout
     * @param height The height of the layout
     * @param orientation The orientation of the layout
     */
    public LinearLayout(int width, int height, @NotNull Orientation orientation) throws IllegalArgumentException {
        if (orientation == null) throw new IllegalArgumentException("Orientation cannot be null");

        this.wrapped = new GridLayout(width, height);
        this.orientation = orientation;
    }

    /**
     * Sets the spacing for this layout.
     * @param spacing The spacing to set
     * @return this class, for chaining
     */
    @NotNull
    public LinearLayout spacing(int spacing) {
        orientation.setSpacing(wrapped, spacing);
        return this;
    }

    @Override
    public boolean isFullscreen() {
        return wrapped.isFullscreen();
    }

    @Override
    public void setFullscreen(boolean fullscreen) {
        wrapped.setFullscreen(fullscreen);
    }

    @Override
    public int getX() {
        return wrapped.getX();
    }

    @Override
    public void setX(int x) {
        wrapped.setX(x);
    }

    @Override
    public int getY() {
        return wrapped.getY();
    }

    @Override
    public void setY(int y) {
        wrapped.setY(y);
    }

    @Override
    public int getWidth() {
        return wrapped.getWidth();
    }

    @Override
    public void setWidth(int width) {
        wrapped.setWidth(width);
    }

    @Override
    public int getHeight() {
        return wrapped.getHeight();
    }

    @Override
    public void setHeight(int height) {
        wrapped.setHeight(height);
    }

    @Override
    public <T extends LayoutElement> T addElement(@NotNull T element, @NotNull LayoutSettings settings) throws IllegalArgumentException {
        orientation.addElement(this.wrapped, element, nextChildIndex, settings);
        nextChildIndex++;

        return element;
    }

    @Override
    public @NotNull LayoutSettings createDefaultSettings() {
        return wrapped.createDefaultSettings();
    }

    @Override
    public void visitChildren(@NotNull Consumer<LayoutElement> visitor) {
        wrapped.visitChildren(visitor);
    }

    @Override
    public void arrangeElements() {
        wrapped.arrangeElements();
    }

    /**
     * Creates a horizontal layout.
     * @return A Horizontal LinearLayout
     */
    @NotNull
    public static LinearLayout horizontal() {
        return new LinearLayout(Orientation.HORIZONTAL);
    }

    /**
     * Creates a vertical layout.
     * @return A Vertical LinearLayout
     */
    @NotNull
    public static LinearLayout vertical() {
        return new LinearLayout(Orientation.VERTICAL);
    }

    /**
     * Represents the orientation for a LinearLayout.
     */
    public enum Orientation {
        /**
         * A LinearLayout moving left to right.
         */
        HORIZONTAL,

        /**
         * A LinearLayout moving up to down.
         */
        VERTICAL;

        /**
         * Sets the spacing of the layout.
         * @param layout The layout to set the spacing of
         * @param spacing The spacing to set
         */
        public void setSpacing(@NotNull GridLayout layout, int spacing) {
            if (layout == null) throw new IllegalArgumentException("Layout cannot be null");

            switch (this) {
                case HORIZONTAL -> layout.setColumnSpacing(spacing);
                case VERTICAL -> layout.setRowSpacing(spacing);
            }
        }

        /**
         * Adds an element to the layout.
         * @param layout The layout to add the element to
         * @param element The element to add
         * @param index The index to add the element at
         * @param layoutSettings The settings to apply to the element
         * @return The element that was added
         * @param <T> The type of the element
         * @throws IllegalArgumentException if the layout, element, or settings are null
         */
        @NotNull
        public <T extends LayoutElement> T addElement(@NotNull GridLayout layout, @NotNull T element, int index, @NotNull LayoutSettings layoutSettings) throws IllegalArgumentException {
            if (layout == null) throw new IllegalArgumentException("Layout cannot be null");

            return switch (this) {
                case HORIZONTAL -> layout.addCell(element, 0, index, layoutSettings);
                case VERTICAL -> layout.addCell(element, index, 0, layoutSettings);
            };
        }
    }
}
