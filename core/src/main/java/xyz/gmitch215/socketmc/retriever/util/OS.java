package xyz.gmitch215.socketmc.retriever.util;

import org.jetbrains.annotations.NotNull;

/**
 * Represents all supported operating systems that the client can run on.
 */
public enum OS {

    /**
     * Any Linux distribution.
     */
    LINUX("linux"),

    /**
     * Any BSD distribution.
     */
    SOLARIS("solaris"),

    /**
     * Any Windows distribution.
     */
    WINDOWS("windows"),

    /**
     * Any Mac OSX distribution.
     */
    OSX("mac"),

    /**
     * An unknown operating system.
     */
    UNKNOWN("unknown")

    ;

    private final String name;

    OS(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the operating system.
     * @return The name of the operating system.
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Gets the operating system by its name.
     * @param name The name of the operating system
     * @return The operating system found, or {@link OS#UNKNOWN} if not found
     */
    @NotNull
    public static OS byName(@NotNull String name) {
        for (OS os : values()) {
            if (os.getName().equalsIgnoreCase(name)) {
                return os;
            }
        }
        return UNKNOWN;
    }

}
