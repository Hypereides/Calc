import javax.swing.*;

public class CalculatorFrame {
    JFrame frame;
    int frameWidth = 500;
    int frameHeight = 600;

    public static void main(String[] args) {
        new CalculatorFrame();
    }

    CalculatorFrame(){
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setLayout(null);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}