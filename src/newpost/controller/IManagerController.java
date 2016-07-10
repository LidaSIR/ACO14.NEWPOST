package newpost.controller;

import newpost.model.*;

/**
 * Created by macaque on 10.07.2016.
 */
public interface IManagerController {
    PostTicket createTicket(Client client, Address sendToAdress, Product product);
    PostTicket filterTicketById(String ticketId);
    PostTicket showTicketByClientPhone(String phone);
    Client getClient(String phone);
    Client addClient (Passport passport, String phone);
}
