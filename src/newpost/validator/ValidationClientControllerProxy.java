package newpost.validator;

import newpost.controller.interfaces.IClientController;
import newpost.exceptions.AppException;
import newpost.model.common.Address;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.model.common.Product;
import newpost.exceptions.AppException;

/**
 * Created by serhii on 10.07.16.
 */
public class ValidationClientControllerProxy implements IClientController {

    private IClientController controller;
    private IValidator validator;

    public ValidationClientControllerProxy(IClientController controller,
                                           IValidator validator) {
        this.controller = controller;
        this.validator = validator;
    }

    @Override
    public PostTicket makeOrder(Client client, Address sendToAdress, Product product) throws AppException {

        String errString = "";
        boolean errors;

        if (!validator.validation(client).getErr()) {
            errString += validator.validation(client).getTextErr();
        }
        if (!validator.validation(sendToAdress).getErr()) {
            if (errString.length() > 0) errString += "\n";
            errString += validator.validation(sendToAdress).getTextErr();
        }
        if (!validator.validation(product).getErr()) {
            if (errString.length() > 0) errString += "\n";
            errString += validator.validation(product).getTextErr();
        }

        if (errString.length() > 0) {
            throw new AppException(errString);
        }

        return controller.makeOrder(client, sendToAdress, product);
    }

    @Override
    public PostTicket showTicketById(String ticketId) throws AppException {
        try {
            int ticketID = Integer.parseInt(ticketId);
        } catch (NumberFormatException ex){
            throw new AppException("Validation: ticket Id is not numeric.");
        }

        if (ticketId.length() > 0){
            return controller.showTicketById(ticketId);
        } else {
            throw new AppException("Validation: ticket Id is empty");
        }
    }

    @Override
    public Product showProductById(int ticketId) throws AppException {
        if (ticketId > -1){
            return controller.showProductById(ticketId);
        } else {
            throw new AppException("Validation: ticket Id is less then 0");
        }
    }

    @Override
    public boolean cancelTicket(int ticketId) throws AppException {
        if (ticketId > -1){
            return controller.cancelTicket(ticketId);
        } else {
            throw new AppException("Validation: ticket Id is empty");
        }
    }

    @Override
    public Product takeProduct(int ticketId) throws AppException {
        if (ticketId > -1){
            return controller.takeProduct(ticketId);
        } else {
            throw new AppException("Validation: ticket Id is less than 0");
        }
    }
}
