package Machines;
import Products.Snack;
import Interfaces.SnackMachineOperations;
import java.util.HashMap;
import Exceptions.*;

/**
 * Class of snack machine
 */
public class SnackMachine extends VendingMachine implements SnackMachineOperations, Cloneable{
    HashMap<Integer, Snack> snacks = new HashMap<Integer, Snack>();
    private int snackID;
    private final int PRODUCTS_LIMIT = 25;
    private final int MAX_BALANCE = 1000;

    public SnackMachine(String name){
        super(name);
        snackID = 0;
    }

    
    /** 
     * @return int
     */
    public int getLimit(){
        return PRODUCTS_LIMIT;
    }

    
    /** 
     * @return int
     */
    public int getSnackID(){
        return snackID;
    }

    
    /** 
     * @param ID
     */
    public void setSnackID(int ID){
        snackID = ID;
    }

    
    /** 
     * @param map
     */
    public void setSnacks(HashMap<Integer,Snack> map){
        snacks = map;
    }

    
    /** 
     * @return HashMap<Integer, Snack>
     */
    public HashMap<Integer, Snack> getSnacks(){
        return snacks;
    }

    
    /** 
     * Method to display all the products
     * @return String
     * @throws VendingException
     */
    @Override 
    public String displayProducts() throws VendingException{
        if(snacks.size() == 0){
            throw new NothingToDisplayException("Snack list is empty. Nothing to display.");
        }
        else{
            StringBuilder result = new StringBuilder();
    
            snacks.forEach(
                (key, value) ->
                    result.append("ID: ").append(key).append(", ").append(value.toString()).append("\n")
            );

            return result.toString();
        }
    }

    
    /** 
     * Method to add new product
     * @param snack
     * @throws VendingException
     */
    public void addProduct(Snack snack) throws VendingException{
        if(snacks.size() < PRODUCTS_LIMIT){
            snacks.put(getSnackID(), snack);
            snackID++;
        }
        else{
            throw new ExceededCapacityException("Cannot add more snacks. Maximum capacity reached.");
        }
    }

    
    /** 
     * Method to purchase product
     * @param ID
     * @throws VendingException
     */
    public void purchaseProduct(int ID) throws VendingException{
        if(ID < 0){
            throw new IllegalArgumentException("Illegal ID provided.");
        }
        else{
            Snack temp = snacks.get(ID);
            int tempBalance = getEuro()*100 + getCents();
            if(temp.getQuantity() < 1){
                throw new OutOfStockException("Snack is out of stock.");
            }
            else if(tempBalance < temp.getPrice()){
                throw new InsufficientBalanceException("Insuficient balance.");
            }
            else{
                tempBalance -= temp.getPrice();
                setEuro(tempBalance/100);
                setCents(tempBalance%100);
                temp.setQuantity(temp.getQuantity()-1);
            }
        }
    }

    
    /** 
     * Method to insert money with additional check  
     * @param cents
     * @throws VendingException
     */
    @Override
    public void insertMoney(int cents) throws VendingException{
        if(super.getEuro() * 100 + super.getCents() / 100 + cents > MAX_BALANCE){
            throw new ExceededCapacityException("Maximum capacity exceeded. Cannot insert money.");
        }

        super.insertMoney(cents);
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return "Snack Machine: name - " + super.getName() +
               ", balance - " + super.getEuro() + " eur " + super.getCents() + " cents."
               + " max. products limit - " + PRODUCTS_LIMIT;
    }

    
    /** 
     * Preparation of the object to cloning
     * @return Object
     */
    @Override
    public Object clone(){
        try{
            SnackMachine clonedMachine = (SnackMachine) super.clone();
            HashMap<Integer, Snack> clonedMap = new HashMap<Integer, Snack>();
            
            snacks.forEach(
                (key, value)->
                    clonedMap.put(key, (Snack)value.clone())
            );
            clonedMachine.setSnacks(clonedMap);

            return clonedMachine;
        }
        catch(CloneNotSupportedException e){
            throw new Error("Clonning not supported");
        }
    }
}

