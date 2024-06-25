package xyz.gmitch215.socketmc.instruction;

import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a rendering-related instruction.
 */
public abstract class RenderInstruction implements Serializable {

    private final int ordinal;

    int subOrdinal = -1;
    boolean isFilled = false;
    final Map<String, Object> data = new HashMap<>();

    private static final String TIME = "time";
    private static final String MATRIX = "matrix";

    private RenderInstruction(int ordinal) {
        this.ordinal = ordinal;
    }

    // Implementation

    /**
     * Gets the ordinal of the instruction.
     * @return Instruction Ordinal
     */
    public int getOrdinal() {
        return ordinal;
    }

    /**
     * Gets the sub-ordinal for the instruction's operation, or {@code -1} if not set.
     * @return Instruction Sub-Ordinal
     */
    public int getSubOrdinal() {
        return subOrdinal;
    }

    /**
     * Checks if this render instruction has its data filled.
     * @return Whether the instruction is filled
     */
    public boolean isFilled() {
        return isFilled;
    }

    void checkFilled() {
        if (isFilled) throw new IllegalStateException("Render Instruction is already filled");
    }

    /**
     * Gets an immutable copy of the data map.
     * @return Data Map
     */
    @NotNull
    public Map<String, Object> getData() {
        return Map.copyOf(data);
    }

    /**
     * Gets the data for the specified key.
     * @param key The key to get the data for
     * @return Data Object
     */
    public Object data(@NotNull String key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (!data.containsKey(key)) throw new IllegalArgumentException("Data key not found: " + key);

        return data.get(key);
    }

    /**
     * Gets the data for the specified key, casted to the specified type.
     * @param key The key to get the data for
     * @param type The type to cast the data to
     * @return Data Object
     * @param <T> The type to cast the data to
     */
    public <T> T data(@NotNull String key, @NotNull Class<T> type) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (type == null) throw new IllegalArgumentException("Type cannot be null");
        if (!data.containsKey(key)) throw new IllegalArgumentException("Data key not found: " + key);

        return type.cast(data.get(key));
    }

    // Subclasses

    /**
     * Creates a new instruction for the GameRenderer.
     * @return Game Renderer Instruction
     */
    @NotNull
    public static RenderInstruction.GameRenderer game() {
        return new GameRenderer();
    }

    /**
     * Represents instructions for the Game Renderer.
     */
    public static final class GameRenderer extends RenderInstruction {

        /**
         * The ordinal for a {@link RenderInstruction.GameRenderer}.
         */
        public static final int ORDINAL = 0;

        @Serial
        private static final long serialVersionUID = -5257954455922166431L;

        private GameRenderer() { super(ORDINAL); }

        /**
         * Modifies the rendering procedure for the item in the player's hand.
         * @param matrix The transformation matrix for the item
         * @param duration The duration of the transformation
         * @throws IllegalArgumentException if matrix is null, or the duration is null/negative
         */
        public void transformItemInHand(@NotNull Matrix4f matrix, @NotNull Duration duration) throws IllegalArgumentException {
            if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
            transformItemInHand(matrix, duration.toMillis());
        }

        /**
         * Modifies the rendering procedure for the item in the player's hand.
         * @param matrix The transformation matrix for the item
         * @param millis The duration of the transformation, in milliseconds
         * @throws IllegalArgumentException if matrix is null, or the duration is negative
         */
        public void transformItemInHand(@NotNull Matrix4f matrix, long millis) throws IllegalArgumentException {
            if (matrix == null) throw new IllegalArgumentException("Matrix cannot be null");
            if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

            checkFilled();

            data.put(MATRIX, matrix);
            data.put(TIME, millis);

            subOrdinal = 0;
            isFilled = true;
        }

    }

}
