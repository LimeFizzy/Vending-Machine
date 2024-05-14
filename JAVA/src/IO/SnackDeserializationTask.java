package IO;

import java.io.*;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import Machines.SnackMachine;

/**
 * Class for snack machine deserialization
 */
public class SnackDeserializationTask implements Runnable{
    private final SnackMachine machine;
    private final Runnable updateProductPanel;
    private final JLabel balanceLabel;

    public SnackDeserializationTask(SnackMachine machine, Runnable updateProductPanel, JLabel balanceLabel) {
        this.machine = machine;
        this.updateProductPanel = updateProductPanel;
        this.balanceLabel = balanceLabel;
    }

    /**
     * Overriden method for runnable object to deserialize and update info label
     */
    @Override
    public void run() {
        deserialize();

        SwingUtilities.invokeLater(() -> {
            balanceLabel.setText("Balance: " + machine.getEuro() + "eur, " + machine.getCents() + "cents.");
        });
    }

    /**
     * Deserialization process for snack machine
     */
    private void deserialize() {
        try(FileInputStream fis = new FileInputStream("snackData.bin");
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            SnackMachine deserializedMachine = (SnackMachine) ois.readObject();
            synchronized (machine) {
                machine.setName(deserializedMachine.getName());
                machine.setEuro(deserializedMachine.getEuro());
                machine.setCents(deserializedMachine.getCents());
                machine.setSnacks(deserializedMachine.getSnacks());
                machine.setSnackID(deserializedMachine.getSnackID());
            }
            System.out.println("Deserialization complete");
            updateProductPanel.run();
        } catch ( IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
