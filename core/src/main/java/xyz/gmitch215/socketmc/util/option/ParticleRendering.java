package xyz.gmitch215.socketmc.util.option;

import org.jetbrains.annotations.NotNull;

/**
 * Represents the different ways particles can be rendered.
 */
public enum ParticleRendering {

    /**
     * Particles are rendered in full.
     */
    ALL,

    /**
     * Particles are rendered in a reduced form.
     */
    DECREASED,

    /**
     * Particles are not rendered at all.
     */
    MINIMAL

    ;

    /**
     * Gets the ParticleRendering by its ordinal.
     * @param ordinal
     * @return ParticleRendering by ordinal
     */
    @NotNull
    public static ParticleRendering byOrdinal(int ordinal) {
        return values()[ordinal];
    }

}
