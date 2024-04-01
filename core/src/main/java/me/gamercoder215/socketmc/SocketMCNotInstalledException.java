package me.gamercoder215.socketmc;

import java.io.Serial;

/**
 * Thrown when the SocketMC Mod is not installed on the client.
 */
public class SocketMCNotInstalledException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -263180179103539481L;

    /**
     * Constructs a new SocketMCNotInstalledException with no message.
     */
    public SocketMCNotInstalledException() {
    }

    /**
     * Constructs a new SocketMCNotInstalledException with the specified message.
     * @param message The message
     */
    public SocketMCNotInstalledException(String message) {
        super(message);
    }

    /**
     * Constructs a new SocketMCNotInstalledException with the specified message and cause.
     * @param message The message
     * @param cause The cause
     */
    public SocketMCNotInstalledException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new SocketMCNotInstalledException with the specified cause.
     * @param cause The cause
     */
    public SocketMCNotInstalledException(Throwable cause) {
        super(cause);
    }
}
