package newpost.test.utils;

import newpost.controller.DataInitFactory;
import newpost.controller.ManagerController;
import newpost.db.AppDataContainer;
import newpost.model.office.Client;

import javax.mail.MessagingException;

/**
 * Created by Serhii Fursenko on 27.07.16.
 */
public class TestSMTP {

    private static String USER_NAME = "lightpostua";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "lightpostuaaco14"; // GMail password
    private static String RECIPIENT = "fyrsenko@gmail.com";

    public static void main(String[] args) throws MessagingException {


        Client testClient = DataInitFactory.clientCreator();

        testClient.setMail("YOUR_TEST_MAIL@gmail.com");
        ManagerController managerController = new ManagerController(new AppDataContainer());

        managerController.createTicket(testClient, DataInitFactory.createAddress(), DataInitFactory.productsCreator()[0]);



        //sendFromGMail("lightpostua", "lightpostuaaco14", "fyrsenko@gmail.com", "TEST", "TEST TEXT");


    }




}


