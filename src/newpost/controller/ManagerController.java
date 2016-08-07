package newpost.controller;


import newpost.controller.interfaces.IManagerController;
import newpost.db.AppDataContainer;
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

/**
 * Created by home on 08.07.2016.
 */
public class ManagerController implements IManagerController {

    public static final int DAYS_IN_ROAD = 2;
    private final static String MAIL_SUBJECT = "Hello from ACO14 New Post";
    private final static String DEFAULT_MESSAGE_TEXT = "Hello dear {name}!!!\n" +
            "\n" +
            "Now your order in progress and your ticket number is:\n" +
            "{ticket}";



    protected AppDataContainer appDataContainer;
    protected Address addressFrom = DataInitFactory.createAddress();
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


        try {
            this.sendMail(client, postTicket);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return postTicket;
    }

    private boolean sendMail(Client client, PostTicket ticket) throws IOException {

        String mailText = DEFAULT_MESSAGE_TEXT.replace("{name}",client.getPassport().getFullname());
        mailText = mailText.replace("{ticket}",ticket.getId());

        if(client.getMail()==null) {
            return false;
        }


        SMTP.sendMail(client.getMail(),MAIL_SUBJECT, mailText);

        return true;
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

    public Client addClient(Passport passport, String phone, String mail) {
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
