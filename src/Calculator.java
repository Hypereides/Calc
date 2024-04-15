import javax.swing.*;

public class Calculator {
    CalculatorFrame calculatorFrame = new CalculatorFrame();
    CalculatorPanel calculatorPanel = new CalculatorPanel();
    CalculatorOperations calculatorOperations = new CalculatorOperations();
    JTextField textField;
    int textFieldWidth = 300;
    int xPosition = (calculatorFrame.frameWidth - textFieldWidth) / 2;

    Calculator(){
        textField = new JTextField();
        textField.setBounds(xPosition, 30, textFieldWidth, 50);
        textField.setEditable(false);

        CalculatorButtons calculatorButtons = new CalculatorButtons(calculatorOperations, textField);
        textField.setFont(calculatorButtons.myFont);

        for(int i = 0; i<9; i++) {
            calculatorButtons.actionButtons[i].addActionListener(calculatorButtons);
        }

        for(int i =0; i<10; i++) {
            calculatorButtons.numberButtons[i].addActionListener(calculatorButtons);
        }

        calculatorPanel.panel.add(calculatorButtons.numberButtons[1]);
        calculatorPanel.panel.add(calculatorButtons.numberButtons[2]);
        calculatorPanel.panel.add(calculatorButtons.numberButtons[3]);
        calculatorPanel.panel.add(calculatorButtons.sumButton);
        calculatorPanel.panel.add(calculatorButtons.numberButtons[4]);
        calculatorPanel.panel.add(calculatorButtons.numberButtons[5]);
        calculatorPanel.panel.add(calculatorButtons.numberButtons[6]);
        calculatorPanel.panel.add(calculatorButtons.subButton);
        calculatorPanel.panel.add(calculatorButtons.numberButtons[7]);
        calculatorPanel.panel.add(calculatorButtons.numberButtons[8]);
        calculatorPanel.panel.add(calculatorButtons.numberButtons[9]);
        calculatorPanel.panel.add(calculatorButtons.mulButton);
        calculatorPanel.panel.add(calculatorButtons.dcButton);
        calculatorPanel.panel.add(calculatorButtons.numberButtons[0]);
        calculatorPanel.panel.add(calculatorButtons.eqButton);
        calculatorPanel.panel.add(calculatorButtons.divButton);


        calculatorFrame.frame.add(calculatorPanel.panel);
        calculatorFrame.frame.add(calculatorButtons.dlButton);
        calculatorFrame.frame.add(calculatorButtons.clsButton);
        calculatorFrame.frame.add(calculatorButtons.enterButton);
        calculatorFrame.frame.add(textField);
        calculatorFrame.frame.add(calculatorPanel.panel);
    
    }

    public static void main(String[] args) {
        new Calculator();
    }
}