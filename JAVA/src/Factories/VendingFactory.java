package Factories;

import Machines.*;

/**
 * Factory class to create new Vending machines
 */
public class VendingFactory {
    /** 
     * @param name
     * @return CoffeeMachine
     */
    public CoffeeMachine createCoffeeMachine(String name){
        return new CoffeeMachine(name);
    }

    /** 
     * @param name
     * @return SnackMachine
     */
    public SnackMachine createSnackMachine(String name){
        return new SnackMachine(name);
    }
}