package Factories;

import Products.*;

/**
 * Factory class to create new products
 */
public class ProductsFactory {
    
    /** 
     * @param name
     * @param priceInCents
     * @param requiredBeans
     * @param requiredMilk
     * @param requiredWater
     * @return Coffee
     */
    public Coffee createCoffee(String name, int priceInCents, int requiredBeans, int requiredMilk, int requiredWater){
        return new Coffee(name, priceInCents, requiredBeans, requiredMilk, requiredWater);
    }

    /** 
     * @param name
     * @param price
     * @param quantity
     * @return Snack
     */
    public Snack createSnack(String name, int price, int quantity){
        return new Snack(name, price, quantity);
    }
}
