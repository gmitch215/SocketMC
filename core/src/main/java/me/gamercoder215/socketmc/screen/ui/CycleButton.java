package me.gamercoder215.socketmc.screen.ui;

import me.gamercoder215.socketmc.util.render.text.CommonTexts;
import me.gamercoder215.socketmc.util.render.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * <p>Represents a Button with multiple states.</p>
 * <p>Clicking on the button cycles through the states forwards.
 * Holding down Alt while doing this will cycle through the states backwards.</p>
 * <strong>Important: The types specified must be serializable, otherwise it will throw an error.</strong>
 * @param <T> the type of objects to cycle through
 */
public final class CycleButton<T> extends AbstractButton {

    @Serial
    private static final long serialVersionUID = 2869080428866180437L;

    private final List<T> values = new ArrayList<>();
    private T initialValue;
    private Function<T, Text> stringifier;

    /**
     * Constructs a new CycleButton using the default dimensions.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param message the text message
     * @param stringifier the function to convert the values to text
     * @param values the values to cycle through
     * @throws IllegalArgumentException if coordinates are negative, or stringifier/values/message is null
     */
    public CycleButton(int x, int y, @NotNull Text message, @NotNull Function<T, Text> stringifier, @NotNull Iterable<T> values) throws IllegalArgumentException {
        this(x, y, message, stringifier, values, null);
    }

    /**
     * Constructs a new CycleButton.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param message the text message
     * @param stringifier the function to convert the values to text
     * @param values the values to cycle through
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or stringifier/values/message is null
     */
    public CycleButton(int x, int y, int width, int height, @NotNull Text message, @NotNull Function<T, Text> stringifier, @NotNull Iterable<T> values) throws IllegalArgumentException {
        this(x, y, width, height, message, stringifier, values, null);
    }

    /**
     * Constructs a new CycleButton using the default dimensions.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param message the text message
     * @param stringifier the function to convert the values to text
     * @param values the values to cycle through
     * @param initialValue the initial value, or null if using first value
     * @throws IllegalArgumentException if coordinates are negative, or stringifier/values/message is null
     */
    public CycleButton(int x, int y, @NotNull Text message, @NotNull Function<T, Text> stringifier, @NotNull Iterable<T> values, @Nullable T initialValue) throws IllegalArgumentException {
        super(x, y, message);

        values.forEach(this.values::add);
        if (this.values.isEmpty()) throw new IllegalArgumentException("Values cannot be empty");

        this.stringifier = stringifier;
        this.initialValue = initialValue;
    }

    /**
     * Constructs a new CycleButton.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param message the text message
     * @param stringifier the function to convert the values to text
     * @param values the values to cycle through
     * @param initialValue the initial value, or null if using first value
     * @throws IllegalArgumentException if coordinates or dimensions are negative, or stringifier/values/message is null
     */
    public CycleButton(int x, int y, int width, int height, @NotNull Text message, @NotNull Function<T, Text> stringifier, @NotNull Iterable<T> values, @Nullable T initialValue) throws IllegalArgumentException {
        super(x, y, width, height, message);

        if (stringifier == null) throw new IllegalArgumentException("Stringifier cannot be null");
        if (values == null) throw new IllegalArgumentException("Values cannot be null");

        values.forEach(this.values::add);
        if (this.values.isEmpty()) throw new IllegalArgumentException("Values cannot be empty");

        this.stringifier = stringifier;
        this.initialValue = initialValue;
    }

    /**
     * Gets an immutable copy of the values to cycle through.
     * @return the values
     */
    @NotNull
    public List<T> getValues() {
        return List.copyOf(values);
    }

    /**
     * Gets the initial value, if set.
     * @return the initial value
     */
    @Nullable
    public T getInitialValue() {
        return initialValue;
    }

    /**
     * Sets the initial value.
     * @param initialValue the initial value, or null if using first value
     * @throws IllegalArgumentException if initialValue is not in the values list
     */
    public void setInitialValue(@Nullable T initialValue) throws IllegalArgumentException {
        this.initialValue = initialValue == null ? values.getFirst() : initialValue;
    }

    /**
     * Gets the function to convert the values to text.
     * @return the function to convert the values to text
     */
    @NotNull
    public Function<T, Text> getStringifier() {
        return stringifier;
    }

    /**
     * Sets the function to convert the values to text.
     * @param stringifier the function to convert the values to text
     * @throws IllegalArgumentException if stringifier is null
     */
    public void setStringifier(@NotNull Function<T, Text> stringifier) throws IllegalArgumentException {
        if (stringifier == null) throw new IllegalArgumentException("Stringifier cannot be null");
        this.stringifier = stringifier;
    }

    // Static Util

    /**
     * Uses {@link #createBoolean(int, int, Text, Text)} with {@link CommonTexts#OPTION_ON} and {@link CommonTexts#OPTION_OFF}.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return the new CycleButton
     * @throws IllegalArgumentException if constructor fails
     */
    @NotNull
    public static CycleButton<Boolean> createOnOff(int x, int y) throws IllegalArgumentException {
        return createBoolean(x, y, CommonTexts.OPTION_ON, CommonTexts.OPTION_OFF);
    }

    /**
     * Creates a new CycleButton with {@code true} and {@code false} values.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param on the text to display when the value is {@code true}
     * @param off the text to display when the value is {@code false}
     * @return the new CycleButton
     * @throws IllegalArgumentException if on or off text is null, or constructor fails
     */
    @NotNull
    public static CycleButton<Boolean> createBoolean(int x, int y, @NotNull Text on, @NotNull Text off) throws IllegalArgumentException {
        if (on == null) throw new IllegalArgumentException("On text cannot be null");
        if (off == null) throw new IllegalArgumentException("Off text cannot be null");

        return new CycleButton<>(x, y, on, b -> b ? on : off, List.of(true, false));
    }

    /**
     * Uses {@link #createBoolean(int, int, Text, Text)} using {@link CommonTexts#OPTION_ON} and {@link CommonTexts#OPTION_OFF}.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @return the new CycleButton
     * @throws IllegalArgumentException if constructor fails
     */
    @NotNull
    public static CycleButton<Boolean> createOnOff(int x, int y, int width, int height) throws IllegalArgumentException {
        return createBoolean(x, y, width, height, CommonTexts.OPTION_ON, CommonTexts.OPTION_OFF);
    }

    /**
     * Creates a new CycleButton with {@code true} and {@code false} values.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param on the text to display when the value is {@code true}
     * @param off the text to display when the value is {@code false}
     * @return the new CycleButton
     * @throws IllegalArgumentException if on or off text is null, or constructor fails
     */
    @NotNull
    public static CycleButton<Boolean> createBoolean(int x, int y, int width, int height, @NotNull Text on, @NotNull Text off) throws IllegalArgumentException {
        if (on == null) throw new IllegalArgumentException("On text cannot be null");
        if (off == null) throw new IllegalArgumentException("Off text cannot be null");

        return new CycleButton<>(x, y, width, height, on, b -> b ? on : off, List.of(true, false));
    }
}
