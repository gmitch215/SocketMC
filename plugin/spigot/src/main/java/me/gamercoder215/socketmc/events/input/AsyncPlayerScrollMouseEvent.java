package me.gamercoder215.socketmc.events.input;

import me.gamercoder215.socketmc.events.SocketEvent;
import me.gamercoder215.socketmc.spigot.SocketPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a player scrolls their mouse.
 */
public class AsyncPlayerScrollMouseEvent extends SocketEvent {

    private final double xOffset;
    private final double yOffset;

    /**
     * Constructs a new PlayerScrollMouseEvent.
     * @param player The player that scrolled their mouse.
     * @param xOffset The x offset of the scroll.
     * @param yOffset The y offset of the scroll.
     */
    public AsyncPlayerScrollMouseEvent(@NotNull SocketPlayer player, double xOffset, double yOffset) {
        super(player);

        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    /**
     * Gets the x offset of the scroll.
     * @return The x offset of the scroll along the x-axis.
     */
    public double getXOffset() {
        return xOffset;
    }

    /**
     * Gets the y offset of the scroll.
     * @return The y offset of the scroll along the y-axis.
     */
    public double getYOffset() {
        return yOffset;
    }
}
