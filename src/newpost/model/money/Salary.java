package newpost.model.money;

/**
 * Created by Lida on 18.07.2016.
 */
public class Salary extends Transaction {
    private Transaction transaction;
    private int salaryAmount;
    private String employeeName;
    private String employeeSurname;

    public Salary(Transaction transaction, int salaryAmount, String employeeName, String employeeSurname) {
        this.transaction = transaction;
        this.salaryAmount = salaryAmount;
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public int getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(int salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "transaction=" + transaction +
                ", salaryAmount=" + salaryAmount +
                ", employeeName='" + employeeName + '\'' +
                ", employeeSurname='" + employeeSurname + '\'' +
                '}';
    }
}
