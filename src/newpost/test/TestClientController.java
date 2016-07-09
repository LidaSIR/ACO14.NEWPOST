package newpost.test;

import newpost.controller.ClientController;
import newpost.db.AppDataContainer;
import newpost.model.*;

/**
 * Created by macaque on 09.07.2016.
 */
public class TestClientController {

    public static void main(String[] args) {
        testMakeOrder();
        testShowByProductId();
        testCancelTicket();

    }

    private static void testMakeOrder(){
        System.out.println("MakeOrder test");
        Size size = new Size(1,1,1,1);
        Passport passport = new Passport("Client1", "CM346452");
        Client client = new Client("+30502343433", passport);
        int price = 5;
        Product product = new Product("Monitor",size,price,client);
        Address toAddress = new Address("Kiev", "Khreshatyk", "5");

        ClientController clientController = new ClientController(new AppDataContainer());
        PostTicket actual = clientController.makeOrder(client,toAddress,product);

        // need to put logic about ID, Address, Product, etc. here
        if (actual != null){
            System.out.println("test passed "  + "\n" + actual.asString() + "\n");
        } else System.out.println("failed");
    }

    public  static void testShowByProductId(){
        System.out.println("ShowByProductId test");
        ClientController clientController = new ClientController(new AppDataContainer());

        if (clientController.showProductById(0) != null){
            System.out.println("test passed" + "\n");
        } else System.out.println("test false" + "\n");
    }

    public static void testCancelTicket(){
        System.out.println("Cancellation test");
        ClientController clientController = new ClientController(new AppDataContainer());
        if (clientController.cancelTicket(0)){
            System.out.println("test passed" +"\n");
        } else System.out.println("test false" + "\n");
    }
}