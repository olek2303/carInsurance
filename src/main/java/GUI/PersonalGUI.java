package GUI;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.*;

public class PersonalGUI extends JPanel {
    private JLabel text = new JLabel("Personal Data: ");
    private JTextField name;
    private JTextField surname;
    private JFormattedTextField dateTextField = new JFormattedTextField(new SimpleDateFormat("yyyy.MM.dd"));
    private JFormattedTextField zipCode= new JFormattedTextField(createFormatter("#####"));

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
        add(Box.createRigidArea(new Dimension(1000,50)));
        
    }
}