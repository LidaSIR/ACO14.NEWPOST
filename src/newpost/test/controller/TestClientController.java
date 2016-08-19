package newpost.test.controller;

import newpost.controller.ClientController;
import newpost.db.AppDataContainer;
import newpost.model.common.Address;
import newpost.model.common.Passport;
import newpost.model.common.Product;
import newpost.model.common.Size;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macaque on 09.07.2016.
 */
public class TestClientController {

    public static void main(String[] args) {
        ClientController clientController = new ClientController(new AppDataContainer());
        testMakeOrder(clientController);
        testShowByProductId(clientController);
        testCancelTicket(clientController);
        testTakeProduct(clientController);

    }

    private static void testMakeOrder(ClientController clientController){
        System.out.println("MakeOrder test:");
        Size size = new Size(1,1,1,1);
        Passport passport = new Passport("Client Name", "CM346452");
        Client client = new Client("380502343433", passport);
        int price = 500;
        Product product = new Product("Monitor",size,price,client);
        Address toAddress = new Address("Kiev", "Khreshatyk", "5");
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        PostTicket actual = clientController.makeOrder(client,toAddress, productList);

        // need to put logic about ID, Address, Product, etc. here
        if (actual != null){
            System.out.println("test passed "  + "\n" + actual.toString() + "\n");
        } else System.out.println("failed");
    }

    public static void testShowByProductId(ClientController clientController){
        System.out.println("ShowByProductId test:");
        if (clientController.showProductById(0) != null){
            System.out.println("test passed" + "\n");
        } else System.out.println("test false" + "\n");
    }

    public static void testCancelTicket(ClientController clientController){
        System.out.println("Cancellation test:");
        if (clientController.cancelTicket(0)){
            System.out.println("test passed" +"\n");

        } else System.out.println("test false" + "\n");
    }

    public static void testTakeProduct(ClientController clientController){
        System.out.println("TakeProduct test:");

        if (clientController.takeProduct(0) != null){
            System.out.println("test passed" +  "\n");
        } else System.out.println("test failed" + "\n");

    }

    public static void showTicketById(ClientController clientController){
        System.out.println("showTicketById test:");
        Product product = clientController.takeProduct(0);
        if (clientController.showProductById(0).equals(product)){
            System.out.println("test passed" + "\n");
        } else System.out.println("test false" + "\n");
    }
}
