package exceptions;

/**
 * Custom exception for invalid configurations in the system.
 */
public class InvalidConfigurationException extends Exception {

    /**
     * Constructs a new InvalidConfigurationException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidConfigurationException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidConfigurationException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public InvalidConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
