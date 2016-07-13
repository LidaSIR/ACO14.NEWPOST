package newpost.controller;

import newpost.model.Address;
import newpost.model.Client;
import newpost.model.PostTicket;
import newpost.model.Product;
import sun.security.krb5.internal.Ticket;

import java.util.Objects;

/**
 * Created by macaque on 09.07.2016.
 */
public interface IValidator {
    ResultValidator validation(Address address);
    ResultValidator validation(Client client);
    ResultValidator validation(Product product);
    ResultValidator validation(PostTicket postTicket);
}
