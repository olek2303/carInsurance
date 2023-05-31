package GUI;

import core.calculator;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import core.webScrap;

public class FormGUI extends JPanel { //klasa zawiera w sobie elementy z biblioteki javax swing
    private JLabel text = new JLabel("Data about Car:"); //naglowek formularza zbierjacego informacje o samochodzie
    private List<JComboBox> boxes = new Vector<JComboBox>(); //pola do przechwytywania danych
    private List<JLabel> texts = new Vector<JLabel>(); //znaczniki do pol sluzacych do przechwytywania danych
    private JButton button = new JButton("Confirm"); //przycisk potwierdzajacy

    public FormGUI() {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);


        Vector<String> brands;
        try {
             brands = webScrap.getBrandsModel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String engines[] = new String[] { "2.0", "1.1", "1.8", "2.5" };

        setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        text.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        add(Box.createRigidArea(new Dimension(1000,50)));

        boxes.add(new JComboBox(brands));
        boxes.add(new JComboBox()); //models
        DefaultComboBoxModel cmbxModel = (DefaultComboBoxModel) boxes.get(1).getModel();
        boxes.add(new JComboBox()); //generations
        DefaultComboBoxModel cmbxGen = (DefaultComboBoxModel) boxes.get(2).getModel();
        boxes.add(new JComboBox()); //types
        DefaultComboBoxModel cmbxType = (DefaultComboBoxModel) boxes.get(3).getModel();
        boxes.add(new JComboBox()); //engines
        DefaultComboBoxModel cmbxEng = (DefaultComboBoxModel) boxes.get(4).getModel();

        JTextField prod = new JTextField();

        texts.add(new JLabel("Select brand: "));
        texts.add(new JLabel("Select model: "));
        texts.add(new JLabel("Select generation: "));
        texts.add(new JLabel("Select car type: "));
        texts.add(new JLabel("Select engine cm3: "));
        texts.add(new JLabel("Put car production year: "));

        for(JLabel txt : texts) {
            txt.setForeground(Color.WHITE);
            txt.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        }
        for (JComboBox box : boxes) {
            box.setSelectedIndex(-1);
            box.setPreferredSize(new Dimension(200,20));
        }
        prod.setPreferredSize(new Dimension(200,20));

        button.setPreferredSize(new Dimension(150,30));
        button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        button.setBackground(Color.GREEN);

        add(text, BorderLayout.CENTER);
        for (int i = 0; i < boxes.size(); i++) {
            add(Box.createRigidArea(new Dimension(1000,10)));
            add(texts.get(i));
            add(boxes.get(i), BorderLayout.EAST);
        }
        add(Box.createRigidArea(new Dimension(1000,10)));
        add(texts.get(texts.size()-1));
        add(prod);
        add(Box.createRigidArea(new Dimension(1000,5)));
        add(button, BorderLayout.EAST);

        prod.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = prod.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    prod.setEditable(true);
                } else {
                    prod.setEditable(false);
                    prod.setText("");
                }
            }
        });

        boxes.get(0).addActionListener(new ActionListener() { //pobieranie danych dot. modelu wybranego samochodu ze strony https://auto-centrum.pl/
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

        boxes.get(1).addActionListener(new ActionListener() { //pobieranie danych dot. typow samochodu danego modelu ze strony https://auto-centrum.pl/
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosenModel = (String) boxes.get(1).getSelectedItem();
                System.out.println(chosenModel);
                try {
                    boxes.get(2).removeAllItems();
                    Vector<String> types = webScrap.getGenerations((String) boxes.get(0).getSelectedItem(), chosenModel);
                    cmbxGen.addAll(types);
                    if (types.size() < 1)
                        cmbxGen.addElement("null");
                    boxes.get(2).setModel(cmbxGen);
                } catch (IOException f) {
                    throw new RuntimeException(f);
                }
            }
        });

        boxes.get(2).addActionListener(new ActionListener() { //pobieranie danych dot. silnika danego modelu ze strony https://auto-centrum.pl/
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosenModel = (String) boxes.get(1).getSelectedItem();
                System.out.println(chosenModel);
                try {
                    boxes.get(3).removeAllItems();
                    Vector<String> gases = webScrap.getType((String) boxes.get(0).getSelectedItem(), chosenModel, (String)boxes.get(2).getSelectedItem());
                    cmbxType.addAll(gases);
                    if (gases.size() < 1)
                        cmbxType.addElement("null");
                    boxes.get(3).setModel(cmbxType);
                } catch (IOException f) {
                    throw new RuntimeException(f);
                }
            }
        });

        boxes.get(3).addActionListener(new ActionListener() { //pobieranie danych dot. silnika ze strony https://auto-centrum.pl/
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosenModel = (String) boxes.get(1).getSelectedItem();
                System.out.println(chosenModel);
                try {
                    boxes.get(4).removeAllItems();
                    Vector<String> eng = webScrap.getEngines((String) boxes.get(0).getSelectedItem(),
                            chosenModel,
                            (String)boxes.get(3).getSelectedItem(),
                            (String)boxes.get(2).getSelectedItem());
                    cmbxEng.addAll(eng);
                    if(eng.size() < 1)
                        cmbxEng.addElement("null");
                    boxes.get(4).setModel(cmbxEng);
                } catch (IOException f) {
                    throw new RuntimeException(f);
                }
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean completed = true;
                boolean validProd = true;
                ArrayList<String> data = new ArrayList<String>(); // lista do zebrania danych z pol typu JComboBox
                for(JComboBox box : boxes) { //sprawdzanie poprawnosci wybranych konfiguracji
                    if(box.getSelectedIndex() == -1) {
                        completed = false;
                        JOptionPane.showMessageDialog(getComponent(0), "You didn't choose an option \n Do it now.");
                        break;
                    } else {
                        data.add((String) box.getSelectedItem());
                    }
                }
                String yearProd = prod.getText();
                for(int i = 0; i < yearProd.length(); i++) {
                    if(!Character.isDigit(yearProd.charAt(i))) {
                        JOptionPane.showMessageDialog(getComponent(0), "Paste only numbers");
                        prod.setText("");
                        validProd = false;
                        break;
                    }
                }
                if (completed && validProd) { // przekierowywanie podanych informacji do pliku ze wszystkimi danymi
                    MyGUI.c.carBrand = data.get(0);
                    MyGUI.c.carModel = data.get(1);
                    MyGUI.c.carGeneration = data.get(2);
                    MyGUI.c.carType = data.get(3);
                    System.out.println("YEAR PROD: " + yearProd);
                    MyGUI.c.carProdYear = yearProd;
                    String s = data.get(4);
                    if(s != null) {
                        s = s.replaceAll("\\s.*", "");
                        calculator.engineCapacity = Float.parseFloat(s);
                    } else
                        calculator.engineCapacity = 0.0F;
                    System.out.println("Added data about car to core.calculator " + data.get(0) + data.get(1) + data.get(2) + data.get(3) + s);
                    setVisible(false);
                    MyGUI.form1.setVisible(true);
                    MyGUI.frame.add(MyGUI.form1);
                }
            }
        });

    }
}
