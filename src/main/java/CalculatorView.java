package main.java;

import javax.swing.*;
import java.awt.*;

public class CalculatorView extends JFrame {
    private Calculator calculator;
    private JTextField typeValue;
    private JComboBox<String> chosenCurrency;
    private JButton countButton;
    private JLabel valueLabel;

    public CalculatorView(Calculator calculator){
        this.calculator = calculator;
        setView();
    }

    private void setView(){
        chosenCurrency = new JComboBox<>(Calculator.getCurrencies().keySet().toArray(new String[0]));
        countButton = new JButton("Count");
        valueLabel = new JLabel();
        typeValue = new JTextField();
        countButton = new JButton("Count");
        chosenCurrency.setSelectedIndex(0);

        try {
            chosenCurrency.setSelectedItem(calculator.getCurrency());
            typeValue.setText(String.valueOf(calculator.getValue()));
            valueLabel.setText("Value: " + calculator.getValueOfCurrencyConversion());
        } catch (Exception e) {
            valueLabel.setText("Value: ");
        }

        countButton.addActionListener(e -> {
            if(Calculator.checkIfNumber(typeValue.getText())) {
                try {
                    calculator.setValue(Double.parseDouble(typeValue.getText()));
                    calculator.setCurrency(chosenCurrency.getSelectedItem().toString());
                    valueLabel.setText("Value: " + calculator.getValueOfCurrencyConversion());
                } catch (Exception ex) {
                    typeValue.setText("0");
                }
            }
        });
        JPanel jPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(3,2);
        jPanel.setLayout(gridLayout);
        jPanel.setBorder(BorderFactory.createEmptyBorder(30,20,30,20));
        gridLayout.setVgap(50);
        gridLayout.setHgap(20);
        jPanel.add(new JLabel("Type value in EUR: "));
        jPanel.add(typeValue);
        jPanel.add(new JLabel("Chosen currency:"));
        jPanel.add(chosenCurrency);
        jPanel.add(countButton);
        jPanel.add(valueLabel);

        add(jPanel);
        setSize(300,250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        }

}
