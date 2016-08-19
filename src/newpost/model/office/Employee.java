package newpost.model.office;

/**
 * Created by Lida on 16.07.2016.
 */
public class Employee extends User{
    private String jobTitle; // make constant?
    private int salary;


    public Employee(String jobTitle, String fullName, String phone, int salary) {
       super(fullName,phone);
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "jobTitle='" + jobTitle + '\'' +
                ", salary=" + salary +
                '}';
    }
}

