package UI;

import Machines.*;
import Factories.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * Main class of the GUI
 */
public class AppUI {
    // Main page fields
    private JFrame frame;
    private JLabel coffeeMachineLabel;
    private JLabel snackMachineLabel;
    private ImageIcon coffeeMachineIcon;
    private ImageIcon snackMachineIcon;
    private JPanel coffeeMachinePan;
    private JPanel snackMachinePan;

    /**
     * Main frame with vending machine selection
     */
    public AppUI() {
        // Create and resize icons for the Coffee and Snack machines
        coffeeMachineIcon = new ImageIcon("/Users/leonardassinkevicius/Documents/University/2semester/JAVA/Project/src/UI/coffeeM.png");
        Image coffeeImage = coffeeMachineIcon.getImage();
        Image modCoffeeImage = coffeeImage.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
        coffeeMachineIcon = new ImageIcon(modCoffeeImage);

        snackMachineIcon = new ImageIcon("/Users/leonardassinkevicius/Documents/University/2semester/JAVA/Project/src/UI/snackM.png");
        Image snackImage = snackMachineIcon.getImage();
        Image modImage = snackImage.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
        snackMachineIcon = new ImageIcon(modImage);
        // ------------------------------------------------------------

        // Create a label for coffee and snack machines and add an icon to it
        coffeeMachineLabel = new JLabel("Coffee machine");
        coffeeMachineLabel.setIcon(coffeeMachineIcon);
        coffeeMachineLabel.setHorizontalTextPosition(JLabel.CENTER);
        coffeeMachineLabel.setVerticalTextPosition(JLabel.TOP);
        coffeeMachineLabel.setFont(new Font("Helvetica", Font.BOLD, 32));

        snackMachineLabel = new JLabel("Snack machine");
        snackMachineLabel.setIcon(snackMachineIcon);
        snackMachineLabel.setHorizontalTextPosition(JLabel.CENTER);
        snackMachineLabel.setVerticalTextPosition(JLabel.TOP);
        snackMachineLabel.setFont(new Font("Helvetica", Font.BOLD, 32));
        // ------------------------------------------------------------

        // Creating a panels for coffee and snack machines creation
        coffeeMachinePan = new JPanel();
        coffeeMachinePan.setLayout(new BorderLayout());
        coffeeMachinePan.add(coffeeMachineLabel);
        JButton createCoffeeM = new JButton("Create coffee machine");
        coffeeMachinePan.add(createCoffeeM, BorderLayout.SOUTH);
        createCoffeeM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                createVendingMachine(1);
            }
        });


        snackMachinePan = new JPanel();
        snackMachinePan.setLayout(new BorderLayout());
        snackMachinePan.add(snackMachineLabel);
        JButton createSnackM = new JButton("Create snack machine");
        snackMachinePan.add(createSnackM, BorderLayout.SOUTH);
        createSnackM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                createVendingMachine(2);
            }
        });
        // ------------------------------------------------------------
    
        frame = new JFrame("Choose vending machine");
        frame.setLayout(new GridBagLayout());

        // Create GridBagConstraints for coffeeMachinePan
        GridBagConstraints gbcCoffeeMachinePan = new GridBagConstraints();
        gbcCoffeeMachinePan.gridx = 0;
        gbcCoffeeMachinePan.gridy = 0;
        gbcCoffeeMachinePan.weightx = 0.5;
        gbcCoffeeMachinePan.anchor = GridBagConstraints.CENTER; 
        frame.add(coffeeMachinePan, gbcCoffeeMachinePan);

        // Create GridBagConstraints for snackMachinePan
        GridBagConstraints gbcSnackMachinePan = new GridBagConstraints();
        gbcSnackMachinePan.gridx = 1;
        gbcSnackMachinePan.gridy = 0;
        gbcSnackMachinePan.weightx = 0.5;
        gbcSnackMachinePan.anchor = GridBagConstraints.CENTER;
        frame.add(snackMachinePan, gbcSnackMachinePan);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setVisible(true);
    }

    
    /** 
     * Method to create new selected vending machine and open corresponding frame
     * @param option
     */
    private void createVendingMachine(int option){
        VendingFactory factory = new VendingFactory();
        if(option == 1){
            frame.dispose();
            openCoffeeFrame(factory.createCoffeeMachine("Coffee machine"));
        }
        else if(option == 2){
            frame.dispose();
            openSnackFrame(factory.createSnackMachine("Snack machine"));
        }
    }

    
    /** 
     * Method to navigate to new Coffee machine frame
     * @param machine
     */
    private void openCoffeeFrame(CoffeeMachine machine){
        new CoffeeFrame(machine);
    }

    
    /** 
     * Method to navigate to new Snack machine frame
     * @param machine
     */
    private void openSnackFrame(SnackMachine machine){
        new SnackFrame(machine);
    }
}