package me.gamercoder215.socketmc.screen.builder;

import me.gamercoder215.socketmc.screen.ui.TextButton;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a builder for a {@link TextButton}.
 */
public final class TextButtonBuilder extends WidgetWithTextBuilder<TextButtonBuilder, TextButton> {

    TextButtonBuilder() {}

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
