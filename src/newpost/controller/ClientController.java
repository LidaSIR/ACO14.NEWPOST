package newpost.controller;

import newpost.model.Address;
import newpost.model.Client;
import newpost.model.Product;
import sun.security.krb5.internal.Ticket;

/**
 * Created by macaque on 09.07.2016.
 */
public class ClientController implements IClientController, IValidator {
    @Override
    public boolean sendProduct(Product product) {
        return false;
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

    @Override
    public boolean validationAddress(Address address) {
        return false;
    }

    @Override
    public boolean validationClient(Client client) {
        return false;
    }

    @Override
    public boolean validationProduct(Product product) {
        return false;
    }

    @Override
    public boolean validationTicket(Ticket ticket) {
        return false;
    }
}
