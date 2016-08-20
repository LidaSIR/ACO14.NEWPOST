package newpost.model.office;

import newpost.model.common.Passport;
import newpost.utils.email.smtp.SMTP;

import java.io.IOException;

/**
 * Created by home on 08.07.2016.
 */
public class Client  extends User{


    private Passport passport;
    private String mail;


    public Client( String phone, Passport passport) {
        super(phone);
        this.passport = passport;
    }

    public Client(String phone, Passport passport, String mail) {
        super(phone);
        this.passport = passport;
        this.mail = mail;

        try {
            SMTP.sendLoginAndPass(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {

        return mail;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }


    @Override
    public String toString() {
        return "Client{" +
                "passport=" + passport +
                ", mail='" + mail + '\'' +
                "phone_login=" + getLogin() +
                ", pass='" + getPassword() + '\'' +
                '}';
    }
}
