package newpost.controller;

import newpost.model.Address;
import newpost.model.Client;
import newpost.model.Product;
import sun.security.krb5.internal.Ticket;

/**
 * Created by macaque on 09.07.2016.
 */
public interface IValidator {
    boolean validation(Address address);
    boolean validation(Client client);
    boolean validation(Product product);
    boolean validation(Ticket ticket);

}
