package newpost.db;

import newpost.model.money.Report;
import newpost.model.money.Transaction;
import newpost.model.office.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Serhii Fursenko on 08.07.2016.
 * fyrsenko@gmail.com
 */

// todo find good collections for Container
public class AppDataContainer {

    private List<Client> clients;
    private List<PostTicket> tickets;
    private List<Driver> drivers;
    private List<Transaction> transactions;
    private List<Employee> employees;
    private List<Report> reports;
    private List<PostOffice> postOffices;
    private Map<String, User> users;

    public AppDataContainer() {
        clients = new ArrayList<>();
        tickets = new ArrayList<>();
        drivers = new ArrayList<>();
        transactions = new ArrayList<>();
        employees = new ArrayList<>();
        reports = new ArrayList<>();
        postOffices = new ArrayList<>();
        users = new HashMap<>();
    }

    public List<PostOffice> getPostOffices() {
        return postOffices;
    }

    public void setPostOffices(List<PostOffice> postOffices) {
        this.postOffices = postOffices;
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

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

}
