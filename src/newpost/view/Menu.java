package newpost.view;

import newpost.controller.IClientController;
import newpost.controller.IEmployeeManagement;
import newpost.controller.IManagerController;
import newpost.controller.IMoneyController;
import newpost.exceptions.LogException;
import newpost.exceptions.ValidationException;
import newpost.filter.Finder;
import newpost.model.common.Address;
import newpost.model.common.Passport;
import newpost.model.common.Product;
import newpost.model.common.Size;
import newpost.model.money.Transaction;
import newpost.model.office.Client;
import newpost.model.office.PostTicket;
import newpost.utils.logging.LogContainer;

import java.util.Scanner;

public class Menu {
    protected final MenuManager menuManager = new MenuManager(this);

    final MenuDirector menuDirector = new MenuDirector(this);

    private final MenuClient menuClient = new MenuClient(this);

    protected Scanner scanner = new Scanner(System.in);

    protected IClientController clientController;
    protected IManagerController managerController;
    protected IEmployeeManagement employeeManagement;
    protected IMoneyController moneyController;

    public void start(IClientController controller, IManagerController managerController) throws ValidationException, LogException {
        clientController = controller;
        this.managerController = managerController;
        scanner.useDelimiter("\\n");

        chooseUser();
     }


    protected void chooseUser() throws ValidationException, LogException {
        System.out.printf("For clients choose: 1\nRor manager choose: 2\nFor director choose 3\n");
        int user = scanner.nextInt();
        switch (user) {
            case 1:
                menuClient.clientEnter(this);
                menuClient.clientMenuRun();
                break;
            case 2:
                while (true) {
                    System.out.println("Log in:");
                    String managerLog = scanner.next();
                    // Log in validation
                    menuManager.managerMenuRun();
                }
            case 3:
                System.out.println("Log in:");
                String directorLog = scanner.next();
                // Log in validation
                menuDirector.directorMenuRun();
        }
    }

    protected void directorMenuRun() {
        menuDirector.directorMenuRun();
    }

    protected void managerMenuRun() throws ValidationException, LogException {

        menuManager.managerMenuRun();
    }

    protected void clientMenuRun() throws ValidationException {
        menuClient.clientMenuRun();
    }




    protected void showTakeProductMenu() throws ValidationException {
        System.out.println("Input ticket ID");
        String ticketId;
        ticketId = scanner.next();
        Product product = clientController.takeProduct(Integer.parseInt(ticketId));

        System.out.println(product.toString());
    }

    protected void showGetClientMenu() throws ValidationException {
        System.out.println("Input clients phone");
        String phone;
        phone = scanner.next();
        try {
            Client client = managerController.getClient(phone);
            if (client != null) {
                System.out.println(client.toString());
            } else {
                System.out.println("Client was not found according to inputted phone number.");
            }
        } catch (ValidationException ex){
            System.out.println(ex.getMessage());
        }
    }

    protected void showTicketByClientPhoneMenu() throws ValidationException {
        System.out.println("Input clients phone");
        String phone;
        phone = scanner.next();
        PostTicket postTicket = managerController.showTicketByClientPhone(phone);
        System.out.println(postTicket.toString());
    }

    protected void showAddClientMenu() throws ValidationException {

        while (true) {
            System.out.println("Input clients first name");
            String name = scanner.next();
            System.out.println("Input clients second name");
            String surname = scanner.next();
            String fullName = name + surname;
            System.out.println("Input passport number");
            String passportNum = scanner.next();
            System.out.println("Input clients phone in format +380938976554");
            String phone = scanner.next();
            try {
                if ((fullName.length() > 0) && (passportNum.length() > 0) && (phone.length() > 0)) {
                    Passport passport = new Passport(fullName, passportNum);
                    Client client = managerController.addClient(passport, phone);
                    System.out.println("Client added");
                    break;
                } else {
                    System.out.println("Either full name or passport number or phone number is empty.");
                }
            } catch (ValidationException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    protected void showAllLogs() throws LogException {
        System.out.println("Show all logs:\n");
        try {
            LogContainer.showAllLogs();
        } catch (LogException ex){
            System.out.println(ex.getMessage());
        }
    }

    protected void showInfoMenu() throws ValidationException {

        System.out.println("Show info: input ticket Id");
        String ticketId;
        ticketId = scanner.next();

        try {
            PostTicket postTicket = clientController.showTicketById(String.valueOf(ticketId));
            if (postTicket != null) {
                System.out.println(postTicket.toString());
            } else {
                System.out.println("No ticket was found according to inputted ticekt Id.");
            }
        } catch (ValidationException ex){
            System.out.println(ex.getMessage());
        }
    }

    protected void showCancelTicketMenu() throws ValidationException {

        System.out.println("Cancel: input product Id to cancel");
        String productId;
        productId = scanner.next();
        int prodId = Integer.parseInt(productId);

        try{
            if (clientController.cancelTicket(prodId)) {
                System.out.println("Your order is canceled");
            } else {
                System.out.println("There is no order according to inputted Id.");
            }
        } catch (NumberFormatException ex){
            System.out.println("Inputted order Id is not numeric.");
        } catch (ValidationException ex){
            System.out.println(ex.getMessage());
        }
    }

    protected void showAddTicketMenu() {
        System.out.println("Create a client:");
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
        String addressToHouseNum;
        while (true) {
            System.out.println("Address destination creation: input house number ");
            addressToHouseNum = scanner.next();
            int num;
            try{
                num = Integer.parseInt(addressToHouseNum);
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Inputted value is not numeric.");
            }
        }

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

        try {
            PostTicket postTicket = clientController.makeOrder(client, addrTo, product);
            System.out.println("Post ticket id is " + postTicket.getId());
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    protected String fullNameInput() {
        String fullName;
        while (true) {
            System.out.println("Input first name and second name ");
            fullName = scanner.next();
            if (fullName.isEmpty()) {
                System.out.println("Incorrect data: inputted value is empty.");
            } else {
                break;
            }
        }
        return fullName;
    }

    protected String passportInput() {
        String passportNumber;
        while (true) {
            System.out.println("Input  passport number in format DF908754(without spaces) ");
            passportNumber = scanner.next();
            if (passportNumber.isEmpty() || (passportNumber.length() > 8) || passportNumber.contains(" ")) {
                System.out.println("Incorrect data: either passport number is empty or length is greater than 8 characters or contains spaces..");
            } else {
                break;
            }
        }
        return passportNumber;
    }

    protected String phoneInput() {
        String phone;
        while (true) {
            System.out.println("Input phone in format: +380935075645 (without spaces)");
            phone = scanner.next();
            if ((phone.length() > 13) || (!(phone.contains("+380")))) {
                System.out.println("Incorrect data: either number length is greater than 13 characters or number does not contain \"+380\".");
            } else {
                break;
            }
        }
        return phone;
    }

    protected void showMainMenuManager() {
        System.out.println("1. Add Ticket");
        System.out.println("2. Show info");
        System.out.println("3. Cancel Ticket");
        System.out.println("4. Show All Logs");
        System.out.println("5. Show Ticket by Clients Number");
        System.out.println("6. Get Client");
        System.out.println("7. Add Client");
        System.out.println("8. Management operations");
        System.out.println("0. Exit");
    }

    public Scanner getScanner() {
        return scanner;
    }


}
