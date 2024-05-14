package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Products.Snack;
import Machines.SnackMachine;
import Exceptions.*;

/**
 * Frame to provide info about snack product and add it to existing list
 */
public class AddSnackFrame extends JFrame {
    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JButton createProdButton;

    public AddSnackFrame(SnackMachine machine){
        setTitle("Add snack");
        setSize(500,400);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Snack name: "));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Price in cents: "));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel("Quantity: "));
        quantityField = new JTextField();
        add(quantityField);

        createProdButton = new JButton("Crate product");
        createProdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String name = nameField.getText();
                int price = Integer.parseInt(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                Snack snack = new Snack(name, price, quantity);

                try{
                    machine.addProduct(snack);
                }
                catch(VendingException ex){
                    throw new RuntimeException(ex);
                }
                dispose();
            }
        });
        add(createProdButton);

        setVisible(true);
    }

}
