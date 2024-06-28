package xyz.gmitch215.socketmc.log;

import java.io.Serial;

/**
 * Represents an exception thrown when an audit log fails to be written.
 */
public class FailedAuditException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 5832442649093265924L;

    /**
     * Constructs a new failed audit exception.
     * @param message The message to display.
     */
    public FailedAuditException(String message) {
        super(message);
    }

    /**
     * Constructs a new failed audit exception.
     * @param message The message to display.
     * @param cause The cause of the exception.
     */
    public FailedAuditException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new failed audit exception.
     * @param cause The cause of the exception.
     */
    public FailedAuditException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new failed audit exception.
     */
    public FailedAuditException() {
        super();
    }
}
