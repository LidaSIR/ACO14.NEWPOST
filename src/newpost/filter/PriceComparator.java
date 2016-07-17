package newpost.filter;

import newpost.model.PostTicket;
import newpost.model.Product;

import java.util.Comparator;

/**
 * Created by Serhii Fursenko on 16.07.2016.
 * fyrsenko@gmail.com
 */
public class PriceComparator implements Comparator<PostTicket>{

    @Override
    public int compare(PostTicket o1, PostTicket o2) {
        if(o1.getPrice()<o2.getPrice()) {
            return -1;
        } else if(o1.getPrice()>o2.getPrice()) {
            return 1;
        }
        return 0;
    }
}
