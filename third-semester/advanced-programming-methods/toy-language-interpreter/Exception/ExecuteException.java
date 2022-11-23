package Exception;

public class ExecuteException extends Exception {
    /**
     * Constructor for ExecuteException without message
     */
    public ExecuteException() {
        super();
    }

    /**
     * Constructor for ExecuteException with message
     * @param message = message to be shown
     */
    public ExecuteException(String message) {
        super(message);
    }
}