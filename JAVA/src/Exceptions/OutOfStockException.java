package Exceptions;

/**
 * Exception that is thrown when selected product is out of stock
 */
public class OutOfStockException extends VendingException {
    public OutOfStockException(String message) {
        super(message);
    }
}
