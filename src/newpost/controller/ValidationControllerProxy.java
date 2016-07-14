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

        if (validator.validation(client).getErr() && validator.validation(sendToAdress).getErr()
                && validator.validation(product).getErr()){
            return controller.makeOrder(client, sendToAdress, product);
        }
        System.out.println(validator.validation(client).getTextErr());
        System.out.println(validator.validation(sendToAdress).getTextErr());
        System.out.println(validator.validation(product).getTextErr());;

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
