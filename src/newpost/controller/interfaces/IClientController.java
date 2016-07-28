package newpost.controller.interfaces;

import newpost.model.common.Address;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.model.common.Product;
import newpost.exceptions.ValidationException;

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
