package newpost.filter;

import newpost.model.Client;
import newpost.model.PostTicket;

import java.util.List;

/**
 * Created by Serhii Fursenko on 16.07.2016.
 * fyrsenko@gmail.com
 */
public abstract class Sorting {

    public static void sortByAddress(List<PostTicket> tickets) {
        tickets.sort(new AddressComparator());
    }
}
