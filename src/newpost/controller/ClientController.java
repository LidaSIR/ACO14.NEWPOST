package newpost.controller;

import newpost.controller.interfaces.IClientController;
import newpost.db.AppDataContainer;
import newpost.model.common.Address;
import newpost.model.common.MyDate;
import newpost.model.common.Product;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.model.office.TicketStatus;
import newpost.utils.TimeUtils;

/**
 * Created by macaque on 09.07.2016.
 */
public class ClientController implements IClientController {

    public static final int DAYS_IN_ROAD = 2;

    protected AppDataContainer appDataContainer;

    public ClientController(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }

    @Override
    public PostTicket makeOrder(Client client, Address sendToAddress, Product product) {


        MyDate currentTime = TimeUtils.getCurrentDate();

        currentTime.setDay(currentTime.getDay() + DAYS_IN_ROAD);

        Product sendProduct = new Product(product.getName(), product.getSize(), product.getPrice(), client);
        Product[] sendProductArr = {sendProduct};
        PostTicket postTicket = new PostTicket(client, sendProductArr, new Address("Kiyv","Lesi","22"), sendToAddress,
                currentTime, currentTime);

        appDataContainer.getTickets().add(postTicket);

        return postTicket;
    }

    @Override
    public PostTicket showTicketById(String ticketId) {
        for(PostTicket postTicket : appDataContainer.getTickets()) {
            if(postTicket.getId().equals(String.valueOf(ticketId))){
                return postTicket;
            }
        }
        return null;
    }

    @Override
    public Product showProductById(int ticketId) {

        for(PostTicket postTicket : appDataContainer.getTickets()) {
            if(postTicket.getId().equals(String.valueOf(ticketId))){
                return postTicket.getProducts()[0];
            }
        }

        return null;
    }

    @Override
    public boolean cancelTicket(int ticketId) {

        for(PostTicket postTicket : appDataContainer.getTickets()) {
            if(postTicket.getId().equals(String.valueOf(ticketId))){
                postTicket.setStatus(TicketStatus.CANCELED);
                return true;
            }
        }

        return false;
    }

    @Override
    public Product takeProduct(int ticketId) {
        for(PostTicket postTicket : appDataContainer.getTickets()) {
            if(postTicket.getId().equals(String.valueOf(ticketId))){
                if(postTicket.getStatus()==TicketStatus.DONE || postTicket.getStatus()==TicketStatus.CANCELED) {
                    return postTicket.getProducts()[0];
                }

            }
        }

        return null;
    }

}
