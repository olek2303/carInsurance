package core;

import GUI.MyGUI;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class carData {
    public static float age;
    public static float amountOfDrivers;
    public static float ocac;
    public static String carType = null; // suv, hatchback, sedan, kombi
    public static String carBrand = null; // audi, bmw, citroen, skoda
    public static String carModel = null; //a5, seria 5, c3
    public static String carGen = null; // 1-gen, 2-gen
    public static float carProdYear; // 2012, 2006
    public static float engineCap; // 2.0, 1.2, 5.0
    public static float counterStatus; // 200 000, 150 000, 70 000
    public static float kmInYear; // 20 000, 30 000
    public static String wherePark; // indoor outdoor
    public static float yearsOfOC; // 5
    public static float yearsOfLastAccident; // 4

    public static void main(String[] args) {
        carData c = new carData(MyGUI.c);
    }

    public static int countAttr(carData c) {
        Class<?> x = c.getClass();
        Field[] fields = x.getDeclaredFields();
        int counter = fields.length - 1;
        return counter;
    }

    public static void presentAttr(carData c) {
        Field[] fields = c.getClass().getDeclaredFields();
        System.out.printf("%d fields:%n", fields.length);
        for (Field field : fields) {
            try {
                System.out.printf("%s %s %s %s%n",
                        Modifier.toString(field.getModifiers()),
                        field.getType().getSimpleName(),
                        field.getName(), field.get(c)
                );
            } catch (IllegalArgumentException e1) {
            } catch (IllegalAccessException e1) {
            }
        }
    }

    public static void changeStrings(carData c) {
        Class<?> x = c.getClass();
        Field[] fields = x.getDeclaredFields();
        Map<String, Object> temp = new HashMap<String, Object>();
        for( Field field : fields ){
            try {
                temp.put(field.getName().toString(), field.get(c));
            } catch (IllegalArgumentException e1) {
            } catch (IllegalAccessException e1) {
            }
        }
        Map<String, Double> cType = new HashMap<>() {{
            put("suv", 0.9);
            put("hatchback", 0.2);
            put("sedan", 0.8);
            put("kombi", 0.4);
            put("cabrio", 0.6);
        }};
        TreeMap<String, Integer> cGen = new TreeMap<>(){{
            put("X", 10);
            put("IX", 9);
            put("V", 5);
            put("IV", 4);
            put("I", 1);
        }};

        for(Field f : fields) {
            switch (f.getName()) {
                case "carBrand" -> {
                    if (Objects.equals(carBrand, "audi") || Objects.equals(carBrand, "bmw") || Objects.equals(carBrand, "porsche") ||
                            Objects.equals(carBrand, "land-rover") || Objects.equals(carBrand, "mercedes") ||
                            Objects.equals(carBrand, "lexus") || Objects.equals(carBrand, "tesla") || Objects.equals(carBrand, "jaguar"))
                        carBrand = "0.9";
                    else if (Objects.equals(carBrand, "volvo") || Objects.equals(carBrand, "alfa-romeo") || Objects.equals(carBrand, "mini"))
                        carBrand = "0.7";
                    else
                        carBrand = "0.5";
                }
                case "carModel" -> {
                    if (engineCap > 3.0) carModel = "0.9";
                    else carModel = "0.4";
                }
                case "carType" -> {
                    for (Map.Entry<String, Double> t : cType.entrySet()) {
                        if (t.getKey().equals(carType))
                            carType = Double.toString(t.getValue());
                        else carType = "0.3";
                    }
                }
                case "carGen" -> {
                    if(carProdYear > 2000 && carProdYear <= 2010) carGen = "0.3";
                    else if (carProdYear > 2010 && carProdYear <= 2015) carGen = "0.5";
                    else if (carProdYear > 2015 && carProdYear <= 2020) carGen = "0.7";
                    else if (carProdYear > 2020) carGen = "0.9";
                    else carGen = "0.2";
                }
                case "wherePark" -> {
                    if (wherePark == "indoor")
                        wherePark = "0.5";
                    else wherePark = "0.9";
                }
            }
        }
        if(yearsOfOC < 10) yearsOfOC /= 10;
        else yearsOfOC /= 100;
        if(yearsOfLastAccident < 10) yearsOfLastAccident /= 10;
        else yearsOfLastAccident /= 100;
        amountOfDrivers /= 10;
        age /= 100;
        engineCap /= 10.0;
        carProdYear /= 10000;
        if (kmInYear < 10000) kmInYear /= 10000;
        else kmInYear /= 100000;
        if(counterStatus < 10000) counterStatus /= 100000;
        else counterStatus /= 1000000;
        //presentAttr(c);
    }

    public carData (float a, float drivers, float oc, String carT, String carB, String carM, String carG, float carProd,
                    float engine, float counter, float kmY, String whereP, float yearsOc, float yearsOfAcc) {
        this.age = a;
        this.amountOfDrivers = drivers;
        this.ocac = oc;
        this.carType = carT;
        this.carBrand = carB;
        this.carModel = carM;
        this.carGen = carG;
        this.carProdYear = carProd;
        this.engineCap = engine;
        this.counterStatus = counter;
        this.kmInYear = kmY;
        this.wherePark = whereP;
        this.yearsOfOC = yearsOc;
        this.yearsOfLastAccident = yearsOfAcc;
    }

    public carData(carData c) {
        this.equal(c);
    }

    public void equal (carData obj) {
        this.age = obj.age;
        this.amountOfDrivers = obj.amountOfDrivers;
        this.ocac = obj.ocac;
        this.carType = obj.carType;
        this.carBrand = obj.carBrand;
        this.carModel = obj.carModel;
        this.carGen = obj.carGen;
        this.carProdYear = obj.carProdYear;
        this.engineCap = obj.engineCap;
        this.counterStatus = obj.counterStatus;
        this.kmInYear = obj.kmInYear;
        this.wherePark = obj.wherePark;
        this.yearsOfOC = obj.yearsOfOC;
        this.yearsOfLastAccident = obj.yearsOfLastAccident;
    }

    public carData(calculator c) {
        c.dateOfBirth = c.dateOfBirth.split(".")[0];
        int year = 2023;

        System.out.println("Year: " + year + " date of birth: " + c.dateOfBirth + " division: ");

        this.age = Float.parseFloat(c.dateOfBirth) - (float) year;
        this.amountOfDrivers = c.amountOfDrivers;
        if(c.OCAC)
            this.ocac = 1.0f;
        else
            this.ocac = 0.0f;
        this.carType = c.carType;
        this.carBrand = c.carBrand;
        this.carModel = c.carModel;
        this.carGen = c.carGeneration;
        this.carProdYear = Float.parseFloat(c.carProdYear);
        this.engineCap = c.engineCapacity;
        this.counterStatus = c.counterStatus;
        this.kmInYear = c.kmInYear;
        this.wherePark = c.wherePark;
        this.yearsOfOC = c.yearsClientOC;
        this.yearsOfLastAccident = c.yearOfAccident;
    }
}
