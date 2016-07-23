package newpost.model.money;

import newpost.model.office.Employee;
import newpost.model.office.PostTicket;

import java.util.Arrays;

/**
 * Created by Lida on 18.07.2016.
 */
public class Report {
    private Transaction [] transactions;
    private PostTicket[] postTickets;
    private Employee[] employees;
    private int income;

    public Report(Transaction[] transactions, PostTicket[] postTickets, Employee[] employees, int income) {
        this.transactions = transactions;
        this.postTickets = postTickets;
        this.employees = employees;
        this.income = income;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void setTransactions(Transaction[] transactions) {
        this.transactions = transactions;
    }

    public PostTicket[] getPostTickets() {
        return postTickets;
    }

    public void setPostTickets(PostTicket[] postTickets) {
        this.postTickets = postTickets;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "Report{" +
                "transactions=" + Arrays.toString(transactions) +
                ", postTickets=" + Arrays.toString(postTickets) +
                ", employees=" + Arrays.toString(employees) +
                ", income=" + income +
                '}';
    }
}
