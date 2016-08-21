package newpost.utils.support;

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
    private static final String HOST = "host";
    private static final String MAIL_STORE_TYPE = "mailStoreType";
    private static final String USER_NAME = "username";
    private static final String PASSWORD = "password";
    private static final String LOCATION = "location";

    public SupportController() throws IOException {
        readMailProperties();
    }

    private void readMailProperties() throws IOException {
        try {
            properties = SMTP.getSMTPPropertiesFromFile("resources/supportProperties");
        } catch (IOException e) {
            throw new IOException("File not found. Please verify properties path.");
        }
    }

    @Override
    public void sendEmail(String email, String textMessage) {
        try {
            SMTP.sendMail(email, textMessage);
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
                                                                                properties.get(HOST),
                                                                                properties.get(MAIL_STORE_TYPE),
                                                                                properties.get(USER_NAME),
                                                                                properties.get(PASSWORD)),
                                                                                                            properties.get(LOCATION));
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
