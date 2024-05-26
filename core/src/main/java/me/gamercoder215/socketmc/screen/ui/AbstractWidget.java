package me.gamercoder215.socketmc.screen.ui;

import me.gamercoder215.socketmc.instruction.util.Text;
import me.gamercoder215.socketmc.util.SerializableConsumer;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a Widget on the screen.
 */
public abstract class AbstractWidget implements Positionable {

    /**
     * The default padding around text elements.
     */
    public static final int DEFAULT_PADDING = 4;

    @Serial
    private static final long serialVersionUID = -8273686084189110401L;

    private int x, y, width, height;
    private String messageJSON;

    private final Set<SerializableConsumer<AbstractWidget>> onClickListeners = new HashSet<>();

    /**
     * Constructs a new widget.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param message the text message
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or message is null
     */
    protected AbstractWidget(int x, int y, int width, int height, @NotNull Text message) throws IllegalArgumentException {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates must be non-negative");
        if (width < 0 || height < 0) throw new IllegalArgumentException("Width and height must be non-negative");
        if (message == null) throw new IllegalArgumentException("Message cannot be null");

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.messageJSON = message.toJSON();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets the message JSON for this widget.
     * @return the message in JSON format
     */
    @NotNull
    public String getMessageJSON() {
        return messageJSON;
    }

    /**
     * Sets the message JSON for this widget.
     * @param messageJSON the message in JSON format
     */
    public void setMessageJSON(@NotNull String messageJSON) {
        if (messageJSON == null) throw new IllegalArgumentException("Message cannot be null");
        this.messageJSON = messageJSON;
    }

    /**
     * Gets the message for this widget.
     * @param message the message
     */
    public void setMessage(@NotNull Text message) {
        if (message == null) throw new IllegalArgumentException("Message cannot be null");
        this.messageJSON = message.toJSON();
    }

    /**
     * Adds an on-click listener to this widget.
     * @param listener the listener
     * @see SerializableConsumer
     */
    public void onClick(@NotNull SerializableConsumer<AbstractWidget> listener) {
        onClickListeners.add(listener);
    }

    /**
     * Clears all listeners from this widget.
     */
    public void clearListeners() {
        onClickListeners.clear();
    }
}
