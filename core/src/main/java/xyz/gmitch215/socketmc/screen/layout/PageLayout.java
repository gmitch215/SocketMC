package xyz.gmitch215.socketmc.screen.layout;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * <p>Represents a layout with a header, footer, and content area. This layout is always at the top left of the screen, with {@link #isFullscreen()} always true.
 * This means that calling {@link #setWidth(int)} and {@link #setHeight(int)} will have no effect.</p>
 */
public final class PageLayout implements Layout {

    @Serial
    private static final long serialVersionUID = 607995459478006524L;

    /**
     * The default height for the header and footer.
     */
    public static final int DEFAULT_MARGIN_HEIGHT = 33;

    /**
     * The top margin of the content area.
     */
    public static final int CONTENT_MARGIN_TOP = 30;

    private final FrameLayout header = new FrameLayout();
    private final FrameLayout footer = new FrameLayout();
    private final FrameLayout contents = new FrameLayout();
    private int width;
    private int height;
    private int headerHeight;
    private int footerHeight;

    /**
     * Creates a new PageLayout using the default margin height.
     * @param marginHeight The height of the header and footer.
     */
    public PageLayout() {
        this(DEFAULT_MARGIN_HEIGHT);
    }
    
    /**
     * Creates a new PageLayout.
     * @param marginHeight The height of the header and footer.
     */
    public PageLayout(int marginHeight) {
        this(marginHeight, marginHeight);
    }
    
    /**
     * Creates a new PageLayout.
     * @param width The width of the layout.
     * @param height The height of the layout.
     * @param headerHeight The height of the header.
     * @param footerHeight The height of the footer.
     */
    public PageLayout(int headerHeight, int footerHeight) {
        this.headerHeight = headerHeight;
        this.footerHeight = footerHeight;
    }

    /**
     * Gets the height of the header.
     * @return Header Height
     */
    public int getHeaderHeight() {
        return headerHeight;
    }

    /**
     * Sets the height of the header.
     * @param headerHeight Header Height
     */
    public void setHeaderHeight(int headerHeight) {
        this.headerHeight = headerHeight;
    }

    /**
     * Gets the height of the footer.
     * @return Footer Height
     */
    public int getFooterHeight() {
        return footerHeight;
    }

    /**
     * Sets the height of the footer.
     * @param footerHeight Footer Height
     */
    public void setFooterHeight(int footerHeight) {
        this.footerHeight = footerHeight;
    }

    /**
     * Gets the height of the content area.
     * @return Content Height
     */
    public int getContentHeight() {
        return height - headerHeight - footerHeight;
    }

    /**
     * Adds an element to the header of this PageLayout.
     * @param element the element to add
     * @param settings the settings for the element
     * @return the element that was added
     * @param <T> the type of element
     * @throws IllegalArgumentException if the element or settings are null
     */
    @NotNull
    public <T extends LayoutElement> T addHeaderElement(@NotNull T element) throws IllegalArgumentException {
        return addHeaderElement(element, createDefaultSettings());
    }

    /**
     * Adds an element to the header of this PageLayout.
     * @param element the element to add
     * @param settings the settings for the element
     * @return the element that was added
     * @param <T> the type of element
     * @throws IllegalArgumentException if the element or settings are null
     */
    @NotNull
    public <T extends LayoutElement> T addHeaderElement(@NotNull T element, @NotNull LayoutSettings settings) throws IllegalArgumentException {
        if (element == null) throw new IllegalArgumentException("Element cannot be null");
        if (settings == null) throw new IllegalArgumentException("Settings cannot be null");

        header.addElement(element, settings);
        return element;
    }

    /**
     * Adds an element to the header of this PageLayout.
     * @param element the element to add
     * @param settings the generator for the element settings
     * @return the element that was added
     * @param <T> the type of element
     * @throws IllegalArgumentException if the element or settings are null
     */
    @NotNull
    public <T extends LayoutElement> T addHeaderElement(@NotNull T element, @NotNull Supplier<LayoutSettings> settings) throws IllegalArgumentException {
        if (element == null) throw new IllegalArgumentException("Element cannot be null");
        if (settings == null) throw new IllegalArgumentException("Settings Supplier cannot be null");

        return addHeaderElement(element, settings.get());
    }

    /**
     * Adds an element to the header of this PageLayout.
     * @param element the element to add
     * @param settings the function for settings applied on {@link #createDefaultSettings()}
     * @return the element that was added
     * @param <T> the type of element
     * @throws IllegalArgumentException if the element or settings are null
     */
    @NotNull
    public <T extends LayoutElement> T addHeaderElement(@NotNull T element, @NotNull Consumer<LayoutSettings> settings) throws IllegalArgumentException {
        if (element == null) throw new IllegalArgumentException("Element cannot be null");
        if (settings == null) throw new IllegalArgumentException("Settings Function cannot be null");

        LayoutSettings settings0 = createDefaultSettings();
        settings.accept(settings0);
        return addHeaderElement(element, settings0);
    }

    @Override
    public boolean isFullscreen() {
        return true;
    }

    @Override
    public void setFullscreen(boolean fullscreen) {}

    /**
     * Adds an element to the contents of this PageLayout.
     * @param element the element to add
     * @param settings the settings for the element
     * @return the element that was added
     * @param <T> the type of element
     * @throws IllegalArgumentException if the element or settings are null
     */
    @Override
    public <T extends LayoutElement> T addElement(@NotNull T element, @NotNull LayoutSettings settings) throws IllegalArgumentException {
        if (element == null) throw new IllegalArgumentException("Element cannot be null");
        if (settings == null) throw new IllegalArgumentException("Settings cannot be null");
        
        contents.addElement(element, settings);
        return element;
    }

    /**
     * Adds an element to the footer of this PageLayout.
     * @param element the element to add
     * @param settings the settings for the element
     * @return the element that was added
     * @param <T> the type of element
     * @throws IllegalArgumentException if the element or settings are null
     */
    @NotNull
    public <T extends LayoutElement> T addFooterElement(@NotNull T element) throws IllegalArgumentException {
        return addFooterElement(element, createDefaultSettings());
    }

    /**
     * Adds an element to the footer of this PageLayout.
     * @param element the element to add
     * @param settings the settings for the element
     * @return the element that was added
     * @param <T> the type of element
     * @throws IllegalArgumentException if the element or settings are null
     */
    @NotNull
    public <T extends LayoutElement> T addFooterElement(@NotNull T element, @NotNull LayoutSettings settings) throws IllegalArgumentException {
        if (element == null) throw new IllegalArgumentException("Element cannot be null");
        if (settings == null) throw new IllegalArgumentException("Settings cannot be null");

        header.addElement(element, settings);
        return element;
    }

    /**
     * Adds an element to the footer of this PageLayout.
     * @param element the element to add
     * @param settings the generator for the element settings
     * @return the element that was added
     * @param <T> the type of element
     * @throws IllegalArgumentException if the element or settings are null
     */
    @NotNull
    public <T extends LayoutElement> T addFooterElement(@NotNull T element, @NotNull Supplier<LayoutSettings> settings) throws IllegalArgumentException {
        if (element == null) throw new IllegalArgumentException("Element cannot be null");
        if (settings == null) throw new IllegalArgumentException("Settings Supplier cannot be null");

        return addHeaderElement(element, settings.get());
    }

    /**
     * Adds an element to the footer of this PageLayout.
     * @param element the element to add
     * @param settings the function for settings applied on {@link #createDefaultSettings()}
     * @return the element that was added
     * @param <T> the type of element
     * @throws IllegalArgumentException if the element or settings are null
     */
    @NotNull
    public <T extends LayoutElement> T addFooterElement(@NotNull T element, @NotNull Consumer<LayoutSettings> settings) throws IllegalArgumentException {
        if (element == null) throw new IllegalArgumentException("Element cannot be null");
        if (settings == null) throw new IllegalArgumentException("Settings Function cannot be null");

        LayoutSettings settings0 = createDefaultSettings();
        settings.accept(settings0);
        return addHeaderElement(element, settings0);
    }

    @Override
    @NotNull
    public LayoutSettings createDefaultSettings() {
        return LayoutSettings.create();
    }

    @Override
    public void visitChildren(@NotNull Consumer<LayoutElement> visitor) {
        header.visitChildren(visitor);
        contents.visitChildren(visitor);
        footer.visitChildren(visitor);
    }
    
    @Override
    public void arrangeElements() {
        header.setMinWidth(width);
        header.setMinHeight(headerHeight);
        header.setPosition(0, 0);
        header.arrangeElements();

        footer.setMinWidth(width);
        footer.setMinHeight(footerHeight);
        footer.arrangeElements();
        footer.setY(height - footerHeight);

        int contentTop = headerHeight + CONTENT_MARGIN_TOP;
        int contentBottom = height - footerHeight - contents.getHeight();
        contents.setMinWidth(width);
        contents.arrangeElements();
        contents.setPosition(0, Math.min(contentTop, contentBottom));
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }
    
    // Unused Implementation

    @Override
    public int getX() { return 0; }

    @Override
    public void setX(int x) {}

    @Override
    public int getY() { return 0; }

    @Override
    public void setY(int y) {}
}
