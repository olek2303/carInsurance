package GUI;

import core.calculator;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static java.awt.Font.*;

public class AdditionalCarInfo extends JPanel {
    private NumberFormat format = NumberFormat.getInstance();
    private NumberFormatter formatter = new NumberFormatter(format);
    JFormattedTextField field = new JFormattedTextField(formatter);
    private JLabel text = new JLabel("Additional Data About Car:");
    private JToggleButton OCAC = new JToggleButton("Select OCAC (default OC): ");
    private List<JFormattedTextField> intCollector = new Vector<>();
    private List<JLabel> labels = new Vector<>();
    private JTextField parking = new JTextField();
    private JButton button = new JButton("Confirm");

    public AdditionalCarInfo() {
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

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
            intCollector.add(new JFormattedTextField(formatter));
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
                ArrayList<Integer> coll = new ArrayList<>();
                /*  0 - amount of doors, 1 - counter status, 2 - km in year
                    3 - years of ownership, 4 - years of oc, 5 - years without accident  */
                for (JFormattedTextField i : intCollector) {
                    int x;
                    if(i.getText().contains(" ")) {
                        String y = i.getText();
                        y = y.replaceAll(" ", "");
                        x = Integer.parseInt(y);
                    }

                    else
                        x = Integer.parseInt(i.getText());
                    coll.add(x);
                }
                boolean oc = OCAC.isSelected(); // true - oc & ac, false - only oc
                calculator.amountOfDoors = coll.get(0);
                calculator.counterStatus = coll.get(1);
                calculator.kmInYear = coll.get(2);
                calculator.yearOfOwnership = coll.get(3);
                calculator.yearsClientOC = coll.get(4);
                calculator.yearOfOwnership = coll.get(5);
            }
        });
    }
}
