package me.gamercoder215.socketmc;

import java.util.logging.Logger;

/**
 * Main utility class for managing the SocketMC API.
 */
public final class SocketMC {

    /**
     * The Mod ID for SocketMC.
     */
    public static final String MOD_ID = "socketmc";

    /**
     * Prints a stack trace to the logger.
     * @param logger The logger to print to
     * @param t The throwable to print
     */
    public static void print(Logger logger, Throwable t) {
        logger.severe(t.getClass().getSimpleName());
        logger.severe("-----------");
        logger.severe(t.getMessage());
        for (StackTraceElement element : t.getStackTrace()) logger.severe(element.toString());

        if (t.getCause() != null) {
            logger.severe("Caused by:");
            print(logger, t.getCause());
        }
    }

}
