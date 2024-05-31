package me.gamercoder215.socketmc.screen.builder;

import me.gamercoder215.socketmc.screen.CustomScreen;
import me.gamercoder215.socketmc.screen.Positionable;
import me.gamercoder215.socketmc.screen.ui.ImageButton;
import me.gamercoder215.socketmc.screen.ui.ImageWidget;
import me.gamercoder215.socketmc.screen.ui.TextButton;
import me.gamercoder215.socketmc.screen.ui.FocusedTextWidget;
import me.gamercoder215.socketmc.util.render.text.PlainText;
import me.gamercoder215.socketmc.util.render.text.Text;
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
