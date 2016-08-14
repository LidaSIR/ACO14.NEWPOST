package newpost.controller.interfaces;

import newpost.exceptions.AppException;
import newpost.model.common.Address;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.model.common.Product;
import newpost.exceptions.ValidationException;

/**
 * Created by macaque on 09.07.2016.
 */
public interface IClientController {
    PostTicket makeOrder(Client client, Address sendToAddress, Product product) throws AppException;
    PostTicket showTicketById(String ticketId) throws AppException;
    Product showProductById(int ticketId) throws AppException;
    boolean cancelTicket(int ticketId) throws AppException;
    Product takeProduct(int ticketId) throws AppException;
}
