package newpost.utils.email.mail_container;

import newpost.utils.email.model_letter.Letter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macaque on 26.07.2016.
 */
public class MailStorage {

    public List<Letter> letterList;
    public String name;

    public MailStorage(String name, List<Letter> letterList) {
        this.name = name;
        this.letterList = letterList ;
    }

    public List<Letter> getLetterList() {
        return letterList;
    }
}
