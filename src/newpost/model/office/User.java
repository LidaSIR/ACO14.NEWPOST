package newpost.model.office;

/**
 * Created by Lida on 19.08.2016.
 */
public class User {
    private String fullName;
    private String phone;
    private String login;
    private String password;
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
