package me.gamercoder215.socketmc.instruction;

import me.gamercoder215.socketmc.instruction.util.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Objects;

/**
 * Represents a SocketMC Instruction to be sent to the client.
 * <br>
 * All instructions are serialized and sent over the network as byte arrays. They contain an {@linkplain #getId() id} and a list of {@linkplain #getParameters() parameters}
 * that are serialized and sent through the player's channel.
 *
 * <h1>Notes</h1>
 * <ul>
 *     <li>Instructions are immutable and cannot be modified after creation.</li>
 *     <li>Instructions are serializable and can be sent over the network as byte arrays.</li>
 *     <li>All X, Y, Height, Width, and other measurements used in drawing instructions are in pixels.</li>
 * </ul>
 */
public final class Instruction implements Serializable {

    // Instruction IDs

    /**
     * Instruction to verify SocketMC is present.
     */
    public static final String PING = "ping";

    /**
     * Instruction to draw text on the client's screen.
     */
    public static final String DRAW_TEXT = "draw_text";

    /**
     * Instruction to draw a shape on the client's screen.
     */
    public static final String DRAW_SHAPE = "draw_shape";

    @Serial
    private static final long serialVersionUID = -4177824277470078500L;

    private final String id;
    private final List<Object> parameters;

    private Instruction(String id, List<Object> parameters) {
        this.id = id;
        this.parameters = parameters;
    }

    /**
     * Gets the ID of this instruction.
     * @return Instruction ID
     */
    @NotNull
    public String getId() {
        return id;
    }

    /**
     * Gets an immutable copy of this instruction's parameters.
     * @return Instruction Parameters
     */
    @Unmodifiable
    @NotNull
    public List<Object> getParameters() {
        return List.copyOf(parameters);
    }

    /**
     * Gets a parameter of this instruction by index.
     * @param index Index of Parameter
     * @return Value of Parameter
     */
    public Object parameter(int index) {
        return parameters.get(index);
    }

    /**
     * Gets a parameter of this instruction by index and casting it to the specified type.
     * @param index Index of Parameter
     * @param type Parameter Type
     * @return Value of Parameter
     * @param <T> Parameter Type
     */
    public <T> T parameter(int index, Class<T> type) {
        return type.cast(parameters.get(index));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return Objects.equals(id, that.id) && Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parameters);
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "id='" + id + '\'' +
                ", parameters=" + parameters +
                '}';
    }

    // Instruction Creation

    /**
     * Creates a {@link #PING} instruction.
     * @return Ping Instruction
     */
    @NotNull
    public static Instruction ping() {
        return new Instruction(PING, List.of());
    }

    /**
     * Creates a {@link #DRAW_TEXT} instruction.
     * @param x X Coordinate for Text
     * @param y Y Coordinate for Text
     * @param text Text to Draw
     * @return Draw Text Instruction
     */
    @NotNull
    public static Instruction drawText(int x, int y, @NotNull String text) {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
        if (text == null || text.isEmpty()) throw new IllegalArgumentException("Text cannot be null or empty");

        return new Instruction(DRAW_TEXT, List.of(x, y, "{\"text\": \"" + text + "\"}", 0xFFFFFFFF, true));
    }

    /**
     * Creates a {@link #DRAW_TEXT} instruction.
     * @param x X Coordinate for Text
     * @param y Y Coordinate for Text
     * @param text Text to Draw
     * @param color Text Color
     * @throws IllegalArgumentException If the coordinates are negative, the text is null, or the color is null
     * @return Draw Text Instruction
     */
    public static Instruction drawText(int x, int y, String text, @NotNull Color color) throws IllegalArgumentException {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
        if (text == null || text.isEmpty()) throw new IllegalArgumentException("Text cannot be null or empty");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");

        return new Instruction(DRAW_TEXT, List.of(x, y, "{\"text\": \"" + text + "\"}", color.getRGB() | 0xFF000000, true));
    }

    /**
     * Creates a {@link #DRAW_TEXT} instruction.
     * @param x X Coordinate for Text
     * @param y Y Coordinate for Text
     * @param text Text to Draw
     * @param color Text Color
     * @param alpha Color Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @return Draw Text Instruction
     * @throws IllegalArgumentException If the coordinates are negative, the text is null, the color is null, or alpha is not 0-255
     */
    public static Instruction drawText(int x, int y, String text, @NotNull Color color, int alpha) throws IllegalArgumentException {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
        if (text == null || text.isEmpty()) throw new IllegalArgumentException("Text cannot be null or empty");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");
        if (alpha < 0 || alpha > 255) throw new IllegalArgumentException("Alpha must be between 0 and 255");

        return new Instruction(DRAW_TEXT, List.of(x, y, "{\"text\": \"" + text + "\"}", color.getRGB() | (alpha << 24), true));
    }

    /**
     * Creates a {@link #DRAW_TEXT} instruction.
     * @param x X Coordinate for Text
     * @param y Y Coordinate for Text
     * @param text Text to Draw
     * @param color Text Color
     * @param alpha Color Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param dropShadow Whether to Draw a Drop Shadow
     * @throws IllegalArgumentException If the coordinates are negative, the text is null, or the color is null
     * @return Draw Text Instruction
     */
    public static Instruction drawText(int x, int y, String text, @NotNull Color color, int alpha, boolean dropShadow) throws IllegalArgumentException {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
        if (text == null || text.isEmpty()) throw new IllegalArgumentException("Text cannot be null or empty");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");
        if (alpha < 0 || alpha > 255) throw new IllegalArgumentException("Alpha must be between 0 and 255");

        return new Instruction(DRAW_TEXT, List.of(x, y, "{\"text\": \"" + text + "\"}", color.getRGB() | (alpha << 24), dropShadow));
    }

    /**
     * Creates a {@link #DRAW_TEXT} instruction.
     * @param x X Coordinate for Text
     * @param y Y Coordinate for Text
     * @param text Text to Draw
     * @return Draw Text Instruction
     * @throws IllegalArgumentException If the coordinates are negative or the text is null
     */
    @NotNull
    public static Instruction drawText(int x, int y, @NotNull Text text) throws IllegalArgumentException {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
        if (text == null) throw new IllegalArgumentException("Text cannot be null");

        return new Instruction(DRAW_TEXT, List.of(x, y, text.toJSON(), text.getColor(), text.isDropShadow()));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a solid shape ({@code "fill"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates or dimensions are negative
     */
    @NotNull
    public static Instruction drawSolidShape(int x, int y, int width, int height) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");

        return new Instruction(DRAW_SHAPE, List.of("fill", x, y, width, height, 0xFFFFFFFF));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a solid shape ({@code "fill"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @param color Shape Color
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates or dimensions are negative, or the color is null
     */
    @NotNull
    public static Instruction drawSolidShape(int x, int y, int width, int height, @NotNull Color color) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");

        return new Instruction(DRAW_SHAPE, List.of("fill", x, y, width, height, color.getRGB() | 0xFF000000));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a solid shape ({@code "fill"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @param color Shape Color
     * @param alpha Color Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates or dimensions are negative, the color is null, or the alpha is not 0-255
     */
    @NotNull
    public static Instruction drawSolidShape(int x, int y, int width, int height, @NotNull Color color, int alpha) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");
        if (alpha < 0 || alpha > 255) throw new IllegalArgumentException("Alpha must be between 0 and 255");

        return new Instruction(DRAW_SHAPE, List.of("fill", x, y, width, height, color.getRGB() | (alpha << 24)));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a gradient shape ({@code "gradient"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @param from Gradient Start Color
     * @param to Gradient End Color
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates or dimensions are negative, or the colors are null
     */
    @NotNull
    public static Instruction drawGradientShape(int x, int y, int width, int height, @NotNull Color from, @NotNull Color to) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (from == null || to == null) throw new IllegalArgumentException("Colors cannot be null");

        return new Instruction(DRAW_SHAPE, List.of("gradient", x, y, width, height, from.getRGB(), to.getRGB(), 0));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a gradient shape ({@code "gradient"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @param from Gradient Start Color
     * @param fromAlpha Gradient Start Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param to Gradient End Color
     * @param toAlpha Gradient End Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates or dimensions are negative, the colors are null, or the alphas are not 0-255
     */
    @NotNull
    public static Instruction drawGradientShape(int x, int y, int width, int height, @NotNull Color from, int fromAlpha, @NotNull Color to, int toAlpha) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (from == null || to == null) throw new IllegalArgumentException("Colors cannot be null");
        if (fromAlpha < 0 || fromAlpha > 255 || toAlpha < 0 || toAlpha > 255) throw new IllegalArgumentException("Alpha must be between 0 and 255");

        return new Instruction(DRAW_SHAPE, List.of("gradient", x, y, width, height, from.getRGB() | (fromAlpha << 24), to.getRGB() | (toAlpha << 24), 0));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a gradient shape ({@code "gradient"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @param from Gradient Start Color
     * @param fromAlpha Gradient Start Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param to Gradient End Color
     * @param toAlpha Gradient End Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param z Shape Z-Index
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates or dimensions are negative, the colors are null, or the alphas are not 0-255
     */
    @NotNull
    public static Instruction drawGradientShape(int x, int y, int width, int height, @NotNull Color from, int fromAlpha, @NotNull Color to, int toAlpha, int z) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (from == null || to == null) throw new IllegalArgumentException("Colors cannot be null");

        return new Instruction(DRAW_SHAPE, List.of("gradient", x, y, width, height, from.getRGB(), to.getRGB(), z));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a vertical line ({@code "line_v"}).
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param height Height of Line
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates or dimensions are negative
     */
    @NotNull
    public static Instruction drawVerticalLine(int x, int y, int height) throws IllegalArgumentException {
        if (x < 0 || y < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");

        return new Instruction(DRAW_SHAPE, List.of("line_v", x, y, height, 0xFFFFFFFF));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a vertical line ({@code "line_v"}).
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param height Height of Line
     * @param color Line Color
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates or dimensions are negative, or the color is null
     */
    @NotNull
    public static Instruction drawVerticalLine(int x, int y, int height, @NotNull Color color) throws IllegalArgumentException {
        if (x < 0 || y < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");

        return new Instruction(DRAW_SHAPE, List.of("line_v", x, y, height, color.getRGB() & 0xFF000000));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a vertical line ({@code "line_v"}).
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param height Height of Line
     * @param color Line Color
     * @param alpha Color Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates or dimensions are negative, the color is null, or the alpha is not 0-255
     */
    @NotNull
    public static Instruction drawVerticalLine(int x, int y, int height, @NotNull Color color, int alpha) throws IllegalArgumentException {
        if (x < 0 || y < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");
        if (alpha < 0 || alpha > 255) throw new IllegalArgumentException("Alpha must be between 0 and 255");

        return new Instruction(DRAW_SHAPE, List.of("line_v", x, y, height, color.getRGB() | (alpha << 24)));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a horizontal line ({@code "line_h"}). The line is 1 pixel long.
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param width Width of Line
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates or dimensions are negative
     */
    @NotNull
    public static Instruction drawHorizontalLine(int x, int y, int width) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");

        return new Instruction(DRAW_SHAPE, List.of("line_h", x, y, width, 0xFFFFFFFF));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a horizontal line ({@code "line_h"}). The line is 1 pixel long.
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param width Width of Line
     * @param color Line Color
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates or dimensions are negative, or the color is null
     */
    @NotNull
    public static Instruction drawHorizontalLine(int x, int y, int width, @NotNull Color color) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");
        return new Instruction(DRAW_SHAPE, List.of("line_h", x, y, width, color.getRGB() | 0xFF000000));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a horizontal line ({@code "line_h"}).
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param width Width of Line
     * @param color Line Color
     * @param alpha Color Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates or dimensions are negative, the color is null, or the alpha is not 0-255
     */
    @NotNull
    public static Instruction drawHorizontalLine(int x, int y, int width, @NotNull Color color, int alpha) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");
        if (alpha < 0 || alpha > 255) throw new IllegalArgumentException("Alpha must be between 0 and 255");

        return new Instruction(DRAW_SHAPE, List.of("line_h", x, y, width, color.getRGB() | (alpha << 24)));
    }

    // Serialization

    /**
     * Serializes this Instruction to a byte array to be passed over the network.
     * @return Byte Array Representation
     */
    @NotNull
    public byte[] toByteArray() {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream outw = new ObjectOutputStream(out);
            outw.writeObject(this);

            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize Instruction", e);
        }
    }

    /**
     * Deserializes an Instruction from a byte array received over the network.
     * @param instruction Byte Array Representation
     * @return Deserialized Instruction
     */
    @Nullable
    public static Instruction fromByteArray(@NotNull byte[] instruction) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(instruction);
            ObjectInputStream inw = new ObjectInputStream(in);

            return (Instruction) inw.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to deserialize Instruction", e);
        }
    }
}
