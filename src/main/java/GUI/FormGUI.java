package GUI;

import  core.calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormGUI extends JPanel{
    public FormGUI() {
        setForeground(Color.BLACK);
        JLabel text = new JLabel("Data about Car:");
        text.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
    }
}
