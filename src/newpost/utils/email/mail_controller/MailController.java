package newpost.utils.email.mail_controller;

import com.sun.mail.pop3.POP3Folder;
import newpost.db.InitDB;
import newpost.utils.email.exceptions.NoNewEmailException;
import newpost.utils.email.model_letter.Letter;

import javax.mail.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by macaque on 27.07.2016.
 */
public class MailController implements IMailController{

    private Store store;
   // private Folder emailFolder;

    @Override
    public Folder connectToEmail(String host, String storeType, String user, String password) {

        try {

            //create properties field
            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            store = emailSession.getStore("pop3s");

            store.connect(host, user, password);


            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);


            return emailFolder;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public List<Letter> getNewEmails(Folder folder, String location) throws MessagingException, IOException, NoNewEmailException {

        // getting new emails from email-server;

        int countNewLetters = 0;

        List<String> localEmailStorage = loadLocalUIDs(folder,location);

        List<Letter> letterNewList = new ArrayList<>();

        List<Message> messageList = Arrays.asList(folder.getMessages());
        POP3Folder pf = (POP3Folder)folder;

        for (Message msg: messageList) {
            String uid = pf.getUID(msg);

            if (!localEmailStorage.contains(uid)){
                Letter letter = new Letter();
                letter.setTopic(msg.getSubject());
                letter.setFromName(msg.getFrom()[0].toString());
                letter.setToName(msg.getAllRecipients()[0].toString());
                letter.setMessage(getBodyFromEmail(msg));
                letter.setState("new");
                letter.setUid(uid);
                letterNewList.add(letter);
                countNewLetters++;
            }
        }
        System.out.println("new messages = " + countNewLetters);
        createLocalUIDs(folder, location);

        //close the store and folder objects
        folder.close(false);
        store.close();

        if (countNewLetters == 0){
            throw new NoNewEmailException("there is no new emails");
        }

        return letterNewList;

    }

    public static String getBodyFromEmail(Message message) throws IOException, MessagingException {
        String body = "";
        Object content = message.getContent();
        if (content instanceof String)
        {
            body = (String)content;
        }
        else if (content instanceof Multipart)
        {
            Multipart mp = (Multipart)content;
            BodyPart bodyPart = mp.getBodyPart(0);
            body = bodyPart.getContent().toString();
        }
        return body;
    }

    public static List<String> loadLocalUIDs(Folder folder, String location) throws MessagingException {

        // Checking has repository for current folder been created;

        List<String> emailStorage = new ArrayList<>();

        if (InitDB.checkFileisCreated(location) && InitDB.checkFileisNotEmpty(location)) {
            try {
                emailStorage = InitDB.loadDBToArray(location, emailStorage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            emailStorage = createLocalUIDs(folder,location);
        }

        return emailStorage;
    }

    public static List<String> createLocalUIDs(Folder folder, String location) throws MessagingException {

        // Creating local repository of UID's  of messages from email;

        List<Message> messageList = Arrays.asList(folder.getMessages());
        List<String> tempList = new ArrayList<>();

        POP3Folder pf = (POP3Folder)folder;

        for (Message msg: messageList) {
            String uid = pf.getUID(msg);
            tempList.add(uid);
        }

        InitDB.saveListToFile(tempList,location,false);

        return tempList;
    }
}
