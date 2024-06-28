package xyz.gmitch215.socketmc.screen.ui;

import xyz.gmitch215.socketmc.util.ElementBounds;
import xyz.gmitch215.socketmc.util.render.text.JsonText;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;

/**
 * Represents a Button with a Lock Icon. Used as the button to Lock the Difficulty in Singleplayer.
 */
public final class LockButton extends AbstractButton {

    @Serial
    private static final long serialVersionUID = -3808809875134206481L;

    /**
     * The width and height of the button.
     */
    public static final int SIZE = 20;

    /**
     * The translation key for the button's narration message.
     */
    public static final String TRANSLATION_KEY = "narrator.button.difficulty_lock";

    /**
     * Constructs a new LockButton.
     * @param bounds the bounds
     */
    public LockButton(@NotNull ElementBounds bounds) {
        this(bounds.getX(), bounds.getY());
    }

    /**
     * Constructs a new LockButton.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @throws IllegalArgumentException if coordinates are negative
     */
    public LockButton(int x, int y) throws IllegalArgumentException {
        super(x, y, SIZE, SIZE, JsonText.translate(TRANSLATION_KEY));
    }

}
