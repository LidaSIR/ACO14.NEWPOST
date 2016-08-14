package newpost.db;

import newpost.exceptions.NoTicketFoundException;
import newpost.model.common.Address;
import newpost.model.common.MyDate;
import newpost.model.common.Product;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.utils.TimeUtils;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Created by serhii on 13.08.16.
 */
public class NewDb {

    private Map<String, PostTicket> tickets = new TreeMap<>();

    public PostTicket findTicket(String ticketId) throws NoTicketFoundException {
        PostTicket postTicket = tickets.get(ticketId);

        if(postTicket == null) {
            throw new NoTicketFoundException("No ticket with id " + ticketId  );
        }

        return postTicket;
    }

    public PostTicket addPostTicket(Client client, Address from, Address sendToAddress, Product product, MyDate estimationDate, MyDate currentDate) {
        String ticketNewId = UUID.randomUUID().toString();

        PostTicket postTicket = new PostTicket(ticketNewId, client, new Product[]{product},
                from,sendToAddress, currentDate,estimationDate);

        tickets.put(ticketNewId, postTicket);

        return postTicket;
    }
}
