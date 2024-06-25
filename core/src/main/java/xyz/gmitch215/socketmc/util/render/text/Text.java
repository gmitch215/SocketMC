package xyz.gmitch215.socketmc.util.render.text;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a text element to be displayed on the client's screen.
 */
public abstract class Text implements Serializable {

    /**
     * The height of a single line of text, in pixels.
     */
    public static final int FONT_HEIGHT = 9;

    @Serial
    private static final long serialVersionUID = 3440974387463824806L;

    /**
     * Whether this text element has a drop shadow.
     */
    protected boolean dropShadow;

    /**
     * Constructs a new text element.
     */
    protected Text() {}

    /**
     * Gets whether this text element has a drop shadow.
     * @return Drop Shadow
     */
    public boolean isDropShadow() {
        return dropShadow;
    }

    /**
     * Sets whether this text element should have a drop shadow.
     * @param dropShadow Drop Shadow
     */
    public void setDropShadow(boolean dropShadow) {
        this.dropShadow = dropShadow;
    }

    /**
     * Gets the color as an ARGB integer.
     * @return String Color
     */
    public abstract int getColor();

    /**
     * Serializes this text element to a JSON string compatible with the Minecraft Component format.
     * @return JSON String
     */
    public abstract String toJSON();

}
