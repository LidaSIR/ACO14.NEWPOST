package newpost.controller;

import newpost.model.Address;
import newpost.model.Client;
import newpost.model.Product;
import sun.security.krb5.internal.Ticket;

/**
 * Created by macaque on 09.07.2016.
 */
public interface IValidator {
    boolean validationAddress(Address address);
    boolean validationClient(Client client);
    boolean validationProduct(Product product);
    boolean validationTicket(Ticket ticket);

}
