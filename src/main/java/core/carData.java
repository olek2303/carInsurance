package core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Date;
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
            put("suv", 1.7);
            put("hatchback", 1.1);
            put("sedan", 1.6);
            put("kombi", 1.3);
            put("cabrio", 1.5);
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
                        carBrand = "20.0";
                    else if (Objects.equals(carBrand, "volvo") || Objects.equals(carBrand, "alfa-romeo") || Objects.equals(carBrand, "mini"))
                        carBrand = "17.0";
                    else
                        carBrand = "15.0";
                }
                case "carModel" -> {
                    if (engineCap > 3.0) carModel = "15.0";
                    else carModel = "12.0";
                }
                case "carType" -> {
                    for (Map.Entry<String, Double> t : cType.entrySet()) {
                        if (t.getKey().equals(carType))
                            carType = Double.toString(t.getValue());
                        else carType = "11.0";
                    }
                }
                case "carGen" -> {
                    if(carProdYear > 2000) carGen = "13.0";
                    else if (carProdYear > 2010) carGen = "14.0";
                    else if (carProdYear > 2015) carGen = "15.0";
                    else if (carProdYear > 2020) carGen = "17.0";
                    else carGen = "12.0";
                }
                case "wherePark" -> {
                    if (wherePark == "indoor")
                        wherePark = "11.0";
                    else wherePark = "14.0";
                }
            }
        }
        carProdYear /= 100;
        kmInYear /= 100;
        if(counterStatus < 10000) counterStatus /= 100;
        else counterStatus /= 1000;
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
        Date d = new Date();
        c.dateOfBirth = c.dateOfBirth.split("-")[0];
        int year = d.getYear();

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
