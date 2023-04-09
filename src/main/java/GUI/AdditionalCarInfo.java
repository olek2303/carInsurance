package GUI;

import core.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AdditionalCarInfo extends JPanel {
    private JLabel text = new JLabel("Additional Data About Car:");
    private JTextField info = new JTextField();

    public AdditionalCarInfo() {
        setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        text.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
    }
}
