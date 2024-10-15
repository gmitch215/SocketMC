package xyz.gmitch215.socketmc.util.option;

import org.jetbrains.annotations.NotNull;

/**
 * Represents the status of cloud rendering.
 */
public enum CloudRendering {

    /**
     * Clouds are not rendered.
     */
    OFF,

    /**
     * Clouds are rendered in performance mode.
     */
    FAST,

    /**
     * Clouds are rendered in quality mode.
     */
    FANCY

    ;

    /**
     * Gets the CloudRendering by its ordinal.
     * @param ordinal
     * @return CloudRendering by ordinal
     */
    @NotNull
    public static CloudRendering byOrdinal(int ordinal) {
        return values()[ordinal];
    }

}
