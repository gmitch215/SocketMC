package xyz.gmitch215.socketmc.util

import org.bukkit.NamespacedKey

// Identifier

/**
 * Creates a new [Identifier] from the given [NamespacedKey].
 * @param key The [NamespacedKey] to create the [Identifier] from.
 * @return The created [Identifier].
 */
fun Identifier(key: NamespacedKey): Identifier =
    Identifier(key.namespace, key.key)

/**
 * Creates a new [Identifier] from the given [NamespacedKey].
 */
val NamespacedKey.identifier: Identifier
    get() = Identifier(this)