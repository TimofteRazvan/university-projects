package Exception;

public class ADTException extends Exception{
    /**
     * Constructor for ADTException without message
     */
    public ADTException() {
        super();
    }

    /**
     * Constructor for ADTException with message
     * @param message = message to be shown
     */
    public ADTException(String message) {
        super(message);
    }
}