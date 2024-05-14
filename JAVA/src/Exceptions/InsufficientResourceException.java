package Exceptions;

/**
 * Excpetion that is thrown when there is not enough resources to provide a product
 */
public class InsufficientResourceException extends VendingException {
    public int leftResource = 0;

    public InsufficientResourceException(String message, int leftResource) {
        super(message+leftResource);
    }


}
