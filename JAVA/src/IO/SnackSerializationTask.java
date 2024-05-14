package IO;

import java.io.*;

import Machines.VendingMachine;

/**
 * Class for snack machine serialization
 */
public class SnackSerializationTask implements Runnable {
    
    private final VendingMachine machine;

    public SnackSerializationTask(VendingMachine machine) {
        this.machine = machine;
    }
    
    /**
     * Overriden runnable object method
     */
    @Override
    public void run() {
        serialize(machine);
    }
    
    
    /** 
     * Serialization process for snack machine
     * @param machine
     */
    private void serialize(VendingMachine machine) {
        try (FileOutputStream fos = new FileOutputStream("snackData.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(machine);
            System.out.println("Serialization complete");
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
