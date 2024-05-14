package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Products.Coffee;
import Machines.CoffeeMachine;
import Exceptions.*;

/**
 * Frame to provide info about new coffee and add it as new option
 */
public class AddCoffeeFrame extends JFrame {
    private JTextField nameField;
    private JTextField priceField;
    private JTextField beansField;
    private JTextField waterField;
    private JTextField milkField;
    private JButton createProdButton;

    public AddCoffeeFrame(CoffeeMachine machine) {
        setTitle("Add coffee");
        setSize(500, 400);
        setLayout(new GridLayout(6,2));

        add(new JLabel("Coffee name: "));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Price in cents: "));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel("Required beans: "));
        beansField = new JTextField();
        add(beansField);

        add(new JLabel("Required milk: "));
        milkField = new JTextField();
        add(milkField);

        add(new JLabel("Required water: "));
        waterField = new JTextField();
        add(waterField);
        
        createProdButton = new JButton("Create product");
        createProdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int price = Integer.parseInt(priceField.getText());
                int beans = Integer.parseInt(beansField.getText());
                int milk = Integer.parseInt(milkField.getText());
                int water = Integer.parseInt(waterField.getText());

                Coffee coffee = new Coffee(name, price, beans, milk, water);

                try{
                    machine.addCoffee(coffee);
                }
                catch (VendingException ex){
                    throw new RuntimeException(ex);
                }
                dispose();
            }
        });
        add(createProdButton);

        setVisible(true);
    }
}
