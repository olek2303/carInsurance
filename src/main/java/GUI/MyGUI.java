package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyGUI extends JFrame {
    public MyGUI() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Welcome to the car insurance app");
        JButton button = new JButton("Let's Start");
        setTitle("Car Insurance Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1000,720);
        getContentPane().setBackground(Color.BLACK);

        panel.setBackground(Color.BLACK);
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        label.setForeground(Color.WHITE);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));

        button.setBounds(100,500,95,30);
        button.setBackground(Color.WHITE);
        panel.add(Box.createRigidArea(new Dimension(1000,100)));
        panel.add(label, BorderLayout.CENTER);
        panel.add(Box.createRigidArea(new Dimension(1000,10)));
        panel.add(button, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(panel);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(getComponent(0), "Welcome to the car insurance calculator. Enter your login.");
                new FormGUI().setVisible(true);
                setVisible(false);
            }
        });
    }
}
