package IO;

import java.io.*;

import Machines.VendingMachine;

/**
 * Class for serialization process of Coffee machine
 */
public class CoffeeSerializationTask implements Runnable {
    
    private final VendingMachine machine;

    public CoffeeSerializationTask(VendingMachine machine) {
        this.machine = machine;
    }
    
    /**
     * Runnable method override
     */
    @Override
    public void run() {
        serialize(machine);
    }
    
    
    /** 
     * Serialization process for the coffee machine
     * @param machine
     */
    private void serialize(VendingMachine machine) {
        try (FileOutputStream fos = new FileOutputStream("coffeeData.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(machine);
            System.out.println("Serialization complete");
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
