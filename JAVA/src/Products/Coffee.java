package Products;

/**
 * Class of coffee object
 */
public class Coffee extends Product implements Cloneable {
    private int requiredBeans;
    private int requiredMilk;
    private int requiredWater;

    public Coffee(String name, int priceInCents, int requiredBeans, int requiredMilk, int requiredWater){
        super(name, priceInCents);
        this.requiredBeans = requiredBeans;
        this.requiredMilk = requiredMilk;
        this.requiredWater = requiredWater;
    }

    
    /** 
     * @return int
     */
    public int getRequiredBeans(){
        return requiredBeans;
    }

    
    /** 
     * @return int
     */
    public int getRequiredMilk(){
        return requiredMilk;
    }

    
    /** 
     * @return int
     */
    public int getRequiredWater(){
        return requiredWater;
    }

    
    /** 
     * @param requiredBeans
     */
    public void setRequiredBeans(int requiredBeans){
        this.requiredBeans = requiredBeans;
    }

    
    /** 
     * @param requiredMilk
     */
    public void setRequiredMilk(int requiredMilk){
        this.requiredMilk = requiredMilk;
    }

    
    /** 
     * @param requiredWater
     */
    public void setRequiredWater(int requiredWater){
        this.requiredWater = requiredWater;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return "Coffee: name - " + super.getName() + ", price - " + super.getPrice()/100 + " eur. " 
            + super.getPrice()%100 + " cents. Products required for preparation: beans - " + getRequiredBeans()
            + "g., milk - " + getRequiredMilk() + "ml., water - " + getRequiredWater() +"ml.";
    }

    
    /** 
     * Preparation of object for cloning
     * @return Object
     */
    @Override
    public Object clone(){
        try{
            Coffee clonedCoffee = (Coffee) super.clone();
            return clonedCoffee;
        }
        catch(CloneNotSupportedException e){
            throw new Error("Clonning not supported");
        }
    }
}
