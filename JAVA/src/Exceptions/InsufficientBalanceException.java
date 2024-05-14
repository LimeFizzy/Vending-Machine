package Exceptions;
/**
 * Exception that is thrown when there is not enough money to make a purchase
 */
public class InsufficientBalanceException extends VendingException{
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
