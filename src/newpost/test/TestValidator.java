package newpost.test;


import newpost.controller.Validator;
import newpost.model.*;


/**
 * Created by Agryzkov on 13.07.2016.
 */
public class TestValidator {

    public static void main(String[] args) {

        Validator validator = new Validator();

        Address address = new Address("kiev","","10");

        System.out.println(validator.validation(address).getTextErr());



        Passport passport = new Passport("rebus rebus","re123123");
        Client client = new Client("0951231232",passport);

        System.out.println(validator.validation(client).getTextErr());

        Size size = new Size(2,2,2,1);
        Product product = new Product("phone", size, -40, client);

        System.out.println(validator.validation(product).getTextErr());

    }
}
