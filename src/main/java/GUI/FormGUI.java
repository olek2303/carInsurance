package GUI;

import core.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import core.webScrap;

public class FormGUI extends JPanel {
    private JLabel text = new JLabel("Data about Car:");
    private List<JComboBox> boxes = new Vector<JComboBox>();
    private List<JLabel> texts = new Vector<JLabel>();
    private JButton button = new JButton("Confirm");

    public FormGUI() {
        Vector<String> brands;
        Vector<String> models;
        try {
             brands = webScrap.getBrandsModel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String m[] = new String[] { "firstly choose a brand" };
        String engines[] = new String[] { "2.0", "1.1", "1.8", "2.5" };
        String types[] = new String[] { "hatchback", "sedan", "kombi", "SUV", "cabrio" };

        setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        text.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        add(Box.createRigidArea(new Dimension(1000,50)));

        boxes.add(new JComboBox(brands));
        boxes.add(new JComboBox(m));
        DefaultComboBoxModel cmbxModel = (DefaultComboBoxModel) boxes.get(1).getModel();
        boxes.add(new JComboBox(engines));
        boxes.add(new JComboBox(types));
        texts.add(new JLabel("Select brand: "));
        texts.add(new JLabel("Select model: "));
        texts.add(new JLabel("Select engine cm3: "));
        texts.add(new JLabel("Select car type: "));

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
        add(texts.get(3));
        add(boxes.get(3), BorderLayout.EAST);
        add(Box.createRigidArea(new Dimension(1000,5)));
        add(button, BorderLayout.EAST);

        boxes.get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String chosenBrand = (String) boxes.get(0).getSelectedItem();
                System.out.println(chosenBrand);
                try {
                    boxes.get(1).removeAllItems();
                    Vector<String> models = webScrap.getModels(chosenBrand);
                    cmbxModel.addAll(models);
                    boxes.get(1).setModel(cmbxModel);
                } catch (IOException f) {
                    throw new RuntimeException(f);
                }
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean completed = true;
                ArrayList<String> data = new ArrayList<String>();
                for(JComboBox box : boxes) {
                    if(box.getSelectedIndex() == -1) {
                        completed = false;
                        JOptionPane.showMessageDialog(getComponent(0), "You didn't choose an option \n Do it now.");
                        break;
                    } else {
                        data.add((String) box.getSelectedItem());
                    }
                }
                if (completed) {
                    calculator.carBrand = data.get(0);
                    calculator.carModel = data.get(1);
                    calculator.engineCapacity = Float.parseFloat(data.get(2));
                    calculator.carType = data.get(3);
                    System.out.println("Added data about car to core.calculator " + data.get(0) + data.get(1) + data.get(2) + data.get(3));
                    setVisible(false);
                    MyGUI.form1.setVisible(true);
                    MyGUI.frame.add(MyGUI.form1);
                }
            }
        });

    }
}
