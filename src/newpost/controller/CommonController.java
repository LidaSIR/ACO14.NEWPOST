package newpost.controller;

import newpost.exceptions.ValidationException;
import newpost.model.common.Address;
import newpost.model.common.Product;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;

/**
 * Created by Lida on 24.07.2016.
 */
abstract class CommonController implements IClientController, IManagerController {
    public CommonController() {
        super();
    }

    @Override
    public PostTicket makeOrder(Client client, Address sendToAddress, Product product) throws ValidationException {
        return null;
    }


    @Override
    public PostTicket showTicketById(String ticketId) throws ValidationException {
        return null;
    }
}
