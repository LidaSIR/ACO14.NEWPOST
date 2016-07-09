package newpost.controller;

import newpost.model.Address;
import newpost.model.Client;
import newpost.model.PostTicket;
import newpost.model.Product;
import sun.security.krb5.internal.Ticket;

/**
 * Created by macaque on 09.07.2016.
 */
public class ClientController implements IClientController {

    @Override
    public PostTicket makeOrder(Client client, Address sendToAdress, Product product) {

        //PostTicket postTicket = new PostTicket(client, )
        return null;
    }

    @Override
    public Product showProductById(int productId) {
        return null;
    }

    @Override
    public boolean cancelTicket(int ticketId) {
        return false;
    }

    @Override
    public Product takeProduct(int ticketId) {
        return null;
    }

}
