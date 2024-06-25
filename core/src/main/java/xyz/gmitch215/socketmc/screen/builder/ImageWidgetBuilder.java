package xyz.gmitch215.socketmc.screen.builder;

import xyz.gmitch215.socketmc.screen.ui.ImageWidget;
import xyz.gmitch215.socketmc.util.Identifier;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a builder for an {@link ImageWidget}.
 */
public final class ImageWidgetBuilder extends WidgetBuilder<ImageWidgetBuilder, ImageWidget> {

    Identifier image;
    ImageWidget.Type type = ImageWidget.Type.TEXTURE;

    ImageWidgetBuilder() {}

    /**
     * Sets the image for the widget.
     * @param image Image Identifier
     * @return this class, for chaining
     * @throws IllegalArgumentException if image is null
     */
    public ImageWidgetBuilder image(@NotNull Identifier image) throws IllegalArgumentException {
        if (image == null) throw new IllegalArgumentException("image cannot be null");
        this.image = image;
        return this;
    }

    /**
     * Sets the type of the image.
     * @param type Image Type
     * @return this class, for chaining
     * @throws IllegalArgumentException if type is null
     */
    public ImageWidgetBuilder type(@NotNull ImageWidget.Type type) throws IllegalArgumentException {
        if (type == null) throw new IllegalArgumentException("type cannot be null");

        this.type = type;
        return this;
    }

    @Override
    public ImageWidget build() {
        return new ImageWidget(x, y, width, height, type, image);
    }

    /**
     * Creates a new ImageWidgetBuilder.
     * @return {@link ImageWidget} Builder
     */
    @NotNull
    public static ImageWidgetBuilder builder() {
        return new ImageWidgetBuilder();
    }

}
