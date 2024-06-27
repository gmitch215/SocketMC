package xyz.gmitch215.socketmc.instruction;

import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import xyz.gmitch215.socketmc.util.DataHolder;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a rendering-related instruction.
 */
public abstract class RenderInstruction implements Serializable, DataHolder {

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

    @Override
    public Map<String, Object> getData() {
        return Map.copyOf(data);
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

        /**
         * Renders the confusion effect, used when the player has the nausea effect.
         * @param strength The strength of the effect, between 0 and 1
         * @throws IllegalArgumentException if the strength is not between 0 and 1
         */
        public void renderConfusionEffect(float strength) throws IllegalArgumentException {
            checkFilled();

            if (strength < 0 || strength > 1) throw new IllegalArgumentException("Strength must be between 0 and 1");

            data.put("strength", strength);

            subOrdinal = 1;
            isFilled = true;
        }

    }

}
