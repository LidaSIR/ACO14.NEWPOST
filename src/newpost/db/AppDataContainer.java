package newpost.db;

import newpost.model.money.Report;
import newpost.model.money.Transaction;
import newpost.model.office.Client;
import newpost.model.office.Driver;
import newpost.model.office.Employee;
import newpost.model.office.PostTicket;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Serhii Fursenko on 08.07.2016.
 * fyrsenko@gmail.com
 */
public class AppDataContainer {

    private List<Client> clients;
    private List<PostTicket> tickets;
    private List<Driver> drivers;
    private List<Transaction> transactions;
    private List<Employee> employees;
    private List<Report> reports;


    public AppDataContainer() {
        clients = new ArrayList<>();
        tickets = new ArrayList<>();
        drivers = new ArrayList<>();
        transactions = new ArrayList<>();
        employees = new ArrayList<>();
        reports = new ArrayList<>();
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<PostTicket> getTickets() {
        return tickets;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
