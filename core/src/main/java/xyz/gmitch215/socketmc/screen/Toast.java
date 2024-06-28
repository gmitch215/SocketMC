package xyz.gmitch215.socketmc.screen;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import xyz.gmitch215.socketmc.util.Paramaterized;
import xyz.gmitch215.socketmc.util.render.DrawingContext;
import xyz.gmitch215.socketmc.util.render.text.PlainText;
import xyz.gmitch215.socketmc.util.render.text.Text;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Toast Message that appears in the top right corner of the screen.
 */
public final class Toast implements Serializable, Paramaterized {

    @Serial
    private static final long serialVersionUID = 1375925887944631068L;

    /**
     * The default width of a toast message.
     */
    public static final int DEFAULT_WIDTH = 160;

    /**
     * The default height of a toast message.
     */
    public static final int DEFAULT_HEIGHT = 32;

    //<editor-fold desc="Implementation" defaultstate="collapsed">

    private final int type;
    private final int width;
    private final int height;
    private final List<Object> paramaters = new ArrayList<>();
    private final long duration;

    private Toast(int type, int width, int height, List<Object> paramaters, long duration) {
        this.type = type;
        this.width = width;
        this.height = height;
        this.paramaters.addAll(paramaters);
        this.duration = duration;
    }

    /**
     * Gets the internal type of the toast message.
     * @return Toast Message Type
     */
    public int getType() {
        return type;
    }

    /**
     * Gets the width of the toast message.
     * @return Toast Width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the toast message.
     * @return Toast Height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets an immutbale copy of the paramaters for the toast message.
     * @return Toast Paramaters
     */
    @NotNull
    @Unmodifiable
    @Override
    public List<Object> getParameters() {
        return List.copyOf(paramaters);
    }

    /**
     * Gets the duration of the toast message, in milliseconds. If it is {@code -1}, the duration is not modifiable.
     * @return Display Duration
     */
    public long getDuration() {
        return duration;
    }

    //</editor-fold>

    // Custom Toast

    /**
     * Creates a custom Toast Message using the default dimensions.
     * @param duration Display Duration
     * @param context Drawing Context to render
     * @return Custom Toast Message
     * @throws IllegalArgumentException if the width, height, or duration is not positive, or if the duration/context is null
     */
    @NotNull
    public static Toast custom(@NotNull DrawingContext context, @NotNull Duration duration) throws IllegalArgumentException {
        return custom(DEFAULT_WIDTH, DEFAULT_HEIGHT, context, duration.toMillis());
    }

    /**
     * Creates a custom Toast Message using the default dimensions.
     * @param width Toast Width
     * @param height Toast Height
     * @param millis Display Duration, in milliseconds
     * @param context Drawing Context to render
     * @return Custom Toast Message
     * @throws IllegalArgumentException if the width, height, or duration is not positive, or if the context is null
     */
    @NotNull
    public static Toast custom(@NotNull DrawingContext context, long millis) throws IllegalArgumentException {
        return custom(DEFAULT_WIDTH, DEFAULT_HEIGHT, context, millis);
    }

    /**
     * Creates a custom Toast Message.
     * @param width Toast Width
     * @param height Toast Height
     * @param duration Display Duration
     * @param context Drawing Context to render
     * @return Custom Toast Message
     * @throws IllegalArgumentException if the width, height, or duration is not positive, or if the duration/context is null
     */
    @NotNull
    public static Toast custom(int width, int height, @NotNull DrawingContext context, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return custom(width, height, context, duration.toMillis());
    }

    /**
     * Creates a custom Toast Message.
     * @param width Toast Width
     * @param height Toast Height
     * @param context Drawing Context to render
     * @param duration Display Duration, in milliseconds
     * @return Custom Toast Message
     * @throws IllegalArgumentException if the width, height, or duration is not positive, or if the context is null
     */
    @NotNull
    public static Toast custom(int width, int height, @NotNull DrawingContext context, long duration) throws IllegalArgumentException {
        if (width <= 0) throw new IllegalArgumentException("Width must be greater than 0");
        if (height <= 0) throw new IllegalArgumentException("Height must be greater than 0");
        if (context == null) throw new IllegalArgumentException("Context cannot be null");
        if (duration <= 0) throw new IllegalArgumentException("Duration must be greater than 0");

        return new Toast(0, width, height, List.of(context), duration);
    }

    // System Toast

    /**
     * Creates a System Toast Message with no message content.
     * @param type System Toast Type
     * @param title Title of Message
     * @return System Toast Message
     * @throws IllegalArgumentException if the type, title, or message is null
     */
    @NotNull
    public static Toast system(@NotNull System type, @NotNull Text title) throws IllegalArgumentException {
        return system(type, title, PlainText.empty());
    }

    /**
     * Creates a System Toast Message.
     * @param type System Toast Type
     * @param title Title of Message
     * @param message Message Content
     * @return System Toast Message
     * @throws IllegalArgumentException if the type, title, or message is null
     */
    @NotNull
    public static Toast system(@NotNull System type, @NotNull Text title, @NotNull Text message) throws IllegalArgumentException {
        if (type == null) throw new IllegalArgumentException("Type cannot be null");
        if (title == null) throw new IllegalArgumentException("Title cannot be null");
        if (message == null) throw new IllegalArgumentException("Message cannot be null");

        return new Toast(1, DEFAULT_WIDTH, DEFAULT_HEIGHT, List.of(type, title.toJSON(), message.toJSON()), -1);
    }

    /**
     * Represents the types for a System Toast Message.
     */
    public enum System {
        /**
         * The narrator has been toggled.
         */
        NARRATOR_TOGGLE,

        /**
         * Your world has been backed up.
         */
        WORLD_BACKUP,

        /**
         * A resource pack failed to load.
         */
        PACK_LOAD_FAILURE,

        /**
         * You cannot access the world.
         */
        WORLD_ACCESS_FAILURE,

        /**
         * A resource pack failed to copy.
         */
        PACK_COPY_FAILURE,

        /**
         * A file being added to the world failed to copy.
         */
        FILE_DROP_FAILURE,

        /**
         * A simple periodic notification.
         */
        PERIODIC_NOTIFICATION,

        /**
         * Your game has low disk space.
         */
        LOW_DISK_SPACE,

        /**
         * A chunk failed to load.
         */
        CHUNK_LOAD_FAILURE,

        /**
         * A chunk failed to save.
         */
        CHUNK_SAVE_FAILURE,

        /**
         * The server you are connecting to is unsecure.
         */
        UNSECURE_SERVER_WARNING
    }

    // Tutorial Toast

    /**
     * Creates a Tutorial Toast Message with no message content.
     * @param type Tutorial Type
     * @param title Title of Message
     * @return Tutorial Toast Message
     * @throws IllegalArgumentException if the type, title, or message is null
     */
    @NotNull
    public static Toast tutorial(@NotNull Tutorial type, @NotNull Text title) throws IllegalArgumentException {
        return tutorial(type, title, PlainText.empty());
    }

    /**
     * Creates a Tutorial Toast Message.
     * @param type Tutorial Type
     * @param title Title of Message
     * @param message Message Content
     * @return Tutorial Toast Message
     * @throws IllegalArgumentException if the type, title, or message is null
     */
    @NotNull
    public static Toast tutorial(@NotNull Tutorial type, @NotNull Text title, @NotNull Text message) throws IllegalArgumentException {
        return tutorial(type, title, message, true);
    }

    /**
     * Creates a Tutorial Toast Message.
     * @param type Tutorial Type
     * @param title Title of Message
     * @param message Message Content
     * @param progressable Whether to show a progress bar during its duration
     * @return Tutorial Toast Message
     * @throws IllegalArgumentException if the type, title, or message is null
     */
    @NotNull
    public static Toast tutorial(@NotNull Tutorial type, @NotNull Text title, @NotNull Text message, boolean progressable) throws IllegalArgumentException {
        if (type == null) throw new IllegalArgumentException("Type cannot be null");
        if (title == null) throw new IllegalArgumentException("Title cannot be null");
        if (message == null) throw new IllegalArgumentException("Message cannot be null");

        return new Toast(2, DEFAULT_WIDTH, DEFAULT_HEIGHT, List.of(type, title.toJSON(), message.toJSON(), progressable), -1);
    }

    /**
     * Represents the icon next to the tutorial message.
     */
    public enum Tutorial {
        /**
         * Learning how to move.
         */
        MOVEMENT_KEYS,

        /**
         * Learning how to use the mouse.
         */
        MOUSE,

        /**
         * Learning how to punch a tree.
         */
        TREE,

        /**
         * Learning how to open your inventory.
         */
        RECIPE_BOOK,

        /**
         * Learning how to craft.
         */
        WOODEN_PLANKS,

        /**
         * Learning how to use the social interactions menu.
         */
        SOCIAL_INTERACTIONS,

        /**
         * Learning how to use the bundle item.
         */
        RIGHT_CLICK
    }
    
}
