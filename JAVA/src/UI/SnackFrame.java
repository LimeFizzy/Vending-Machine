package UI;

import Machines.SnackMachine;
import Products.Snack;
import IO.*;
import Exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowAdapter;
import java.util.HashMap;

/**
 * Frame with all the info an functionality of the snack machine
 */
public class SnackFrame extends JFrame {

    public SnackFrame(SnackMachine machine){
        setTitle(machine.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,700);
        setLayout(new BorderLayout());

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(1, 1));
        statusPanel.setBounds(0,0,800,150);

        JLabel balanceLabel = new JLabel("Balance: " + machine.getEuro() + "eur, " + machine.getCents() + "cents.");
        JLabel addCentsLabel = new JLabel("Insert money (in cents):");
        JTextField moneyInput = new JTextField(5);
        JButton addMoneyButton = new JButton("Insert");
        addMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int amount = Integer.parseInt(moneyInput.getText());
                try{
                    machine.insertMoney(amount);
                } catch (VendingException ex){
                    throw new RuntimeException(ex);
                }
                balanceLabel.setText("Balance: " + machine.getEuro() + " eur, " + machine.getCents() + " cents.");
            }
        });

        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 0;
        gbcLeft.weightx = 0;
        gbcLeft.anchor = GridBagConstraints.WEST;

        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.gridx = 1;
        gbcRight.gridy = 0;
        gbcRight.weightx = 1;
        gbcRight.anchor = GridBagConstraints.SOUTH;

        JPanel money = new JPanel(new GridBagLayout());
        JPanel curMoney = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel updMoney = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        curMoney.add(balanceLabel);
        updMoney.add(addCentsLabel);
        updMoney.add(moneyInput);
        updMoney.add(addMoneyButton);
        money.add(curMoney, gbcLeft);
        money.add(updMoney, gbcRight);

        statusPanel.add(money);

        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BorderLayout());

        JLabel productsHeading = new JLabel("Available products: ");
        productPanel.add(productsHeading, BorderLayout.NORTH);

        JPanel productInfoPanel = new JPanel(new GridLayout(0, 1));
        productInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Runnable updateSnackPanel = () -> {
            SwingUtilities.invokeLater(() -> {
                productInfoPanel.removeAll();

                HashMap<Integer, Snack> products = machine.getSnacks();
                if (products.isEmpty()) {
                    JLabel noProducts = new JLabel("Nothing to display");
                    productPanel.add(noProducts);
                }
                else {
                    for (HashMap.Entry<Integer, Snack> entry : products.entrySet()){
                        JPanel productDetailsPanel = new JPanel(new GridLayout(1, 1));

                        JLabel prodInfo = new JLabel("ID: " + entry.getKey() + ", " + entry.getValue());
                        productDetailsPanel.add(prodInfo);

                        productInfoPanel.add(productDetailsPanel);
                    }
                }

                productInfoPanel.revalidate();
                productInfoPanel.repaint();
            });
        };

        JButton addSnackButton = new JButton("Add snack");
        addSnackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSnackFrame addSnackFrame = new AddSnackFrame(machine);

                addSnackFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e){
                        updateSnackPanel.run();
                    }
                });
            }
        });

        JPanel buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(addSnackButton, BorderLayout.NORTH);

        JButton purchaseSnackButton = new JButton("Purchase snack");
        purchaseSnackButton.addActionListener(new ActionListener() {        
            @Override
            public void actionPerformed(ActionEvent e){
                purchaseSnack purchase = new purchaseSnack(machine);

                purchase.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e){
                        updateSnackPanel.run();
                        balanceLabel.setText("Balance: " + machine.getEuro() + " eur, " + machine.getCents() + " cents.");
                    }
                });
            }
        });
        buttonsPanel.add(purchaseSnackButton, BorderLayout.CENTER);

        JPanel serializationPanel = new JPanel(new GridLayout(1, 2));
        JButton serializeButton = new JButton("Export machine");
        serializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                SnackSerializationTask serializeSnack = new SnackSerializationTask(machine);
                SwingUtilities.invokeLater(serializeSnack);
            }
        });
 
        JButton deserializeButton = new JButton("Import machine");
        deserializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                SnackDeserializationTask deserializeSnack = new SnackDeserializationTask(machine, updateSnackPanel, balanceLabel);
                SwingUtilities.invokeLater(deserializeSnack);
            }
        });

        serializationPanel.add(serializeButton);
        serializationPanel.add(deserializeButton);
        buttonsPanel.add(serializationPanel, BorderLayout.SOUTH);

        productPanel.add(new JScrollPane(productInfoPanel), BorderLayout.CENTER);
        productPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(statusPanel, BorderLayout.NORTH);
        add(productPanel, BorderLayout.CENTER);

        updateSnackPanel.run();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
    
}
        