package xyz.gmitch215.socketmc.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

/**
 * Represents an object that holds parameters.
 */
public interface Paramaterized {

    /**
     * Gets an immutable copy of the parameters.
     * @return Object Parameters
     */
    @NotNull
    @Unmodifiable
    List<Object> getParameters();

    /**
     * Gets the parameter at the specified index.
     * @param index The index of the parameter to get
     * @return The parameter at the specified index
     * @throws IllegalArgumentException if the index is out of bounds
     */
    @NotNull
    default Object parameter(int index) throws IllegalArgumentException {
        return getParameters().get(index);
    }

    /**
     * Gets the first parameter.
     * @return The first parameter
     */
    default Object firstParameter() {
        return getParameters().getFirst();
    }

    /**
     * Gets the last parameter.
     * @return The last parameter
     */
    default Object lastParameter() {
        return getParameters().getLast();
    }

    /**
     * Gets the parameter at the specified index.
     * @param index The index of the parameter to get
     * @param <T> The type of the parameter
     * @return The parameter at the specified index
     * @throws IllegalArgumentException if the index is out of bounds or type is null
     */
    @NotNull
    default <T> T parameter(int index, @NotNull Class<T> type) throws IllegalArgumentException {
        if (type == null) throw new IllegalArgumentException("type cannot be null");
        return type.cast(parameter(index));
    }

    /**
     * Gets the parameter at the specified index.
     * @param index The index of the parameter to get
     * @param type The type of the parameter
     * @param def The default value to return if the parameter is null
     * @return The parameter at the specified index
     * @param <T> The type of the parameter
     * @throws IllegalArgumentException if the index is out of bounds, type is null, or def is null
     */
    @NotNull
    default <T> T parameter(int index, @NotNull Class<T> type, @NotNull T def) throws IllegalArgumentException {
        if (type == null) throw new IllegalArgumentException("type cannot be null");
        if (def == null) throw new IllegalArgumentException("def cannot be null");

        T param = parameter(index, type);
        return param == null ? def : param;
    }

    /**
     * Gets the first parameter.
     * @param type The type of the parameter
     * @return The first parameter
     * @param <T> The type of the parameter
     * @throws IllegalArgumentException if type is null
     */
    @NotNull
    default <T> T firstParameter(@NotNull Class<T> type) throws IllegalArgumentException {
        if (type == null) throw new IllegalArgumentException("type cannot be null");

        return type.cast(firstParameter());
    }

    /**
     * Gets the last parameter.
     * @param type The type of the parameter
     * @return The last parameter
     * @param <T> The type of the parameter
     * @throws IllegalArgumentException if type is null
     */
    @NotNull
    default <T> T lastParameter(@NotNull Class<T> type) throws IllegalArgumentException {
        if (type == null) throw new IllegalArgumentException("type cannot be null");

        return type.cast(lastParameter());
    }

    /**
     * Gets a parameter as an integer.
     * @param index The index of the parameter to get
     * @return The parameter at the specified index as an integer
     */
    default int intParameter(int index) {
        return parameter(index, Integer.class, 0);
    }

    /**
     * Gets a parameter as an integer.
     * @param index The index of the parameter to get
     * @param def The default value to return if the parameter is null
     * @return The parameter at the specified index as an integer
     */
    default int intParameter(int index, int def) {
        return parameter(index, Integer.class, def);
    }

    /**
     * Gets the first parameter as an integer.
     * @return The first parameter as an integer
     */
    default int firstIntParameter() {
        return firstParameter(Integer.class);
    }

    /**
     * Gets the last parameter as an integer.
     * @return The last parameter as an integer
     */
    default int lastIntParameter() {
        return lastParameter(Integer.class);
    }

    /**
     * Gets a parameter as a long.
     * @param index The index of the parameter to get
     * @return The parameter at the specified index as a long
     */
    default long longParameter(int index) {
        return parameter(index, Long.class, 0L);
    }
    
    /**
     * Gets a parameter as a long.
     * @param index The index of the parameter to get
     * @param def The default value to return if the parameter is null
     * @return The parameter at the specified index as a long
     */
    default long longParameter(int index, long def) {
        return parameter(index, Long.class, def);
    }
    
    /**
     * Gets the first parameter as a long.
     * @return The first parameter as a long
     */
    default long firstLongParameter() {
        return firstParameter(Long.class);
    }
    
    /**
     * Gets the last parameter as a long.
     * @return The last parameter as a long
     */
    default long lastLongParameter() {
        return lastParameter(Long.class);
    }

    /**
     * Gets a parameter as a float.
     * @param index The index of the parameter to get
     * @return The parameter at the specified index as a float
     */
    default float floatParameter(int index) {
        return parameter(index, Float.class, 0F);
    }

    /**
     * Gets a parameter as a float.
     * @param index The index of the parameter to get
     * @param def The default value to return if the parameter is null
     * @return The parameter at the specified index as a float
     */
    default float floatParameter(int index, float def) {
        return parameter(index, Float.class, def);
    }

    /**
     * Gets the first parameter as a float.
     * @return The first parameter as a float
     */
    default float firstFloatParameter() {
        return firstParameter(Float.class);
    }

    /**
     * Gets the last parameter as a float.
     * @return The last parameter as a float
     */
    default float lastFloatParameter() {
        return lastParameter(Float.class);
    }

    /**
     * Gets a parameter as a double.
     * @param index The index of the parameter to get
     * @return The parameter at the specified index as a double
     */
    default double doubleParameter(int index) {
        return parameter(index, Double.class, 0D);
    }

    /**
     * Gets a parameter as a double.
     * @param index The index of the parameter to get
     * @param def The default value to return if the parameter is null
     * @return The parameter at the specified index as a double
     */
    default double doubleParameter(int index, double def) {
        return parameter(index, Double.class, def);
    }

    /**
     * Gets the first parameter as a double.
     * @return The first parameter as a double
     */
    default double firstDoubleParameter() {
        return firstParameter(Double.class);
    }

    /**
     * Gets the last parameter as a double.
     * @return The last parameter as a double
     */
    default double lastDoubleParameter() {
        return lastParameter(Double.class);
    }

    /**
     * Gets a parameter as a boolean.
     * @param index The index of the parameter to get
     * @return The parameter at the specified index as a boolean
     */
    default boolean booleanParameter(int index) {
        return parameter(index, Boolean.class, false);
    }

    /**
     * Gets a parameter as a boolean.
     * @param index The index of the parameter to get
     * @param def The default value to return if the parameter is null
     * @return The parameter at the specified index as a boolean
     */
    default boolean booleanParameter(int index, boolean def) {
        return parameter(index, Boolean.class, def);
    }

    /**
     * Gets the first parameter as a boolean.
     * @return The first parameter as a boolean
     */
    default boolean firstBooleanParameter() {
        return firstParameter(Boolean.class);
    }

    /**
     * Gets the last parameter as a boolean.
     * @return The last parameter as a boolean
     */
    default boolean lastBooleanParameter() {
        return lastParameter(Boolean.class);
    }

    /**
     * Gets a parameter as a string.
     * @param index The index of the parameter to get
     * @return The parameter at the specified index as a string
     */
    @NotNull
    default String stringParameter(int index) {
        return parameter(index, String.class, "");
    }

    /**
     * Gets a parameter as a string.
     * @param index The index of the parameter to get
     * @param def The default value to return if the parameter is null
     * @return The parameter at the specified index as a string
     */
    @NotNull
    default String stringParameter(int index, @NotNull String def) {
        return parameter(index, String.class, def);
    }

    /**
     * Gets the first parameter as a string.
     * @return The first parameter as a string
     */
    @NotNull
    default String firstStringParameter() {
        return firstParameter(String.class);
    }

    /**
     * Gets the last parameter as a string.
     * @return The last parameter as a string
     */
    @NotNull
    default String lastStringParameter() {
        return lastParameter(String.class);
    }

}
