package xyz.gmitch215.socketmc.retriever;

import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.retriever.util.OS;
import xyz.gmitch215.socketmc.retriever.util.Window;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Represents a type of retriever. They are used to retrieve data from the client.</p>
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
 *     // Retrieved window object is passed to consumer
 *     int x = w.getHeight();
 * }
 * }</pre>
 *
 * @param <T> The type of the retriever
 */
public final class RetrieverType<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 8546973768967806532L;

    /**
     * A retriever for the client's window information.
     */
    public static final RetrieverType<Window> WINDOW = new RetrieverType<>("window", Window.class);

    /**
     * A retriever for the client's operating system information.
     */
    public static final RetrieverType<OS> OPERATING_SYSTEM = new RetrieverType<>("os", OS.class);

    /**
     * A retriever for whether the client's game is paused.
     */
    public static final RetrieverType<Boolean> PAUSED = new RetrieverType<>("paused", Boolean.class);

    /**
     * A retriever for the client's frames per second.
     */
    public static final RetrieverType<Integer> FPS = new RetrieverType<>("fps", Integer.class);

    /**
     * A retriever for the client's percentage of GPU usage, between {@code 0.0} to {@code 1.0}.
     */
    public static final RetrieverType<Double> GPU_UTILIZATION = new RetrieverType<>("gpu_utilization", Double.class);

    /**
     * A retriever for the number of frames the client has rendered.
     */
    public static final RetrieverType<Integer> FRAMES = new RetrieverType<>("frames", Integer.class);

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
