package newpost.model.common;

/**
 * Created by home on 08.07.2016.
 */
public class Passport {

    private String fullname;
    private String number;

    public Passport(String fullname, String number) {
        this.fullname = fullname;
        this.number = number;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "fullname='" + fullname + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
