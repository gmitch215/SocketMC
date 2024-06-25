package xyz.gmitch215.socketmc.instruction.builder;

import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.util.Identifier;
import org.jetbrains.annotations.NotNull;

/**
 * Builder for {@link Instruction#DRAW_TEXTURE}.
 */
public final class TextureBuilder implements HUDBuilder<TextureBuilder> {

    Identifier texture;
    int x, y, width, height, startTop = 0, startLeft = 0, regionWidth = -1, regionHeight = -1;
    long millis;

    TextureBuilder() {}

    /**
     * Sets the texture to draw.
     * @param texture Texture Identifier
     * @return this class, for chaining
     */
    @NotNull
    public TextureBuilder texture(@NotNull Identifier texture) {
        this.texture = texture;
        return this;
    }

    @Override
    @NotNull
    public TextureBuilder x(int x) {
        this.x = x;
        return this;
    }

    @Override
    @NotNull
    public TextureBuilder y(int y) {
        this.y = y;
        return this;
    }

    /**
     * @deprecated Unsupported
     */
    @Override
    @Deprecated
    public TextureBuilder argb(int argb) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TextureBuilder duration(long millis) {
        this.millis = millis;
        return this;
    }

    /**
     * Sets the width of the texture.
     * @param width Texture Width, in pixels
     * @return this class, for chaining
     */
    @NotNull
    public TextureBuilder width(int width) {
        this.width = width;
        return this;
    }

    /**
     * Sets the height of the texture.
     * @param height Texture Height, in pixels
     * @return this class, for chaining
     */
    @NotNull
    public TextureBuilder height(int height) {
        this.height = height;
        return this;
    }

    /**
     * Sets the width of the region to draw.
     * @param regionWidth Region Width, in pixels
     * @return this class, for chaining
     */
    @NotNull
    public TextureBuilder regionWidth(int regionWidth) {
        this.regionWidth = regionWidth;
        return this;
    }

    /**
     * Sets the height of the region to draw.
     * @param regionHeight Region Height, in pixels
     * @return this class, for chaining
     */
    @NotNull
    public TextureBuilder regionHeight(int regionHeight) {
        this.regionHeight = regionHeight;
        return this;
    }

    /**
     * Sets where to start drawing from the top of the texture.
     * @param startTop Start Top Amount, in pixels
     * @return this class, for chaining
     */
    @NotNull
    public TextureBuilder startTop(int startTop) {
        this.startTop = startTop;
        return this;
    }

    /**
     * Sets where to start drawing from the left of the texture.
     * @param startLeft Start Left Amount, in pixels
     * @return this class, for chaining
     */
    @NotNull
    public TextureBuilder startLeft(int startLeft) {
        this.startLeft = startLeft;
        return this;
    }

    @Override
    @NotNull
    public Instruction build() {
        return Instruction.drawTexture(x, y, width, height, texture, startTop, startLeft, regionWidth, regionHeight, millis);
    }
}
