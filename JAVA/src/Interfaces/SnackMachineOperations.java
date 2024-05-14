package Interfaces;

import Exceptions.VendingException;
import Products.Snack;

/**
 * Interface for specific operations of snack machine
 */
public interface SnackMachineOperations{
    void addProduct(Snack snack) throws VendingException;
    void purchaseProduct(int ID) throws VendingException;
    String displayProducts() throws VendingException;
}