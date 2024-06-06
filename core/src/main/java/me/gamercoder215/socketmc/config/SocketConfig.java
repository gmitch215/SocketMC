package me.gamercoder215.socketmc.config;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * <p>Represents the configuration for the SocketMC Mod.</p>
 * <p>Setters will not work and will throw when used on the server.</p>
 */
public interface SocketConfig extends Serializable {

    /**
     * Gets the path to the configuration JSON file, relative to the client folder.
     * @return Configuration Path
     */
    @NotNull
    default String getFilePath() {
        return "config/socketmc.json";
    }

    /**
     * Gets whether the mod permission is enabled.
     * @param permission Mod Permission
     * @return
     */
    boolean isPermissionEnabled(@NotNull ModPermission permission);

}
