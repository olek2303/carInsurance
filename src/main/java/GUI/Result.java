package GUI;

import core.Neuron;
import core.NeuronNet;
import core.carData;
import core.carDataConverted;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.BOLD;
import static java.awt.Font.SANS_SERIF;

public class Result extends JPanel {
    private JLabel res;
    private JButton end = new JButton("End Program");
    public Result() { // ostania karta GUI, w której przedstawiony jest juz wyliczony przedzial cenowy skladki
        carData c1 = new carData(MyGUI.c);
        carDataConverted cx1 = new carDataConverted(c1);
        int countResult = App.net.count(cx1);
        setBackground(Color.BLACK);
        switch(countResult) {
            case 0:
                res = new JLabel("Price is in bounds of (0, 900)");
                break;
            case 1:
                res = new JLabel("Price is in bounds of (900, 1400)");
                break;
            case 2:
                res = new JLabel("Price is in bounds of (1400, 1800)");
                break;
            case 3:
                res = new JLabel("Price is in bounds of (1800,2000)");
                break;
            case 4:
                res = new JLabel("Price is in bounds of (2000, 2500)");
                break;
            case 5:
                res = new JLabel("Price is in bounds of (2500,3000)");
                break;
        }
        res.setForeground(Color.WHITE);
        res.setFont(new Font(SANS_SERIF, BOLD, 32));
        add(res);
        end.setPreferredSize(new Dimension(150,30));
        end.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        end.setBackground(Color.RED);

        add(Box.createRigidArea(new Dimension(1000,10)));
        add(end);

        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MyGUI.frame.isDisplayable()) {
                    MyGUI.frame.dispose();
                }
            }
        });
    }
}
