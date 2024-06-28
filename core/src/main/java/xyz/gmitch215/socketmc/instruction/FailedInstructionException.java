package xyz.gmitch215.socketmc.instruction;

import java.io.Serial;

/**
 * Thrown when a SocketMC instruction fails to execute.
 */
public class FailedInstructionException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -1888285832734051215L;

    /**
     * Constructs a new FailedInstructionException with no message.
     */
    public FailedInstructionException() {
    }

    /**
     * Constructs a new FailedInstructionException with the specified message.
     * @param message The message
     */
    public FailedInstructionException(String message) {
        super(message);
    }

    /**
     * Constructs a new FailedInstructionException with the specified message and cause.
     * @param message The message
     * @param cause The cause
     */
    public FailedInstructionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new FailedInstructionException with the specified cause.
     * @param cause The cause
     */
    public FailedInstructionException(Throwable cause) {
        super(cause);
    }
}
