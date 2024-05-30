package me.gamercoder215.socketmc.screen.ui;

import me.gamercoder215.socketmc.util.Identifier;
import me.gamercoder215.socketmc.util.Position;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;

/**
 * Represents a widget that displays an image.
 */
public final class ImageWidget extends AbstractWidget {

    @Serial
    private static final long serialVersionUID = -8231582022193902962L;

    private final Type type;
    private Identifier location;

    /**
     * Constructs a new image widget.
     * @param position the position
     * @param type the type of image
     * @param location the location of the image
     * @throws IllegalArgumentException if the type or location are null
     */
    public ImageWidget(@NotNull Position position, @NotNull Type type, @NotNull Identifier location) throws IllegalArgumentException {
        this(position.getX(), position.getY(), position.getWidth(), position.getHeight(), type, location);
    }

    /**
     * Constructs a new widget.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param type the type of image
     * @param location the location of the image
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or if the type/location is null
     */
    public ImageWidget(int x, int y, int width, int height, @NotNull Type type, @NotNull Identifier location) throws IllegalArgumentException {
        super(x, y, width, height);
        if (type == null) throw new IllegalArgumentException("type cannot be null");
        if (location == null) throw new IllegalArgumentException("location cannot be null");

        this.type = type;
        this.location = location;
    }

    /**
     * Gets the type of the image widget.
     * @return Image Widget Type
     */
    @NotNull
    public Type getType() {
        return type;
    }

    /**
     * Gets the location of the image.
     * @return Image File Location
     */
    @NotNull
    public Identifier getLocation() {
        return location;
    }

    /**
     * Sets the location of the image.
     * @param location Image File Location
     * @throws IllegalArgumentException if the location is null
     */
    public void setLocation(@NotNull Identifier location) throws IllegalArgumentException {
        if (location == null) throw new IllegalArgumentException("location cannot be null");
        this.location = location;
    }

    /**
     * Constructs a new image widget with {@link Type#TEXTURE}.
     * @param position the position of the widget
     * @param location the location of the image
     * @return Image Widget
     */
    @NotNull
    public static ImageWidget texture(@NotNull Position position, @NotNull Identifier location) throws IllegalArgumentException {
        return new ImageWidget(position, Type.TEXTURE, location);
    }

    /**
     * Constructs a new image widget with {@link Type#TEXTURE}.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param location the location of the image
     * @return Image Widget
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or if the location is null
     */
    @NotNull
    public static ImageWidget texture(int x, int y, int width, int height, @NotNull Identifier location) throws IllegalArgumentException {
        return new ImageWidget(x, y, width, height, Type.TEXTURE, location);
    }

    /**
     * Constructs a new image widget with {@link Type#SPRITE}.
     * @param position the position of the widget
     * @param location the location of the image
     * @return Image Widget
     * @throws IllegalArgumentException if the location is null
     */
    @NotNull
    public static ImageWidget sprite(@NotNull Position position, @NotNull Identifier location) throws IllegalArgumentException {
        return new ImageWidget(position, Type.SPRITE, location);
    }

    /**
     * Constructs a new image widget with {@link Type#SPRITE}.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param location the location of the image
     * @return Image Widget
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or if the location is null
     */
    @NotNull
    public static ImageWidget sprite(int x, int y, int width, int height, @NotNull Identifier location) throws IllegalArgumentException {
        return new ImageWidget(x, y, width, height, Type.SPRITE, location);
    }

    /**
     * Represents the type of image.
     */
    public enum Type {

        /**
         * The image is a texture from a {@code .png} file.
         */
        TEXTURE,

        /**
         * The image is a sprite from a larger sprite file.
         */
        SPRITE
    }

}
