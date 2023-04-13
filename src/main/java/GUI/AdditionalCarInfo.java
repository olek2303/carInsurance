package GUI;

import core.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static java.awt.Font.*;

public class AdditionalCarInfo extends JPanel {
    private JLabel text = new JLabel("Additional Data About Car:");
    private JToggleButton OCAC = new JToggleButton("Select OCAC (default OC): ");
    private List<JTextField> intCollector = new Vector<>();
    private List<JLabel> labels = new Vector<>();
    private JTextField parking = new JTextField();
    private JButton button = new JButton("Confirm");

    public AdditionalCarInfo() {
        setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        text.setFont(new Font(SANS_SERIF, BOLD, 32));
        labels.add(new JLabel("Amount of doors: "));
        labels.add(new JLabel("Current counter status: "));
        labels.add(new JLabel("Kilometers in Year: "));
        labels.add(new JLabel("Years of Ownership: "));
        labels.add(new JLabel("Years of OC: "));
        labels.add(new JLabel("Years without accident: "));
        for(int i = 0; i < labels.size(); i++) {
            intCollector.add(new JTextField());
        }
        JLabel text = new JLabel("Additional Data About Car");
        text.setForeground(Color.WHITE);
        text.setFont(new Font(SANS_SERIF, BOLD, 32));
        add(Box.createRigidArea(new Dimension(1000,10)));
        add(text);
        add(Box.createRigidArea(new Dimension(1000,10)));
        add(OCAC);
        for(JLabel txt : labels) {
            txt.setForeground(Color.WHITE);
            txt.setFont(new Font(MONOSPACED, PLAIN, 16));
        }
        for(JTextField i : intCollector) {
            i.setColumns(20);
        }
        for(int i = 0; i < labels.size(); i++) {
            add(Box.createRigidArea(new Dimension(1000,10)));
            add(labels.get(i));
            add(intCollector.get(i));
        }

        button.setPreferredSize(new Dimension(150,30));
        button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        button.setBackground(Color.GREEN);

        add(Box.createRigidArea(new Dimension(1000,10)));
        add(button);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> coll = new ArrayList<>();
                /*  0 - amount of doors, 1 - counter status, 2 - km in year
                    3 - years of ownership, 4 - years of oc, 5 - years without accident  */
                for (JTextField i : intCollector) {
                    coll.add(i.getText());
                }
                boolean oc = OCAC.isSelected(); // true - oc & ac, false - only oc
            }
        });
    }
}
