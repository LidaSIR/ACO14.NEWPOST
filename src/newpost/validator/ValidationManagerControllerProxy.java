package newpost.validator;

import newpost.controller.interfaces.IManagerController;
import newpost.exceptions.ValidationException;
import newpost.model.common.Address;
import newpost.model.common.Passport;
import newpost.model.common.Product;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;

import java.util.List;

/**
 * Created by Vladislav on 19.07.2016.
 */
public class ValidationManagerControllerProxy implements IManagerController {

    private IManagerController managerController;
    private Validator validator;

    public ValidationManagerControllerProxy(IManagerController originalController, Validator validator) {
        this.managerController = originalController;
        this.validator = validator;
    }

    @Override
    public PostTicket createTicket(Client client, Address sendToAdress, Product product) throws ValidationException {

        String err = "";

        if (!validator.validation(client).getErr()) err += validator.validation(client).getTextErr();
        if (!validator.validation(sendToAdress).getErr()) err += validator.validation(sendToAdress).getTextErr();
        if (!validator.validation(product).getErr()) err += validator.validation(product).getTextErr();

        if (err.length() > 0) return managerController.createTicket(client, sendToAdress, product);
        else throw new ValidationException(err);
    }

    @Override
    public PostTicket filterTicketById(String ticketId) throws ValidationException {
        try {
            int num = Integer.parseInt(ticketId);
            PostTicket pt = managerController.filterTicketById(ticketId);
            if (pt != null) {
                return pt;
            } else {
                throw new ValidationException("No ticket was found per inputted Id.");
            }
        } catch (NumberFormatException ex) {
            throw new ValidationException("Inputted ticket Id is not numeric.");
        }
    }

    @Override
    public PostTicket showTicketByClientPhone(String phone) throws ValidationException {
        if (validator.isPhone(phone)) {
            PostTicket ps = managerController.showTicketByClientPhone(phone);
            if (ps != null) {
                return ps;
            } else {
                throw new ValidationException("No ticket was found per inputted phone.");
            }
        } else {
            throw new ValidationException("Phone number is not correct. Please verify.");
        }
    }

    @Override
    public Client getClient(String phone) throws ValidationException {
        if (validator.isPhone(phone)) {
            Client cl = managerController.getClient(phone);
            if (cl != null) {
                return cl;
            } else {
                throw new ValidationException("No client was found per inputted Id.");
            }
        } else {
            throw new ValidationException("Phone number is not correct. Please verify.");
        }
    }

    @Override
    public Client addClient(Passport passport, String phone) throws ValidationException {

        String err = "";

        if (validator.isPhone(phone)) {
        } else {
            err += "Phone number is not correct. Please verify.";
        }
        if (validator.isPassport(passport)) {
        } else {
            if (err.length() > 0) err += "\n";
            err += "Passport info or full name is not correct. Please verify.";
        }

        if (err.length() > 0) {
            throw new ValidationException(err);
        } else {
            return managerController.addClient(passport, phone);
        }
    }

    @Override
    public Client addClient(Passport passport, String phone, String mail) throws ValidationException {

        String err = "";

        if (validator.isPhone(phone)) {
        } else {
            err += "Phone number is not correct. Please verify.";
        }
        if (validator.isPassport(passport)) {
        } else {
            if (err.length() > 0) err += "\n";
            err += "Passport info or full name is not correct. Please verify.";
        }

        if (err.length() > 0) {
            throw new ValidationException(err);
        } else {
            return managerController.addClient(passport, phone, mail);
        }
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

    public ValidationManagerControllerProxy() {
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

}
