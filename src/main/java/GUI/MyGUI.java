package GUI;

import core.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyGUI extends JFrame {
    public static FormGUI form = new FormGUI();
    public static PersonalGUI form1 = new PersonalGUI();
    public MyGUI() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Welcome to the car insurance app");
        JLabel text = new JLabel("Please enter your login to continue");
        JTextField login = new JTextField(20);
        JButton button = new JButton("Confirm");

        setTitle("Car Insurance Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1000,720);
        getContentPane().setBackground(Color.BLACK);

        panel.setBackground(Color.BLACK);
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        text.setForeground(Color.WHITE);
        text.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));

        button.setPreferredSize(new Dimension(150,30));
        button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        button.setBackground(Color.GREEN);

        label.setForeground(Color.WHITE);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        panel.add(Box.createRigidArea(new Dimension(1000,100)));
        panel.add(label, BorderLayout.CENTER);
        panel.add(Box.createRigidArea(new Dimension(1000,50)));
        panel.add(text, BorderLayout.CENTER);
        panel.add(Box.createRigidArea(new Dimension(1000,5)));
        panel.add(login, BorderLayout.CENTER);
        panel.add(Box.createRigidArea(new Dimension(1000,5)));
        panel.add(button, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.add(Box.createRigidArea(new Dimension(1000,10)));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(panel);

        login.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_ENTER) {
                    button.doClick();
                }
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String loginGet = login.getText();
                login.setText(null);
                if(calculator.validateLogin(loginGet)) {
                    System.out.println("Login correct");
                    panel.setVisible(false);
                    form.setVisible(true);
                    add(form);
                }
                else {
                    JOptionPane.showMessageDialog(getComponent(0), "Your login has to have more than 3 letters. Try again.");
                }
            }
        });
    }
}
