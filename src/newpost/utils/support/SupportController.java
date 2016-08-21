package newpost.utils.support;

import newpost.utils.PropertiesHolder;
import newpost.utils.email.exceptions.NoNewEmailException;
import newpost.utils.email.mail_controller.MailController;
import newpost.utils.email.model_letter.Letter;
import newpost.utils.email.smtp.SMTP;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Vladislav on 20.08.2016.
 */
public class SupportController implements ISupportController {
    private Map<String, String> properties;
    private static final String HOST = "supporthost";
    private static final String MAIL_STORE_TYPE = "supportmailStoreType";
    private static final String USER_NAME = "supportusername";
    private static final String PASSWORD = "supportpassword";
    private static final String LOCATION = "supportlocation";

    @Override
    public void sendEmail(String email, String textMessage) {
        try {
            SMTP.sendMailFromSupport(email, textMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Letter> getNewEmails(){
        MailController mailController = new MailController();
        List<Letter> letterList = new ArrayList<>();

        try {
            letterList = mailController.getNewEmails(
                                                    mailController.connectToEmail(
                                                                                PropertiesHolder.get(HOST),
                                                                                PropertiesHolder.get(MAIL_STORE_TYPE),
                                                                                PropertiesHolder.get(USER_NAME),
                                                                                PropertiesHolder.get(PASSWORD)),
                                                                                                                PropertiesHolder.get(LOCATION));
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoNewEmailException e) {
            //e.printStackTrace();
        }

        return letterList;
    }
}
