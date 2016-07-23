package newpost.validator;

import newpost.model.common.Address;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.model.common.Product;

/**
 * Created by macaque on 09.07.2016.
 */
public interface IValidator {
    ResultValidator validation(Address address);
    ResultValidator validation(Client client);
    ResultValidator validation(Product product);
    ResultValidator validation(PostTicket postTicket);
}
