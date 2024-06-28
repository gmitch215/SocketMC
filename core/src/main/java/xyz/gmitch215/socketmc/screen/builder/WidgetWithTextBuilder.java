package xyz.gmitch215.socketmc.screen.builder;

import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.screen.ui.AbstractTextWidget;
import xyz.gmitch215.socketmc.screen.ui.AbstractWidget;
import xyz.gmitch215.socketmc.util.render.text.PlainText;
import xyz.gmitch215.socketmc.util.render.text.Text;

/**
 * Represents a builder for creating a widget with a text element ({@link AbstractTextWidget}).
 * @param <B> This builder type.
 * @param <T> The type of widget to build.
 */
@SuppressWarnings("unchecked")
public abstract class WidgetWithTextBuilder<B extends WidgetBuilder<B, T>, T extends AbstractWidget> extends WidgetBuilder<B, T> {

    String messageJSON;

    WidgetWithTextBuilder() {}

    /**
     * Sets the message of the widget.
     * @param messageJSON The message in JSON format.
     * @return this class, for chaining
     */
    @NotNull
    public B messageJSON(@NotNull String messageJSON) {
        this.messageJSON = messageJSON;
        return (B) this;
    }

    /**
     * Sets the message of the widget.
     * @param message The message.
     * @return this class, for chaining
     */
    @NotNull
    public B message(@NotNull String message) {
        this.messageJSON = new PlainText(message).toJSON();
        return (B) this;
    }

    /**
     * Sets the message of the widget.
     * @param message The message.
     * @return this class, for chaining
     */
    @NotNull
    public B message(@NotNull Text message) {
        this.messageJSON = message.toJSON();
        return (B) this;
    }

}
