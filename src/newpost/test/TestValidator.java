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
                product.getSize().getHeight(), product.getSize().getWidth(),product.getPrice()));

        if (validator.validation(product).getErr()){
            System.out.println("test positive Validate Product - pass\n");
        } else System.out.println("test Positive Validate Product - fail\n");
    }


}
