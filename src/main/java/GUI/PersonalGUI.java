package GUI;

import core.calculator;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class PersonalGUI extends JPanel {
    private JLabel text = new JLabel("Personal Data: ");
    private JTextField name = new JTextField(20);
    private JTextField surname = new JTextField(20);
    private JFormattedTextField dateTextField = new JFormattedTextField(new SimpleDateFormat("yyyy.MM.dd"));
    private JFormattedTextField zipCode= new JFormattedTextField(createFormatter("#####"));
    private JButton button = new JButton("Confirm");
    private JTextField cityField = new JTextField(20);

    protected MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
            int zip = Integer.parseInt(s);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException nfe) {
            return null;
        }
        return formatter;
    }

    public PersonalGUI() {
        setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        text.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));

        List<JLabel> textList = new ArrayList<JLabel>();
        textList.add(new JLabel("Name: "));
        textList.add(new JLabel("Surname: "));
        textList.add(new JLabel("Date of birth (yyyy.MM.dd): "));
        textList.add(new JLabel("Zip Code: "));
        textList.add(new JLabel("City: "));

        for(JLabel txt : textList) {
            txt.setForeground(Color.WHITE);
            txt.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        }

        button.setPreferredSize(new Dimension(150,30));
        button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        button.setBackground(Color.GREEN);

        dateTextField.setColumns(20);
        zipCode.setColumns(20);


        add(text);
        add(Box.createRigidArea(new Dimension(1000,50)));
        add(textList.get(0));
        add(name);
        add(Box.createRigidArea(new Dimension(1000,10)));
        add(textList.get(1));
        add(surname);
        add(Box.createRigidArea(new Dimension(1000,10)));
        add(textList.get(2));
        add(dateTextField);
        add(Box.createRigidArea(new Dimension(1000,10)));
        add(textList.get(3));
        add(zipCode);
        add(Box.createRigidArea(new Dimension(1000,10)));
        add(textList.get(4));
        add(cityField);
        add(Box.createRigidArea(new Dimension(1000,10)));
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String z = dateTextField.getText();
                if (z.length() > 0) {
                    System.out.println("Alles klar with date " + z);
                    calculator.dateOfBirth = dateTextField.getText();
                }
            }
        });
    }
}