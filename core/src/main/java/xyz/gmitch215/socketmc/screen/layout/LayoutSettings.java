package xyz.gmitch215.socketmc.screen.layout;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents settings for a specific layout.
 */
public final class LayoutSettings implements Cloneable, Serializable {

    @Serial
    private static final long serialVersionUID = -5446055926482347494L;

    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;
    private float alignmentX;
    private float alignmentY;

    private LayoutSettings() {}

    private LayoutSettings(LayoutSettings old) {
        this.paddingLeft = old.paddingLeft;
        this.paddingTop = old.paddingTop;
        this.paddingRight = old.paddingRight;
        this.paddingBottom = old.paddingBottom;
        this.alignmentX = old.alignmentX;
        this.alignmentY = old.alignmentY;
    }

    // Implementation

    /**
     * Gets the padding on the left side of the layout.
     * @return Padding
     */
    public int getPaddingLeft() {
        return paddingLeft;
    }

    /**
     * Sets the padding on the left side of the layout.
     * @param paddingLeft Left Padding
     * @return this class, for chaining
     */
    @NotNull
    public LayoutSettings setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
        return this;
    }

    /**
     * Gets the padding on the top side of the layout.
     * @return Padding
     */
    public int getPaddingTop() {
        return paddingTop;
    }

    /**
     * Sets the padding on the top side of the layout.
     * @param paddingTop Top Padding
     * @return this class, for chaining
     */
    @NotNull
    public LayoutSettings setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
        return this;
    }

    /**
     * Gets the padding on the right side of the layout.
     * @return Right Padding
     */
    public int getPaddingRight() {
        return paddingRight;
    }

    /**
     * Sets the padding on the right side of the layout.
     * @param paddingRight Right Padding
     * @return this class, for chaining
     */
    @NotNull
    public LayoutSettings setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
        return this;
    }

    /**
     * Gets the padding on the bottom side of the layout.
     * @return Bottom Padding
     */
    public int getPaddingBottom() {
        return paddingBottom;
    }

    /**
     * Sets the padding on the bottom side of the layout.
     * @param paddingBottom Bottom Padding
     */
    @NotNull
    public LayoutSettings setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
        return this;
    }

    /**
     * Sets the padding on the horizontal sides of the layout.
     * @param horizontal Left and Right Padding
     * @return this class, for chaining
     */
    @NotNull
    public LayoutSettings setPaddingHorizontal(int horizontal) {
        this.paddingLeft = horizontal;
        this.paddingRight = horizontal;
        return this;
    }

    /**
     * Sets the padding on the vertical sides of the layout.
     * @param vertical Top and Bottom Padding
     * @return this class, for chaining
     */
    public LayoutSettings setPaddingVertical(int vertical) {
        this.paddingTop = vertical;
        this.paddingBottom = vertical;
        return this;
    }

    /**
     * Sets the padding on all sides of the layout.
     * @param horizontal Left and Right Padding
     * @param vertical Top and Bottom Padding
     * @return this class, for chaining
     */
    @NotNull
    public LayoutSettings setPadding(int horizontal, int vertical) {
        this.paddingLeft = horizontal;
        this.paddingTop = vertical;
        this.paddingRight = horizontal;
        this.paddingBottom = vertical;
        return this;
    }

    /**
     * Sets the padding on all sides of the layout.
     * @param left Left Padding
     * @param top Top Padding
     * @param right Right Padding
     * @param bottom Bottom Padding
     * @return this class, for chaining
     */
    @NotNull
    public LayoutSettings setPadding(int left, int top, int right, int bottom) {
        this.paddingLeft = left;
        this.paddingTop = top;
        this.paddingRight = right;
        this.paddingBottom = bottom;
        return this;
    }

    /**
     * Gets the alignment of the layout on the x-axis.
     * @return X-Axis Alignment Percentage
     */
    public float getAlignmentX() {
        return alignmentX;
    }

    /**
     * Sets the alignment of the layout on the x-axis.
     * @param alignmentX X-Axis Alignment Percentage, between 0 and 1
     * @return this class, for chaining
     * @throws IllegalArgumentException if alignment is not between 0 and 1
     */
    @NotNull
    public LayoutSettings setAlignmentX(float alignmentX) throws IllegalArgumentException {
        if (alignmentX < 0 || alignmentX > 1) throw new IllegalArgumentException("Alignment must be between 0 and 1");
        this.alignmentX = alignmentX;
        return this;
    }

    /**
     * Sets the alignment of the layout on the x-axis to the left, 0%.
     * @return this class, for chaining
     */
    @NotNull
    public LayoutSettings alignHorizontallyLeft() {
        this.alignmentX = 0;
        return this;
    }

    /**
     * Sets the alignment of the layout on the x-axis to the center, 50%.
     * @return this class, for chaining
     */
    @NotNull
    public LayoutSettings alignHorizontallyCenter() {
        this.alignmentX = 0.5f;
        return this;
    }

    /**
     * Sets the alignment of the layout on the x-axis to the right, 100%.
     * @return this class, for chaining
     */
    @NotNull
    public LayoutSettings alignHorizontallyRight() {
        this.alignmentX = 1;
        return this;
    }

    /**
     * Gets the alignment of the layout on the y-axis.
     * @return Y-Axis Alignment Percentage
     */
    public float getAlignmentY() {
        return alignmentY;
    }

    /**
     * Sets the alignment of the layout on the y-axis.
     * @param alignmentY Y-Axis Alignment Percentage, between 0 and 1
     * @return this class, for chaining
     * @throws IllegalArgumentException if alignment is not between 0 and 1
     */
    public LayoutSettings setAlignmentY(float alignmentY) throws IllegalArgumentException {
        if (alignmentY < 0 || alignmentY > 1) throw new IllegalArgumentException("Alignment must be between 0 and 1");
        this.alignmentY = alignmentY;
        return this;
    }

    /**
     * Sets the alignment of the layout on the y-axis to the top, 0%.
     * @return this class, for chaining
     */
    @NotNull
    public LayoutSettings alignVerticallyTop() {
        this.alignmentY = 0;
        return this;
    }

    /**
     * Sets the alignment of the layout on the y-axis to the center, 50%.
     * @return this class, for chaining
     */
    @NotNull
    public LayoutSettings alignVerticallyCenter() {
        this.alignmentY = 0.5f;
        return this;
    }

    /**
     * Sets the alignment of the layout on the y-axis to the bottom, 100%.
     * @return this class, for chaining
     */
    @NotNull
    public LayoutSettings alignVerticallyBottom() {
        this.alignmentY = 1;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LayoutSettings that)) return false;
        return paddingLeft == that.paddingLeft && paddingTop == that.paddingTop && paddingRight == that.paddingRight && paddingBottom == that.paddingBottom && Float.compare(alignmentX, that.alignmentX) == 0 && Float.compare(alignmentY, that.alignmentY) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paddingLeft, paddingTop, paddingRight, paddingBottom, alignmentX, alignmentY);
    }

    @Override
    public String toString() {
        return "LayoutSettings{" +
                "paddingLeft=" + paddingLeft +
                ", paddingTop=" + paddingTop +
                ", paddingRight=" + paddingRight +
                ", paddingBottom=" + paddingBottom +
                ", xAlignment=" + alignmentX +
                ", yAlignment=" + alignmentY +
                '}';
    }

    @Override
    public LayoutSettings clone() {
        return new LayoutSettings(this);
    }

    // Constructors

    /**
     * Creates new LayoutSettings with empty padding and alignment.
     * @return LayoutSettings
     */
    @NotNull
    public static LayoutSettings create() {
        return new LayoutSettings();
    }

    /**
     * Creates new LayoutSettings with the specified padding and alignment.
     * @param paddingLeft Left Padding
     * @param paddingTop Top Padding
     * @param paddingRight Right Padding
     * @param paddingBottom Bottom Padding
     * @param alignmentX X-Axis Alignment Percentage
     * @param alignmentY Y-Axis Alignment Percentage
     * @return LayoutSettings
     */
    @NotNull
    public static LayoutSettings create(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom, float alignmentX, float alignmentY) {
        LayoutSettings settings = new LayoutSettings();

        settings.paddingLeft = paddingLeft;
        settings.paddingTop = paddingTop;
        settings.paddingRight = paddingRight;
        settings.paddingBottom = paddingBottom;
        settings.alignmentX = alignmentX;
        settings.alignmentY = alignmentY;
        return settings;
    }

    /**
     * Creates new LayoutSettings with the specified padding.
     * @param paddingLeft Left Padding
     * @param paddingTop Top Padding
     * @param paddingRight Right Padding
     * @param paddingBottom Bottom Padding
     * @return LayoutSettings
     */
    @NotNull
    public static LayoutSettings create(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        return create(paddingLeft, paddingTop, paddingRight, paddingBottom, 0F, 0F);
    }

    /**
     * Creates new LayoutSettings with the specified padding and alignment.
     * @param padding Padding
     * @return LayoutSettings
     */
    @NotNull
    public static LayoutSettings create(int padding) {
        return create(padding, padding, padding, padding);
    }

    /**
     * Creates new LayoutSettings with the specified padding and alignment.
     * @param padding Padding
     * @param alignmentX X-Axis Alignment Percentage
     * @param alignmentY Y-Axis Alignment Percentage
     * @return LayoutSettings
     */
    @NotNull
    public static LayoutSettings create(int padding, float alignmentX, float alignmentY) {
        return create(padding, padding, padding, padding, alignmentX, alignmentY);
    }

    /**
     * Creates new LayoutSettings with the specified padding and alignment.
     * @param padding Padding
     * @param alignment Alignment Percentage
     * @return LayoutSettings
     */
    @NotNull
    public static LayoutSettings create(int padding, float alignment) {
        return create(padding, padding, padding, padding, alignment, alignment);
    }

    /**
     * Creates new LayoutSettings with the specified alignment.
     * @param alignmentX X-Axis Alignment Percentage
     * @param alignmentY Y-Axis Alignment Percentage
     * @return LayoutSettings
     */
    @NotNull
    public static LayoutSettings create(float alignmentX, float alignmentY) {
        return create(0, 0, 0, 0, alignmentX, alignmentY);
    }

    /**
     * Creates new LayoutSettings with both alignments set to the center.
     * @return LayoutSettings
     */
    @NotNull
    public static LayoutSettings createCentered() {
        return create(0.5f, 0.5f);
    }

    /**
     * Creates new LayoutSettings with the horizontal alignment set to the center.
     * @return LayoutSettings
     */
    @NotNull
    public static LayoutSettings createHorizontallyCentered() {
        return create(0.5f, 0);
    }

    /**
     * Creates new LayoutSettings with the vertical alignment set to the center.
     * @return LayoutSettings
     */
    @NotNull
    public static LayoutSettings createVerticallyCentered() {
        return create(0, 0.5f);
    }

}
