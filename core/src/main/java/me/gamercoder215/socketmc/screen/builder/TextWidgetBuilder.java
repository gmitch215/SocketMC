package me.gamercoder215.socketmc.screen.builder;

import me.gamercoder215.socketmc.screen.ui.TextWidget;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a builder for a {@link TextWidget}.
 */
public final class TextWidgetBuilder extends WidgetWithTextBuilder<TextWidgetBuilder, TextWidget> {

    TextWidgetBuilder() {}

    boolean isDropShadow = true;
    int padding = TextWidget.DEFAULT_PADDING;

    /**
     * Sets whether to render a drop shadow.
     * @param isDropShadow Whether to render a drop shadow.
     * @return this class, for chaining
     */
    @NotNull
    public TextWidgetBuilder isDropShadow(boolean isDropShadow) {
        this.isDropShadow = isDropShadow;
        return this;
    }

    /**
     * Sets the padding around the text.
     * @param padding The padding around the text.
     * @return this class, for chaining
     */
    @NotNull
    public TextWidgetBuilder padding(int padding) {
        this.padding = padding;
        return this;
    }

    @Override
    public TextWidget build() {
        return new TextWidget(x, y, width, height, messageJSON, isDropShadow, padding);
    }

    /**
     * Creates a new TextWidgetBuilder.
     * @return {@link TextWidgetBuilder} Builder
     */
    @NotNull
    public static TextWidgetBuilder builder() {
        return new TextWidgetBuilder();
    }

}
