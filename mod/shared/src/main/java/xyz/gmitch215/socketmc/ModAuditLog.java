package xyz.gmitch215.socketmc;

import xyz.gmitch215.socketmc.log.AuditLog;

import java.io.File;

public final class ModAuditLog extends AuditLog {

    public static final ModAuditLog INSTANCE = new ModAuditLog();

    private ModAuditLog() {
        super(new File(SocketMC.GAME_DIRECTORY.get(), "logs/SocketMC"));
    }

}
