package Interfaces;

import Exceptions.VendingException;

/**
 * Interface for basic money operations for all vending machines
 */
public interface MoneyOperations{
    void insertMoney(int cents) throws VendingException;
    void insertMoney(int euro, int cents) throws VendingException;
}
