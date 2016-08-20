package newpost.model.office;

import newpost.model.common.Passport;

/**
 * Created by Lida on 19.08.2016.
 */
public class User {
    private String fullName;
    private String phone;
    private String login;
    private String password;
    protected String mail;
    protected Passport passport;


    protected UserType userType;
    private String randomPass = Integer.toString(100000 + (int)(Math.random() * ((999999 - 100000))));

    public User(String fullName, String phone) {
        this.fullName = fullName;
        this.phone = phone;
        this.login = phone;
        this.password = randomPass;
    }
    public User(String phone){
        this.phone = phone;
        this.login = phone;
        this.password = randomPass;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return !(phone != null ? !phone.equals(user.phone) : user.phone != null);

    }

    @Override
    public int hashCode() {
        return phone != null ? phone.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", login='" + login + '\'' +
                ", password=" + password +
                ", randomPass=" + randomPass +
                '}';
    }
}
