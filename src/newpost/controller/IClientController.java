package newpost.controller;

import newpost.model.Address;
import newpost.model.Client;
import newpost.model.PostTicket;
import newpost.model.Product;
import newpost.model.exceptions.ValidationException;

/**
 * Created by macaque on 09.07.2016.
 */
public interface IClientController {
    PostTicket makeOrder(Client client, Address sendToAddress, Product product) throws ValidationException;
    PostTicket showTicketById(String ticketId) throws ValidationException;
    Product showProductById(int ticketId) throws ValidationException;
    boolean cancelTicket(int ticketId) throws ValidationException;
    Product takeProduct(int ticketId) throws ValidationException;
}
