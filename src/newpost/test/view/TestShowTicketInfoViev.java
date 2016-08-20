package newpost.test.view;

import newpost.model.common.*;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.view.frame.ShowTicketInfoView;

/**
 * Created by sasha on 20.08.2016.
 */
public class TestShowTicketInfoViev {

    public static void main(String[] args) {

        Address from = new Address("kiev", "starokievska", "10");

        Address to = new Address("poltava", "stepnaya", "12");

        Passport passport = new Passport("Petya Vasechkin", "as123456");
        Client client = new Client("0951234567", passport);

        Size size = new Size(1, 2, 3, 4);
        Product product1 = new Product("iPhone 7", size, 50, client);
        Product product2 = new Product("iPhone 6", size, 40, client);
        Product product3 = new Product("iPhone 5", size, 30, client);
        Product[] products = {product1, product2, product3};

        MyDate dateStart = new MyDate(2016, 7, 12, 13, 25);
        MyDate dateEnd = new MyDate(2016, 8, 12, 13, 30);

        PostTicket postTicket = new PostTicket(client, products, from, to, dateStart, dateEnd);

        ShowTicketInfoView showTicketInfoView = new ShowTicketInfoView(postTicket);

    }
}
