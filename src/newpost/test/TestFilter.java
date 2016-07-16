package newpost.test;

import newpost.db.AppDataContainer;
import newpost.filter.AddressComparator;
import newpost.filter.OwnerNameComparator;
import newpost.filter.PriceComparator;
import newpost.filter.TicketIdComparator;
import newpost.model.*;

/**
 * Created by Serhii Fursenko on 16.07.2016.
 * fyrsenko@gmail.com
 */
public class TestFilter {

    public static void main(String[] args) {

        AppDataContainer appDataContainer = new AppDataContainer();
        fillDB(appDataContainer);


        //testSortingByAddress();
        //testSortingByOwnerName();
        testSortingByPrice(appDataContainer);
        testSortingByTicketId(appDataContainer);

    }

    public static void fillDB(AppDataContainer appDataContainer) {
        Client client1 = new Client("0935330230", new Passport("Ivanov Denis", "CN203022"));
        Address address1 = new Address("Yalta", "Petra", "20");

        Client client2 = new Client("0502392309", new Passport("Anfisov Oleg", "KB0239023"));
        Address address2 = new Address("Borispol", "Franka", "18");

        Client client3 = new Client("0662390909", new Passport("Zibrov Ivan", "LS238476"));
        Address address3 = new Address("Odesa", "Lesi", "1");

        Size size = new Size(1, 1, 1, 1);

        Product product1 = new Product("IPhone 6", size, 6000, client1);
        Product product2 = new Product("IPhone 6s", size, 8000, client2);
        Product product3 = new Product("IPhone 6 plus", size, 10000, client2);
        Product product4 = new Product("IPhone 5", size, 4000, client3);
        Product product5 = new Product("IPhone 5s", size, 5000, client3);
        Product product6 = new Product("IPhone 5c", size, 3000, client3);


        PostTicket postTicket1 = new PostTicket(client1, new Product[]{product1}, address1, address1,
                new MyDate(2000, 10, 1, 20, 0), new MyDate(2000, 10, 1, 20, 0));

        PostTicket postTicket2 = new PostTicket(client2, new Product[]{product2, product3}, address2, address2,
                new MyDate(2000, 10, 1, 20, 0), new MyDate(2000, 10, 1, 20, 0));


        PostTicket postTicket3 = new PostTicket(client3, new Product[]{product4, product5, product6}, address3, address3,
                new MyDate(2000, 10, 1, 20, 0), new MyDate(2000, 10, 1, 20, 0));

        appDataContainer.getTickets().add(postTicket1);
        appDataContainer.getTickets().add(postTicket2);
        appDataContainer.getTickets().add(postTicket3);

        appDataContainer.getClients().add(client1);
        appDataContainer.getClients().add(client2);
        appDataContainer.getClients().add(client3);
    }


    public static void showTickets(AppDataContainer appDataContainer) {
        for (PostTicket postTicket : appDataContainer.getTickets()) {
            System.out.printf("Id: %s, from: %s, price: %d\n", postTicket.getId(),
                    postTicket.getFrom().getCity(), postTicket.getPrice());
        }
    }

    public static void showClients(AppDataContainer appDataContainer) {
        int index = 1;
        for (Client client : appDataContainer.getClients()) {
            System.out.printf("%d. %s\n", index++, client.getPassport().getFullname());
        }
    }

    public static void showProducts(AppDataContainer appDataContainer){
        for (PostTicket postTicket : appDataContainer.getTickets()) {
            System.out.printf("Id: %s, price : %s\n", postTicket.getId(), postTicket.getPrice());
        }
    }

    public static void testSortingByAddress(AppDataContainer appDataContainer) {
        appDataContainer = new AppDataContainer();

        System.out.println("Before sorting by city:\n");
        showTickets(appDataContainer);

        appDataContainer.getTickets().sort(new AddressComparator());

        System.out.println("\nAfter sorting by city:\n");
        showTickets(appDataContainer);
    }


    public static void testSortingByOwnerName(AppDataContainer appDataContainer) {
        appDataContainer = new AppDataContainer();

        System.out.println("Before sorting by owner name");
        showClients(appDataContainer);

        appDataContainer.getClients().sort(new OwnerNameComparator());

        System.out.println("\nAfter sorting by owner name:\n");
        showClients(appDataContainer);
    }

    public static void testSortingByPrice(AppDataContainer appDataContainer) {
        appDataContainer = new AppDataContainer();

        System.out.println("Before sorting by price");
        showTickets(appDataContainer);

        appDataContainer.getTickets().sort(new PriceComparator());

        System.out.println("\nAfter sorting by price:\n");
        showTickets(appDataContainer);

    }

    private static void testSortingByTicketId(AppDataContainer appDataContainer) {

        appDataContainer = new AppDataContainer();

        System.out.println("Before sorting by id");
        showTickets(appDataContainer);

        appDataContainer.getTickets().sort(new TicketIdComparator());

        System.out.println("\nAfter sorting by id:\n");
        showTickets(appDataContainer);

    }

}
