package me.gamercoder215.socketmc.spigot

import me.gamercoder215.socketmc.instruction.Instruction
import org.bukkit.entity.Player

// Player

/**
 * Get the SocketPlayer instance of the player.
 * @return SocketPlayer instance
 */
val Player.socket: SocketPlayer
    get() = SocketPlayer(this)

/**
 * Send an instruction to the player.
 * @param instruction Instruction to send
 */
fun Player.sendInstruction(instruction: Instruction) = socket.sendInstruction(instruction)