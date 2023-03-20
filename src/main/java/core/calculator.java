package core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class calculator {
    public static Date dateOfBirth = null; // drivers date of birth
    public static int zipCode; //owners zip code
    public static String city = null;
    public static boolean maleFemale; // false - male, true - female
    public static byte amountOfDrivers; // amount of drivers which drive owners car
    public static boolean OCAC; // false - OC, ture - OC & AC
    public static String login = null; // drivers login
    public static String carType = null; // type of car for example SUV, Compact, Sedan, Cabrio
    public static String carBrand = null; // for example Citroen, Ford, Audi, BMW
    public static String carModel = null;
    public static String serialNumber = null; // cars serial number
    public static String carGeneration = null; // generation of the car I, II, III
    public static boolean gasDesiel; // false - diesel, true - gas
    public static float engineCapacity; // engine capacity - for example 1.1, 2.0, 3.0, 2.8
    public static byte amountOfDoors; // amount of door in the car - 3,5
    public static int counterStatus; // current counter status in the car
    public static int kmInYear; // how many kilometers the client will drive in a year
    public static String wherePark = null; // where the client will park his/her car (outdoor, garage)
    public static int yearOfOwnership; // when the client owned the car (year)
    public static int yearsClientOC; // how many years client buys OC
    public static int yearOfAccident; // when the owner had an accident

    public static boolean validateLogin(String l) {
        if (l.length() >= 4) {
            login = l;
            System.out.println("Hello " + login);
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateDate(String z) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateOfBirth = df.parse(z);
        } catch (ParseException e) {
            dateOfBirth = null;
            return false;
        }
        return true;
    }

    public static boolean validateZipCode(String z) {
        int zip;
        try {
            zip = Integer.parseInt(z);
        } catch (NumberFormatException e) {
            return false;
        }
        zipCode = zip;
        return true;
    }
}
