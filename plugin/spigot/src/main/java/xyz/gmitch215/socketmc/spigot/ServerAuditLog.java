package xyz.gmitch215.socketmc.spigot;

import xyz.gmitch215.socketmc.log.AuditLog;
import org.bukkit.Bukkit;

import java.io.File;

/**
 * Represents an audit log instance on the server.
 */
public final class ServerAuditLog extends AuditLog {

    /**
     * Represents the singleton instance of the server audit log.
     */
    public static final ServerAuditLog INSTANCE = new ServerAuditLog();

    private ServerAuditLog() {
        super(new File(Bukkit.getWorldContainer(), "plugins/SocketMC/logs"));
    }

}
