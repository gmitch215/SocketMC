package me.gamercoder215.socketmc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface SocketMC {

    Logger LOGGER = LoggerFactory.getLogger("SocketMC");

    static void print(Throwable t) {
        LOGGER.error("[SocketMC] {}", t.getClass().getSimpleName());
        LOGGER.error("-----------");
        LOGGER.error(t.getMessage());
        for (StackTraceElement element : t.getStackTrace()) LOGGER.error("  {}", element.toString());

        if (t.getCause() != null) {
            LOGGER.error("Caused by:");
            print(t.getCause());
        }
    }
}
