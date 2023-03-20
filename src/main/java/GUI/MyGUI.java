package GUI;

import core.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyGUI {
    public static FormGUI form = new FormGUI();
    public static PersonalGUI form1 = new PersonalGUI();
    public static JFrame frame = new JFrame();
    public static JPanel panel = new JPanel();

    public MyGUI() {
        form.setVisible(false);
        form1.setVisible(false);
        JLabel label = new JLabel("Welcome to the car insurance app");
        JLabel text = new JLabel("Please enter your login to continue");
        JTextField login = new JTextField(20);
        JButton button = new JButton("Confirm");

        frame.setTitle("Car Insurance Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1000,720);
        frame.getContentPane().setBackground(Color.BLACK);

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

        frame.add(panel);

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
                    frame.add(form);
                }
                else {
                    JOptionPane.showMessageDialog(frame.getComponent(0), "Your login has to have more than 3 letters. Try again.");
                }
            }
        });
    }
}
