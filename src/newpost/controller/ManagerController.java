package newpost.controller;


import newpost.db.AppDataContainer;
import newpost.model.*;

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

    public PostTicket[] flisterTicketsById(String id) {
        return null;
    }
    public PostTicket[] flisterTicketsByClientPhone (String phone) {
        return null;
    }

    public PostTicket getTicketById(String id) {
        return null;
    }

    public Client getClient(String phone) {
        Object[] clientArr = appDataContainer.getClients().toArray();
        for(Object obj : clientArr) {
            Client iterClient = (Client) obj;
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
