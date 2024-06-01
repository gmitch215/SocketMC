package me.gamercoder215.socketmc.instruction.util

import me.gamercoder215.socketmc.util.Identifier
import org.bukkit.NamespacedKey

// Identifier

/**
 * Creates a new [Identifier] from the given [NamespacedKey].
 * @param key The [NamespacedKey] to create the [Identifier] from.
 * @return The created [Identifier].
 */
fun Identifier(key: NamespacedKey): Identifier =
    Identifier(key.namespace, key.key)