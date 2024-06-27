package xyz.gmitch215.socketmc.screen.layout;

import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.util.ElementBounds;
import xyz.gmitch215.socketmc.util.math.MathUtil;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>Represents a Screen Layout with basic implementation.</p>
 * <p>You can extend this class to implement your own custom layout. The Layout must be serializable.</p>
 */
public abstract class AbstractLayout implements Layout {

    @Serial
    private static final long serialVersionUID = 1952806375002891474L;

    /**
     * The x position of the layout.
     */
    protected int x;

    /**
     * The y position of the layout.
     */
    protected int y;

    /**
     * The width of the layout.
     */
    protected int width;

    /**
     * The height of the layout.
     */
    protected int height;

    private boolean fullscreen;

    /**
     * Constructs a new AbstractLayout with the specified bounds.
     * @param bounds the bounds of the layout
     */
    protected AbstractLayout(@NotNull ElementBounds bounds) {
        this(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
    }
    
    /**
     * Constructs a new AbstractLayout with the specified bounds.
     * @param x the x-coordinate of the layout
     * @param y the y-coordinate of the layout
     * @param width the width of the layout
     * @param height the height of the layout
     * @throws IllegalArgumentException if the size is negative
     */
    protected AbstractLayout(int x, int y, int width, int height) throws IllegalArgumentException {
        if (width < 0) throw new IllegalArgumentException("Width cannot be negative");
        if (height < 0) throw new IllegalArgumentException("Height cannot be negative");

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
        this.visitChildren(element -> {
            int elementX = element.getX() + (x - getX());
            element.setX(elementX);
        });
        
        this.x = x;
    }

    @Override
    public final int getY() {
        return y;
    }

    @Override
    public final void setY(int y) {
        this.visitChildren(element -> {
            int elementY = element.getY() + (y - this.getY());
            element.setY(elementY);
        });
        
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
    public final boolean isFullscreen() {
        return fullscreen;
    }

    @Override
    public final void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }

    /**
     * A utility class to contain various elements inside of a Layout and its settings.
     */
    protected static abstract class AbstractChildContainer implements Serializable {

        @Serial
        private static final long serialVersionUID = -508609005077757914L;

        private final LayoutElement element;
        private final LayoutSettings settings;

        /**
         * Constructs a new AbstractChildContainer with the specified element and settings.
         * @param element the element
         * @param settings the settings
         */
        protected AbstractChildContainer(@NotNull LayoutElement element, @NotNull LayoutSettings settings) {
            if (element == null) throw new IllegalArgumentException("Element cannot be null");
            if (settings == null) throw new IllegalArgumentException("Settings cannot be null");
            
            this.element = element;
            this.settings = settings;
        }

        /**
         * Gets the element this container is holding.
         * @return the element
         */
        @NotNull
        public LayoutElement getElement() {
            return element;
        }

        /**
         * Gets the settings of this container.
         * @return the settings
         */
        @NotNull
        public LayoutSettings getSettings() {
            return settings;
        }

        /**
         * Gets the height of the element with vertical padding.
         * @return the height
         */
        public final int getHeight() {
            return element.getHeight() + settings.getPaddingTop() + settings.getPaddingBottom();
        }

        /**
         * Gets the width of the element with horizontal padding.
         * @return the width
         */
        public final int getWidth() {
            return element.getWidth() + settings.getPaddingLeft() + settings.getPaddingRight();
        }

        /**
         * Sets the horizontal bounds of the element.
         * @param x the x-coordinate
         * @param width the width
         */
        public final void setHorizontalBounds(int x, int width) {
            float left = settings.getPaddingLeft();
            float width0 = width - element.getWidth() - settings.getPaddingRight();
            int offset = (int) MathUtil.lerp(settings.getAlignmentX(), left, width0);

            element.setX(offset + x);
        }

        /**
         * Sets the vertical bounds of the element.
         * @param y the y-coordinate
         * @param height the height
         */
        public final void setVerticalBounds(int y, int height) {
            float top = settings.getPaddingTop();
            float height0 = height - element.getHeight() - settings.getPaddingBottom();
            int offset = Math.round(MathUtil.lerp(settings.getAlignmentY(), top, height0));

            element.setY(offset + y);
        }
    }
}
