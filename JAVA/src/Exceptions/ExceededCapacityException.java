package Exceptions;
/**
 * Exception that is thrown when maximum capacity in vending machine is exceeded
 */
public class ExceededCapacityException extends VendingException {
    public ExceededCapacityException(String message) {
        super(message);
    }
}
