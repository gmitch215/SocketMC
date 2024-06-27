package xyz.gmitch215.socketmc.screen.layout;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.gmitch215.socketmc.screen.ui.AbstractWidget;

import java.io.Serializable;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Represents a Screen Layout.
 */
public interface Layout extends LayoutElement, Serializable {

    /**
     * Gets whether this layout is fullscreen. If true, this layout will automatically have its witdh and height set to the screen's width and height.
     * @return true if this layout is fullscreen
     */
    boolean isFullscreen();

    /**
     * Sets whether this layout is fullscreen. If true, this layout will automatically have its witdh and height set to the screen's width and height.
     * @param fullscreen true if this layout is fullscreen
     */
    void setFullscreen(boolean fullscreen);

    /**
     * Adds an element to this layout using the default settings.
     * @param element the element to add
     * @return the added element
     * @param <T> the type of the element
     * @throws IllegalArgumentException if the element is null
     */
    default <T extends LayoutElement> T addElement(@NotNull T element) throws IllegalArgumentException {
        if (element == null) throw new IllegalArgumentException("Element cannot be null");
        return addElement(element, createDefaultSettings());
    }

    /**
     * Adds an element to this layout.
     * @param element the element to add
     * @param settings the generator for the element settings
     * @return the added element
     * @param <T> the type of the element
     * @throws IllegalArgumentException if the element or settings are null
     */
    default <T extends LayoutElement> T addElement(@NotNull T element, @NotNull Supplier<LayoutSettings> settings) throws IllegalArgumentException {
        if (element == null) throw new IllegalArgumentException("Element cannot be null");
        if (settings == null) throw new IllegalArgumentException("Settings Supplier cannot be null");

        return addElement(element, settings.get());
    }

    /**
     * Adds an element to this layout.
     * @param element the element to add
     * @param settings the function for settings applied on {@link #createDefaultSettings()}
     * @return the added element
     * @param <T> the type of the element
     * @throws IllegalArgumentException if the element or settings are null
     */
    default <T extends LayoutElement> T addElement(@NotNull T element, @NotNull Consumer<LayoutSettings> settings) throws IllegalArgumentException {
        if (element == null) throw new IllegalArgumentException("Element cannot be null");
        if (settings == null) throw new IllegalArgumentException("Settings Function cannot be null");

        LayoutSettings settings0 = createDefaultSettings();
        settings.accept(settings0);
        return addElement(element, settings0);
    }

    /**
     * Adds an element to this layout.
     * @param element the element to add
     * @param settings the settings for the element
     * @return the added element
     * @param <T> the type of the element
     * @throws IllegalArgumentException if the element or settings are null
     */
    <T extends LayoutElement> T addElement(@NotNull T element, @NotNull LayoutSettings settings) throws IllegalArgumentException;

    /**
     * Generates the default layout settings for this layout.
     * @return Default Layout Settings
     */
    @NotNull
    LayoutSettings createDefaultSettings();

    /**
     * Visits this layout and all of its children.
     * @param visitor Element Visitor
     */
    void visitChildren(@NotNull Consumer<LayoutElement> visitor);

    /**
     * Visits all the widgets in this layout.
     * @param visitor Widget Visitor
     */
    default void visitWidgets(@Nullable Consumer<AbstractWidget> visitor) {
        if (visitor == null) return;
        visitChildren(element -> element.visitWidget(visitor));
    }

    /**
     * Arranges the elements inside this Layout.
     */
    default void arrangeElements() {
        visitChildren(element -> {
            if (element instanceof Layout layout)
                layout.arrangeElements();
        });
    }

}
