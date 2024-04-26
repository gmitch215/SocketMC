package me.gamercoder215.socketmc.events.input;

import me.gamercoder215.socketmc.events.SocketEvent;
import me.gamercoder215.socketmc.spigot.SocketPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a player moves their mouse.
 */
public class PlayerMoveMouseEvent extends SocketEvent {

    private final double newX;
    private final double newY;

    /**
     * Constructs a new PlayerMoveMouseEvent.
     * @param player The player that moved their mouse
     * @param newX The new x position of the mouse
     * @param newY The new y position of the mouse
     */
    public PlayerMoveMouseEvent(@NotNull SocketPlayer player, double newX, double newY) {
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
