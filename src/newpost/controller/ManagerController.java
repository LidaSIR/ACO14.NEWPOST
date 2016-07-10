package newpost.controller;


import newpost.db.AppDataContainer;
import newpost.model.*;

import java.util.List;

/**
 * Created by home on 08.07.2016.
 */
public class ManagerController {

    private AppDataContainer appDataContainer;

    public ManagerController(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }

    public PostTicket createTicket(Client client, Product[] products, Address from, Address to){
        return null;
    }

    public PostTicket[] filterTicketsById(String id) {
        return null;
    }
    public PostTicket[] filterTicketsByClientPhone (String phone) {
        return null;
    }

    public PostTicket getTicketById(String id) {
        return null;
    }

    public Client getClient(String phone) {

        List<Client> clientArr = appDataContainer.getClients();
        for(Client client : clientArr) {
            Client iterClient = client;
            if(iterClient.getPhone().equals(phone)) {
                return iterClient;
            }
        }
        return null;
    }

    public Client addClient (Passport passport, String phone) {
        Client client = new Client(phone, passport);
        appDataContainer.getClients().add(client);
        return client;
    }
}
