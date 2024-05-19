package me.gamercoder215.socketmc.instruction.util;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a Namespaced Identifier.
 */
public final class Identifier implements Serializable {

    @Serial
    private static final long serialVersionUID = 1949958315378726339L;

    private final String namespace;
    private final String path;

    /**
     * Creates a new Identifier.
     * @param namespace The namespace of the Identifier.
     * @param path The path of the Identifier.
     */
    public Identifier(@NotNull String namespace, @NotNull String path) {
        this.namespace = namespace;
        this.path = path;
    }

    /**
     * Gets the namespace of the Identifier.
     * @return The namespace of the Identifier.
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Gets the path of the Identifier.
     * @return The path of the Identifier.
     */
    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identifier that)) return false;
        return Objects.equals(namespace, that.namespace) && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace, path);
    }

    @Override
    public String toString() {
        return namespace + ":" + path;
    }

    /**
     * Creates a new Identifier with the namespace "minecraft".
     * @param path The path of the Identifier.
     * @return The new Identifier.
     */
    @NotNull
    public static Identifier minecraft(@NotNull String path) {
        return new Identifier("minecraft", path);
    }

}
