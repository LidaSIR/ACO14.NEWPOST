package newpost.test;


import newpost.controller.Validator;
import newpost.model.Address;
import newpost.model.Client;
import newpost.model.Passport;


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

    }
}
