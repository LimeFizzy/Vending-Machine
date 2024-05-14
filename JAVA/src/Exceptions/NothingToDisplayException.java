package Exceptions;

/**
 * Exception that is thrown when there is nothing to display
 */
public class NothingToDisplayException extends VendingException{
    public NothingToDisplayException(String message) {
        super(message);
    }
}
