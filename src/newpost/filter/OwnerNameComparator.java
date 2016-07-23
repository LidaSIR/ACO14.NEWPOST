package newpost.filter;

import newpost.model.office.Client;

import java.util.Comparator;

/**
 * Created by Serhii Fursenko on 16.07.2016.
 * fyrsenko@gmail.com
 */
public class OwnerNameComparator implements Comparator<Client> {
    @Override
    public int compare(Client o1, Client o2) {
        return o1.getPassport().getFullname().compareTo(o2.getPassport().getFullname());
    }
}
