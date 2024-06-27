package xyz.gmitch215.socketmc.screen.layout;

import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.util.ElementBounds;
import xyz.gmitch215.socketmc.util.math.Divisor;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Represents a layout with equal spacing.
 */
public final class EqualSpacingLayout extends AbstractLayout {

    @Serial
    private static final long serialVersionUID = -3368163773094600646L;

    private final List<ChildContainer> children = new ArrayList<>();
    private final Orientation orientation;

    /**
     * Creates a new equal spacing layout with the given bounds and orientation.
     * @param bounds The bounds of the layout
     * @param orientation The orientation of the layout
     */
    public EqualSpacingLayout(@NotNull ElementBounds bounds, @NotNull Orientation orientation) {
        this(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), orientation);
    }

    /**
     * Creates a new equal spacing layout with the given width, height, and orientation.
     * @param width The width of the layout
     * @param height The height of the layout
     * @param orientation The orientation of the layout
     */
    public EqualSpacingLayout(int width, int height, @NotNull Orientation orientation) {
        this(0, 0, width, height, orientation);
    }

    /**
     * Creates a new equal spacing layout with the given x, y, width, height, and orientation.
     * @param x The x position of the layout
     * @param y The y position of the layout
     * @param width The width of the layout
     * @param height The height of the layout
     * @param orientation The orientation of the layout
     */
    public EqualSpacingLayout(int x, int y, int width, int height, @NotNull Orientation orientation) {
        super(x, y, width, height);

        if (orientation == null) throw new IllegalArgumentException("Orientation cannot be null");
        this.orientation = orientation;
    }
    
    @Override
    public <T extends LayoutElement> T addElement(@NotNull T element, @NotNull LayoutSettings settings) throws IllegalArgumentException {
        return null;
    }

    @Override
    @NotNull
    public LayoutSettings createDefaultSettings() {
        return LayoutSettings.create();
    }

    @Override
    public void visitChildren(@NotNull Consumer<LayoutElement> visitor) {
        children.forEach(container -> visitor.accept(container.getElement()));
    }
    
    @Override
    public void arrangeElements() {
        super.arrangeElements();
        if (children.isEmpty()) return;
        
        int i = 0;
        int bound = orientation.getSecondaryLength(this);
        
        for (ChildContainer container : children) {
            i += orientation.getPrimaryLength(container);
            bound = Math.max(bound, orientation.getSecondaryLength(container));
        }
        
        int length = orientation.getPrimaryLength(this) - i;
        int pos = orientation.getPrimaryPosition(this);

        Iterator<ChildContainer> iterator = children.iterator();
        ChildContainer firstContainer = iterator.next();
        orientation.setPrimaryPosition(firstContainer, pos);

        pos += orientation.getPrimaryLength(firstContainer);
        if (children.size() >= 2) {
            Divisor divisor = new Divisor(length, children.size() - 1);
            while (divisor.hasNext()) {
                ChildContainer container = iterator.next();
                orientation.setPrimaryPosition(container, pos += divisor.next());
                pos += orientation.getPrimaryLength(container);
            }
        }
        
        int pos2 = orientation.getSecondaryPosition(this);
        for (ChildContainer childContainer4 : children)
            orientation.setSecondaryPosition(childContainer4, pos2, bound);
        
        switch (orientation) {
            case HORIZONTAL -> this.height = bound;
            case VERTICAL -> this.width = bound;
        }
    }

    /**
     * The orientation of the equal spacing layout.
     */
    public enum Orientation {
        /**
         * Horizontally oriented.
         */
        HORIZONTAL,

        /**
         * Vertically oriented.
         */
        VERTICAL
        
        ;

        /**
         * Gets the primary length according to this orientation.
         * @param element The element to get the primary length of
         * @return The primary length of the element.
         * @throws IllegalArgumentException if the element is null
         */
        public int getPrimaryLength(@NotNull LayoutElement element) throws IllegalArgumentException {
            if (element == null) throw new IllegalArgumentException("Element cannot be null");
            
            return switch (this) {
                case HORIZONTAL -> element.getWidth();
                case VERTICAL -> element.getHeight();
            };
        }

        /**
         * Gets the primary length according to this orientation.
         * @param container The container to get the primary length of
         * @return The primary length of the container.
         */
        public int getPrimaryLength(@NotNull ChildContainer container) throws IllegalArgumentException {
            if (container == null) throw new IllegalArgumentException("Container cannot be null");
            
            return switch (this) {
                case HORIZONTAL -> container.getWidth();
                case VERTICAL -> container.getHeight();
            };
        }

        /**
         * Gets the secondary length according to this orientation.
         * @param element The element to get the secondary length of
         * @return The secondary length of the element.
         * @throws IllegalArgumentException if the element is null
         */
        public int getSecondaryLength(@NotNull LayoutElement element) throws IllegalArgumentException {
            if (element == null) throw new IllegalArgumentException("Element cannot be null");

            return switch (this) {
                case HORIZONTAL -> element.getHeight();
                case VERTICAL -> element.getWidth();
            };
        }

        /**
         * Gets the secondary length according to this orientation.
         * @param container The container to get the secondary length of
         * @return The secondary length of the container.
         * @throws IllegalArgumentException if the container is null
         */
        public int getSecondaryLength(@NotNull ChildContainer container) throws IllegalArgumentException {
            if (container == null) throw new IllegalArgumentException("Container cannot be null");
            return switch (this) {
                case HORIZONTAL -> container.getHeight();
                case VERTICAL -> container.getWidth();
            };
        }

        /**
         * Sets the primary position of the container.
         * @param container The container to set the primary position of
         * @param position The position to set the primary position to
         * @throws IllegalArgumentException if the container is null
         */
        public void setPrimaryPosition(@NotNull ChildContainer container, int position) throws IllegalArgumentException {
            if (container == null) throw new IllegalArgumentException("Container cannot be null");

            switch (this) {
                case HORIZONTAL -> container.setHorizontalBounds(position, container.getWidth());
                case VERTICAL -> container.setVerticalBounds(position, container.getHeight());
            }
        }

        /**
         * Sets the secondary position of the container.
         * @param container The container to set the secondary position of
         * @param position The position to set the secondary position to
         * @param length The length of the container
         */
        public void setSecondaryPosition(@NotNull ChildContainer container, int position, int length) {
            if (container == null) throw new IllegalArgumentException("Container cannot be null");

            switch (this) {
                case HORIZONTAL -> container.setVerticalBounds(position, length);
                case VERTICAL -> container.setHorizontalBounds(position, length);
            }
        }

        /**
         * Gets the primary position of the element.
         * @param element The element to get the primary position of
         * @return The primary position of the element
         * @throws IllegalArgumentException if the element is null
         */
        public int getPrimaryPosition(@NotNull LayoutElement element) throws IllegalArgumentException {
            if (element == null) throw new IllegalArgumentException("Element cannot be null");

            return switch (this) {
                case HORIZONTAL -> element.getX();
                case VERTICAL -> element.getY();
            };
        }

        /**
         * Gets the secondary position of the element.
         * @param element The element to get the secondary position of
         * @return The secondary position of the element
         * @throws IllegalArgumentException if the element is null
         */
        public int getSecondaryPosition(@NotNull LayoutElement element) throws IllegalArgumentException {
            return switch (this) {
                case HORIZONTAL -> element.getY();
                case VERTICAL -> element.getX();
            };
        }
    }
}
