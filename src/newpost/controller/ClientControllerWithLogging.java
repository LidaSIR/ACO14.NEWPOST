package newpost.controller;

import newpost.db.AppDataContainer;
import newpost.model.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by macaque on 09.07.2016.
 */
public class ClientControllerWithLogging implements IClientController {

    private IClientController originalClientController;

    public ClientControllerWithLogging(IClientController clientController)
    {
        originalClientController = clientController;
    }

    @Override
    public PostTicket makeOrder(Client client, Address sendToAdress, Product product) {
        Logging.logEvent("Client " + client.getPassport().getFullname() + " have made order to " + sendToAdress.getCity() + " for product " + product.getName());
        return originalClientController.makeOrder(client, sendToAdress, product);
    }

    @Override
    public PostTicket showTicketById(String ticketId) {
        Logging.logEvent("Attempt to show ticket by Id " + ticketId);
        return originalClientController.showTicketById(ticketId);
    }

    @Override
    public Product showProductById(int ticketId) {
        Logging.logEvent("Attempt to show product by Id " + ticketId);
        return originalClientController.showProductById(ticketId);
    }

    @Override
    public boolean cancelTicket(int ticketId) {
        Logging.logEvent("Attempt to cancel ticket by Id " + ticketId);
        return originalClientController.cancelTicket(ticketId);
    }

    @Override
    public Product takeProduct(int ticketId) {
        Logging.logEvent("Attempt to take ticket by Id " + ticketId);
        return originalClientController.takeProduct(ticketId);
    }
}
