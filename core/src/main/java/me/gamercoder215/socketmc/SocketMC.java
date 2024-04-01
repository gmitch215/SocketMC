package me.gamercoder215.socketmc;

import me.gamercoder215.socketmc.events.ModListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.ArrayList;
import java.util.List;

/**
 * Main utility class for managing the SocketMC API.
 */
public final class SocketMC {

    /**
     * The Mod ID for SocketMC.
     */
    public static final String MOD_ID = "socketmc";

    private static final List<ModListener> listeners = new ArrayList<>();

    /**
     * Gets an immutable copy of all listeners registered for the SocketMC Mod.
     * @return All registered listeners
     */
    @Unmodifiable
    @NotNull
    public static List<ModListener> getListeners() {
        return List.copyOf(listeners);
    }

    /**
     * Registers a new listener for the SocketMC Mod.
     * @param listener The listener to register.
     */
    public static void addModListener(@NotNull ModListener listener) {
        listeners.add(listener);
    }

}
