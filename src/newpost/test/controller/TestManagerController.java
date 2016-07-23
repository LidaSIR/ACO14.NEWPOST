package newpost.test.controller;

import newpost.controller.ManagerController;
import newpost.db.AppDataContainer;
import newpost.model.common.Address;
import newpost.model.common.Passport;
import newpost.model.common.Product;
import newpost.model.common.Size;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;

/**
 * Created by home on 08.07.2016.
 */
public class TestManagerController {

    public static void main(String[] args) {

        ManagerController managerController = new ManagerController(new AppDataContainer());

        testAddClient(managerController);

        testGetClient(managerController);

        testCreateTicket(managerController);

        testFilterTicketById(managerController);

        testShowTicketByClientPhone(managerController);


    }

    public static void testAddClient(ManagerController managerController) {

        Passport in1 = new Passport("ER546454", "Bob Jason");
        String in2 = "0935551666";
        Client expected = new Client("0935551666", new Passport("ER546454", "Bob Jason"));

        Client actual = managerController.addClient(in1, in2);

        System.out.printf("%s, test add client in1 %s,\nexpected %s,\nactual %s\n",
                actual.toString().equals(expected.toString()), in1,  expected, actual);
        System.out.println();
    }

    private static void testGetClient(ManagerController managerController) {

        managerController.addClient(new Passport("ER546454", "Bob Jason"),"0935551666");

        String in = "0935551666";
        Client expected = new Client("0935551666", new Passport("ER546454", "Bob Jason"));
        Client actual = managerController.getClient(in);
        System.out.printf("%s, test get client in %s,\nexpected %s,\nactual %s\n",
                actual.toString().equals(expected.toString()), in,  expected, actual);
        System.out.println();
    }

    private static void testCreateTicket(ManagerController managerController) {

        System.out.println("Testing CreateTicket method:");

        Passport in1 = new Passport("Bob Jason", "ER546454");
        String in2 = "0935551666";
        Client expected = new Client("0935551666", new Passport("ER546454", "Bob Jason"));

        Address address = new Address("Kiyv", "Lesi", "2");

        Product product = new Product("ProductName", new Size(1,1,1,1), 1000, expected);

        PostTicket postTicket = managerController.createTicket(expected, address, product);

        if(!in1.getFullname().equals(postTicket.getClient().getPassport().getFullname())) {
            System.out.println("Not passed");
            System.out.println();
            return;
        }
        if(!in1.getNumber().equals(postTicket.getClient().getPassport().getNumber())) {
            System.out.println("Not passed");
            System.out.println();
            return;
        }
        if(!expected.getPhone().equals(postTicket.getClient().getPhone())) {
            System.out.println("Not passed");
            System.out.println();
            return;
        }
        if(!address.getCity().equals(postTicket.getTo().getCity())) {
            System.out.println("Not passed");
            System.out.println();
            return;
        }
        if(!address.getStreet().equals(postTicket.getTo().getStreet())) {
            System.out.println("Not passed");
            System.out.println();
            return;
        }
        if(!address.getHouseNum().equals(postTicket.getTo().getHouseNum())) {
            System.out.println("Not passed");
            System.out.println();
            return;
        }
        if(!product.getName().equals(postTicket.getProducts()[0].getName())) {
            System.out.println("Not passed");
            System.out.println();
            return;
        }
        if(product.getPrice() != (postTicket.getProducts()[0].getPrice())) {
            System.out.println("Not passed");
            System.out.println();
            return;
        }

        System.out.println("Passed");
        System.out.println();
    }


    private static void testFilterTicketById(ManagerController managerController){

        System.out.println("Testing FilterTicketById method:");

        Passport in1 = new Passport("ER546454", "Bob Jason");
        String in2 = "0935551666";
        Client client = new Client("0935551666", new Passport("ER546454", "Bob Jason"));

        Address address = new Address("Kiyv", "Lesi", "2");

        Product product = new Product("ProductName", new Size(1,1,1,1), 1000, client);

        PostTicket postTicketExpected = managerController.createTicket(client, address, product);

        PostTicket postTicketActual = managerController.filterTicketById("1");
        if(postTicketActual.getId().equals(postTicketExpected.getId())) {
            System.out.println("Passed");
            System.out.println();
        }else {
            System.out.println("Not passed");
            System.out.println();
        }
    }

    private static void testShowTicketByClientPhone(ManagerController managerController){
        System.out.println("Testing ShowTicketByClientPhone method:");

        Passport in1 = new Passport("ER546454", "Bob Jason");
        String in2 = "0935551666";
        Client client = new Client("0935551666", new Passport("ER546454", "Bob Jason"));

        Address address = new Address("Kiyv", "Lesi", "2");

        Product product = new Product("ProductName", new Size(1,1,1,1), 1000, client);

        PostTicket postTicketExpected = managerController.createTicket(client, address, product);

        PostTicket postTicketActual = managerController.showTicketByClientPhone(in2);


        if(!in1.getFullname().equals(postTicketActual.getClient().getPassport().getFullname())) {
            System.out.println("Not passed");
            System.out.println();
            return;
        }
        if(!in1.getNumber().equals(postTicketActual.getClient().getPassport().getNumber())) {
            System.out.println("Not passed");
            System.out.println();
            return;
        }
        if(!in2.equals(postTicketActual.getClient().getPhone())) {
            System.out.println("Not passed");
            System.out.println();
            return;
        }
        if(!address.getCity().equals(postTicketActual.getTo().getCity())) {
            System.out.println("Not passed");
            System.out.println();
            return;
        }
        if(!address.getStreet().equals(postTicketActual.getTo().getStreet())) {
            System.out.println("Not passed");
            System.out.println();
            return;
        }
        if(!address.getHouseNum().equals(postTicketActual.getTo().getHouseNum())) {
            System.out.println("Not passed");
            System.out.println();
            return;
        }
        if(!product.getName().equals(postTicketActual.getProducts()[0].getName())) {
            System.out.println("Not passed");
            System.out.println();
            return;
        }
        if(product.getPrice() != (postTicketActual.getProducts()[0].getPrice())) {
            System.out.println("Not passed");
            System.out.println();
            return;
        }

        System.out.println("Passed");
        System.out.println();
    }

}
