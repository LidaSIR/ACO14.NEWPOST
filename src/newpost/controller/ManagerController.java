package newpost.controller;


import newpost.db.AppDataContainer;
import newpost.filter.AddressComparator;
import newpost.filter.Finder;
import newpost.filter.OwnerNameComparator;
import newpost.filter.PriceComparator;
import newpost.model.common.Address;
import newpost.model.common.MyDate;
import newpost.model.common.Passport;
import newpost.model.common.Product;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by home on 08.07.2016.
 */
public class ManagerController implements IManagerController {

    public static final int DAYS_IN_ROAD = 2;

    private AppDataContainer appDataContainer;
    private Address addressFrom = DataInitFactory.createAddress();
    public ManagerController(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }

    @Override
    public PostTicket createTicket(Client client, Address sendToAdress, Product product) {
        Calendar calendar = GregorianCalendar.getInstance();
        MyDate currentTime = new MyDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

        MyDate estimationArrivalDate = currentTime;
        estimationArrivalDate.setDay(currentTime.getDay()+ DAYS_IN_ROAD);

        Product sendProduct = new Product(product.getName(), product.getSize(), product.getPrice(), client);
        Product[] sendProductArr = {sendProduct};

        PostTicket postTicket = new PostTicket(client, sendProductArr, addressFrom, sendToAdress,
                currentTime, estimationArrivalDate);

        appDataContainer.getTickets().add(postTicket);

        return postTicket;
    }

    @Override
    public PostTicket filterTicketById(String ticketId) {

        for(PostTicket postTicket : appDataContainer.getTickets()) {
            if (postTicket.getId().equals(String.valueOf(ticketId))) {
                return postTicket;
            }

        }
        return null;
    }

    @Override
    public PostTicket showTicketByClientPhone(String phone) {
        for(PostTicket postTicket : appDataContainer.getTickets()) {
            if(postTicket.getClient().getPhone().equals(String.valueOf(phone))){
                return postTicket;
            }
        }
        return null;
    }

    @Override
    public Client getClient(String phone) {

        List<Client> clientArr = appDataContainer.getClients();
        for(Client client : clientArr) {
            Client iterClient = client;
            if(iterClient.getPhone().equals(phone)) {
                return iterClient;
            }
        }
        return null;
    }

    @Override
    public Client addClient(Passport passport, String phone) {
        Client client = new Client(phone, passport);
        appDataContainer.getClients().add(client);
        return client;
    }

    public void sortTicketsByAddress() {
        appDataContainer.getTickets().sort(new AddressComparator());
    }

    @Override
    public void sortClientsByName() {
        appDataContainer.getClients().sort(new OwnerNameComparator());
    }

    @Override
    public void sortTicketsByPrice() {
        appDataContainer.getTickets().sort(new PriceComparator());
    }

    @Override
    public void sortTicketsById() {
        appDataContainer.getTickets().sort(new PriceComparator());
    }

    @Override
    public List findByPrice(int price) {

        return Finder.findByPrice(appDataContainer, price);
    }

}
