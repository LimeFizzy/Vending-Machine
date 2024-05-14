package Exceptions;

/**
 * Main custom exception class
 */
public class VendingException extends Exception {
    public VendingException(String message){
        super(message);
    }
}