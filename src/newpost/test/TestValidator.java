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
        System.out.println(product.toString());

        if (validator.validation(product).getErr()){
            System.out.println("test positive Validate Product - pass\n");
        } else System.out.println("test Positive Validate Product - fail\n");
    }


}
