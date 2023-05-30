package GUI;

import core.Neuron;
import core.NeuronNet;
import core.carData;
import core.carDataConverted;

import javax.swing.*;

public class Result extends JPanel {
    private JLabel res = new JLabel();
    public Result() { // ostania karta GUI, w której przedstawiony jest juz wyliczony przedzial cenowy skladki
        carData c1 = new carData(MyGUI.c);
        carDataConverted cx1 = new carDataConverted(c1);
        NeuronNet.predict(cx1);
    }
}
