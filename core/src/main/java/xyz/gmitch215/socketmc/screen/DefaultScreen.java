package xyz.gmitch215.socketmc.screen;

import xyz.gmitch215.socketmc.util.render.text.PlainText;
import xyz.gmitch215.socketmc.util.render.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serial;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Represents default screens built-in to the client.
 */
public final class DefaultScreen extends AbstractScreen {
    @Serial
    private static final long serialVersionUID = 8336967742008601990L;

    /**
     * Represents the title screen.
     */
    public static final DefaultScreen TITLE = new DefaultScreen("title");

    /**
     * Represents the pause menu.
     */
    public static final DefaultScreen PAUSE = new DefaultScreen("pause");

    /**
     * Represents the options menu.
     */
    public static final DefaultScreen OPTIONS = new DefaultScreen("options");

    /**
     * Represents the share to Local-Access-Network (LAN) menu.
     */
    public static final DefaultScreen SHARE_TO_LAN = new DefaultScreen("share_to_lan");

    /**
     * Represents the advancements screen.
     */
    public static final DefaultScreen ADVANCEMENTS = new DefaultScreen("advancements");

    /**
     * Represents the player statistics screen.
     */
    public static final DefaultScreen STATS = new DefaultScreen("stats");

    private final String identifier;
    private final Map<String, Object> data = new HashMap<>();

    private DefaultScreen(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Gets the identifier for this default screen.
     * @return Screen Identifier
     */
    @NotNull
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Gets an immutable copy for the data for this default screen.
     * @return Screen Data
     */
    @NotNull
    public Map<String, Object> getData() {
        return Map.copyOf(data);
    }

    /**
     * Gets a specific piece of data from this default screen.
     * @param key Data Key
     * @return Data Value
     * @throws IllegalArgumentException if key is null
     */
    @Nullable
    public Object data(@NotNull String key) throws IllegalArgumentException {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        return data.get(key);
    }

    /**
     * Gets a specific piece of data from this default screen.
     * @param key Data Key
     * @param def Default Value
     * @return Data Value
     * @throws IllegalArgumentException if key or default value are null
     */
    @NotNull
    public Object data(@NotNull String key, @NotNull Object def) throws IllegalArgumentException {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (def == null) throw new IllegalArgumentException("Default value cannot be null");

        return data.getOrDefault(key, def);
    }

    /**
     * Gets a specific piece of data from this default screen.
     * @param key Data Key
     * @param clazz Data Class
     * @return Data Value
     * @param <T> Data Type
     * @throws IllegalArgumentException if key or class are null
     */
    @Nullable
    public <T> T data(@NotNull String key, @NotNull Class<T> clazz) throws IllegalArgumentException {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (clazz == null) throw new IllegalArgumentException("Class cannot be null");

        return clazz.cast(data.get(key));
    }

    /**
     * Gets a specific piece of data from this default screen.
     * @param key Data Key
     * @param clazz Data Class
     * @param def Default Value
     * @return Data Value
     * @param <T> Data Type
     * @throws IllegalArgumentException if key, class, or default value are null
     */
    @NotNull
    public <T> T data(@NotNull String key, @NotNull Class<T> clazz, @NotNull T def) throws IllegalArgumentException {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (clazz == null) throw new IllegalArgumentException("Class cannot be null");
        if (def == null) throw new IllegalArgumentException("Default value cannot be null");

        return clazz.cast(data.getOrDefault(key, def));
    }

    @Override
    @NotNull
    public String getTitleJSON() {
        throw new UnsupportedOperationException("Default screens do not have titles accessible here");
    }

    // Constructors

    /**
     * Creates a new alert menu.
     * @param title Menu Title
     * @param message Alert Message
     * @return Alert Menu
     * @throws IllegalArgumentException if title or message are null
     */
    @NotNull
    public static DefaultScreen alert(@NotNull Text title, @NotNull Text message) throws IllegalArgumentException {
        return alert(title, message, null);
    }

    /**
     * Creates a new alert menu.
     * @param title Menu Title
     * @param message Alert Message
     * @param button OK Button Text
     * @return Alert Menu
     * @throws IllegalArgumentException if title or message are null
     */
    @NotNull
    public static DefaultScreen alert(@NotNull Text title, @NotNull Text message, @Nullable Text button) throws IllegalArgumentException {
        if (title == null) throw new IllegalArgumentException("Title cannot be null");
        if (message == null) throw new IllegalArgumentException("Message cannot be null");

        DefaultScreen screen = new DefaultScreen("alert");
        screen.data.put("title", title.toJSON());
        screen.data.put("message", message.toJSON());

        if (button != null)
            screen.data.put("button", button.toJSON());

        return screen;
    }

    /**
     * Creates a new disconnected screen.
     * @param title Screen Title
     * @param reason Disconnection Reason
     * @return Disconnected Screen
     * @throws IllegalArgumentException if title or reason are null
     */
    @NotNull
    public static DefaultScreen disconnected(@NotNull Text title, @NotNull Text reason) throws IllegalArgumentException {
        return disconnected(title, reason, null);
    }

    /**
     * Creates a new disconnected screen.
     * @param title Screen Title
     * @param reason Disconnection Reason
     * @param button Return Button Text
     * @return Disconnected Screen
     * @throws IllegalArgumentException if title or reason are null
     */
    @NotNull
    public static DefaultScreen disconnected(@NotNull Text title, @NotNull Text reason, @Nullable Text button) throws IllegalArgumentException {
        if (title == null) throw new IllegalArgumentException("Title cannot be null");
        if (reason == null) throw new IllegalArgumentException("Reason cannot be null");

        DefaultScreen screen = new DefaultScreen("disconnected");
        screen.data.put("title", title.toJSON());
        screen.data.put("reason", reason.toJSON());

        if (button != null)
            screen.data.put("button", button.toJSON());

        return screen;
    }

    /**
     * Creates a new message screen.
     * @param message Message to Display
     * @return Message Screen
     * @throws IllegalArgumentException if message is null
     */
    @NotNull
    public static DefaultScreen message(@NotNull Text message) throws IllegalArgumentException {
        if (message == null) throw new IllegalArgumentException("Message cannot be null");

        DefaultScreen screen = new DefaultScreen("message");
        screen.data.put("message", message.toJSON());

        return screen;
    }

    /**
     * Creates a new death screen.
     * @return Death Screen
     */
    @NotNull
    public static DefaultScreen death() {
        return death(null, false);
    }

    /**
     * Creates a new death screen.
     * @param causeOfDeath Cause of Death
     * @return Death Screen
     */
    @NotNull
    public static DefaultScreen death(@Nullable Text causeOfDeath) {
        return death(causeOfDeath, false);
    }

    /**
     * Creates a new death screen.
     * @param causeOfDeath Cause of Death
     * @param hardcore Whether they died in hardcore mode
     * @return Death Screen
     */
    @NotNull
    public static DefaultScreen death(@Nullable Text causeOfDeath, boolean hardcore) {
        DefaultScreen screen = new DefaultScreen("death");
        screen.data.put("hardcore", hardcore);

        if (causeOfDeath != null)
            screen.data.put("cause", causeOfDeath.toJSON());

        return screen;
    }

    /**
     * Creates a new error screen.
     * @param title Screen Title
     * @param message Error Message
     * @return Error Screen
     * @throws IllegalArgumentException if title or message are null
     */
    @NotNull
    public static DefaultScreen error(@NotNull Text title, @NotNull Text message) throws IllegalArgumentException {
        if (title == null) throw new IllegalArgumentException("Title cannot be null");
        if (message == null) throw new IllegalArgumentException("Message cannot be null");

        DefaultScreen screen = new DefaultScreen("error");
        screen.data.put("title", title.toJSON());
        screen.data.put("message", message.toJSON());

        return screen;
    }

    // Util

    /**
     * Gets all the default screens built-in to the client.
     * @return Default Screens
     */
    @NotNull
    public static Set<DefaultScreen> getAllScreens() {
        Set<DefaultScreen> screens = new HashSet<>();

        try {
            for (Field f : DefaultScreen.class.getDeclaredFields()) {
                if (!Modifier.isStatic(f.getModifiers())) continue;
                if (!Modifier.isFinal(f.getModifiers())) continue;
                if (!DefaultScreen.class.isAssignableFrom(f.getType())) continue;

                screens.add((DefaultScreen) f.get(null));
            }

            List<String> visited = new ArrayList<>();
            for (Method m : DefaultScreen.class.getDeclaredMethods()) {
                if (visited.contains(m.getName())) continue;
                if (!Modifier.isStatic(m.getModifiers())) continue;
                if (!Modifier.isPublic(m.getModifiers())) continue;
                if (!DefaultScreen.class.isAssignableFrom(m.getReturnType())) continue;

                List<Object> args = new ArrayList<>();
                for (Class<?> clazz : m.getParameterTypes()) {
                    switch (clazz.getSimpleName()) {
                        case "Text" -> args.add(PlainText.empty());
                        case "String" -> args.add("");
                        case "boolean" -> args.add(false);
                        default -> args.add(null);
                    }
                }

                screens.add((DefaultScreen) m.invoke(null, args.toArray()));
                visited.add(m.getName());
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return screens;
    }

    @Override
    public String toString() {
        return "DefaultScreen{'" + identifier + "'}";
    }
}
