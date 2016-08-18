package newpost.controller.interfaces;

import newpost.db.AppDataContainer;
import newpost.exceptions.ValidationException;
import newpost.model.common.Address;
import newpost.model.common.Passport;
import newpost.model.common.Product;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;

import java.util.List;

/**
 * Created by macaque on 10.07.2016.
 */
public interface IManagerController {
    PostTicket createTicket(Client client, Address sendToAdress, List<Product> product) throws ValidationException;
    PostTicket filterTicketById(String ticketId) throws ValidationException;
    List<PostTicket> showTicketByClientPhone(String phone) throws ValidationException;
    Client getClient(String phone) throws ValidationException;
    Client addClient (Passport passport, String phone) throws ValidationException;
    Client addClient (Passport passport, String phone, String mail) throws ValidationException;
    List<PostTicket> sortTicketsByAddress();
    List<Client>sortClientsByName();
    List<PostTicket> sortTicketsByPrice();
    List<PostTicket> sortTicketsById();
    List findByPrice(int price);
    List<PostTicket> findByAddress(Address address);
    List<PostTicket> findByCity(String city);
    List<PostTicket> findByOwnerName( String name);


}
