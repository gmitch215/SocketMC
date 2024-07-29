package xyz.gmitch215.socketmc.retriever;

import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.config.ModPermission;
import xyz.gmitch215.socketmc.spigot.SocketPlugin;
import xyz.gmitch215.socketmc.util.InputType;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * <p>Represents a type of retriever. They are used to retrieve data from the client. Any data element can be null.</p>
 * <p>Example:</p>
 * <pre>{@code
 * SocketPlayer sp = ...
 * SocketRetriever retriever = sp.getRetriever();
 *
 * // Enum Retriever Type
 * RetrieverType<Window> window = RetriverType.WINDOW;
 *
 * // Params: RetrieverType<T>, Consumer<T>
 * retriever.request(window, w -> {
 *     // Check if null
 *     if (w == null) return;
 *
 *     // Retrieved window object is passed to consumer
 *     int x = w.getHeight();
 * }
 * }</pre>
 *
 * @param <T> The type of the retriever
 */
@SuppressWarnings({"unchecked"})
public final class RetrieverType<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 8546973768967806532L;

    /**
     * A retriever for the client's window information.
     */
    @RetrieverPermission(ModPermission.READ_GUI_PROPERTIES)
    public static final RetrieverType<Window> CURRENT_WINDOW = new RetrieverType<>("current_window", Window.class);

    /**
     * A retriever for the client's operating system information.
     */
    @RetrieverPermission(ModPermission.REQUIRED)
    public static final RetrieverType<OS> OPERATING_SYSTEM = new RetrieverType<>("operating_system", OS.class);

    /**
     * A retriever for whether the client's game is paused.
     */
    @RetrieverPermission(ModPermission.READ_GUI_PROPERTIES)
    public static final RetrieverType<Boolean> PAUSED = new RetrieverType<>("paused", Boolean.class);

    /**
     * A retriever for the client's frames per second.
     */
    @RetrieverPermission(ModPermission.READ_GUI_PROPERTIES)
    public static final RetrieverType<Integer> FPS = new RetrieverType<>("fps", Integer.class);

    /**
     * A retriever for the client's percentage of GPU usage, between {@code 0.0} to {@code 1.0}.
     */
    @RetrieverPermission(ModPermission.READ_SYSTEM_PROPERTIES)
    public static final RetrieverType<Double> GPU_UTILIZATION = new RetrieverType<>("gpu_utilization", Double.class);

    /**
     * A retriever for the client's launched version.
     */
    @RetrieverPermission(ModPermission.REQUIRED)
    public static final RetrieverType<String> LAUNCH_VERSION = new RetrieverType<>("launch_version", String.class);

    /**
     * A retriever for the client's current version.
     */
    @RetrieverPermission(ModPermission.REQUIRED)
    public static final RetrieverType<String> VERSION_TYPE = new RetrieverType<>("version_type", String.class);

    /**
     * A retriever for the client's last input type.
     */
    @RetrieverPermission(ModPermission.USE_GUI)
    public static final RetrieverType<InputType> LAST_INPUT_TYPE = new RetrieverType<>("last_input_type", InputType.class);

    /**
     * A retriever for the client's available processors on its JVM.
     */
    @RetrieverPermission(ModPermission.READ_SYSTEM_PROPERTIES)
    public static final RetrieverType<Integer> AVAILABLE_PROCESSORS = new RetrieverType<>("available_processors", Integer.class);

    /**
     * A retriever for the permissions the client has for each plugin.
     */
    @RetrieverPermission(ModPermission.REQUIRED)
    public static final RetrieverType<Map<SocketPlugin, Set<ModPermission>>> PLUGIN_PERMISSIONS = new RetrieverType<>("plugin_permissions", (Class<Map<SocketPlugin, Set<ModPermission>>>) (Object) Map.class);

    /**
     * A retriever for the client's free memory on its JVM.
     */
    @RetrieverPermission(ModPermission.READ_SYSTEM_PROPERTIES)
    public static final RetrieverType<Long> FREE_MEMORY = new RetrieverType<>("free_memory", Long.class);

    /**
     * A retriever for the client's total memory on its JVM.
     */
    @RetrieverPermission(ModPermission.READ_SYSTEM_PROPERTIES)
    public static final RetrieverType<Long> TOTAL_MEMORY = new RetrieverType<>("total_memory", Long.class);

    /**
     * A retriever for the client's maximum memory on its JVM.
     */
    @RetrieverPermission(ModPermission.READ_SYSTEM_PROPERTIES)
    public static final RetrieverType<Long> MAX_MEMORY = new RetrieverType<>("max_memory", Long.class);

    /**
     * A retriever for the client's command history.
     */
    @RetrieverPermission(ModPermission.READ_GAME_PROPERTIES)
    public static final RetrieverType<String[]> COMMAND_HISTORY = new RetrieverType<>("command_history", String[].class);

    //<editor-fold desc="Implementation" defaultstate="collapsed">

    private final String id;
    private final Class<T> type;

    private RetrieverType(String id, Class<T> type) {
        this.id = id;
        this.type = type;
    }

    /**
     * Gets the ID of the retriever.
     * @return The ID of the retriever
     */
    @NotNull
    public String getId() {
        return id;
    }

    /**
     * Gets the type of the retriever.
     * @return The type of the retriever
     */
    @NotNull
    public Class<T> getType() {
        return type;
    }

    /**
     * Gets the permission required to use this retriever.
     * @return Retriever Permission
     */
    @NotNull
    public ModPermission getPermission() {
        return getPermission(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RetrieverType<?> that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Gets the permission required to use a retriever by its ID.
     * @param id Retriever Type ID
     * @return Retriever Permission
     */
    @NotNull
    public static ModPermission getPermission(@NotNull String id) {
        try {
            Field f = RetrieverType.class.getDeclaredField(id.toUpperCase());
            return f.getAnnotation(RetrieverPermission.class).value();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to get permission for retriever: " + id, e);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("RetrieverType missing permission: " + id, e);
        }
    }

    // Serialization

    /**
     * Converts the retriever type to a byte array.
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
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts a byte array to a retriever type.
     * @param bytes The byte array to convert
     * @return The retriever type
     */
    @NotNull
    public static RetrieverType<?> fromByteArray(byte[] bytes) {
        if (bytes == null) throw new IllegalArgumentException("Bytes cannot be null");

        try {
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream inw = new ObjectInputStream(in);
            return (RetrieverType<?>) inw.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets all the retriever types of a specific type.
     * @param type The type of the retriever
     * @return All Retriever Types of the specified type
     * @param <T> The type of the retriever
     */
    @NotNull
    @SuppressWarnings("unchecked")
    public static <T> RetrieverType<T>[] values(@NotNull Class<T> type) {
        if (type == null) throw new IllegalArgumentException("Type cannot be null");

        return Arrays.stream(values())
                .filter(t -> t.getType().equals(type))
                .toArray(RetrieverType[]::new);
    }

    /**
     * Gets all the retriever types.
     * @return All Retriever Types
     */
    @NotNull
    public static RetrieverType<?>[] values() {
        List<RetrieverType<?>> values = new ArrayList<>();

        try {
            for (Field f : RetrieverType.class.getDeclaredFields()) {
                if (!Modifier.isPublic(f.getModifiers())) continue;
                if (!Modifier.isStatic(f.getModifiers())) continue;
                if (!Modifier.isFinal(f.getModifiers())) continue;
                if (!f.getType().equals(RetrieverType.class)) continue;

                values.add((RetrieverType<?>) f.get(null));
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }

        return values.toArray(RetrieverType[]::new);
    }

    //</editor-fold>

}
