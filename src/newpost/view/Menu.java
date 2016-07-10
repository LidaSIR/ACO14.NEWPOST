package newpost.view;

import newpost.controller.IClientController;
import newpost.model.*;

import java.util.Scanner;
/**
 * Created by Vladislav on 09.07.2016.
 */
public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private IClientController clientController;


    public void start(IClientController controller){
        clientController = controller;

        while(true){
            showMainMenu();

            int choice = scanner.nextInt();

            if(choice == 1){
                showAddTicketMenu();
            } else if(choice == 2){
                showShowInfoMenu();
            } else if(choice == 3){
                showCancelTicketMenu();
            } else if(choice == 0){
                break;
            }

        }
    }

    private void showShowInfoMenu() {

        System.out.println("Show info: input product Id to show ");
        String productId;
        productId = scanner.next();

        clientController.showProductById(Integer.parseInt(productId));

    }

    private void showCancelTicketMenu() {

        System.out.println("Cancel: input product Id to cancel ");
        String productId;
        productId = scanner.next();

        clientController.cancelTicket(Integer.parseInt(productId));

    }

    private void showAddTicketMenu(){

        System.out.println("Client creation: input Client phone ");
        String clientPhone;
        clientPhone = scanner.next();
        System.out.println("Client creation: input Client full name ");
        String clientFullName;
        clientFullName = scanner.next();
        System.out.println("Client creation: input Client passport number ");
        String clientPassportNumber;
        clientPassportNumber = scanner.next();

        Client client = new Client(clientPhone, new Passport(clientFullName, clientPassportNumber));

        System.out.println("Address destination creation: input city ");
        String addressToCity;
        addressToCity = scanner.next();
        System.out.println("Address destination creation: input street ");
        String addressToStreet;
        addressToStreet = scanner.next();
        System.out.println("Address destination creation: input house number ");
        String addressToHouseNum;
        addressToHouseNum = scanner.next();

        Address addrTo = new Address(addressToCity, addressToStreet, addressToHouseNum);

        System.out.println("Product creation: input product name ");
        String productName;
        productName = scanner.next();
        System.out.println("Product creation: input product length ");
        String productLength;
        productLength = scanner.next();
        System.out.println("Product creation: input product width ");
        String productWidth;
        productWidth = scanner.next();
        System.out.println("Product creation: input product height ");
        String productHeight;
        productHeight = scanner.next();
        System.out.println("Product creation: input product weight ");
        String productWeight;
        productWeight = scanner.next();
        System.out.println("Product creation: input product price ");
        String productPrice;
        productPrice = scanner.next();

        Product product = new Product(productName,
                                        new Size(Integer.parseInt(productLength),
                                                Integer.parseInt(productWidth),
                                                Integer.parseInt(productHeight),
                                                Integer.parseInt(productWeight)),
                                        Integer.parseInt(productPrice),
                                        client);

        clientController.makeOrder(client, addrTo, product);

    }


    private void showMainMenu(){
        System.out.println("1. Add Ticket");
        System.out.println("2. Show info");
        System.out.println("3. Cancel");
        System.out.println("0. Exit");
    }
}
