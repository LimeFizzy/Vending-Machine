package UI;

import Machines.CoffeeMachine;
import Products.Coffee;
import IO.*;
import Exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowAdapter;
import java.util.HashMap;

/**
 * Frame with all the information about generated Coffee machine
 */
public class CoffeeFrame extends JFrame {

    public CoffeeFrame(CoffeeMachine machine){
        setTitle(machine.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,700);
        setLayout(new BorderLayout());

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(4, 1, 0, 10));

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

        JLabel waterLabel = new JLabel("Water level: " + machine.getWaterLevel());
        JLabel addWaterLabel = new JLabel("Add water in ml: ");
        JTextField waterAmountInput = new JTextField(5);
        JButton addWaterBut = new JButton("Add");
        addWaterBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int amount = Integer.parseInt(waterAmountInput.getText());
                machine.addWater(amount);
                waterLabel.setText("Water level: " + machine.getWaterLevel());
            }
        });
        JPanel water = new JPanel(new GridBagLayout());
        JPanel curWater = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel updWater = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        curWater.add(waterLabel);
        updWater.add(addWaterLabel);
        updWater.add(waterAmountInput);
        updWater.add(addWaterBut);
        water.add(curWater, gbcLeft);
        water.add(updWater, gbcRight);

        JLabel milkLabel = new JLabel("Milk level: " + machine.getMilkLevel());
        JLabel addMilkLabel = new JLabel("Add milk in ml: ");
        JTextField milkAmountInput = new JTextField(5);
        JButton addMilkBut = new JButton("Add");
        addMilkBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int amount = Integer.parseInt(milkAmountInput.getText());
                machine.addMilk(amount);
                milkLabel.setText("Milk level: " + machine.getMilkLevel());
            }
        });
        JPanel milk = new JPanel(new GridBagLayout());
        JPanel curMilk = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel updMilk = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        curMilk.add(milkLabel);
        updMilk.add(addMilkLabel);
        updMilk.add(milkAmountInput);
        updMilk.add(addMilkBut);
        milk.add(curMilk, gbcLeft);
        milk.add(updMilk, gbcRight);


        JLabel beansLabel = new JLabel("Beans level: " + machine.getCoffeeBeansLevel());
        JLabel addBeansLabel = new JLabel("Add beans in gr: ");
        JTextField beansAmountInput = new JTextField(5);
        JButton addBeansBut = new JButton("Add");
        addBeansBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int amount = Integer.parseInt(beansAmountInput.getText());
                machine.addBeans(amount);
                beansLabel.setText("Beans level: " + machine.getCoffeeBeansLevel());
            }
        });
        JPanel beans = new JPanel(new GridBagLayout());
        JPanel curBeans = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel updBeans = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        curBeans.add(beansLabel);
        updBeans.add(addBeansLabel);
        updBeans.add(beansAmountInput);
        updBeans.add(addBeansBut);
        beans.add(curBeans, gbcLeft);
        beans.add(updBeans, gbcRight);

        statusPanel.add(money);
        statusPanel.add(water);
        statusPanel.add(milk);
        statusPanel.add(beans);

        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BorderLayout());

        JLabel productsHeading = new JLabel("Available products: ");
        productPanel.add(productsHeading, BorderLayout.NORTH);

        JPanel productInfoPanel = new JPanel(new GridLayout(0, 1));
        productInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Runnable updateProductPanel = () -> {
            SwingUtilities.invokeLater(() -> {
                productInfoPanel.removeAll();

                HashMap<Integer, Coffee> products = machine.getProducts();
                if (products.isEmpty()) {
                    JLabel noProducts = new JLabel("Nothing to display");
                    productPanel.add(noProducts, BorderLayout.CENTER);
                }
                else {
                    for (HashMap.Entry<Integer, Coffee> entry : products.entrySet()){
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

        JButton addCoffeeButton = new JButton("Add coffee");
        addCoffeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCoffeeFrame addCoffeeFrame = new AddCoffeeFrame(machine);
                addCoffeeFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e){
                        updateProductPanel.run();
                    }
                });
            }
        });

        JPanel buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(addCoffeeButton, BorderLayout.NORTH);

        JButton brewButton = new JButton("Brew coffee");
        brewButton.addActionListener(new ActionListener() {        
            @Override
            public void actionPerformed(ActionEvent e){
                brewCoffeeFrame brew = new brewCoffeeFrame(machine);

                brew.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e){
                        updateProductPanel.run();
                        waterLabel.setText("Water level: " + machine.getWaterLevel());
                        milkLabel.setText("Milk level: " + machine.getMilkLevel());
                        beansLabel.setText("Beans level: " + machine.getCoffeeBeansLevel());
                        balanceLabel.setText("Balance: " + machine.getEuro() + " eur, " + machine.getCents() + " cents.");
                    }
                });
            }
        });
        buttonsPanel.add(brewButton, BorderLayout.CENTER);

        JPanel serializationPanel = new JPanel(new GridLayout(1, 2));
        JButton serializeButton = new JButton("Export machine");
        serializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                CoffeeSerializationTask serializeCoffee = new CoffeeSerializationTask(machine);
                SwingUtilities.invokeLater(serializeCoffee);
            }
        });

        JButton deserializeButton = new JButton("Import machine");
        deserializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                CoffeeDeserializationTask deserializeCoffee = new CoffeeDeserializationTask(machine, updateProductPanel, balanceLabel, waterLabel, milkLabel, beansLabel);
                // SwingUtilities.invokeLater(deserializeCoffee);
                new Thread(deserializeCoffee).start();;
            }
        });

        serializationPanel.add(serializeButton);
        serializationPanel.add(deserializeButton);
        buttonsPanel.add(serializationPanel, BorderLayout.SOUTH);

        productPanel.add(new JScrollPane(productInfoPanel), BorderLayout.CENTER);
        productPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(statusPanel, BorderLayout.NORTH);
        add(productPanel, BorderLayout.CENTER);

        updateProductPanel.run();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
    
}
        