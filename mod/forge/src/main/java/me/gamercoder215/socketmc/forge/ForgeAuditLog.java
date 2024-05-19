package me.gamercoder215.socketmc.forge;

import me.gamercoder215.socketmc.log.AuditLog;
import net.minecraft.client.Minecraft;

import java.io.File;

public final class ForgeAuditLog extends AuditLog {

    public static final ForgeAuditLog INSTANCE = new ForgeAuditLog();

    private ForgeAuditLog() {
        super(new File(Minecraft.getInstance().gameDirectory, "logs/SocketMC"));
    }

}
