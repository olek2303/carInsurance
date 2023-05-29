package GUI;

import core.webScrap;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class App { //klasa uruchamia aplikacje
    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyGUI().frame.setVisible(true);
            }
        });
    }
}
