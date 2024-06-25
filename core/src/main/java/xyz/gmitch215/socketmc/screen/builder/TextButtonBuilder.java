package xyz.gmitch215.socketmc.screen.builder;

import xyz.gmitch215.socketmc.screen.ui.TextButton;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a builder for a {@link TextButton}.
 */
public final class TextButtonBuilder extends WidgetWithTextBuilder<TextButtonBuilder, TextButton> {

    TextButtonBuilder() {
        width = TextButton.DEFAULT_WIDTH;
        height = TextButton.DEFAULT_HEIGHT;
    }

    @Override
    public TextButton build() {
        return new TextButton(x, y, width, height, messageJSON);
    }

    /**
     * Creates a new TextButtonBuilder.
     * @return {@link TextButton} Builder
     */
    @NotNull
    public static TextButtonBuilder builder() {
        return new TextButtonBuilder();
    }

}
