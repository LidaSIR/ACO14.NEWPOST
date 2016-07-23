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

    public static final int MIN_PASSPORT_NUMBER = 100000;
    public static final int RAND_PASSPORT_NUMBER = 800000;
    public static final int RAND_HOUSE_NUM = 55;
    private static int userNumber;

    public static Address createAddress() {

        String[] city = {"Kiyv", "Kharkiv", "Fastiv", "Rivne"};
        String[] street = {"Persha Str", "Druga Str", "Tretya Str", "Chet Str"};

        String randCity = city[(int) (Math.random() * city.length)];
        String randStreet = street[(int) (Math.random() * city.length)];
        String randHouseNum = String.valueOf((int) (Math.random() * RAND_HOUSE_NUM));

        return new Address(randCity, randStreet, randHouseNum);
    }

    public static Passport passportCreator() {
        userNumber++;
        int passNumber = (int) (MIN_PASSPORT_NUMBER + Math.random() * RAND_PASSPORT_NUMBER);
        return new Passport("User #" + userNumber, "CM" + String.valueOf(passNumber));

    }

    public static Client clientCreator() {

        return new Client("+380501535433", passportCreator());
    }

    public static Product[] productsCreator() {

        // productName initialization
        String[] productName = {"Phone", "TV", "Microwave", "Fridge"};
        String randProductName = productName[(int) (Math.random() * productName.length)];

        // size initialization
        int length = (int) (Math.random() * 100);
        int width = (int) (Math.random() * 100);
        int height = (int) (Math.random() * 100);
        int weight = (int) (Math.random() * 100);
        Size size = new Size(length, width, height, weight);

        // price initialization

        int price = (int) (Math.random() * 1000);

        Product product = new Product(randProductName, size, price, clientCreator());
        return new Product[]{product};
    }

    public static PostTicket ticketCreator(Client client) {


        Calendar calendar = GregorianCalendar.getInstance();
        MyDate currentTime = new MyDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

        int daysInRoad = (int) (Math.random() * 5);

        currentTime.setDay(currentTime.getDay() + daysInRoad);

        return new PostTicket(client, productsCreator(), createAddress(),
                createAddress(), currentTime, currentTime);
    }
}
