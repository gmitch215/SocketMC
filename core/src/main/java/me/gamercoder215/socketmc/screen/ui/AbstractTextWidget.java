package me.gamercoder215.socketmc.screen.ui;

import me.gamercoder215.socketmc.util.render.text.Text;
import me.gamercoder215.socketmc.util.Position;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;

/**
 * Represents a widget with a text message.
 */
public abstract class AbstractTextWidget extends AbstractWidget {

    @Serial
    private static final long serialVersionUID = -8273686084189110401L;
    
    private String messageJSON;

    /**
     * Constructs a new text widget.
     * @param position the position
     * @param message the text message
     * @throws IllegalArgumentException if message is null
     */
    public AbstractTextWidget(@NotNull Position position, @NotNull Text message) throws IllegalArgumentException {
        this(position.getX(), position.getY(), position.getWidth(), position.getHeight(), message);
    }

    /**
     * Constructs a new text widget.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param message the text message
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or message is null
     */
    public AbstractTextWidget(int x, int y, int width, int height, @NotNull Text message) throws IllegalArgumentException {
        super(x, y, width, height);

        if (message == null) throw new IllegalArgumentException("Message cannot be null");
        this.messageJSON = message.toJSON();
    }

    /**
     * Constructs a new text widget.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param messageJSON the text message in JSON format
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or message is null
     */
    public AbstractTextWidget(int x, int y, int width, int height, @NotNull String messageJSON) throws IllegalArgumentException {
        super(x, y, width, height);

        if (messageJSON == null) throw new IllegalArgumentException("Message cannot be null");
        this.messageJSON = messageJSON;
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
}