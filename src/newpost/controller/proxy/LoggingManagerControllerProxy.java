package newpost.controller.proxy;

import newpost.controller.interfaces.IManagerController;
import newpost.exceptions.ValidationException;
import newpost.model.common.Address;
import newpost.model.common.Passport;
import newpost.model.common.Product;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.utils.logging.LogContainer;

import java.util.List;

/**
 * Created by Vladislav on 20.07.2016.
 */
public class LoggingManagerControllerProxy implements IManagerController {

    protected IManagerController managerController;

    public LoggingManagerControllerProxy(IManagerController controller) {
        managerController = controller;
    }

    @Override
    public PostTicket createTicket(Client client, Address sendToAdress, Product product) throws ValidationException {
        LogContainer.logEvent("Manager: there was an attempt to create ticket with client number " + client.getPhone() + ", destination " + sendToAdress.getCity() + ", product " + product.getName());
        return managerController.createTicket(client, sendToAdress, product);
    }

//    @Override
//    public PostTicket filterTicketById(String ticketId) throws ValidationException {
//        LogContainer.logEvent("Manager: there was an attempt to filter ticket by Id " + ticketId);
//        return managerController.filterTicketById(ticketId);
//    }

    @Override
    public PostTicket showTicketByClientPhone(String phone) throws ValidationException {
        LogContainer.logEvent("Manager: there was an attempt to show ticket by client phone " + phone);
        return managerController.showTicketByClientPhone(phone);
    }

    @Override
    public Client getClient(String phone) throws ValidationException {
        LogContainer.logEvent("Manager: there was an attempt to get client by phone " + phone);
        return managerController.getClient(phone);
    }

    @Override
    public Client addClient(Passport passport, String phone) throws ValidationException {
        LogContainer.logEvent("Manager: there was an attempt to add client with passport " + passport.getNumber() + " and phone " + phone);
        return managerController.addClient(passport, phone);
    }

    @Override
    public List<PostTicket> sortTicketsByAddress() {
        return null;
    }

    @Override
    public List<Client> sortClientsByName() {
        return null;
    }

    @Override
    public List<PostTicket> sortTicketsByPrice() {
        return null;
    }

    @Override
    public List<PostTicket> sortTicketsById() {
        return null;
    }

    @Override
    public List findByPrice(int price) {
        return null;
    }

    public LoggingManagerControllerProxy() {
        super();
    }

    @Override
    public List<PostTicket> findByAddress(Address address) {
        return null;
    }

    @Override
    public List<PostTicket> findByCity(String city) {
        return null;
    }

    @Override
    public List<PostTicket> findByOwnerName(String name) {
        return null;
    }

    @Override
    public PostTicket findById(String id) {
        return null;
    }
}
