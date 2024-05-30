package me.gamercoder215.socketmc.screen.builder;

import me.gamercoder215.socketmc.screen.ui.ImageButton;
import me.gamercoder215.socketmc.screen.util.Sprite;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a builder for an {@link ImageButton}.
 */
public final class ImageButtonBuilder extends WidgetBuilder<ImageButtonBuilder, ImageButton> {

    Sprite sprite;

    ImageButtonBuilder() {}

    /**
     * Sets the sprite for the button.
     * @param image Button Sprite
     * @return this class, for chaining
     */
    public ImageButtonBuilder sprite(@NotNull Sprite sprite) {
        this.sprite = sprite;
        return this;
    }

    @Override
    public ImageButton build() {
        return new ImageButton(x, y, width, height, sprite);
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
