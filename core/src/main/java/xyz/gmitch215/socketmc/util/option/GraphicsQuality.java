package xyz.gmitch215.socketmc.util.option;

import org.jetbrains.annotations.NotNull;

/**
 * Represents the different graphics rendering options.
 */
public enum GraphicsQuality {

    /**
     * Graphics are rendered in the fastest way possible.
     */
    FAST,

    /**
     * Graphics are rendered in a medium quality way.
     */
    FANCY,

    /**
     * Graphics are rendered in the highest quality possible.
     */
    FABULOUS

    ;

    /**
     * Gets the GraphicsRendering by its ordinal.
     * @param ordinal
     * @return GraphicsRendering by ordinal
     */
    @NotNull
    public static GraphicsQuality byOrdinal(int ordinal) {
        return values()[ordinal];
    }

}
