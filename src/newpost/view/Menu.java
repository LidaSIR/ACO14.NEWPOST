package newpost.view;

import newpost.controller.IClientController;
import newpost.model.*;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private IClientController clientController;

    public void start(IClientController controller) {
        clientController = controller;
        chooseUser();
    }


    private void chooseUser() {
        System.out.printf("For clients choose: 1\nfor managers choose: 2 \n");
        int user = scanner.nextInt();
        switch (user) {
            case 1:  // client
                clientEnter();
                clientMenuRun();
                break;
            case 2:  // manager
                while (true) {
                    System.out.println("Log in:");
                    String managerLog = scanner.next();
                    // Log in validation
                    managerMenuRun();
                }
        }
    }

    private void managerMenuRun() {
        while (true) {
            showMainMenu();

            int choice = scanner.nextInt();

            if (choice == 1) {
                showAddTicketMenu();
            } else if (choice == 2) {
                showInfoMenu();
            } else if (choice == 3) {
                showCancelTicketMenu();
            } else if (choice == 4) {
                showAllLogs();
            } else if (choice == 0) {
                break;
            }
        }

    }

    private void clientMenuRun() {
        while (true) {
            showMenuClient();

            int clientChoice = scanner.nextInt();

            if (clientChoice == 1) {
                showInfoMenu();
            } else if (clientChoice == 2) {
                showCancelTicketMenu();
            } else if (clientChoice == 3) {
                showPreviousActions();
            } else if (clientChoice == 0) {
                break;
            }
        }

    }

    private void showPreviousActions() {
    }

    private void clientEnter() {
        while (true) {
//            System.out.println("Input: 1.I am already have account in Art Post ");
//            System.out.println("Input: 2. I am a new user "); //for receivers
//            int userAnswer = scanner.nextInt();
//            if (userAnswer != 1 && userAnswer != 2) System.out.println("Incorrect input");
//            if (userAnswer == 1) {
            System.out.println("Enter your login");
            String userLog = scanner.next();
            System.out.println("Enter your password");
            String userPass = scanner.next();
            break;
            //validation
            // if wrong System.out.println("Wrong login or password")

        }
    }

    private void showAllLogs() {
        System.out.println("Show all logs: ");
        LogContainer.showAllLogs();
    }

    private void showInfoMenu() {

        System.out.println("Show info: input product Id to show ");
        String productId;
        productId = scanner.next();

        Product product = clientController.showProductById(Integer.parseInt(productId));
        System.out.println(product.toString());
    }

    private void showCancelTicketMenu() {

        System.out.println("Cancel: input product Id to cancel");
        String productId;
        productId = scanner.next();

        clientController.cancelTicket(Integer.parseInt(productId));

    }

    private void showAddTicketMenu() {
        System.out.println("Create  a client:");
        String clientPhone = phoneInput();
        String clientFullName = fullNameInput();
        String clientPassportNumber = passportInput();

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

        System.out.println("Product creation: input product name(from 2 to 20 symbols) ");
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

        PostTicket postTicket = clientController.makeOrder(client, addrTo, product);

        System.out.println("post ticket id is " + postTicket.getId());
    }

    private String fullNameInput() {
        String fullName;
        while (true) {
            System.out.println("Input  first name and family name ");
            fullName = scanner.next();
            if (fullName.isEmpty()) {
                System.out.println("incorrect data");
            } else {
                break;
            }
        }
        return fullName;
    }

    private String passportInput() {
        String passportNumber;
        while (true) {
            System.out.println("Input  passport number in format DF908754((without spaces)) ");
            passportNumber = scanner.next();
            if ((passportNumber.isEmpty() || passportNumber.length() > 8)) {
                System.out.println("incorrect data");
            } else {
                break;
            }
        }
        return passportNumber;
    }

    private String phoneInput() {
        String phone;
        while (true) {
            System.out.println("Input  phone in format: +380935075645 (without spaces)");
            phone = scanner.next();
            if ((phone.length() > 13) || (!(phone.contains("+380")))) {
                System.out.println("incorrect data");
            } else {
                break;
            }
        }
        return phone;
    }


    private void showMainMenu() {
        System.out.println("1. Add Ticket");
        System.out.println("2. Show info");
        System.out.println("3. Cancel");
        System.out.println("4. Show all logs");
        System.out.println("0. Exit");
    }

    private void showMenuClient() {
        System.out.println("1.Show info");
        System.out.println("2.Cancel");
        System.out.println("3.Show previous activity");
        System.out.println("0. Exit");
    }
}
