package newpost.test.utils;

import newpost.controller.DataInitFactory;
import newpost.controller.ManagerController;
import newpost.db.AppDataContainer;
import newpost.model.office.Client;
import newpost.utils.email.smtp.SMTP;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by Serhii Fursenko on 27.07.16.
 */
public class TestSMTP {

    public static void main(String[] args) throws IOException {


        Client testClient = DataInitFactory.clientCreator();

        testClient.setMail("YOUR_MAIL_ADDRESS@gmail.com");
        ManagerController managerController = new ManagerController(new AppDataContainer());

        managerController.createTicket(testClient, DataInitFactory.createAddress(), Arrays.asList(DataInitFactory.productsCreator()));


    }


}


