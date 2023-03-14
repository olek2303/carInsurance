package GUI;

import core.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FormGUI extends JPanel{
    private JLabel text = new JLabel("Data about Car:");
    private List<JComboBox> boxes = new ArrayList<JComboBox>();
    private List<JLabel> texts = new ArrayList<JLabel>();
    private JButton button = new JButton("Confirm");
    public FormGUI() {
        String brands[] = new String[] { "Audi", "BMW", "Alfa-Romeo" };
        String models[] = new String[] { "A5", "Seria 3", "Guilietta" };
        String engines[] = new String[] { "2.0", "1.1", "1.8", "2.5" };
        setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        text.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        add(Box.createRigidArea(new Dimension(1000,50)));

        boxes.add(new JComboBox(brands));
        boxes.add(new JComboBox(models));
        boxes.add(new JComboBox(engines));
        texts.add(new JLabel("Select brand: "));
        texts.add(new JLabel("Select model: "));
        texts.add(new JLabel("Select engine: "));

        for(JLabel txt : texts) {
            txt.setForeground(Color.WHITE);
            txt.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        }
        for (JComboBox box : boxes) {
            box.setSelectedIndex(-1);
            box.setPreferredSize(new Dimension(200,20));
        }

        button.setPreferredSize(new Dimension(150,30));
        button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        button.setBackground(Color.GREEN);

        add(text, BorderLayout.CENTER);
        add(Box.createRigidArea(new Dimension(1000,10)));
        add(texts.get(0));
        add(boxes.get(0), BorderLayout.EAST);
        add(Box.createRigidArea(new Dimension(1000,5)));
        add(texts.get(1));
        add(boxes.get(1), BorderLayout.EAST);
        add(Box.createRigidArea(new Dimension(1000,5)));
        add(texts.get(2));
        add(boxes.get(2), BorderLayout.EAST);
        add(Box.createRigidArea(new Dimension(1000,5)));
        add(button, BorderLayout.EAST);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(JComboBox box : boxes) {
                    if(box.getSelectedIndex() == -1) {
                        JOptionPane.showMessageDialog(getComponent(0), "You didn't choose an option \n Do it now.");
                        break;
                    }
                }
            }
        });

    }
}
