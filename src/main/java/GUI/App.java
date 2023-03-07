package GUI;

import java.awt.*;

public class App {
    public static void main(String[] rgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyGUI();
            }
        });
    }
}
