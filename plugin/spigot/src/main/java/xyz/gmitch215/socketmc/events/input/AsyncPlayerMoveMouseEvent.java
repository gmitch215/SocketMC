package xyz.gmitch215.socketmc.events.input;

import xyz.gmitch215.socketmc.events.SocketEvent;
import xyz.gmitch215.socketmc.spigot.SocketPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a player moves their mouse. This method will not fire if {@link #getNewX()} / {@link #getNewY()}'s offset off of their old values is less than {@code 10}.
 */
public class AsyncPlayerMoveMouseEvent extends SocketEvent {

    private final double newX;
    private final double newY;

    /**
     * Constructs a new PlayerMoveMouseEvent.
     * @param player The player that moved their mouse
     * @param newX The new x position of the mouse
     * @param newY The new y position of the mouse
     */
    public AsyncPlayerMoveMouseEvent(@NotNull SocketPlayer player, double newX, double newY) {
        super(player);
        this.newX = newX;
        this.newY = newY;
    }

    /**
     * Gets the new x position of the mouse.
     * @return The new x position of the mouse
     */
    public double getNewX() {
        return newX;
    }

    /**
     * Gets the new y position of the mouse.
     * @return The new y position of the mouse
     */
    public double getNewY() {
        return newY;
    }

}
