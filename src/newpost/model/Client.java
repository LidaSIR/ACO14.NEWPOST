package newpost.model;

/**
 * Created by home on 08.07.2016.
 */
public class Client {


    private String phone;
    private Passport passport;

    public Client(String phone, Passport passport) {
        this.phone = phone;
        this.passport = passport;
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
