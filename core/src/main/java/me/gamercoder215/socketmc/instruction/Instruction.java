package me.gamercoder215.socketmc.instruction;

import me.gamercoder215.socketmc.instruction.util.Identifier;
import me.gamercoder215.socketmc.instruction.util.Text;
import me.gamercoder215.socketmc.instruction.util.render.RenderBuffer;
import me.gamercoder215.socketmc.log.AuditLog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import javax.sound.sampled.AudioSystem;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a SocketMC Instruction to be sent to the client.
 * <br>
 * All instructions are serialized and sent over the network as byte arrays. They contain an {@linkplain #getId() id} and a list of {@linkplain #getParameters() parameters}
 * that are serialized and sent through the player's channel.
 * <h2>Notes</h2>
 * <ul>
 *     <li>Instructions are immutable and cannot be modified after creation.</li>
 *     <li>Instructions are serializable and can be sent over the network as byte arrays.</li>
 *     <li>All X, Y, Height, Width, and other measurements used in drawing instructions are in pixels.</li>
 *     <li>Players render things on the screen based on their own FPS (Frames Per Second), hence the provision of timed renderings in milliseconds.</li>
 * </ul>
 */
public final class Instruction implements Serializable {

    // Instruction IDs

    /**
     * Instruction to verify SocketMC is present. This then enables events to be sent from the client to the server, if not already enabled.
     */
    public static final String PING = "ping";

    /**
     * Instruction to draw text on the client's screen.
     */
    public static final String DRAW_TEXT = "draw_text";

    /**
     * Instruction to draw a rectangle on the client's screen.
     */
    public static final String DRAW_SHAPE = "draw_shape";

    /**
     * Instruction to play audio on the client's device. This supports all audio formats supported by the client, according to its JDK. Most commonly is {@code .wav} and {@code .au}.
     * @see AudioSystem#getAudioFileTypes()
     */
    public static final String PLAY_AUDIO = "play_audio";

    /**
     * Instruction to set the draw buffer for the client. This is used to draw complex shapes on the client's screen.
     */
    public static final String DRAW_BUFFER = "draw_buffer";

    /**
     * Instruction to log a message in the client's logs. This is not the same thing as the {@link AuditLog} API.
     */
    public static final String LOG_MESSAGE = "log";

    /**
     * Instruction to draw a texture on the client's screen.
     */
    public static final String DRAW_TEXTURE = "draw_texture";

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

    /**
     * Gets the last parameter of this instruction.
     * @return Last Parameter
     */
    public Object lastParameter() {
        return parameters.getLast();
    }

    /**
     * Gets the last parameter of this instruction and casts it to the specified type.
     * @param type Parameter Type
     * @return Last Parameter
     * @param <T> Parameter Type
     */
    public <T> T lastParameter(Class<T> type) {
        return type.cast(parameters.getLast());
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
        return "Instruction(\"" + id + "\") {" + parameters + '}';
    }

    /**
     * Gets all the IDs of the instructions in this class.
     * @return Instruction IDs
     */
    public static String[] ids() {
        List<String> ids = new ArrayList<>();

        try {
            for (Field f : Instruction.class.getDeclaredFields()) {
                int m = f.getModifiers();

                if (!Modifier.isStatic(m)) continue;
                if (!Modifier.isFinal(m)) continue;
                if (!Modifier.isPublic(m)) continue;
                if (!String.class.equals(f.getType())) continue;

                ids.add((String) f.get(null));
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to get instruction IDs", e);
        }

        return ids.toArray(String[]::new);
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
     * @param duration Time Duration
     * @throws IllegalArgumentException If the coordinates or duration are negative, or the text is null
     * @return Draw Text Instruction
     */
    @NotNull
    public static Instruction drawText(int x, int y, @NotNull String text, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawText(x, y, text, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_TEXT} instruction.
     * @param x X Coordinate for Text
     * @param y Y Coordinate for Text
     * @param text Text to Draw
     * @param millis Duration to display, in milliseconds
     * @throws IllegalArgumentException If the coordinates or duration are negative, or the text is null
     * @return Draw Text Instruction
     */
    @NotNull
    public static Instruction drawText(int x, int y, @NotNull String text, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
        if (text == null || text.isEmpty()) throw new IllegalArgumentException("Text cannot be null or empty");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_TEXT, List.of(x, y, "{\"text\": \"" + text + "\"}", 0xFFFFFFFF, true, millis));
    }

    /**
     * Creates a {@link #DRAW_TEXT} instruction.
     * @param x X Coordinate for Text
     * @param y Y Coordinate for Text
     * @param text Text to Draw
     * @param color Text Color
     * @param duration Time Duration
     * @throws IllegalArgumentException If the coordinates or duration are negative, the text is null, or the color is null
     * @return Draw Text Instruction
     */
    @NotNull
    public static Instruction drawText(int x, int y, String text, @NotNull Color color, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawText(x, y, text, color, duration.toMillis());
    }


    /**
     * Creates a {@link #DRAW_TEXT} instruction.
     * @param x X Coordinate for Text
     * @param y Y Coordinate for Text
     * @param text Text to Draw
     * @param color Text Color
     * @param millis Duration to display, in milliseconds
     * @throws IllegalArgumentException If the coordinates are negative, the text is null, or the color is null
     * @return Draw Text Instruction
     */
    @NotNull
    public static Instruction drawText(int x, int y, String text, @NotNull Color color, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
        if (text == null || text.isEmpty()) throw new IllegalArgumentException("Text cannot be null or empty");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_TEXT, List.of(x, y, "{\"text\": \"" + text + "\"}", color.getRGB() | 0xFF000000, true, millis));
    }

    /**
     * Creates a {@link #DRAW_TEXT} instruction.
     * @param x X Coordinate for Text
     * @param y Y Coordinate for Text
     * @param text Text to Draw
     * @param color Text Color
     * @param alpha Color Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param duration Time Duration
     * @return Draw Text Instruction
     * @throws IllegalArgumentException If the coordinates or duration are negative, the text is null, the color is null, or alpha is not 0-255
     */
    @NotNull
    public static Instruction drawText(int x, int y, String text, @NotNull Color color, int alpha, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawText(x, y, text, color, alpha, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_TEXT} instruction.
     * @param x X Coordinate for Text
     * @param y Y Coordinate for Text
     * @param text Text to Draw
     * @param color Text Color
     * @param alpha Color Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param millis Duration to display, in milliseconds
     * @return Draw Text Instruction
     * @throws IllegalArgumentException If the coordinates or duration are negative, the text is null, the color is null, or alpha is not 0-255
     */
    @NotNull
    public static Instruction drawText(int x, int y, String text, @NotNull Color color, int alpha, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
        if (text == null || text.isEmpty()) throw new IllegalArgumentException("Text cannot be null or empty");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");
        if (alpha < 0 || alpha > 255) throw new IllegalArgumentException("Alpha must be between 0 and 255");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_TEXT, List.of(x, y, "{\"text\": \"" + text + "\"}", color.getRGB() | (alpha << 24), true, millis));
    }

    /**
     * Creates a {@link #DRAW_TEXT} instruction.
     * @param x X Coordinate for Text
     * @param y Y Coordinate for Text
     * @param text Text to Draw
     * @param color Text Color
     * @param alpha Color Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param dropShadow Whether to Draw a Drop Shadow
     * @param duration Time Duration
     * @throws IllegalArgumentException If the coordinates are negative, the text is null, or the color is null
     * @return Draw Text Instruction
     */
    @NotNull
    public static Instruction drawText(int x, int y, String text, @NotNull Color color, int alpha, boolean dropShadow, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawText(x, y, text, color, alpha, dropShadow, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_TEXT} instruction.
     * @param x X Coordinate for Text
     * @param y Y Coordinate for Text
     * @param text Text to Draw
     * @param color Text Color
     * @param alpha Color Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param dropShadow Whether to Draw a Drop Shadow
     * @param millis Duration to display, in milliseconds
     * @throws IllegalArgumentException If the coordinates are negative, the text is null, or the color is null
     * @return Draw Text Instruction
     */
    @NotNull
    public static Instruction drawText(int x, int y, String text, @NotNull Color color, int alpha, boolean dropShadow, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
        if (text == null || text.isEmpty()) throw new IllegalArgumentException("Text cannot be null or empty");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");
        if (alpha < 0 || alpha > 255) throw new IllegalArgumentException("Alpha must be between 0 and 255");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_TEXT, List.of(x, y, "{\"text\": \"" + text + "\"}", color.getRGB() | (alpha << 24), dropShadow, millis));
    }

    /**
     * Creates a {@link #DRAW_TEXT} instruction.
     * @param x X Coordinate for Text
     * @param y Y Coordinate for Text
     * @param text Text to Draw
     * @param argb Text Color (ARGB)
     * @param dropShadow Whether to Draw a Drop Shadow
     * @param duration Time Duration
     * @throws IllegalArgumentException If the coordinates are negative, the text is null, or the color is null
     * @return Draw Text Instruction
     */
    @NotNull
    public static Instruction drawText(int x, int y, String text, int argb, boolean dropShadow, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawText(x, y, text, argb, dropShadow, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_TEXT} instruction.
     * @param x X Coordinate for Text
     * @param y Y Coordinate for Text
     * @param text Text to Draw
     * @param color Text Color
     * @param argb Text Color (ARGB)
     * @param millis Duration to display, in milliseconds
     * @throws IllegalArgumentException If the coordinates are negative, the text is null, or the color is null
     * @return Draw Text Instruction
     */
    @NotNull
    public static Instruction drawText(int x, int y, String text, int argb, boolean dropShadow, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
        if (text == null || text.isEmpty()) throw new IllegalArgumentException("Text cannot be null or empty");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_TEXT, List.of(x, y, "{\"text\": \"" + text + "\"}", argb, dropShadow, millis));
    }

    /**
     * Creates a {@link #DRAW_TEXT} instruction.
     * @param x X Coordinate for Text
     * @param y Y Coordinate for Text
     * @param text Text to Draw
     * @param duration Time Duration
     * @return Draw Text Instruction
     * @throws IllegalArgumentException If the coordinates or duration are negative, or the text is null
     */
    @NotNull
    public static Instruction drawText(int x, int y, @NotNull Text text, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawText(x, y, text, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_TEXT} instruction.
     * @param x X Coordinate for Text
     * @param y Y Coordinate for Text
     * @param text Text to Draw
     * @param millis Duration to display, in milliseconds
     * @return Draw Text Instruction
     * @throws IllegalArgumentException If the coordinates or duration are negative, or the text is null
     */
    @NotNull
    public static Instruction drawText(int x, int y, @NotNull Text text, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
        if (text == null) throw new IllegalArgumentException("Text cannot be null");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_TEXT, List.of(x, y, text.toJSON(), text.getColor(), text.isDropShadow(), millis));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a rectangle ({@code "fill"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @param time Duration to display, in milliseconds
     * @param unit Time Unit
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative
     */
    @NotNull
    public static Instruction drawRect(int x, int y, int width, int height, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawRect(x, y, width, height, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a rectangle ({@code "fill"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @param millis Duration to display, in milliseconds
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative
     */
    @NotNull
    public static Instruction drawRect(int x, int y, int width, int height, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_SHAPE, List.of("fill", x, y, width, height, 0xFFFFFFFF, millis));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a rectangle ({@code "fill"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @param color Shape Color
     * @param duration Time Duration
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, or the color is null
     */
    @NotNull
    public static Instruction drawRect(int x, int y, int width, int height, @NotNull Color color, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawRect(x, y, width, height, color, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a rectangle ({@code "fill"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @param color Shape Color
     * @param millis Duration to display, in milliseconds
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, or the color is null
     */
    @NotNull
    public static Instruction drawRect(int x, int y, int width, int height, @NotNull Color color, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_SHAPE, List.of("fill", x, y, width, height, color.getRGB() | 0xFF000000, millis));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a rectangle ({@code "fill"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @param color Shape Color
     * @param alpha Color Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param duration Time Duration
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, the color is null, or the alpha is not 0-255
     */
    @NotNull
    public static Instruction drawRect(int x, int y, int width, int height, @NotNull Color color, int alpha, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawRect(x, y, width, height, color, alpha, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a rectangle ({@code "fill"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @param color Shape Color
     * @param alpha Color Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param millis Duration to display, in milliseconds
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, the color is null, or the alpha is not 0-255
     */
    @NotNull
    public static Instruction drawRect(int x, int y, int width, int height, @NotNull Color color, int alpha, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");
        if (alpha < 0 || alpha > 255) throw new IllegalArgumentException("Alpha must be between 0 and 255");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_SHAPE, List.of("fill", x, y, width, height, color.getRGB() | (alpha << 24), millis));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a gradient rectangle ({@code "gradient"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @param from Gradient Start Color
     * @param to Gradient End Color
     * @param duration Time Duration
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, or the colors are null
     */
    @NotNull
    public static Instruction drawGradientRect(int x, int y, int width, int height, @NotNull Color from, @NotNull Color to, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawGradientRect(x, y, width, height, from, to, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a gradient rectangle ({@code "gradient"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @param from Gradient Start Color
     * @param to Gradient End Color
     * @param millis Duration to display, in milliseconds
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, or the colors are null
     */
    @NotNull
    public static Instruction drawGradientRect(int x, int y, int width, int height, @NotNull Color from, @NotNull Color to, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (from == null || to == null) throw new IllegalArgumentException("Colors cannot be null");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_SHAPE, List.of("gradient", x, y, width, height, from.getRGB(), to.getRGB(), 0, millis));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a gradient rectangle ({@code "gradient"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @param from Gradient Start Color
     * @param fromAlpha Gradient Start Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param to Gradient End Color
     * @param toAlpha Gradient End Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param duration Time Duration
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, the colors are null, or the alphas are not 0-255
     */
    @NotNull
    public static Instruction drawGradientRect(int x, int y, int width, int height, @NotNull Color from, int fromAlpha, @NotNull Color to, int toAlpha, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawGradientRect(x, y, width, height, from, fromAlpha, to, toAlpha, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a gradient rectangle ({@code "gradient"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @param from Gradient Start Color
     * @param fromAlpha Gradient Start Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param to Gradient End Color
     * @param toAlpha Gradient End Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param millis Duration to display, in milliseconds
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, the colors are null, or the alphas are not 0-255
     */
    @NotNull
    public static Instruction drawGradientRect(int x, int y, int width, int height, @NotNull Color from, int fromAlpha, @NotNull Color to, int toAlpha, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (from == null || to == null) throw new IllegalArgumentException("Colors cannot be null");
        if (fromAlpha < 0 || fromAlpha > 255 || toAlpha < 0 || toAlpha > 255) throw new IllegalArgumentException("Alpha must be between 0 and 255");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_SHAPE, List.of("gradient", x, y, width, height, from.getRGB() | (fromAlpha << 24), to.getRGB() | (toAlpha << 24), 0, millis));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a gradient rectangle ({@code "gradient"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @param from Gradient Start Color
     * @param fromAlpha Gradient Start Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param to Gradient End Color
     * @param toAlpha Gradient End Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param z Shape Z-Index
     * @param duration Time Duration
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, the colors are null, or the alphas are not 0-255
     */
    @NotNull
    public static Instruction drawGradientRect(int x, int y, int width, int height, @NotNull Color from, int fromAlpha, @NotNull Color to, int toAlpha, int z, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawGradientRect(x, y, width, height, from, fromAlpha, to, toAlpha, z, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a gradient rectangle ({@code "gradient"}).
     * @param x X Coordinate for Shape
     * @param y Y Coordinate for Shape
     * @param width Width of Shape
     * @param height Height of Shape
     * @param from Gradient Start Color
     * @param fromAlpha Gradient Start Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param to Gradient End Color
     * @param toAlpha Gradient End Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param z Shape Z-Index
     * @param millis Duration to display, in milliseconds
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, the colors are null, or the alphas are not 0-255
     */
    @NotNull
    public static Instruction drawGradientRect(int x, int y, int width, int height, @NotNull Color from, int fromAlpha, @NotNull Color to, int toAlpha, int z, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (from == null || to == null) throw new IllegalArgumentException("Colors cannot be null");
        if (fromAlpha < 0 || fromAlpha > 255 || toAlpha < 0 || toAlpha > 255) throw new IllegalArgumentException("Alpha must be between 0 and 255");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_SHAPE, List.of("gradient", x, y, width, height, from.getRGB(), to.getRGB(), z, millis));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a vertical line ({@code "line_v"}). The line is 1 pixel wide.
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param height Height of Line
     * @param duration Time Duration
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates or dimensions are negative
     */
    @NotNull
    public static Instruction drawVerticalLine(int x, int y, int height, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawVerticalLine(x, y, height, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a vertical line ({@code "line_v"}). The line is 1 pixel wide.
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param height Height of Line
     * @param millis Duration to display, in milliseconds
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative
     */
    @NotNull
    public static Instruction drawVerticalLine(int x, int y, int height, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_SHAPE, List.of("line_v", x, y, height, 0xFFFFFFFF, millis));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a vertical line ({@code "line_v"}). The line is 1 pixel wide.
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param height Height of Line
     * @param color Line Color
     * @param duration Time Duration
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, or the color is null
     */
    @NotNull
    public static Instruction drawVerticalLine(int x, int y, int height, @NotNull Color color, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawVerticalLine(x, y, height, color, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a vertical line ({@code "line_v"}). The line is 1 pixel wide.
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param height Height of Line
     * @param color Line Color
     * @param millis Duration to display, in milliseconds
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, or the color is null
     */
    @NotNull
    public static Instruction drawVerticalLine(int x, int y, int height, @NotNull Color color, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_SHAPE, List.of("line_v", x, y, height, color.getRGB() & 0xFF000000, millis));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a vertical line ({@code "line_v"}). The line is 1 pixel wide.
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param height Height of Line
     * @param color Line Color
     * @param alpha Color Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param duration Time Duration
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, the color is null, or the alpha is not 0-255
     */
    @NotNull
    public static Instruction drawVerticalLine(int x, int y, int height, @NotNull Color color, int alpha, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawVerticalLine(x, y, height, color, alpha, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a vertical line ({@code "line_v"}). The line is 1 pixel wide.
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param height Height of Line
     * @param color Line Color
     * @param alpha Color Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param millis Duration to display, in milliseconds
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, the color is null, or the alpha is not 0-255
     */
    @NotNull
    public static Instruction drawVerticalLine(int x, int y, int height, @NotNull Color color, int alpha, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0 || height < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");
        if (alpha < 0 || alpha > 255) throw new IllegalArgumentException("Alpha must be between 0 and 255");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_SHAPE, List.of("line_v", x, y, height, color.getRGB() | (alpha << 24), millis));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a horizontal line ({@code "line_h"}). The line is 1 pixel long.
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param width Width of Line
     * @param duration Time Duration
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates or dimensions are negative
     */
    @NotNull
    public static Instruction drawHorizontalLine(int x, int y, int width, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawHorizontalLine(x, y, width, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a horizontal line ({@code "line_h"}). The line is 1 pixel long.
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param width Width of Line
     * @param millis Duration to display, in milliseconds
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates or dimensions are negative
     */
    @NotNull
    public static Instruction drawHorizontalLine(int x, int y, int width, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_SHAPE, List.of("line_h", x, y, width, 0xFFFFFFFF, millis));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a horizontal line ({@code "line_h"}). The line is 1 pixel long.
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param width Width of Line
     * @param color Line Color
     * @param duration Time Duration
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, or the color is null
     */
    @NotNull
    public static Instruction drawHorizontalLine(int x, int y, int width, @NotNull Color color, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawHorizontalLine(x, y, width, color, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a horizontal line ({@code "line_h"}). The line is 1 pixel long.
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param width Width of Line
     * @param color Line Color
     * @param millis Duration to display, in milliseconds
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, or the color is null
     */
    @NotNull
    public static Instruction drawHorizontalLine(int x, int y, int width, @NotNull Color color, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_SHAPE, List.of("line_h", x, y, width, color.getRGB() | 0xFF000000, millis));
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a horizontal line ({@code "line_h"}).
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param width Width of Line
     * @param color Line Color
     * @param alpha Color Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param duration Time Duration
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, the color is null, or the alpha is not 0-255
     */
    @NotNull
    public static Instruction drawHorizontalLine(int x, int y, int width, @NotNull Color color, int alpha, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawHorizontalLine(x, y, width, color, alpha, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_SHAPE} instruction for a horizontal line ({@code "line_h"}).
     * @param x X Coordinate for Line
     * @param y Y Coordinate for Line
     * @param width Width of Line
     * @param color Line Color
     * @param alpha Color Alpha ({@code 0-255} / {@code 0x00 - 0xFF}).
     * @param millis Duration to display, in milliseconds
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, the color is null, or the alpha is not 0-255
     */
    @NotNull
    public static Instruction drawHorizontalLine(int x, int y, int width, @NotNull Color color, int alpha, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0 || width < 0) throw new IllegalArgumentException("Coordinates and dimensions cannot be negative");
        if (color == null) throw new IllegalArgumentException("Color cannot be null");
        if (alpha < 0 || alpha > 255) throw new IllegalArgumentException("Alpha must be between 0 and 255");

        return new Instruction(DRAW_SHAPE, List.of("line_h", x, y, width, color.getRGB() | (alpha << 24), millis));
    }

    /**
     * Creates a {@link #PLAY_AUDIO} instruction.
     * @param file Path to Audio File
     * @return Play Audio Instruction
     */
    @NotNull
    public static Instruction playAudio(@NotNull File file) {
        if (file == null) throw new IllegalArgumentException("File cannot be null");
        if (!file.exists()) throw new IllegalArgumentException("File does not exist");

        try {
            return playAudio(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to read audio file", e);
        }
    }

    /**
     * Creates a {@link #DRAW_BUFFER} instruction.
     * @param buffer Render Buffer
     * @param duration Time Duration
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the buffer is null, or the duration is negative
     */
    @NotNull
    public static Instruction drawBuffer(@NotNull RenderBuffer buffer, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawBuffer(buffer, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_BUFFER} instruction.
     * @param buffer Render Buffer
     * @param millis Duration to display, in milliseconds
     * @return Draw Shape Instruction
     * @throws IllegalArgumentException If the buffer is null, or the duration is negative
     */
    @NotNull
    public static Instruction drawBuffer(@NotNull RenderBuffer buffer, long millis) throws IllegalArgumentException {
        if (buffer == null) throw new IllegalArgumentException("Buffer cannot be null");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_BUFFER, List.of(buffer, millis));
    }

    /**
     * Creates a {@link #PLAY_AUDIO} instruction.
     * @param file Input Stream to Audio File
     * @return Play Audio Instruction
     * @throws IllegalArgumentException If the file is null
     * @throws IllegalStateException If the file cannot be read
     */
    @NotNull
    public static Instruction playAudio(@NotNull InputStream file) throws IllegalArgumentException, IllegalStateException {
        if (file == null) throw new IllegalArgumentException("File cannot be null");

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = file.read(buf)) != -1;) bos.write(buf, 0, readNum);

            buf = bos.toByteArray();
            return new Instruction(PLAY_AUDIO, List.of(buf));
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read audio file", e);
        }
    }

    /**
     * Creates a {@link #LOG_MESSAGE} instruction.
     * @param message Message to Log
     * @return Log Message Instruction
     * @throws IllegalArgumentException If the message is null or empty
     */
    @NotNull
    public static Instruction logMessage(@NotNull String message) throws IllegalArgumentException {
        if (message == null || message.isEmpty()) throw new IllegalArgumentException("Message cannot be null or empty");
        return new Instruction(LOG_MESSAGE, List.of(message));
    }

    /**
     * Creates a {@link #DRAW_TEXTURE} instruction.
     * @param x X Coordinate for Texture
     * @param y Y Coordinate for Texture
     * @param texture Texture Identifier
     * @param width Width of Texture
     * @param height Height of Texture
     * @param startTop Where to start drawing from the top of the texture
     * @param startLeft Where to start drawing from the left of the texture
     * @param duration Time Duration
     * @return Draw Texture Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, or the texture is null
     */
    @NotNull
    public static Instruction drawTexture(int x, int y, int width, int height, @NotNull Identifier texture, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawTexture(x, y, width, height, texture, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_TEXTURE} instruction.
     * @param x X Coordinate for Texture
     * @param y Y Coordinate for Texture
     * @param texture Texture Identifier
     * @param width Width of Texture
     * @param height Height of Texture
     * @param millis Duration to display, in milliseconds
     * @return Draw Texture Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, or the texture is null
     */
    @NotNull
    public static Instruction drawTexture(int x, int y, int width, int height, @NotNull Identifier texture, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
        if (texture == null) throw new IllegalArgumentException("Texture cannot be null");
        if (width < 0 || height < 0) throw new IllegalArgumentException("Dimensions cannot be negative");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_TEXTURE, List.of(x, y, width, height, texture, 0, 0, -1, -1, millis));
    }

    /**
     * Creates a {@link #DRAW_TEXTURE} instruction.
     * @param x X Coordinate for Texture
     * @param y Y Coordinate for Texture
     * @param texture Texture Identifier
     * @param width Width of Texture
     * @param height Height of Texture
     * @param startLeft The horizontal offset to start drawing from the left of the texture
     * @param startTop The vertical offset to start drawing from the top of the texture
     * @param duration Time Duration
     * @return Draw Texture Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, or the texture is null
     */
    @NotNull
    public static Instruction drawTexture(int x, int y, int width, int height, @NotNull Identifier texture, int startLeft, int startTop, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawTexture(x, y, width, height, texture, startLeft, startTop, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_TEXTURE} instruction.
     * @param x X Coordinate for Texture
     * @param y Y Coordinate for Texture
     * @param texture Texture Identifier
     * @param width Width of Texture
     * @param height Height of Texture
     * @param startLeft The horizontal offset to start drawing from the left of the texture
     * @param startTop The vertical offset to start drawing from the top of the texture
     * @param millis Duration to display, in milliseconds
     * @return Draw Texture Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, or the texture is null
     */
    @NotNull
    public static Instruction drawTexture(int x, int y, int width, int height, @NotNull Identifier texture, int startLeft, int startTop, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
        if (texture == null) throw new IllegalArgumentException("Texture cannot be null");
        if (width < 0 || height < 0) throw new IllegalArgumentException("Dimensions cannot be negative");
        if (startTop < 0 || startLeft < 0) throw new IllegalArgumentException("Start coordinates cannot be negative");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_TEXTURE, List.of(x, y, width, height, texture, startLeft, startTop, -1, -1, millis));
    }

    /**
     * Creates a {@link #DRAW_TEXTURE} instruction.
     * @param x X Coordinate for Texture
     * @param y Y Coordinate for Texture
     * @param texture Texture Identifier
     * @param width Width of Texture
     * @param height Height of Texture
     * @param startLeft The horizontal offset to start drawing from the left of the texture
     * @param startTop The vertical offset to start drawing from the top of the texture
     * @param regionWidth Width of Region to Draw
     * @param regionHeight Height of Region to Draw
     * @param duration Time Duration
     * @return Draw Texture Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, or the texture is null
     */
    @NotNull
    public static Instruction drawTexture(int x, int y, int width, int height, @NotNull Identifier texture, int startLeft, int startTop, int regionWidth, int regionHeight, @NotNull Duration duration) throws IllegalArgumentException {
        if (duration == null) throw new IllegalArgumentException("Duration cannot be null");
        return drawTexture(x, y, width, height, texture, startLeft, startTop, regionWidth, regionHeight, duration.toMillis());
    }

    /**
     * Creates a {@link #DRAW_TEXTURE} instruction.
     * @param x X Coordinate for Texture
     * @param y Y Coordinate for Texture
     * @param texture Texture Identifier
     * @param width Width of Texture
     * @param height Height of Texture
     * @param startLeft The horizontal offset to start drawing from the left of the texture
     * @param startTop The vertical offset to start drawing from the top of the texture
     * @param regionWidth Width of Region to Draw
     * @param regionHeight Height of Region to Draw
     * @param millis Duration to display, in milliseconds
     * @return Draw Texture Instruction
     * @throws IllegalArgumentException If the coordinates, dimensions, or duration are negative, or the texture is null
     */
    @NotNull
    public static Instruction drawTexture(int x, int y, int width, int height, @NotNull Identifier texture, int startLeft, int startTop, int regionWidth, int regionHeight, long millis) throws IllegalArgumentException {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
        if (texture == null) throw new IllegalArgumentException("Texture cannot be null");
        if (width < 0 || height < 0) throw new IllegalArgumentException("Dimensions cannot be negative");
        if (startTop < 0 || startLeft < 0) throw new IllegalArgumentException("Start coordinates cannot be negative");
        if (regionWidth < 0 || regionHeight < 0) throw new IllegalArgumentException("Region dimensions cannot be negative");
        if (millis < 0) throw new IllegalArgumentException("Duration cannot be negative");

        return new Instruction(DRAW_TEXTURE, List.of(x, y, width, height, texture, startLeft, startTop, regionWidth, regionHeight, millis));
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
            throw new IllegalArgumentException("Failed to deserialize Instruction", e);
        }
    }
}
