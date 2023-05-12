package GUI;

import core.carData;

import javax.swing.*;

public class Result extends JPanel {
    private JLabel res = new JLabel();
    public Result() {
        carData c;
        if(MyGUI.dataCompleted)
            c = new carData(MyGUI.c);
    }
}
