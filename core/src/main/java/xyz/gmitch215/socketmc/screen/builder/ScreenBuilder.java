package xyz.gmitch215.socketmc.screen.builder;

import org.jetbrains.annotations.Nullable;
import xyz.gmitch215.socketmc.screen.CustomScreen;
import xyz.gmitch215.socketmc.screen.Positionable;
import xyz.gmitch215.socketmc.screen.ScreenBackground;
import xyz.gmitch215.socketmc.screen.layout.Layout;
import xyz.gmitch215.socketmc.screen.ui.ImageButton;
import xyz.gmitch215.socketmc.screen.ui.ImageWidget;
import xyz.gmitch215.socketmc.screen.ui.TextButton;
import xyz.gmitch215.socketmc.screen.ui.FocusedTextWidget;
import xyz.gmitch215.socketmc.screen.ui.TextWidget;
import xyz.gmitch215.socketmc.util.render.text.PlainText;
import xyz.gmitch215.socketmc.util.render.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Represents a builder for creating a {@link CustomScreen}.
 */
public final class ScreenBuilder {

    private final CustomScreen screen;

    private ScreenBuilder(CustomScreen screen) {
        this.screen = screen;
    }

    // Builder

    /**
     * Sets the title of the screen.
     * @param title The title of the screen.
     * @return this class, for chaining
     */
    @NotNull
    public ScreenBuilder title(@NotNull Text title) {
        screen.setTitle(title);
        return this;
    }

    /**
     * Sets the title of the screen.
     * @param title The title of the screen.
     * @return this class, for chaining
     */
    @NotNull
    public ScreenBuilder title(@NotNull String title) {
        screen.setTitle(new PlainText(title));
        return this;
    }

    // Builder - Widgets

    /**
     * Adds a new element to the screen.
     * @param element The element to add.
     * @return this class, for chaining
     */
    @NotNull
    public ScreenBuilder element(@NotNull Supplier<Positionable> element) {
        screen.addChild(element.get());
        return this;
    }

    /**
     * Adds a new element to the screen.
     * @param element The element to add.
     * @return this class, for chaining
     */
    @NotNull
    public ScreenBuilder element(@NotNull Positionable element) {
        screen.addChild(element);
        return this;
    }

    /**
     * Adds a new element to the screen.
     * @param widget Constructor for a {@link FocusedTextWidget}
     * @return this class, for chaining
     */
    @NotNull
    public ScreenBuilder focusedTextWidget(@NotNull Consumer<FocusedTextWidgetBuilder> widget) {
        FocusedTextWidgetBuilder builder = FocusedTextWidgetBuilder.builder();
        widget.accept(builder);

        screen.addChild(builder.build());
        return this;
    }

    /**
     * Adds a new element to the screen.
     * @param widget Constructor for a {@link TextWidget}
     * @return this class, for chaining
     */
    @NotNull
    public ScreenBuilder textWidget(@NotNull Consumer<TextWidgetBuilder> widget) {
        TextWidgetBuilder builder = TextWidgetBuilder.builder();
        widget.accept(builder);

        screen.addChild(builder.build());
        return this;
    }

    /**
     * Adds a new element to the screen.
     * @param button Constructor for a {@link TextButton}
     * @return this class, for chaining
     */
    @NotNull
    public ScreenBuilder textButton(@NotNull Consumer<TextButtonBuilder> button) {
        TextButtonBuilder builder = TextButtonBuilder.builder();
        button.accept(builder);

        screen.addChild(builder.build());
        return this;
    }

    /**
     * Adds a new element to the screen.
     * @param widget Constructor for a {@link ImageWidget}
     * @return this class, for chaining
     */
    @NotNull
    public ScreenBuilder imageWidget(@NotNull Consumer<ImageWidgetBuilder> widget) {
        ImageWidgetBuilder builder = ImageWidgetBuilder.builder();
        widget.accept(builder);

        screen.addChild(builder.build());
        return this;
    }

    /**
     * Adds a new element to the screen.
     * @param button Constructor for a {@link ImageButton}
     * @return this class, for chaining
     */
    @NotNull
    public ScreenBuilder imageButton(@NotNull Consumer<ImageButtonBuilder> button) {
        ImageButtonBuilder builder = ImageButtonBuilder.builder();
        button.accept(builder);

        screen.addChild(builder.build());
        return this;
    }

    /**
     * Sets the background of the screen.
     * @param background The background of the screen.
     * @return this class, for chaining
     * @throws IllegalArgumentException if the background is null
     */
    @NotNull
    public ScreenBuilder background(@NotNull ScreenBackground background) throws IllegalArgumentException {
        screen.setBackground(background);
        return this;
    }

    /**
     * Sets the layout of the screen.
     * @param layout The layout of the screen.
     * @return this class, for chaining
     */
    @NotNull
    public ScreenBuilder layout(@Nullable Layout layout) {
        screen.setLayout(layout);
        return this;
    }

    /**
     * Sets the layout of the screen.
     * @param layout The generator for the screen layout.
     * @return this class, for chaining
     * @throws IllegalArgumentException if the generator is null
     */
    @NotNull
    public ScreenBuilder layout(@NotNull Supplier<Layout> layout) throws IllegalArgumentException {
        if (layout == null) throw new IllegalArgumentException("Layout cannot be null");

        screen.setLayout(layout.get());
        return this;
    }

    // Build

    /**
     * Builds the screen.
     * @return The built screen.
     */
    @NotNull
    public CustomScreen build() {
        return screen;
    }

    //<editor-fold desc="Static Constructors" defaultstate="collapsed">

    // Static Constructors

    /**
     * Creates a new {@link ScreenBuilder} with a new {@link CustomScreen}, with an empty title.
     * @return ScreenBuilder
     */
    @NotNull
    public static ScreenBuilder builder() {
        return new ScreenBuilder(new CustomScreen(PlainText.empty()));
    }

    /**
     * Creates a new {@link ScreenBuilder} with a new {@link CustomScreen}, with the given title.
     * @param title The title of the screen.
     * @return ScreenBuilder
     */
    @NotNull
    public static ScreenBuilder builder(@NotNull Text title) {
        return new ScreenBuilder(new CustomScreen(title));
    }

    //</editor-fold>

}
