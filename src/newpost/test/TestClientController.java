package newpost.test;

import newpost.controller.ClientController;
import newpost.model.*;

/**
 * Created by macaque on 09.07.2016.
 */
public class TestClientController {

    public static void main(String[] args) {

    }

    private static boolean testMakeOrder(){
        Size size = new Size(1,1,1,1);
        Passport passport = new Passport("Client1", "CM346452");
        Client client = new Client("+30502343433", passport);
        int price = 5;
        Product product = new Product("Monitor",size,price,client);
        Address toAddress = new Address("Kiev", "Khreshatyk", "5");

        ClientController clientController = new ClientController();
        PostTicket actual = clientController.makeOrder(client,toAddress,product);
        if (actual.)

    }
}