package newpost.controller;

import newpost.db.AppDataContainer;
import newpost.model.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by macaque on 09.07.2016.
 */
public class ClientController implements IClientController {

    private AppDataContainer appDataContainer;

    public ClientController(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }

    @Override
    public PostTicket makeOrder(Client client, Address sendToAdress, Product product) {


        Calendar calendar = GregorianCalendar.getInstance();
        MyDate currentTime = new MyDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

        MyDate estimationArrivalDate = currentTime;
        estimationArrivalDate.setDay(currentTime.getDay()+2);

        Product sendProduct = new Product(product.getName(), product.getSize(), product.getPrice(), client);
        Product[] sendProductArr = {sendProduct};

        PostTicket postTicket = new PostTicket(client, sendProductArr, new Address("Kiyv","Lesi","22"), sendToAdress,
                currentTime, estimationArrivalDate);

        return postTicket;
    }

    @Override
    public Product showProductById(int productId) {

        for(PostTicket postTicket : appDataContainer.getTickets()) {
            if(postTicket.getId().equalsIgnoreCase(postTicket.getId())){
                return postTicket.getProducts()[0];
            }
        }

        return null;
    }

    @Override
    public boolean cancelTicket(int ticketId) {

        for(PostTicket postTicket : appDataContainer.getTickets()) {
            if(postTicket.getId().equalsIgnoreCase(postTicket.getId())){
                postTicket.setStatus(TicketStatus.CANCELED);
                return true;
            }
        }

        return false;
    }

    @Override
    public Product takeProduct(int ticketId) {
        for(PostTicket postTicket : appDataContainer.getTickets()) {
            if(postTicket.getId().equalsIgnoreCase(postTicket.getId())){
                if(postTicket.getStatus()==TicketStatus.DONE) {
                    return postTicket.getProducts()[0];
                }

            }
        }

        return null;
    }

}
