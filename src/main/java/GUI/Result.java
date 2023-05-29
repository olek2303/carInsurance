package GUI;

import core.Neuron;
import core.NeuronNet;
import core.carData;
import core.carDataConverted;

import javax.swing.*;

public class Result extends JPanel {
    private JLabel res = new JLabel();
    public Result() {
        carData c1 = new carData(MyGUI.c);
        carDataConverted cx1 = new carDataConverted(c1);
        NeuronNet.predict(cx1);
    }
}
