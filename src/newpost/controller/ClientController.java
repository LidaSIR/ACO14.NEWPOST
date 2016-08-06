package newpost.controller;

import newpost.controller.interfaces.IClientController;
import newpost.db.AppDataContainer;
import newpost.model.common.Address;
import newpost.model.common.MyDate;
import newpost.model.common.Product;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.model.office.TicketStatus;

import java.util.Calendar;
import java.util.GregorianCalendar;

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


        Calendar calendar = GregorianCalendar.getInstance();
        MyDate currentTime = new MyDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

        MyDate estimationArrivalDate = currentTime;
        estimationArrivalDate.setDay(currentTime.getDay()+DAYS_IN_ROAD);

        Product sendProduct = new Product(product.getName(), product.getSize(), product.getPrice(), client);
        Product[] sendProductArr = {sendProduct};
        PostTicket postTicket = new PostTicket(client, sendProductArr, new Address("Kiyv","Lesi","22"), sendToAddress,
                currentTime, estimationArrivalDate);

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
