package me.gamercoder215.socketmc.fabric;

import me.gamercoder215.socketmc.log.AuditLog;
import net.minecraft.client.Minecraft;

import java.io.File;

public final class FabricAuditLog extends AuditLog {

    public static final FabricAuditLog INSTANCE = new FabricAuditLog();

    private FabricAuditLog() {
        super(new File(Minecraft.getInstance().gameDirectory, "logs/SocketMC"));
    }

}
