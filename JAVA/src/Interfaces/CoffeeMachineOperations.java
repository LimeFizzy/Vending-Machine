package Interfaces;

import Exceptions.VendingException;
import Products.Coffee;

/**
 * Interface for specific Coffee machine operations
 */
public interface CoffeeMachineOperations{
    void addCoffee(Coffee coffee) throws VendingException;
    void purchaseCoffee(int ID) throws VendingException;
    String displayCoffee() throws VendingException;
}