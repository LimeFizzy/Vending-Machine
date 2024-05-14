package Machines;
import Exceptions.*;
import Interfaces.MoneyOperations;
import java.io.Serializable;
/**
 * Vending machine main class
 */
public abstract class VendingMachine implements MoneyOperations, Serializable{
    private String name;
    private int euro;
    private int cents;

    private static int objCount = 0;
    public static final double EURO_TO_CENTS = 100;

    public VendingMachine(String name){
        this();
        this.name = name;
        objCount++;
    }

    public VendingMachine(){
        name = "";
        euro = 0;
        cents = 0;
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
    public int getEuro(){
        return euro;
    }

    
    /** 
     * @return int
     */
    public int getCents(){
        return cents;
    }

    
    /** 
     * @return int
     */
    public static int getObjCount(){
        return objCount;
    }

    
    /** 
     * @param newName
     */
    public void setName(String newName){
        name = newName;
    }

    
    /** 
     * @param euro
     */
    public void setEuro(int euro){
        this.euro = euro;
    }

    
    /** 
     * @param cents
     */
    public void setCents(int cents){
        this.cents = cents;
    }

    
    /** 
     * Insert money operation with only cents
     * @param amount
     * @throws VendingException
     */
    @Override
    public void insertMoney(int amount) throws VendingException{
        if(amount < 0){
            throw new IllegalArgumentException("Exception: Invalid argument provided.");
        }
        else{
            cents += amount;
            if(cents >= 100){
                euro += cents/100;
                cents %= 100;
            }
        }   
    }

    
    /** 
     * Insert money operation with euros and cents 
     * @param euro
     * @param cents
     * @throws VendingException
     */
    @Override
    public void insertMoney(int euro, int cents) throws VendingException{
        if(euro < 0 || cents < 0){
            throw new IllegalArgumentException("Exception: Invalid argument provided.");
        }
        else{
            this.euro += euro;
            this.cents += cents;
            if(this.cents >= 100){
                this.euro += this.cents/100;
                this.cents %= this.cents;
            }
        }   
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return "Vending Machine: name - " + getName() +
               ", balance - " + getEuro() + " eur " + getCents() + " cents.";
    }

    
}