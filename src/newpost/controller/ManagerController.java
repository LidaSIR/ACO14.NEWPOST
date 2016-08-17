package newpost.controller;


import newpost.controller.interfaces.IManagerController;
import newpost.db.AppDataContainer;
import newpost.exceptions.ValidationException;
import newpost.filter.*;
import newpost.model.common.Address;
import newpost.model.common.MyDate;
import newpost.model.common.Passport;
import newpost.model.common.Product;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.test.utils.TestSMTP;
import newpost.utils.email.smtp.SMTP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by home on 08.07.2016.
 */
public class ManagerController implements IManagerController {

    public static final int DAYS_IN_ROAD = 2;
    private static final String DEFAULT_ATTACHMENT = "resources/orderTemplate.rtf";



    protected AppDataContainer appDataContainer;
    protected Address addressFrom = DataInitFactory.createAddress();
    public ManagerController(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }

    @Override
    public PostTicket createTicket(Client client, Address sendToAdress, List<Product> product) {
        Calendar calendar = GregorianCalendar.getInstance();
        MyDate currentTime = new MyDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

        MyDate estimationArrivalDate = currentTime;
        estimationArrivalDate.setDay(currentTime.getDay()+ DAYS_IN_ROAD);

        Product[] productsArray = product.toArray(new Product[product.size()]);

        PostTicket postTicket = new PostTicket(client, productsArray, addressFrom, sendToAdress,
                currentTime, estimationArrivalDate);

        appDataContainer.getTickets().add(postTicket);

        try {
            SMTP.sendMail(client,postTicket,DEFAULT_ATTACHMENT);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    @Override
    public Client addClient(Passport passport, String phone, String mail) throws ValidationException {
        Client client = new Client(phone, passport, mail);
        appDataContainer.getClients().add(client);
        return client;
    }


    public List<PostTicket> sortTicketsByAddress() {
         List <PostTicket> tickets = new ArrayList<>();
        tickets = appDataContainer.getTickets();
        tickets.sort(new AddressComparator());
       return tickets;
    }

    @Override
    public List<Client> sortClientsByName() {
        List <Client> clients = new ArrayList<>();
        clients = appDataContainer.getClients();
        clients.sort(new OwnerNameComparator());
        return clients;
    }

    @Override
    public List<PostTicket> sortTicketsByPrice(){
        List<PostTicket> tickets= new ArrayList<>();
        tickets = appDataContainer.getTickets();
        tickets.sort(new PriceComparator());
        return tickets;
    }

    @Override
    public List<PostTicket> sortTicketsById() {
        List<PostTicket> tickets= new ArrayList<>();
        tickets = appDataContainer.getTickets();
        tickets.sort(new TicketIdComparator());
       return tickets;
    }

    @Override
    public List findByPrice(int price) {

        return Finder.findByPrice(appDataContainer, price);
    }

    public ManagerController() {
        super();
    }

    @Override
    public List<PostTicket> findByAddress(Address address) {
        return  Finder.findByAddress(appDataContainer,address);
    }

    @Override
    public List<PostTicket> findByCity(String city) {
        return Finder.findByCity(appDataContainer,city);
    }

    @Override
    public List<PostTicket> findByOwnerName(String name) {
        return Finder.findByOwnerName(appDataContainer,name);
    }

}
