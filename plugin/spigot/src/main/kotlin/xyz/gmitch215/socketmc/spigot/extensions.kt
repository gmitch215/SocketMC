package xyz.gmitch215.socketmc.spigot

import xyz.gmitch215.socketmc.instruction.Instruction
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

// Player

/**
 * Get the SocketPlayer instance of the player.
 * @return SocketPlayer instance
 */
val Player.socket: SocketPlayer
    get() = SocketPlayerRegistry.createIfAbsent(this)

/**
 * Pings the player.
 */
fun Player.ping() = socket.ping()

/**
 * Send an instruction to the player.
 * @param instruction Instruction to send
 * @param plugin Plugin to send the instruction with
 */
fun Player.sendInstruction(instruction: Instruction, plugin: Plugin) = socket.sendInstruction(instruction, plugin)

/**
 * Send an instruction to the player.
 * @param instruction Instruction to send
 * @param plugin Plugin to send the instruction with
 */
fun Player.sendInstruction(instruction: Instruction, plugin: SocketPlugin) = socket.sendInstruction(instruction, plugin)