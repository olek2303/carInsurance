package GUI;

import core.calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormGUI extends JFrame{
    public FormGUI() {
        setTitle("Car Insurance Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1000,720);
        getContentPane().setBackground(Color.BLACK);

        JLabel text = new JLabel("Please enter your login to continue");
        JPanel panel = new JPanel();
        JTextField login = new JTextField(20);
        JButton button = new JButton("Confirm");

        panel.setBackground(Color.BLACK);
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        text.setForeground(Color.WHITE);
        text.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));

        button.setBounds(100,500,95,30);
        button.setBackground(Color.GREEN);

        panel.add(Box.createRigidArea(new Dimension(1000,100)));
        panel.add(text, BorderLayout.CENTER);
        panel.add(Box.createRigidArea(new Dimension(1000,5)));
        panel.add(login, BorderLayout.CENTER);
        panel.add(Box.createRigidArea(new Dimension(1000,5)));
        panel.add(button, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(panel);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String loginGet = login.getText();
                login.setText(null);
                if(calculator.validateLogin(loginGet)) {
                    System.out.println("Login correct");
                    setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(getComponent(0), "Your login has to have more than 3 letters. Try again.");
                }
            }
        });

    }
}
