package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Machines.SnackMachine;
import Exceptions.*;

/**
 * Frame to enter selected snack ID
 */
public class purchaseSnack extends JFrame{
    public purchaseSnack(SnackMachine machine){
        setTitle("Purchase snack");
        setLayout(new BorderLayout());

        JPanel selectionPanel = new JPanel(new GridLayout(3, 1));
        selectionPanel.add(new JLabel("Enter snack ID:"));
        JTextField inputField = new JTextField(5);
        selectionPanel.add(inputField);
        
        JButton purchase = new JButton("Purchase");
        purchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int ID = Integer.parseInt(inputField.getText());

                try {
                    machine.purchaseProduct(ID);
                } catch (VendingException ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
            }
        });

        selectionPanel.add(purchase);
        add(selectionPanel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }
}
