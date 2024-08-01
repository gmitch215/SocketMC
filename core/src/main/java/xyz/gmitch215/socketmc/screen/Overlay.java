package xyz.gmitch215.socketmc.screen;

import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.util.render.DrawingContext;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents an overlay to be displayed over the screen.
 */
public final class Overlay implements Serializable {

    @Serial
    private static final long serialVersionUID = -2496604238424247511L;

    private final DrawingContext context;
    private boolean pauseScreen;

    /**
     * Constructs a new overlay.
     * @param context The drawing context to use on the overlay.
     * @param pauseScreen Whether this overlay should pause the game.
     */
    public Overlay(@NotNull DrawingContext context, boolean pauseScreen) {
        this.context = context.clone();
        this.context.lock();

        this.pauseScreen = pauseScreen;
    }

    /**
     * Gets the immutable drawing context of this overlay.
     * @return The Drawing Context.
     */
    @NotNull
    public DrawingContext getContext() {
        return context;
    }

    /**
     * Returns whether this overlay should pause the game.
     * @return true if the game should be paused, false otherwise.
     */
    public boolean isPauseScreen() {
        return pauseScreen;
    }

    /**
     * Sets whether this overlay should pause the game.
     * @param pauseScreen true if the game should be paused, false otherwise.
     */
    public void setPauseScreen(boolean pauseScreen) {
        this.pauseScreen = pauseScreen;
    }
}
