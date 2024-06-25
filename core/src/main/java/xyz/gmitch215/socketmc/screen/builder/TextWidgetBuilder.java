package xyz.gmitch215.socketmc.screen.builder;

import xyz.gmitch215.socketmc.screen.ui.TextWidget;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a builder for a {@link TextWidget}.
 */
public final class TextWidgetBuilder extends WidgetWithTextBuilder<TextWidgetBuilder, TextWidget> {

    TextWidgetBuilder() {}

    @Override
    public TextWidget build() {
        return new TextWidget(x, y, width, height, messageJSON);
    }

    /**
     * Creates a new TextWidgetBuilder.
     * @return {@link TextWidget} Builder
     */
    @NotNull
    public static TextWidgetBuilder builder() {
        return new TextWidgetBuilder();
    }

}
