package newpost.utils.email.mail_controller;

import newpost.utils.email.exceptions.NoNewEmailException;
import newpost.utils.email.mail_container.MailStorage;
import newpost.utils.email.model_letter.Letter;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by macaque on 27.07.2016.
 */
public interface IMailController {

    Folder connectToEmail(String host, String storeType, String user,
                                String password);
    List<Letter> getNewEmails(Folder folder, String location) throws MessagingException, IOException, NoNewEmailException;


}


