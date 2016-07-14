package newpost.test;


import newpost.controller.Validator;
import newpost.model.*;


/**
 * Created by Agryzkov on 13.07.2016.
 */
public class TestValidator {

    public static void main(String[] args) {

        Validator validator = new Validator();

        testPositiveValidateAdress(validator);
        testPositiveValidateClient(validator);
        testPositiveValidateProducte(validator);

        testNegativeValidateAdressBadCity(validator);
        testNegativeValidateAdressBadStreet(validator);
        testNegativeValidateAdressBadHouseNum(validator);

        testNegativeValidateClientBadFullName(validator);
        testNegativeValidateClientBadPassportNum(validator);
        testNegativeValidateClientBadPhone(validator);


    }

    // test negative bad passport number
    public static void testNegativeValidateClientBadPassportNum(Validator validator) {

        Passport passport = new Passport("Petya Vasechkin", "12123456");
        Client client = new Client("951234567", passport);

        System.out.println("negative test client - bad passport number \n");
        System.out.println("test data: \n" + client.toString());

        if (!validator.validation(client).getErr()) {
            System.out.println("result: \n" + validator.validation(client).getTextErr());
            System.out.println(String.format("textErr must contain this message 'passportNumber - false': %b ",
                    validator.validation(client).getTextErr().contains("passportNumber - false")));
            System.out.println("test Negative Validate Client - pass\n");
        } else System.out.println("test Negative Validate Client - fail\n");
    }

    // test negative bad full name
    public static void testNegativeValidateClientBadFullName(Validator validator) {

        Passport passport = new Passport("Petya12 Vasechkin", "as123456");
        Client client = new Client("951234567", passport);

        System.out.println("negative test client - bad full name \n");
        System.out.println("test data: \n" + client.toString());

        if (!validator.validation(client).getErr()) {
            System.out.println("result: \n" + validator.validation(client).getTextErr());
            System.out.println(String.format("textErr must contain this message 'fullName - false': %b ",
                    validator.validation(client).getTextErr().contains("fullName - false")));
            System.out.println("test Negative Validate Client - pass\n");
        } else System.out.println("test Negative Validate Client - fail\n");
    }

    // test negative bad phone
    public static void testNegativeValidateClientBadPhone(Validator validator) {

        Passport passport = new Passport("Petya Vasechkin", "as123456");
        Client client = new Client("0951237", passport);

        System.out.println("negative test client - bad phone \n");
        System.out.println("test data: \n" + client.toString());

        if (!validator.validation(client).getErr()) {
            System.out.println("result: \n" + validator.validation(client).getTextErr());
            System.out.println(String.format("textErr must contain this message 'phone - false': %b ",
                    validator.validation(client).getTextErr().contains("phone - false")));
            System.out.println("test Negative Validate Client - pass\n");
        } else System.out.println("test Negative Validate Client - fail\n");
    }

    // test negative bad city
    public static void testNegativeValidateAdressBadCity(Validator validator) {

        Address address = new Address("12", "starokievska", "10");

        System.out.println("negative test address - bad city");
        System.out.println(String.format("Address:\n City: %s \t Street: %s \t houseNumber: %s",
                address.getCity(), address.getStreet(), address.getHouseNum()));

        if (!validator.validation(address).getErr()) {
            System.out.println("test Negative Validate Adress - pass\n");
            System.out.println(validator.validation(address).getTextErr());
        } else System.out.println("test Negative Validate Adress - fail\n");
    }

    // test negative bad street
    public static void testNegativeValidateAdressBadStreet(Validator validator) {

        Address address = new Address("kiev", "staro00evska", "10");

        System.out.println("negative test address - bad street");
        System.out.println(String.format("Address:\n City: %s \t Street: %s \t houseNumber: %s",
                address.getCity(), address.getStreet(), address.getHouseNum()));

        if (!validator.validation(address).getErr()) {
            System.out.println("test Negative Validate Adress - pass\n");
            System.out.println(validator.validation(address).getTextErr());
        } else System.out.println("test Negative Validate Adress - fail\n");
    }
    // test negative bad house number
    public static void testNegativeValidateAdressBadHouseNum(Validator validator) {

        Address address = new Address("12", "starokievska", "yy");

        System.out.println("negative test address - bad house number");
        System.out.println(String.format("Address:\n City: %s \t Street: %s \t houseNumber: %s",
                address.getCity(), address.getStreet(), address.getHouseNum()));

        if (!validator.validation(address).getErr()) {
            System.out.println("test Negative Validate Adress - pass\n");
            System.out.println(validator.validation(address).getTextErr());
        } else System.out.println("test Negative Validate Adress - fail\n");
    }

    public static void testPositiveValidateAdress(Validator validator) {

        Address address = new Address("kiev", "starokievska", "10");

        System.out.println("positive test address");
        System.out.println(String.format("Address:\n City: %s \t Street: %s \t houseNumber: %s",
                address.getCity(), address.getStreet(), address.getHouseNum()));

        if (validator.validation(address).getErr()) {
            System.out.println("test Positive Validate Adress - pass\n");
        } else System.out.println("test Positive Validate Adress - fail\n");
    }

    public static void testPositiveValidateClient(Validator validator) {

        Passport passport = new Passport("Petya Vasechkin", "as123456");
        Client client = new Client("0951234567", passport);

        System.out.println("positive test client");
        System.out.println(client.toString());

        if (validator.validation(client).getErr()) {
            System.out.println("test Positive Validate Client - pass\n");
        } else System.out.println("test Positive Validate Client - fail\n");
    }

    public static void testPositiveValidateProducte(Validator validator) {

        Passport passport = new Passport("Petya Vasechkin", "as123456");
        Client client = new Client("0951234567", passport);
        Size size = new Size(1,2,3,4);
        Product product = new Product("iPhone 7",size, 50, client);

        System.out.println("positive test Product");
        System.out.println(client.toString());
        System.out.println(String.format("product name - %s \t size: %4d %4d %4d %4d \t prise: %6d",
                product.getName(), product.getSize().getHeight(), product.getSize().getLength(),
                product.getSize().getHeight(), product.getSize().getWidth(), product.getPrice()));

        if (validator.validation(product).getErr()){
            System.out.println("test positive Validate Product - pass\n");
        } else System.out.println("test Positive Validate Product - fail\n");
    }


}
