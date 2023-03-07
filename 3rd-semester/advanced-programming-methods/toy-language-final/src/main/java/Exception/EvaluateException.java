package Exception;

public class EvaluateException extends Exception{
    /**
     * Constructor for EvaluateException without message
     */
    public EvaluateException() {
        super();
    }

    /**
     * Constructor for EvaluateException with message
     * @param message = message to be shown
     */
    public EvaluateException(String message) {
        super(message);
    }
}