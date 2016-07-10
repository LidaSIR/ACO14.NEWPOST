package newpost.controller;

import newpost.model.Address;
import newpost.model.Client;
import newpost.model.PostTicket;
import newpost.model.Product;

/**
 * Created by serhii on 10.07.16.
 */
public class ValidationControllerProxy implements IClientController {

    private IClientController controller;
    private IValidator validator;

    public ValidationControllerProxy(IClientController controller,
                                     IValidator validator) {
        this.controller = controller;
        this.validator = validator;
    }

    @Override
    public PostTicket makeOrder(Client client, Address sendToAdress, Product product) {
        if(validator.validation(client)
                && validator.validation(sendToAdress)
                && validator.validation(product)){
            return controller.makeOrder(client, sendToAdress, product);
        }

        System.out.println("Error while validation");

        return null;
    }

    @Override
    public PostTicket showTicketById(String ticketId) {
        return null;
    }

    @Override
    public Product showProductById(int ticketId) {
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
}
