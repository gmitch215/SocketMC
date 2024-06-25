package xyz.gmitch215.socketmc.screen.builder;

import xyz.gmitch215.socketmc.screen.ui.FocusedTextWidget;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a builder for a {@link FocusedTextWidget}.
 */
public final class FocusedTextWidgetBuilder extends WidgetWithTextBuilder<FocusedTextWidgetBuilder, FocusedTextWidget> {

    FocusedTextWidgetBuilder() {}

    boolean alwaysShowBorder = true;
    int padding = FocusedTextWidget.DEFAULT_PADDING;

    /**
     * Sets whether to always show the border.
     * @param alwaysShowBorder Whether to always show the border.
     * @return this class, for chaining
     */
    @NotNull
    public FocusedTextWidgetBuilder alwaysShowBorder(boolean alwaysShowBorder) {
        this.alwaysShowBorder = alwaysShowBorder;
        return this;
    }

    /**
     * Sets the padding around the text.
     * @param padding The padding around the text.
     * @return this class, for chaining
     */
    @NotNull
    public FocusedTextWidgetBuilder padding(int padding) {
        this.padding = padding;
        return this;
    }

    @Override
    public FocusedTextWidget build() {
        return new FocusedTextWidget(x, y, width, height, messageJSON, alwaysShowBorder, padding);
    }

    /**
     * Creates a new FocusedTextWidgetBuilder.
     * @return {@link FocusedTextWidget} Builder
     */
    @NotNull
    public static FocusedTextWidgetBuilder builder() {
        return new FocusedTextWidgetBuilder();
    }

}
