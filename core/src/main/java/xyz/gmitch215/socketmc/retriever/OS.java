package xyz.gmitch215.socketmc.retriever;

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

    /**
     * Gets the current operating system that this JVM is running on.
     * @return The current operating system
     */
    public static OS current() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            return WINDOWS;
        } else if (os.contains("mac")) {
            return OSX;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            return LINUX;
        } else if (os.contains("sunos")) {
            return SOLARIS;
        } else {
            return UNKNOWN;
        }
    }

}
