package me.gamercoder215.socketmc.spigot

import org.bukkit.entity.Player

// Player

/**
 * Get the SocketPlayer instance of the player.
 * @return SocketPlayer instance
 */
val Player.socket: SocketPlayer
    get() = SocketPlayer(this)