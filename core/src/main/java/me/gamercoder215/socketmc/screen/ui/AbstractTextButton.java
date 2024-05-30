package me.gamercoder215.socketmc.screen.ui;

import me.gamercoder215.socketmc.util.render.text.Text;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;

/**
 * Represents a button on the screen with an attached text message.
 */
public abstract class AbstractTextButton extends AbstractButton {

    @Serial
    private static final long serialVersionUID = -5917267247021394156L;

    private String messageJSON;

    /**
     * Constructs a new text button using the default dimesions.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param message the text message
     * @throws IllegalArgumentException if coordinates are negative, or message is null
     */
    protected AbstractTextButton(int x, int y, @NotNull Text message) throws IllegalArgumentException {
        this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, message);
    }

    /**
     * Constructs a new text button.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param message the text message
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or message is null
     */
    protected AbstractTextButton(int x, int y, int width, int height, @NotNull Text message) throws IllegalArgumentException {
        super(x, y, width, height);

        if (message == null) throw new IllegalArgumentException("Message cannot be null");
        this.messageJSON = message.toJSON();
    }

    /**
     * Constructs a new text button.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param messageJSON the text message in JSON format
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or message is null
     */
    protected AbstractTextButton(int x, int y, int width, int height, @NotNull String messageJSON) throws IllegalArgumentException {
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
