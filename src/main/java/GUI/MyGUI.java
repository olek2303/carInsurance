package GUI;

import javax.swing.*;
import java.awt.*;

public class MyGUI extends JFrame {
    public MyGUI() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Welcome to the car insurance app");
        JButton button = new JButton("Next");
        frame.setTitle("Car Insurance Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1000,720);
        frame.getContentPane().setBackground(Color.BLACK);

        panel.setBackground(Color.BLACK);


        label.setForeground(Color.WHITE);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));

        button.setBounds(100,500,95,30);
        button.setBackground(Color.WHITE);
        panel.add(label);
        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);
    }
}
