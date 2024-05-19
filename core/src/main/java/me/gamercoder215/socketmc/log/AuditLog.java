package me.gamercoder215.socketmc.log;

import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.spigot.SocketPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

/**
 * Represents an audit log instance on either a server or a client.
 */
public abstract class AuditLog {

    /**
     * Represents the date format used to name the audit log files.
     */
    public static final SimpleDateFormat FILE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Represents the default date format used inside the audit log.
     */
    public static final SimpleDateFormat LOG_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Represents the message format for when a client receives an instruction.
     */
    public static final String CLIENT_RECEIVED_MESSAGE = "Received instruction: {}, size {} bytes";

    /**
     * The folder where the audit log is stored.
     */
    protected final File folder;

    /**
     * Constructs a new audit log instance. This will create the folder if it does not exist.
     * @param folder The folder where the audit log is stored.
     */
    protected AuditLog(@NotNull File folder) {
        this.folder = folder;
        if (!folder.exists()) folder.mkdirs();
    }

    /**
     * Gets the folder where the audit log is stored.
     * @return The folder where the audit log is stored.
     */
    @NotNull
    public File getFolder() {
        return folder;
    }

    /**
     * Gets the current file where the audit log is stored. The file may not exist yet.
     * @return Current Log File
     */
    @NotNull
    public File getCurrentFile() {
        return new File(folder, FILE_DATE_FORMAT.format(System.currentTimeMillis()) + ".log");
    }

    /**
     * Deletes all {@code .log} files in the audit log folder.
     */
    public void clean() {
        File[] files = folder.listFiles();
        if (files == null) return;

        for (File file : files)
            if (file.getName().endsWith(".log")) file.delete();
    }

    // Logging

    /**
     * Logs a message to the audit log.
     * @param level Audit Log Level
     * @param message Message to log
     */
    public void log(@NotNull Level level, @NotNull String message) {
        try {
            File log = getCurrentFile();
            if (!log.exists()) log.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(log, true));
            writer.write("[" + level.getName() + "] " + "[" + LOG_DATE_FORMAT.format(System.currentTimeMillis()) + "] " + message);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new FailedAuditException("Failed to write audit log", e);
        }
    }

    /**
     * Logs a message of level {@link Level#INFO} to the audit log.
     * @param message Message to log
     */
    public void log(@NotNull String message) {
        log(Level.INFO, message);
    }

    /**
     * Logs a sent instruction to the audit log.
     * @param sent The instruction that was sent.
     * @param sender The plugin that sent the instruction.
     */
    public void logSent(@NotNull Instruction sent, @NotNull SocketPlugin sender) {
        String msg = "\"" + sender.getPluginName() + " v" + sender.getPluginVersion() + "\" sent instruction: " + sent;
        log(msg);
    }

    /**
     * Logs a received instruction to the audit log.
     * @param received The instruction that was received.
     * @param sender The plugin that sent the instruction.
     */
    public void logReceived(@NotNull Instruction received, @NotNull SocketPlugin sender) {
        String msg = "Received instruction from " + sender.getPluginName() + " v" + sender.getPluginVersion() + "\": " + received;
        log(msg);
    }

}
