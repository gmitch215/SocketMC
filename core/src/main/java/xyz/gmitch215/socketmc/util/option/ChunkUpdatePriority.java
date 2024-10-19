package xyz.gmitch215.socketmc.util.option;

import org.jetbrains.annotations.NotNull;

/**
 * Represents the priority for chunk updates.
 */
public enum ChunkUpdatePriority {

    /**
     * Chunk updates are not prioritized.
     */
    NONE,

    /**
     * Chunk updates are prioritized if a player is nearby.
     */
    PLAYER_AFFECTED,

    /**
     * Chunk updates are prioritized if the client is rendering the chunk.
     */
    NEARBY

    ;

    /**
     * Gets the ChunkUpdatePriority by its ordinal.
     * @param ordinal The ordinal
     * @return ChunkUpdatePriority by ordinal
     */
    @NotNull
    public static ChunkUpdatePriority byOrdinal(int ordinal) {
        return values()[ordinal];
    }

}
