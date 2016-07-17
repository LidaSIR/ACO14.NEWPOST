package newpost.filter;

import newpost.model.Address;
import newpost.model.Client;
import newpost.model.PostTicket;

import java.util.Comparator;

/**
 * Created by Serhii Fursenko on 16.07.2016.
 * fyrsenko@gmail.com
 */
public class AddressComparator implements Comparator<PostTicket>{

    @Override
    public int compare(PostTicket o1, PostTicket o2) {
        return o1.getFrom().getCity().compareTo(o2.getFrom().getCity());
    }
}
