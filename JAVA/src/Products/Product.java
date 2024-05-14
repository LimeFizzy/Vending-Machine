package Products;

import java.io.Serializable;

/**
 * Product object main class
 */
public class Product implements Serializable{
    private int priceInCents;
    private String name;
    
    public Product(String name, int price){
        this.name = name;
        priceInCents = price;
    }

    
    /** 
     * @return String
     */
    public String getName(){
        return name;
    }

    
    /** 
     * @return int
     */
    public int getPrice(){
        return priceInCents;
    }

    
    /** 
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    
    /** 
     * @param price
     */
    public void setPrice(int price){
        priceInCents = price;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return "Product: name - " + getName() + " price - " + getPrice()/100 + " eur. " 
            + getPrice()%100 + " cents.";
    }

}