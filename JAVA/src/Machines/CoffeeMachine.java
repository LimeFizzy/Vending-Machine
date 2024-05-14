package Machines;

import Products.Coffee;
import Interfaces.CoffeeMachineOperations;
import java.util.HashMap;

import Exceptions.*;

/**
 * Class of coffee machine
 */
public class CoffeeMachine extends VendingMachine implements CoffeeMachineOperations, Cloneable{
    private HashMap<Integer, Coffee> coffeeMap = new HashMap<Integer, Coffee>();
    private int waterLevel;
    private int coffeeBeansLevel;
    private int milkLevel;

    private int coffeeID;

    private final int MAX_WATER_CAPACITY = 10000;
    private final int MAX_COFFEE_BEANS_CAPACITY = 10000;
    private final int MAX_MILK_CAPACITY = 10000;
    private final int MAX_COFFEE_CAPACITY = 10;
    private final int MAX_BALANCE = 1000;

    public CoffeeMachine(String name) {
        super(name);
        waterLevel = 0;
        coffeeBeansLevel = 0;
        milkLevel = 0;
        coffeeID = 0;
    }

    
    /** 
     * @return int
     */
    public int getWaterLevel(){
        return waterLevel;
    }
    
    /** 
     * @return int
     */
    public int getCoffeeBeansLevel(){
        return coffeeBeansLevel;
    }
    
    /** 
     * @return int
     */
    public int getMilkLevel(){
        return milkLevel;
    }

    
    /** 
     * @return int
     */
    public int getCoffeeID(){
        return coffeeID;
    }

    
    /** 
     * @return HashMap<Integer, Coffee>
     */
    public HashMap<Integer, Coffee> getProducts(){
        return coffeeMap;
    }

    
    /** 
     * @param water
     */
    public void addWater(int water){
        waterLevel += water;
    }

    
    /** 
     * @param milk
     */
    public void addMilk(int milk){
        milkLevel += milk;
    }

    
    /** 
     * @param beans
     */
    public void addBeans(int beans){
        coffeeBeansLevel += beans;
    }

    
    /** 
     * @param waterAmount
     */
    public void setWaterLevel(int waterAmount){
        if(waterAmount >= 0 && this.waterLevel + waterAmount <= MAX_WATER_CAPACITY) {
            waterLevel = waterAmount;
        } else {
            throw new IllegalArgumentException("Invalid water level. Water level must be between 0 and " + MAX_WATER_CAPACITY);
        }
    }

    
    /** 
     * @param beansAmount
     */
    public void setCoffeeBeansLevel(int beansAmount){
        if(beansAmount >= 0 && coffeeBeansLevel + beansAmount <= MAX_COFFEE_BEANS_CAPACITY){
            coffeeBeansLevel = beansAmount;
        } else {
            throw new IllegalArgumentException("Invalid coffee beans level. Coffee beans level must be between 0 and " + MAX_COFFEE_BEANS_CAPACITY);
        }

    }

    
    /** 
     * @param milkAmount
     */
    public void setMilkLevel(int milkAmount){
        if (milkAmount >= 0 && milkLevel + milkAmount <= MAX_MILK_CAPACITY) {
            milkLevel = milkAmount;
        } else {
            throw new IllegalArgumentException("Invalid milk level. Milk level must be between 0 and " + MAX_MILK_CAPACITY);
        }
    }

    
    /** 
     * @param ID
     */
    public void setCoffeeID(int ID){
        coffeeID = ID;
    }

    
    /** 
     * @param map
     */
    public void setCoffees(HashMap<Integer,Coffee> map){
        coffeeMap = map;
    }

    
    /** 
     * Method to add new coffee
     * @param coffee
     * @throws VendingException
     */
    public void addCoffee(Coffee coffee) throws VendingException{
        if(coffeeMap.size() < MAX_COFFEE_CAPACITY){
            coffeeMap.put(coffeeID, coffee);
            coffeeID++;
        }
        else{
            throw new ExceededCapacityException("Cannot add more coffee types. Maximum capacity reached.");
        }
    }

    
    /** 
     * Method to display all coffees
     * @return String
     * @throws VendingException
     */
    @Override
    public String displayCoffee() throws VendingException{
        if(coffeeMap.isEmpty()){
            throw new VendingException("Coffee list is empty. Nothing to display.");
        }
        else{
            StringBuilder result = new StringBuilder();
    
            coffeeMap.forEach(
                (key, value) ->
                    result.append("ID: ").append(key).append(", ").append(value.toString()).append("\n")
            );

            return result.toString();
        }
    }
    
    
    /** 
     * Method with the logic to purchase product
     * @param ID
     * @throws VendingException
     */
    public void purchaseCoffee(int ID) throws VendingException{
        if(ID < 0){
            throw new IllegalArgumentException("Illegal ID provided.");
        }
        else{
            Coffee temp = coffeeMap.get(ID);
            int tempBalance = getEuro()*100 + getCents();
            if(temp.getRequiredBeans() > getCoffeeBeansLevel()){
                throw new InsufficientResourceException("Not enough beans in the machine. Left beans in gr - ",getCoffeeBeansLevel());
            }
            else if(temp.getRequiredMilk() > getMilkLevel()){
                throw new InsufficientResourceException("Not enough milk in the machine. Left milk in ml - ",getMilkLevel());
            }
            else if(temp.getRequiredWater() > getWaterLevel()){
                throw new InsufficientResourceException("Not enough water in the machine. Left water in ml - ", getWaterLevel());
            }
            else if(tempBalance < temp.getPrice()){
                throw new InsufficientBalanceException("Insuficient balance.");
            }
            else{
                this.waterLevel -= temp.getRequiredWater();
                this.milkLevel -= temp.getRequiredMilk();
                this.coffeeBeansLevel -= temp.getRequiredBeans();
                tempBalance -= temp.getPrice();
                setEuro(tempBalance/100);
                setCents(tempBalance%100);
            }
        }
    }

    
    /** 
     * @param money
     * @throws VendingException
     */
    @Override
    public void insertMoney(int money) throws VendingException{
        if( super.getEuro() * 100 + super.getCents() / 100 + money > MAX_BALANCE){
            throw new ExceededCapacityException("Maximum capacity exceeded. Cannot add money.");
        }
        super.insertMoney(money);
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return "Coffee Machine: name - " + super.getName() +
               ", balance - " + super.getEuro() + " eur " + super.getCents() + " cents."
               + " water level - " + getWaterLevel() + ", coffee beans amount - " + getCoffeeBeansLevel()
               + " gramms, milk level - " + getMilkLevel() + " ml.";
    }

    
    /** 
     * Preparation of object for cloning
     * @return Object
     */
    @Override
    public Object clone(){
        try{
            CoffeeMachine clonedMachine = (CoffeeMachine) super.clone();
            HashMap<Integer, Coffee> clonedMap = new HashMap<Integer, Coffee>();
            
            coffeeMap.forEach(
                (key, value)->
                    clonedMap.put(key, (Coffee)value.clone())
            );
            clonedMachine.setCoffees(clonedMap);

            return clonedMachine;
        }
        catch(CloneNotSupportedException e){
            throw new Error("Clonning not supported");
        }
    }
}
