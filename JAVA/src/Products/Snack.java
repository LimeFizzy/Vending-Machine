package Products;

/**
 * Snack object class
 */
public class Snack extends Product implements Cloneable{
    
    private int quantity;

    public Snack(String name, int price, int quantity){
        super(name, price);
        this.quantity = quantity;
    }

    
    /** 
     * @return int
     */
    public int getQuantity(){
        return quantity;
    }

    
    /** 
     * @param quantity
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return "Snack: name - " + super.getName() + ", price - " + super.getPrice()/100 + " eur. " 
            + super.getPrice()%100 + " cents., available quantity - " + getQuantity() + ".";
    }

    
    /** 
     * Preparation of snack object for cloning
     * @return Object
     */
    @Override
    public Object clone(){
        try{
            Snack clonedSnack = (Snack) super.clone();
            return clonedSnack;
        }
        catch(CloneNotSupportedException e){
            throw new Error("Clonning not supported");
        }
    }
}
