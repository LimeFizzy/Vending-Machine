package IO;

import java.io.*;

import javax.swing.JLabel;

import Machines.CoffeeMachine;

/**
 * Class for deserialization of coffee machine
 */
public class CoffeeDeserializationTask implements Runnable{
    private final CoffeeMachine machine;
    private final Runnable updateProductPanel;
    private final JLabel balanceLabel;
    private final JLabel waterLabel;
    private final JLabel milkLabel;
    private final JLabel beansLabel;

    public CoffeeDeserializationTask(CoffeeMachine machine, Runnable updateProductPanel, JLabel balanceLabel, JLabel waterLabel,
                                    JLabel milkLabel, JLabel beansLabel) {
        this.machine = machine;
        this.updateProductPanel = updateProductPanel;
        this.balanceLabel = balanceLabel;
        this.waterLabel = waterLabel;
        this.milkLabel = milkLabel;
        this.beansLabel = beansLabel;
    }

    /**
     * Overriden method for runnable object to deserialize object and update info labels
     */
    @Override
    public void run() {
        deserialize();

        balanceLabel.setText("Balance: " + machine.getEuro() + "eur, " + machine.getCents() + "cents.");
        waterLabel.setText("Water level: " + machine.getWaterLevel());
        milkLabel.setText("Milk level: " + machine.getMilkLevel());
        beansLabel.setText("Beans level: " + machine.getCoffeeBeansLevel());
        
    }

    /**
     * Deserialization process for the coffee machine
     */
    private void deserialize() {
        try(FileInputStream fis = new FileInputStream("coffeeData.bin");
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            CoffeeMachine deserializedMachine = (CoffeeMachine) ois.readObject();
            synchronized (machine) {
                machine.setName(deserializedMachine.getName());
                machine.setEuro(deserializedMachine.getEuro());
                machine.setCents(deserializedMachine.getCents());
                machine.setCoffeeID(deserializedMachine.getCoffeeID());
                machine.setCoffees(deserializedMachine.getProducts());
                machine.setCoffeeBeansLevel(deserializedMachine.getCoffeeBeansLevel());
                machine.setMilkLevel(deserializedMachine.getMilkLevel());
                machine.setWaterLevel(deserializedMachine.getWaterLevel());
            }
            System.out.println("Deserialization complete");
            updateProductPanel.run();
        } catch ( IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
