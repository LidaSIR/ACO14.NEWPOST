package newpost.utils.support;

import newpost.utils.email.model_letter.Letter;

import java.io.IOException;
import java.util.List;

/**
 * Created by Vladislav on 20.08.2016.
 */
public interface ISupportController {
    public void sendEmail(String email, String testMessage);
    public List<Letter> getNewEmails() throws IOException;
}
