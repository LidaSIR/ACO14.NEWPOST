package newpost.controller.proxy;

import newpost.controller.interfaces.IClientController;
import newpost.exceptions.AppException;
import newpost.model.common.Address;
import newpost.model.common.Product;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.utils.logging.LogContainer;

import java.util.List;

/**
 * Created by macaque on 09.07.2016.
 */
public class LoggingClientControllerProxy implements IClientController {

    protected IClientController originalClientController;

    public LoggingClientControllerProxy(IClientController clientController) {
        originalClientController = clientController;
    }

    @Override
    public PostTicket makeOrder(Client client, Address sendToAddress, List<Product> product) throws AppException {
        String productsString = "";
        for (Product p : product) {
            productsString+=p.getName() + ", ";
        }
        productsString = productsString.substring(0, productsString.length()-1);

        LogContainer.logEvent("Client: Client " + client.getPassport().getFullname() + " have made order to " + sendToAddress.getCity() + " for product " + productsString);
        return originalClientController.makeOrder(client, sendToAddress, product);
    }

    @Override
    public PostTicket showTicketById(String ticketId) throws AppException {
        LogContainer.logEvent("Client: Attempt to show ticket by Id " + ticketId);
        return originalClientController.showTicketById(ticketId);
    }

    @Override
    public Product showProductById(int ticketId) throws AppException {
        LogContainer.logEvent("Client: Attempt to show product by Id " + ticketId);
        return originalClientController.showProductById(ticketId);
    }

    @Override
    public boolean cancelTicket(int ticketId) throws AppException {
        LogContainer.logEvent("Client: Attempt to cancel ticket by Id " + ticketId);
        return originalClientController.cancelTicket(ticketId);
    }

    @Override
    public Product takeProduct(int ticketId) throws AppException {
        LogContainer.logEvent("Client: Attempt to take ticket by Id " + ticketId);
        return originalClientController.takeProduct(ticketId);
    }

    @Override
    public List<PostTicket> showAllClientTickets(Client client) {
        return null;
    }
}
