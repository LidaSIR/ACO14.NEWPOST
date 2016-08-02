package newpost.test.utils.email;

import newpost.utils.email.exceptions.NoNewEmailException;
import newpost.utils.email.mail_controller.MailController;
import newpost.utils.email.model_letter.Letter;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by macaque on 25.07.2016.
 */
public class TestPOP {

    public static void main(String args[]) {

        String host = "pop.gmail.com";// change accordingly
        String mailStoreType = "pop3";
        String username = "lightpostua@gmail.com";// change accordingly
        String password = "lightpostuaaco14";// change accordingly
        String location = "mail_storage/lightpostua.txt";

        MailController mailController = new MailController();
        List<Letter> letterList = new ArrayList<>();

        try {
            letterList = mailController.getNewEmails(mailController.connectToEmail(host,mailStoreType,username,password),location);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoNewEmailException e) {
            e.printStackTrace();
        }

        System.out.println(letterList.toString());







        }

}
