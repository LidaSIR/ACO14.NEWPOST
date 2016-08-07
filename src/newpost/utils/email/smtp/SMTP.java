package newpost.utils.email.smtp;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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


    public static void sendMail(String to, String subject, String body) throws IOException {
        // todo get from properties file

        Properties props = System.getProperties();
        Map<String, String> propertiesMap = getPropertiesFromFile();

        propertiesMap.keySet().stream().forEach((key)->{
            props.put(key,propertiesMap.get(key));
        });

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(propertiesMap.get(SMTP_LOGIN_KEY)));
            InternetAddress toAddress = new InternetAddress(to);

            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject(subject);
            message.setText(body);

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

    public static Map<String,String> getPropertiesFromFile() throws IOException {

        Map<String,String> propertiesMap = new HashMap<>();

        List<String> properList = Files.readAllLines(Paths.get("resources\\properties"));

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
}
