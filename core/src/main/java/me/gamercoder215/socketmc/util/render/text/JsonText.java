package me.gamercoder215.socketmc.util.render.text;

import org.jetbrains.annotations.NotNull;

/**
 * Represents text formatted from a JSON string.
 */
public final class JsonText extends Text {

    private String json;

    private JsonText(String json) {
        this.json = json;
    }

    /**
     * @deprecated Unsupported
     */
    @Override
    @Deprecated
    public int getColor() {
        throw new UnsupportedOperationException("JsonText does not support getColor()");
    }

    /**
     * Gets the JSON string.
     * @return JSON string
     */
    @Override
    public String toJSON() {
        return json;
    }

    /**
     * Creates an empty JsonText object.
     * @return JsonText object
     */
    @NotNull
    public static JsonText empty() {
        return new JsonText("{}");
    }

    /**
     * Creates a new JsonText object with the given JSON string. This method does not perform JSON validation.
     * @param json JSON string
     * @return JsonText object
     * @throws IllegalArgumentException if the JSON is null or empty
     */
    @NotNull
    public static JsonText raw(@NotNull String json) throws IllegalArgumentException {
        if (json == null) throw new IllegalArgumentException("JSON cannot be null");
        if (json.isEmpty()) throw new IllegalArgumentException("JSON cannot be empty");

        return new JsonText(json);
    }

    /**
     * Creates a new JsonText object with the given message in text form.
     * @param text JSON string
     * @return JsonText object
     * @throws IllegalArgumentException if the text is null or empty
     */
    @NotNull
    public static JsonText text(@NotNull String text) throws IllegalArgumentException {
        if (text == null) throw new IllegalArgumentException("Text cannot be null");
        if (text.isEmpty()) throw new IllegalArgumentException("Text cannot be empty");

        return new JsonText("{\"text\":\"" + text + "\"}");
    }

    /**
     * Creates a new JsonText object with the given message in translation form.
     * @param key Translation key
     * @return JsonText object
     * @throws IllegalArgumentException if the key is null or empty
     */
    @NotNull
    public static JsonText translate(@NotNull String key) throws IllegalArgumentException {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (key.isEmpty()) throw new IllegalArgumentException("Key cannot be empty");

        return new JsonText("{\"translate\":\"" + key + "\"}");
    }
}
