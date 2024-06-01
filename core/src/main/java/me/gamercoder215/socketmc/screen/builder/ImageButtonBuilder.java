package me.gamercoder215.socketmc.screen.builder;

import me.gamercoder215.socketmc.screen.ui.ImageButton;
import me.gamercoder215.socketmc.screen.util.Sprite;
import me.gamercoder215.socketmc.util.render.text.Text;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a builder for an {@link ImageButton}.
 */
public final class ImageButtonBuilder extends WidgetBuilder<ImageButtonBuilder, ImageButton> {

    Sprite sprite;
    String messageJSON;

    ImageButtonBuilder() {
        width = ImageButton.DEFAULT_WIDTH;
        height = ImageButton.DEFAULT_HEIGHT;
    }

    /**
     * Sets the sprite for the button.
     * @param sprite Button Sprite
     * @return this class, for chaining
     * @throws IllegalArgumentException if sprite is null
     */
    @NotNull
    public ImageButtonBuilder sprite(@NotNull Sprite sprite) throws IllegalArgumentException {
        if (sprite == null) throw new IllegalArgumentException("Sprite cannot be null");
        this.sprite = sprite;
        return this;
    }

    /**
     * Sets the message for the button.
     * @param message Button Message
     * @return this class, for chaining
     * @throws IllegalArgumentException if message is null
     */
    @NotNull
    public ImageButtonBuilder message(@NotNull Text message) throws IllegalArgumentException {
        if (message == null) throw new IllegalArgumentException("Message cannot be null");
        this.messageJSON = message.toJSON();
        return this;
    }

    /**
     * Sets the message JSON for the button.
     * @param messageJSON Button Message JSON
     * @return this class, for chaining
     * @throws IllegalArgumentException if messageJSON is null
     */
    @NotNull
    public ImageButtonBuilder messageJSON(@NotNull String messageJSON) throws IllegalArgumentException {
        if (messageJSON == null) throw new IllegalArgumentException("Message JSON cannot be null");
        this.messageJSON = messageJSON;
        return this;
    }

    @Override
    public ImageButton build() {
        return new ImageButton(x, y, width, height, sprite, messageJSON);
    }

    /**
     * Creates a new ImageButtonBuilder.
     * @return {@link ImageButton} Builder
     */
    @NotNull
    public static ImageButtonBuilder builder() {
        return new ImageButtonBuilder();
    }

}
