package newpost.test.utils;



import newpost.model.common.*;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.validator.Validator;


/**
 * Created by Agryzkov on 13.07.2016.
 */
public class TestValidator {

    public static void main(String[] args) {

        Validator validator = new Validator();

        testPositiveValidateAdress(validator);
        testPositiveValidateClient(validator);
        testPositiveValidateProducte(validator);
        testPositiveValidatePostTicket(validator);

        testNegativeValidateAdressBadCity(validator);
        testNegativeValidateAdressBadStreet(validator);
        testNegativeValidateAdressBadHouseNum(validator);

        testNegativeValidateClientBadFullName(validator);
        testNegativeValidateClientBadPassportNum(validator);
        testNegativeValidateClientBadPhone(validator);

        testNegativeValidateProductBadSize(validator);
        testNegativeValidateProductBadName(validator);
        testNegativeValidateProductBadPrise(validator);
        testNegativeValidateProductBadClient(validator);

        testNegativeValidatePostTicketBadTo(validator);
        testNegativeValidatePostTicketBadFrom(validator);
        testNegativeValidatePostTicketBadProduct(validator);
        testNegativeValidatePostTicketBadClient(validator);
        testNegativeValidatePostTicketBadProducts(validator);

    }

    // test negative postTicket bad client
    public static void testNegativeValidatePostTicketBadClient(Validator validator) {

        Address from = new Address("kiev", "starokievskaya", "10");

        Address to = new Address("poltava", "stepnaya", "12");

        Passport passport = new Passport("Petya12 Vasechkin", "as123456");
        Client client = new Client("0951234567", passport);

        Size size = new Size(1, 2, 3, 4);
        Product product1 = new Product("iPhone 7", size, 50, client);
        Product product2 = new Product("iPhone 6", size, 40, client);
        Product product3 = new Product("iPhone 5", size, 30, client);
        Product[] products = {product1, product2, product3};

        MyDate dateStart = new MyDate(2016, 7, 12, 13, 25);
        MyDate dateEnd = new MyDate(2016, 8, 12, 13, 30);

        PostTicket postTicket = new PostTicket(client, products, from, to, dateStart, dateEnd);

        if (!validator.validation(postTicket).getErr()
                && validator.validation(postTicket).getTextErr().contains("fullName - false"))
        {
            System.out.println("test negative validate PostTicket: bad client - pass");
            //System.out.println(validator.validation(postTicket).getTextErr() + '\n');
        } else System.out.println("test negative validate PostTicket: bad client - fail");
    }

    // test negative postTicket empty products[]
    public static void testNegativeValidatePostTicketBadProducts(Validator validator) {

        Address from = new Address("kiev", "starokievskaya", "10");

        Address to = new Address("poltava", "stepnaya", "12");

        Passport passport = new Passport("Petya Vasechkin", "as123456");
        Client client = new Client("0951234567", passport);

        Size size = new Size(1, 2, 3, 4);
        
        Product[] products = {};

        MyDate dateStart = new MyDate(2016, 7, 12, 13, 25);
        MyDate dateEnd = new MyDate(2016, 8, 12, 13, 30);

        PostTicket postTicket = new PostTicket(client, products, from, to, dateStart, dateEnd);

        if (!validator.validation(postTicket).getErr()
                && validator.validation(postTicket).getTextErr().contains("product[] is empty"))
        {
            System.out.println("test negative validate PostTicket: bad products[] - pass");
            //System.out.println(validator.validation(postTicket).getTextErr() + '\n');
        } else System.out.println("test negative validate PostTicket: bad products[] - fail");
    }

    // test negative postTicket bad product
    public static void testNegativeValidatePostTicketBadProduct(Validator validator) {

        Address from = new Address("kiev", "starokievskaya", "10");

        Address to = new Address("poltava", "stepnaya", "12");

        Passport passport = new Passport("Petya Vasechkin", "as123456");
        Client client = new Client("0951234567", passport);

        Size size = new Size(1, 2, 3, 4);
        Product product1 = new Product("iPhone 7", size, 50, client);
        Product product2 = new Product("iPhone 6", size, -40, client);
        Product product3 = new Product("iPhone 5", size, 30, client);
        Product[] products = {product1, product2, product3};

        MyDate dateStart = new MyDate(2016, 7, 12, 13, 25);
        MyDate dateEnd = new MyDate(2016, 8, 12, 13, 30);

        PostTicket postTicket = new PostTicket(client, products, from, to, dateStart, dateEnd);

        if (!validator.validation(postTicket).getErr()
                && validator.validation(postTicket).getTextErr().contains("prise - false"))
        {
            System.out.println("test negative validate PostTicket: bad product - pass");
            //System.out.println(validator.validation(postTicket).getTextErr() + '\n');
        } else System.out.println("test negative validate PostTicket: bad product - fail");
    }

    // test negative postTicket bad from
    public static void testNegativeValidatePostTicketBadFrom(Validator validator) {

        Address from = new Address("kiev", "", "10");

        Address to = new Address("poltava", "stepnaya", "12");

        Passport passport = new Passport("Petya Vasechkin", "as123456");
        Client client = new Client("0951234567", passport);

        Size size = new Size(1, 2, 3, 4);
        Product product1 = new Product("iPhone 7", size, 50, client);
        Product product2 = new Product("iPhone 6", size, 40, client);
        Product product3 = new Product("iPhone 5", size, 30, client);
        Product[] products = {product1, product2, product3};

        MyDate dateStart = new MyDate(2016, 7, 12, 13, 25);
        MyDate dateEnd = new MyDate(2016, 8, 12, 13, 30);

        PostTicket postTicket = new PostTicket(client, products, from, to, dateStart, dateEnd);

        if (!validator.validation(postTicket).getErr()
                && validator.validation(postTicket).getTextErr().contains("Street - false"))
        {
            System.out.println("test negative validate PostTicket: bad adress from - pass");
            //System.out.println(validator.validation(postTicket).getTextErr() + '\n');
        } else System.out.println("test negative validate PostTicket: bad adress from - fail");
    }

    // test negative postTicket bad to
    public static void testNegativeValidatePostTicketBadTo(Validator validator) {

        Address from = new Address("kiev", "starokievska", "10");

        Address to = new Address("", "stepnaya", "12");

        Passport passport = new Passport("Petya Vasechkin", "as123456");
        Client client = new Client("0951234567", passport);

        Size size = new Size(1, 2, 3, 4);
        Product product1 = new Product("iPhone 7", size, 50, client);
        Product product2 = new Product("iPhone 6", size, 40, client);
        Product product3 = new Product("iPhone 5", size, 30, client);
        Product[] products = {product1, product2, product3};

        MyDate dateStart = new MyDate(2016, 7, 12, 13, 25);
        MyDate dateEnd = new MyDate(2016, 8, 12, 13, 30);

        PostTicket postTicket = new PostTicket(client, products, from, to, dateStart, dateEnd);

        if (!validator.validation(postTicket).getErr()
                && validator.validation(postTicket).getTextErr().contains("City - false"))
        {
                    System.out.println("test negative validate PostTicket: bad adress to - pass");
                    //System.out.println(validator.validation(postTicket).getTextErr() + '\n');
        } else System.out.println("test negative validate PostTicket: bad adress to - fail");
    }

    // test negative product bad client
    public static void testNegativeValidateProductBadClient(Validator validator) {

        Passport passport = new Passport("Petya12 Vasechkin", "as123456");
        Client client = new Client("0951234567", passport);
        Size size = new Size(1, 2, 3, 4);
        Product product = new Product("iPhone 7", size, 50, client);

        if (!validator.validation(product).getErr() && validator.validation(product).getTextErr().contains("client - false")) {
            System.out.println("test negative validate Product: bad client - pass");
            System.out.println(validator.validation(product).getTextErr() + '\n');
        } else System.out.println("test negative validate Product: bad client - fail");
    }

    // test negative product bad prise
    public static void testNegativeValidateProductBadPrise(Validator validator) {

        Passport passport = new Passport("Petya Vasechkin", "as123456");
        Client client = new Client("0951234567", passport);
        Size size = new Size(1, 2, 3, 4);
        Product product = new Product("iPhone 7", size, -50, client);

        if (!validator.validation(product).getErr() && validator.validation(product).getTextErr().contains("prise - false")) {
            System.out.println("test negative validate Product: bad prise - pass");
            System.out.println(validator.validation(product).getTextErr() + '\n');
        } else System.out.println("test negative validate Product: bad prise - fail");
    }

    // test negative product bad name
    public static void testNegativeValidateProductBadName(Validator validator) {

        Passport passport = new Passport("Petya Vasechkin", "as123456");
        Client client = new Client("0951234567", passport);
        Size size = new Size(1, 2, 3, 4);
        Product product = new Product("", size, 50, client);

        if (!validator.validation(product).getErr() && validator.validation(product).getTextErr().contains("name - false")) {
            System.out.println("test negative validate Product: bad name - pass");
            System.out.println(validator.validation(product).getTextErr() + '\n');
        } else System.out.println("test negative validate Product: bad name - fail");
    }

    // test negative product bad size
    public static void testNegativeValidateProductBadSize(Validator validator) {

        Passport passport = new Passport("Petya Vasechkin", "as123456");
        Client client = new Client("0951234567", passport);
        Size size = new Size(1, 2, -3, 4);
        Product product = new Product("iPhone 7", size, 50, client);

        if (!validator.validation(product).getErr() && validator.validation(product).getTextErr().contains("size - false")) {
            System.out.println("test negative validate Product: bad size - pass");
            System.out.println(validator.validation(product).getTextErr() + '\n');
        } else System.out.println("test negative validate Product: bad size - fail");
    }

    // test negative bad passport number
    public static void testNegativeValidateClientBadPassportNum(Validator validator) {

        Passport passport = new Passport("Petya Vasechkin", "12123456");
        Client client = new Client("951234567", passport);

        if (!validator.validation(client).getErr()
                && validator.validation(client).getTextErr().contains("passportNumber - false")) {
            System.out.println("test negative validate client: bad passportNumber - pass ");
            System.out.println(validator.validation(client).getTextErr());
        } else System.out.println("test negative validate client: bad passportNumber - fail");
    }

    // test negative bad full name
    public static void testNegativeValidateClientBadFullName(Validator validator) {

        Passport passport = new Passport("Petya12 Vasechkin", "as123456");
        Client client = new Client("951234567", passport);

        if (!validator.validation(client).getErr()
                && validator.validation(client).getTextErr().contains("fullName - false")) {
            System.out.println("test negative validate client: bad fullName - pass");
            System.out.println(validator.validation(client).getTextErr());
        } else System.out.println("test negative validate client: bad fullName - fail");
    }

    // test negative bad phone
    public static void testNegativeValidateClientBadPhone(Validator validator) {

        Passport passport = new Passport("Petya Vasechkin", "as123456");
        Client client = new Client("0951237", passport);

        if (!validator.validation(client).getErr()
                && validator.validation(client).getTextErr().contains("phone - false")) {
            System.out.println("test negative validate client: bad phone - pass");
            System.out.println(validator.validation(client).getTextErr());
        } else System.out.println("test negative validate client: bad phone - fail");
    }

    // test negative bad city
    public static void testNegativeValidateAdressBadCity(Validator validator) {

        Address address = new Address("12", "starokievska", "10");

        if (!validator.validation(address).getErr()
                && validator.validation(address).getTextErr().contains("City - false")) {
            System.out.println("test negative Validate Adress: bad city - pass");
            System.out.println(validator.validation(address).getTextErr());
        } else System.out.println("test negative Validate Adress: bad city - fail");
    }

    // test negative bad street
    public static void testNegativeValidateAdressBadStreet(Validator validator) {

        Address address = new Address("kiev", "staro00evska", "10");

        if (!validator.validation(address).getErr()
                && validator.validation(address).getTextErr().contains("Street - false")) {
            System.out.println("test negative Validate Adress: bad street - pass");
            System.out.println(validator.validation(address).getTextErr());
        } else System.out.println("test negative Validate Adress: bad street - fail");
    }

    // test negative bad house number
    public static void testNegativeValidateAdressBadHouseNum(Validator validator) {

        Address address = new Address("kiev", "starokievska", "yy");

        if (!validator.validation(address).getErr()
                && validator.validation(address).getTextErr().contains("HouseNum - false")) {
            System.out.println("test negative Validate Adress: bad houseNumber - pass");
            System.out.println(validator.validation(address).getTextErr());
        } else System.out.println("test negative Validate Adress: bad houseNumber - fail");
    }

    public static void testPositiveValidatePostTicket(Validator validator) {

        Address from = new Address("kiev", "starokievska", "10");

        Address to = new Address("poltava", "stepnaya", "12");

        Passport passport = new Passport("Petya Vasechkin", "as123456");
        Client client = new Client("0951234567", passport);

        Size size = new Size(1, 2, 3, 4);
        Product product1 = new Product("iPhone 7", size, 50, client);
        Product product2 = new Product("iPhone 6", size, 40, client);
        Product product3 = new Product("iPhone 5", size, 30, client);
        Product[] products = {product1, product2, product3};

        MyDate dateStart = new MyDate(2016, 7, 12, 13, 25);
        MyDate dateEnd = new MyDate(2016, 8, 12, 13, 30);

        PostTicket postTicket = new PostTicket(client, products, from, to, dateStart, dateEnd);

        if (validator.validation(postTicket).getErr()) {
            System.out.println("test Positive Validate postTicket - pass\n");
        } else System.out.println("test Positive Validate postTicket - fail\n");

    }

    public static void testPositiveValidateAdress(Validator validator) {

        Address address = new Address("kiev", "starokievska", "10");

        if (validator.validation(address).getErr()) {
            System.out.println("test Positive Validate Adress - pass\n");
        } else System.out.println("test Positive Validate Adress - fail\n");
    }

    public static void testPositiveValidateClient(Validator validator) {

        Passport passport = new Passport("Petya Vasechkin", "as123456");
        Client client = new Client("0951234567", passport);

        if (validator.validation(client).getErr()) {
            System.out.println("test Positive Validate Client - pass\n");
        } else System.out.println("test Positive Validate Client - fail\n");
    }

    public static void testPositiveValidateProducte(Validator validator) {

        Passport passport = new Passport("Petya Vasechkin", "as123456");
        Client client = new Client("0951234567", passport);
        Size size = new Size(1, 2, 3, 4);
        Product product = new Product("iPhone 7", size, 50, client);

        if (validator.validation(product).getErr()) {
            System.out.println("test positive Validate Product - pass\n");
        } else System.out.println("test Positive Validate Product - fail\n");
    }

}
