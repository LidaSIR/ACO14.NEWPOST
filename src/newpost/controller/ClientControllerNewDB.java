package newpost.controller;

import newpost.controller.interfaces.IClientController;
import newpost.db.NewDb;
import newpost.exceptions.AppException;
import newpost.exceptions.NoTicketFoundException;
import newpost.exceptions.ValidationException;
import newpost.model.common.Address;
import newpost.model.common.Product;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.model.office.TicketStatus;

import java.util.List;

/**
 * Created by serhii on 13.08.16.
 */
public class ClientControllerNewDB implements IClientController {

    private NewDb newDb;

    @Override
    public PostTicket makeOrder(Client client, Address sendToAddress, List<Product> product)
            throws AppException {

        PostTicket postTicket = null;//newDb.addPostTicket(client, sendToAddress, product);

        return postTicket;
    }

    @Override
    public PostTicket showTicketById(String ticketId) throws AppException {

        // validation
        // preprocess -> logging, prepare data, session

        try {
            PostTicket found = newDb.findTicket(ticketId);
            return found;
        } catch (NoTicketFoundException ex){
            throw new AppException(ex.getMessage());
        }
    }

    @Override
    public Product showProductById(int ticketId) throws AppException {
        try {
            PostTicket found = newDb.findTicket(String.valueOf(ticketId));
            return found.getProducts()[0];
        } catch (NoTicketFoundException ex){
            throw new AppException(ex.getMessage());
        }
    }

    @Override
    public boolean cancelTicket(int ticketId) throws AppException {
        try {
            PostTicket found = newDb.findTicket(String.valueOf(ticketId));
            found.setStatus(TicketStatus.CANCELED);
            return true;
        } catch (NoTicketFoundException ex){
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public Product takeProduct(int ticketId) throws AppException {
        return null;
    }
}
