package GUI;

import core.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Vector;

public class AdditionalCarInfo extends JPanel {
    private JLabel text = new JLabel("Additional Data About Car:");
    private JToggleButton OCAC = new JToggleButton("Select OCAC (default OC): ");
    private List<JTextField> intCollector = new Vector<>(6);
    private List<JLabel> labels = new Vector<>();
    private JTextField parking = new JTextField();
    private JButton button = new JButton("Confirm");

    public AdditionalCarInfo() {
        setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        text.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        add(OCAC);
        labels.add(new JLabel("Amount of doors: "));
        labels.add(new JLabel("Current counter status: "));
        labels.add(new JLabel("Kilometers in Year: "));
        labels.add(new JLabel("Years of Ownership: "));
        labels.add(new JLabel("Years of OC: "));
        labels.add(new JLabel("Years without accident: "));
        for(JLabel txt : labels) {
            txt.setForeground(Color.WHITE);
            txt.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        }
        for(JTextField i : intCollector) {
            i.setColumns(20);
        }
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
