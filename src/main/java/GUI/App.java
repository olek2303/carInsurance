package GUI;

import core.Layer;
import core.Neuron;
import core.NeuronNet;
import core.webScrap;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class App { //klasa uruchamia aplikacje
    public static NeuronNet net;
    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyGUI().frame.setVisible(true);
                Thread newThread = new Thread(() -> {
                    System.out.println("Running neural net and training started....");
                    net = new NeuronNet();
                    Neuron.setRangeWeight(-1,1);
                    net.layers = new Layer[3];
                    net.layers[0] = null;
                    net.layers[1] = new Layer(14,42);
                    net.layers[2] = new Layer(42,6);
                    net.CreateTrainingData();
                    net.train(1000000, 0.001f);
                });
                newThread.start();
            }
        });
    }
}
