package newpost.utils.email.pop;

import java.util.Properties;

import javax.mail.*;

public class TestPOP3 {

    public static void check(String host, String storeType, String user,
                             String password) {
        try {

            //create properties field
            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");

            store.connect(host, user, password);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            emailFolder.getUnreadMessageCount();
            emailFolder.getMessages();

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();


            System.out.println("messages.length---" + messages.length);

            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                Flags flags = message.getFlags();
                Flags.Flag[] systemFlags = flags.getSystemFlags();
                for (int j = 0; i <systemFlags.length ; i++) {
                    System.out.println(systemFlags[j].toString());
                    if (systemFlags[i] == Flags.Flag.SEEN) {
                        System.out.println("---------------------------------");
                        System.out.println("Email Number " + (i + 1));
                        System.out.println("Subject: " + message.getSubject());
                        System.out.println("From: " + message.getFrom()[0]);
                        System.out.println("Text: " + message.getContent().toString());
                    } else System.out.println("this message already seen");
                }
                // flags.contains()
             //   else System.out.println("sorry, there is no new letters");

            }

            //close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String host = "pop.gmail.com";// change accordingly
        String mailStoreType = "pop3";
        String username = "lightpostua@gmail.com";// change accordingly
        String password = "lightpostuaaco14";// change accordingly

        check(host, mailStoreType, username, password);

    }


}
