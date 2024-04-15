import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorButtons implements ActionListener {
    JButton[] numberButtons = new JButton[10];
    JButton[] actionButtons = new JButton[9];
    JButton sumButton, subButton, divButton, mulButton;
    JButton dcButton, eqButton, dlButton, clsButton, enterButton;
    Font myFont = new Font("Helvetica", Font.BOLD, 40);
    CalculatorOperations calculatorOperations;
    JTextField textField;

    CalculatorButtons(CalculatorOperations calculatorOperations, JTextField textField) {
        this.calculatorOperations = calculatorOperations;
        this.textField = textField;

        sumButton = new JButton("+");
        subButton = new JButton("-");
        divButton = new JButton("/");
        mulButton = new JButton("*");
        dcButton = new JButton(".");
        eqButton = new JButton("=");
        dlButton = new JButton("DEL");
        clsButton = new JButton("C");
        enterButton = new JButton("Enter");

        actionButtons[0] = sumButton;
        actionButtons[1] = subButton;
        actionButtons[2] = divButton;
        actionButtons[3] = mulButton;
        actionButtons[4] = dcButton;
        actionButtons[5] = eqButton;
        actionButtons[6] = dlButton;
        actionButtons[7] = clsButton;
        actionButtons[8] = enterButton;

        int buttonWidth = 100;
        int buttonHeight = 50;
        int offsetX = 10;
        int offsetY = 10;
        int additionalOffset = buttonHeight + 5; //r

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);

            int x = offsetX + (i % 3) * (buttonWidth + 5);
            int y = offsetY + (i / 3) * (buttonHeight + 5);

            numberButtons[i].setBounds(x, y, buttonWidth, buttonHeight);
        }

        for (int i = 0; i < 9; i++) {
            actionButtons[i].addActionListener(this);
            actionButtons[i].setFont(myFont);
            actionButtons[i].setFocusable(false);

            int x = offsetX + (i % 3) * (buttonWidth + 5);
            int y = offsetY + ((i / 3) * (buttonHeight + 5)) + additionalOffset;

            actionButtons[i].setBounds(x, y, buttonWidth, buttonHeight);
        }
        // Set the bounds for the dlButton and clsButton
        int buttonSpacing = 20;
        int newOffsetY = offsetY + 5 * (buttonHeight + 5) + buttonSpacing;
        dlButton.setBounds(offsetX + buttonWidth / 2, newOffsetY, buttonWidth, buttonHeight);
        clsButton.setBounds(offsetX + buttonWidth / 2 + buttonWidth + 5, newOffsetY, buttonWidth, buttonHeight);

        // Set the bounds for the enterButton
        enterButton.setBounds(offsetX + buttonWidth / 2, newOffsetY + buttonHeight + buttonSpacing, 2 * buttonWidth + 5,buttonHeight);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();

        if (buttonText.equals("C")) {
            clearTextFieldAndStack();
        } else if (buttonText.equals("DEL")) {
            deleteLastCharacter();
        } else if (buttonText.equals("Enter")) {
            pushInputToStack();
        } else if (buttonText.equals("=")) {
            if (calculatorOperations.stack.size() >= 2) {
                calculatorOperations.performOperation();
            }
        } else if (buttonText.equals(".")) {
            appendToTextField(buttonText);
        } else {
            handleNumberOrOperator(buttonText);
        }
    }

    private void clearTextFieldAndStack() {
        textField.setText("");
        calculatorOperations.stack.clear();
    }

    private void deleteLastCharacter() {
        String text = textField.getText();
        if (!text.isEmpty()) {
            textField.setText(text.substring(0, text.length() - 1));
        }
    }

    private void pushInputToStack() {
        if (!textField.getText().isEmpty()) {
            calculatorOperations.stack.push(textField.getText());
            textField.setText("");
        }
    }

    private void appendToTextField(String text) {
        textField.setText(textField.getText() + text);
    }

    private void handleNumberOrOperator(String buttonText) {
        try {
            Double.parseDouble(buttonText);
            appendToTextField(buttonText);
        } catch (NumberFormatException ex) {
            // if parsing failed then it is an operator not a number
            if (calculatorOperations.stack.size() >= 2) {
                calculatorOperations.performOperation();
                calculatorOperations.stack.push(buttonText);
            }
        }
    }
}