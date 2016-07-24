package newpost.controller;

import newpost.model.common.*;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by macaque on 10.07.2016.
 */
public class DataInitFactory {

    private static final int MIN_PASSPORT_NUMBER = 100000;
    private static final int RAND_PASSPORT_NUMBER = 800000;
    private static final int RAND_HOUSE_NUM = 55;
    private static final int RAND_SIZE = 100;
    private static final int RAND_PRICE = 1000;
    private static final int RAND_TIME_IN_ROAD = 5;
    public static final int MIN_PHONE_NUMBER = 1000000;
    public static final int RAND_PHONE_NUMBER = 8000000;
    private static int userNumber;

    public static Address createAddress() {

        String[] city = {"Kiyv", "Kharkiv", "Fastiv", "Rivne"};
        String[] street = {"Persha Str", "Druga Str", "Tretya Str", "Chet Str"};

        String randCity = city[(int) (Math.random() * city.length)];
        String randStreet = street[(int) (Math.random() * city.length)];
        String randHouseNum = String.valueOf((int) (Math.random() * RAND_HOUSE_NUM));

        return new Address(randCity, randStreet, randHouseNum);
    }

    public static String createFullName() {


        String[] name = {"Ivan", "Stepan", "Boris", "Gregory", "Anton", "Nikola"};
        String[] surname = {"Ivanov", "Stepanov", "Borisov", "Gregoriev", "Antonov", "Tesla"};

        String randName = name[(int) (Math.random() * name.length)];
        String randSurname = surname[(int) (Math.random() * surname.length)];

        return randName + " " + randSurname;
    }

    public static String createJobTitle() {
        String[] jobTitle = {"Manager", "Driver"};

        String randJobTitle = jobTitle[(int) (Math.random() * jobTitle.length)];

        return randJobTitle;
    }

    public static String createPnoneNumber() {
        String[] operator = {"+38050", "+38063", "+38067", "+38044", "+38093", "+38099"};
        String randOper = operator[(int) (Math.random() * operator.length)];

        int phoneNumber = (int) (MIN_PHONE_NUMBER + (Math.random() * RAND_PHONE_NUMBER));
        return randOper + phoneNumber;
    }

    public static int createSalary() {
        int[] salary = {4000, 4500, 5000, 5500, 6000, 9000};
        int randSalary = salary[(int) (Math.random() * salary.length)];
        return randSalary;
    }

    public static Passport passportCreator() {
        userNumber++;
        int passNumber = (int) (MIN_PASSPORT_NUMBER + Math.random() * RAND_PASSPORT_NUMBER);
        return new Passport("User #" + userNumber, "CM" + String.valueOf(passNumber));

    }

    public static Client clientCreator() {


        double phoneNumber = (double) (MIN_PHONE_NUMBER + Math.random() * RAND_PHONE_NUMBER);
        return new Client("+38050" + String.valueOf(phoneNumber), passportCreator());
    }

    public static Product[] productsCreator() {

        // productName initialization
        String[] productName = {"Phone", "TV", "Microwave", "Fridge"};
        String randProductName = productName[(int) (Math.random() * productName.length)];

        // size initialization
        int length = (int) (Math.random() * RAND_SIZE);
        int width = (int) (Math.random() * RAND_SIZE);
        int height = (int) (Math.random() * RAND_SIZE);
        int weight = (int) (Math.random() * RAND_SIZE);
        Size size = new Size(length, width, height, weight);

        // price initialization

        int price = (int) (Math.random() * RAND_PRICE);

        Product product = new Product(randProductName, size, price, clientCreator());
        return new Product[]{product};
    }

    public static PostTicket ticketCreator(Client client) {


        Calendar calendar = GregorianCalendar.getInstance();
        MyDate currentTime = new MyDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

        int daysInRoad = (int) (Math.random() * RAND_TIME_IN_ROAD);

        currentTime.setDay(currentTime.getDay() + daysInRoad);

        return new PostTicket(client, productsCreator(), createAddress(),
                createAddress(), currentTime, currentTime);
    }



}
