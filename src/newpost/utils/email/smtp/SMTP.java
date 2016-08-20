package newpost.utils.email.smtp;

import newpost.model.office.Client;
import newpost.model.office.PostTicket;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Serhii Fursenko on 27.07.16.
 */
public class SMTP {

    public static final String SMTP_LOGIN_KEY = "mail.smtp.user";
    public static final String SMTP_PASSWORD_KEY = "mail.smtp.password";
    public static final String SMTP_HOST_KEY = "mail.smtp.host";
    private final static String MAIL_SUBJECT = "Hello from ACO14 New Post";
    private final static String DEFAULT_MESSAGE_TEXT = "Hello dear {name}!!!\n" +
            "\n" +
            "Now your order in progress and your ticket number is:\n" +
            "{ticket}";
    private final static String DEFAULT_MESSAGE_LOGIN_AND_PASSWORD = "Hello dear {name}!!!\n" +
            "\n" +
            "Your login is: " + "{login}" +
            "Your password is: " + "{pass}" ;

    public static void sendMail(Client to, PostTicket postTicket, String attachmentPath) throws IOException {

        if (to.getMail() == null) return;


        String mailText = DEFAULT_MESSAGE_TEXT.replace("{name}", to.getPassport().getFullname());
        mailText = mailText.replace("{ticket}", postTicket.getId());


        send(to, mailText, attachmentPath);
    }

    public static void sendLoginAndPass(Client to) throws IOException {

        if (to.getMail() == null) return;

        String mailText = DEFAULT_MESSAGE_LOGIN_AND_PASSWORD.replace("{name}", to.getPassport().getFullname());
        mailText = mailText.replace("{login}", to.getLogin());
        mailText = mailText.replace("{pass}", to.getPassword());

        send(to, mailText, null);

    }

    private static Map<String, String> getPropertiesFromFile() throws IOException {

        Map<String, String> propertiesMap = new HashMap<>();

        List<String> properList = Files.readAllLines(Paths.get("resources/properties"));

        properList.stream().forEach((e) -> {
            e = e.replaceAll("\\s", "");
            try {

                String key = e.split(":")[0];
                String value = e.split(":")[1];

                propertiesMap.put(key, value);

            } catch (Exception ex) {

            }

        });


        return propertiesMap;
    }

    private static void send (Client client, String mailText, String attachmentPath) throws IOException {

        Properties props = System.getProperties();
        Map<String, String> propertiesMap = getPropertiesFromFile();

        props.setProperty("mail.smtp.tls.trust", "smtp.gmail.com");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(propertiesMap.get(SMTP_LOGIN_KEY)));
            InternetAddress toAddress = new InternetAddress(client.getMail());

            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject(MAIL_SUBJECT);

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(mailText);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // attachment
            if (attachmentPath != null) {
                messageBodyPart = new MimeBodyPart();
                String filename = attachmentPath;
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);
            }
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(propertiesMap.get(SMTP_HOST_KEY), propertiesMap.get(SMTP_LOGIN_KEY), propertiesMap.get(SMTP_PASSWORD_KEY));
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }



}
