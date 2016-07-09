package newpost.db;

import newpost.model.Client;
import newpost.model.Driver;
import newpost.model.PostTicket;

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


    public AppDataContainer() {
        clients = new ArrayList<>();
        tickets = new ArrayList<>();
        drivers = new ArrayList<>();
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
}
