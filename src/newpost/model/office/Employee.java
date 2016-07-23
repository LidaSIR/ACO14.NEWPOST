package newpost.model.office;

/**
 * Created by Lida on 16.07.2016.
 */
public class Employee {
    private String jobTitle; // make constant?
    private String fullName;
    private String phone;
    private int salary;
    private String login;
    private int password;
    private  int randomPass = 100000 + (int)(Math.random() * ((999999 - 100000)));

    public Employee() {
    }

    public Employee(String jobTitle, String fullName, String phone, int salary) {
        this.jobTitle = jobTitle;
        this.fullName = fullName;
        this.phone = phone;
        this.salary = salary;
        this.login = fullName;
        this.password = randomPass;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "jobTitle='" + jobTitle + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                ", login='" + login + '\'' +
                ", password=" + password +
                '}';
    }
}

