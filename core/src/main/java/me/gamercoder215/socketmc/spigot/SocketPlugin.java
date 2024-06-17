package me.gamercoder215.socketmc.spigot;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.Objects;

/**
 * Wrapper class around a Plugin interface to be serialized and sent to the mod. Used for logging, settings, and other information purposes.
 * In the eventuality that a Plugin interface is not available, use {@link #UNKNOWN}.
 */
public final class SocketPlugin implements Serializable, Comparable<SocketPlugin> {

    @Serial
    private static final long serialVersionUID = -3106242000999129778L;

    @ApiStatus.Internal
    static final SocketPlugin LIBRARY =
            new SocketPlugin("<library>", "SocketMC", "unknown", "unknown", "unknown");

    /**
     * Represents an unknown plugin.
     */
    public static final SocketPlugin UNKNOWN =
            new SocketPlugin("<unknown>", "Unknown", "unknown", "unknown", "unknown");

    private final String mainClass;
    private final String pluginName;
    private final String pluginVersion;
    private final String pluginUrl;
    private final String jarFilePath;

    SocketPlugin(String mainClass, String pluginName, String pluginVersion, String pluginUrl, String jarFilePath) {
        this.mainClass = mainClass;
        this.pluginName = pluginName;
        this.pluginVersion = pluginVersion;
        this.pluginUrl = pluginUrl;
        this.jarFilePath = jarFilePath;
    }

    /**
     * Gets the main class of the plugin. Primary use of this is to identify the plugin across renames and other changes.
     * @return Plugin Main Class
     */
    @NotNull
    public String getMainClass() {
        return mainClass;
    }

    /**
     * Gets the name of the plugin.
     * @return Plugin Name
     */
    @NotNull
    public String getPluginName() {
        return pluginName;
    }

    /**
     * Gets the version of the plugin.
     * @return Plugin Version
     */
    @NotNull
    public String getPluginVersion() {
        return pluginVersion;
    }

    /**
     * Gets the URL of the plugin.
     * @return Plugin Website
     */
    @Nullable
    public String getPluginUrl() {
        return pluginUrl;
    }

    /**
     * Gets the file path of the plugin jar file.
     * @return Plugin File Path
     */
    @NotNull
    public String getJarFilePath() {
        return jarFilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SocketPlugin that)) return false;
        return Objects.equals(mainClass, that.mainClass);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mainClass);
    }

    // Serialization

    /**
     * Serializes this SocketPlugin to a byte array to be passed over the network.
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
            throw new RuntimeException("Failed to serialize SocketPlugin", e);
        }
    }

    /**
     * Deserializes an SocketPlugin from a byte array received over the network.
     * @param plugin Byte Array Representation
     * @return Deserialized Instruction
     */
    @Nullable
    public static SocketPlugin fromByteArray(@NotNull byte[] plugin) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(plugin);
            ObjectInputStream inw = new ObjectInputStream(in);

            return (SocketPlugin) inw.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Failed to deserialize SocketPlugin", e);
        }
    }

    @Override
    public int compareTo(@NotNull SocketPlugin o) {
        return pluginName.compareTo(o.pluginName);
    }
}
