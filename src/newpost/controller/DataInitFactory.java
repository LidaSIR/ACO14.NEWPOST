package newpost.controller;

import newpost.model.common.*;
import newpost.model.office.Client;
import newpost.model.office.Employee;
import newpost.model.office.PostOffice;
import newpost.model.office.PostTicket;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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

        String[] city = {"Kiyv", "Kharkiv", "Fastiv", "Rivne", "Lviv", "Odesa", "Sumy"};
        String[] street = {"Persha Str", "Druga Str", "Tretya Str", "Chet Str", "Myru Str", "Peremogy Str", "Sadova Str"};

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


        double phoneNumber = (MIN_PHONE_NUMBER + Math.random()* RAND_PHONE_NUMBER);
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

    public static List<PostOffice> postOfficeCreator() {

        List<PostOffice> list = new ArrayList<>();

        list.add(new PostOffice(new Address("Львів", "Вулиця Степана Бандери", "15")));
        list.add(new PostOffice(new Address("Рівне", "Вулиця Соборна", "8")));
        list.add(new PostOffice(new Address("Фастів", "Вулиця Ковпака", "12")));
        list.add(new PostOffice(new Address("Одеса", "Проспект Гагаріна", "8")));
        list.add(new PostOffice(new Address("Луцьк", "Вулиця Коперника", "9")));
        list.add(new PostOffice(new Address("Суми", "Вулиця Черепіна", "4")));
        list.add(new PostOffice(new Address("Харків", "Салтівське шосе", "7")));

        return list;
    }

    public static Employee createEmployee() {
        return new Employee("Manager", createFullName(), createPnoneNumber(), createSalary());
    }

}
