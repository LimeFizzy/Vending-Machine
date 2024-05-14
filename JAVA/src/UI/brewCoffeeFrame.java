package UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Exceptions.VendingException;
import Machines.CoffeeMachine;

/**
 * Frame to input id of a selected coffee and purchase it
 */
public class brewCoffeeFrame extends JFrame {
    public brewCoffeeFrame(CoffeeMachine machine) {
        setTitle("Brew coffee");
        setLayout(new BorderLayout());

        JPanel selectionPanel = new JPanel(new GridLayout(3, 1));
        selectionPanel.add(new JLabel("Enter coffee ID:"));
        JTextField coffeeIdInput = new JTextField(5);
        selectionPanel.add(coffeeIdInput);
        JButton brew = new JButton("Brew");
        brew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int ID = Integer.parseInt(coffeeIdInput.getText());
                try{
                    machine.purchaseCoffee(ID);
                } catch (VendingException ex){
                    throw new RuntimeException(ex);
                }
                dispose();
            }
        });

        selectionPanel.add(brew);

        add(selectionPanel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }
}
