package Exception;

public class MyException extends Exception{
    /**
     * Generic exception
     * @param msg = the message to be shown
     */
    public MyException(String msg){
        super(msg);
    }
}
