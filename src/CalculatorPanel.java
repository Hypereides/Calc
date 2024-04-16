import javax.swing.*;
import java.awt.*;

public class CalculatorPanel {
    JPanel panel;

    CalculatorPanel(){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(50,100,350,350);
        panel.setBackground(Color.black);
        panel.setVisible(true);
    }
}
