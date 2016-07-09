package newpost.controller;

import newpost.model.Product;

/**
 * Created by macaque on 09.07.2016.
 */
public interface IClientController {
    boolean sendProduct(Product product);
    Product showProductById(int productId);
    boolean cancelTicket(int ticketId);
    Product takeProduct(int ticketId);
}
