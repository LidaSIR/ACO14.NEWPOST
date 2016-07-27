package newpost.model.office;

import newpost.model.common.Passport;

/**
 * Created by home on 08.07.2016.
 */
public class Client {


    private String phone;
    private Passport passport;
    private String mail;


    public Client(String phone, Passport passport) {
        this.phone = phone;
        this.passport = passport;
    }

    public Client(String phone, Passport passport, String mail) {
        this.phone = phone;
        this.passport = passport;
        this.mail = mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {

        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
                "phone='" + phone + '\'' +
                ", passport=" + passport +
                '}';
    }
}
