package me.gamercoder215.socketmc.screen.ui;

import me.gamercoder215.socketmc.screen.util.Sprite;
import me.gamercoder215.socketmc.util.render.text.PlainText;
import me.gamercoder215.socketmc.util.render.text.Text;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;

/**
 * Represents a button that displays an image.
 */
public final class ImageButton extends AbstractButton {

    @Serial
    private static final long serialVersionUID = -5441250515550764721L;

    private Sprite sprite;

    /**
     * Constructs a new button using the default dimesions and an empty message.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param sprite the sprite
     * @throws IllegalArgumentException if coordinates are negative, or sprite is null
     */
    public ImageButton(int x, int y, @NotNull Sprite sprite) throws IllegalArgumentException {
        this(x, y, sprite, PlainText.empty());
    }

    /**
     * Constructs a new button with an empty message.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param sprite the sprite
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or sprite is null
     */
    public ImageButton(int x, int y, int width, int height, @NotNull Sprite sprite) throws IllegalArgumentException {
        this(x, y, width, height, sprite, PlainText.empty());
    }

    /**
     * Constructs a new button using the default dimesions.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param sprite the sprite
     * @throws IllegalArgumentException if coordinates are negative, or message/sprite is null
     */
    public ImageButton(int x, int y, @NotNull Sprite sprite, @NotNull Text message) throws IllegalArgumentException {
        super(x, y, message);
        if (sprite == null) throw new IllegalArgumentException("Sprite cannot be null");

        this.sprite = sprite;
    }

    /**
     * Constructs a new button.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param sprite the sprite
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or sprite is null
     */
    public ImageButton(int x, int y, int width, int height, @NotNull Sprite sprite, @NotNull Text message) throws IllegalArgumentException {
        super(x, y, width, height, message);
        if (sprite == null) throw new IllegalArgumentException("Sprite cannot be null");

        this.sprite = sprite;
    }

    /**
     * Gets the sprite for this button.
     * @return the sprite
     */
    @NotNull
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Sets the sprite for this button.
     * @param sprite the sprite
     * @throws IllegalArgumentException if sprite is null
     */
    public void setSprite(@NotNull Sprite sprite) throws IllegalArgumentException {
        if (sprite == null) throw new IllegalArgumentException("Sprite cannot be null");
        this.sprite = sprite;
    }
}
