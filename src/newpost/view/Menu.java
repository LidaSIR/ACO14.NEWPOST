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
import newpost.model.office.Employee;
import newpost.model.office.PostTicket;
import newpost.utils.logging.LogContainer;

import java.util.Scanner;

public class Menu {
    protected final MenuManager menuManager = new MenuManager(this);
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
                clientEnter();
                clientMenuRun();
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
                directorMenuRun();
        }
    }

    protected void directorMenuRun() {
        while (true) {
            showMainMenuDirector();
            int choice = scanner.nextInt();
            if (choice == 1) {
                showAddStaffMenu();
            } else if (choice == 2) {
                showRemoveStaffMenu();
            } else if (choice == 3) {
                showFindStaffByNameMenu();
            } else if (choice == 4) {
                showStaffInfo();
            } else if (choice == 5) {
                showPaySalaryMenu();
            } else if (choice == 6) {
                showPayTaxMenu();
            } else if (choice == 7) {
                showMakePaymentMenu();
            } else if (choice == 8) {
                showFindTransactionByIdMenu();  // test failed
            } else if (choice == 0){
                break;
            }
        }
    }

    protected void managerMenuRun() throws ValidationException, LogException {

        menuManager.managerMenuRun();
    }

    protected void clientMenuRun() throws ValidationException {
        while (true) {
            showMenuClient();

            int clientChoice = scanner.nextInt();

            if (clientChoice == 1) {
                showAddTicketMenu();
            } else if (clientChoice == 2) {
                showInfoMenu();
            } else if (clientChoice == 3) {
                showCancelTicketMenu();
            } else if (clientChoice == 4) {
                showTakeProductMenu();
            } else if (clientChoice == 0) {
                break;
            }
        }
    }
    protected void showFindTransactionByIdMenu() {
        System.out.println("Input transaction Id");
        String id = scanner.next();
        Transaction transaction = moneyController.findTransactionByID(id);
        System.out.println(transaction.toString());

    }

    protected void showMakePaymentMenu() {
        System.out.println("Input ART-Post bank account");
        int ourAccount = scanner.nextInt();
        System.out.println("Input recipient bank account");
        int recipientAccount = scanner.nextInt();
        System.out.println("Input transfer amount");
        int transferAmount = scanner.nextInt();
        System.out.println("Input payment purpose");
        String paymentPurpose = scanner.next();

        moneyController.makePayment(ourAccount, recipientAccount,transferAmount,paymentPurpose);
        System.out.println("Payment made");
    }

    protected void showPayTaxMenu() {
        System.out.println("Input ART-Post bank account");
        int ourAccount = scanner.nextInt();
        System.out.println("Input recipient bank account");
        int recipientAccount = scanner.nextInt();
        System.out.println("Input transfer amount");
        int transferAmount = scanner.nextInt();
        System.out.println("Input payment purpose");
        String paymentPurpose = scanner.next();
        System.out.println("Input income");
        int income = scanner.nextInt();

        moneyController.payTax(new Transaction(ourAccount,recipientAccount,transferAmount,paymentPurpose),income);
        System.out.println("Tax paid");
    }

    protected void showPaySalaryMenu() {
        System.out.println("Input employees name");
        String name = scanner.next();
        System.out.println("Input employees surname");
        String surname = scanner.next();
        System.out.println("Input salary amount");
        int salary = scanner.nextInt();
        System.out.println("Input ART-Post bank account");
        int ourAccount = scanner.nextInt();
        System.out.println("Input recipient bank account");
        int recipientAccount = scanner.nextInt();
        System.out.println("Input transfer amount");
        int transferAmount = scanner.nextInt();
        System.out.println("Input payment purpose");
        String paymentPurpose = scanner.next();

        moneyController.paySalary(name,surname,salary,
                new Transaction(ourAccount,recipientAccount,transferAmount,paymentPurpose));
    }

    protected void showStaffInfo() {
        employeeManagement.showStaffInfo();
    }

    protected void showFindStaffByNameMenu() {
        System.out.println("Input employees name");
        String name = scanner.next();
        System.out.println("Input employees surname");
        String surname = scanner.next();
        String fullName = name + surname;

        Employee employee = employeeManagement.findStaffByName(fullName);
        System.out.println(employee.toString());
    }

    protected void showRemoveStaffMenu() {
        System.out.println("Input employees name");
        String name = scanner.next();
        System.out.println("Input employees surname");
        String surname = scanner.next();
        String fullName = name + surname;

        employeeManagement.removeStaff(fullName);
        System.out.println("Employee removed");
    }
    protected void showAddStaffMenu() {
        System.out.println("Input job title");
        String jobTitle = scanner.next();
        System.out.println("Input employees name");
        String name = scanner.next();
        System.out.println("Input employees surname");
        String surname = scanner.next();
        String fullName = name + surname;
        System.out.println("Input Employees telephone");
        String phone = scanner.next();
        System.out.println("Input salary amount");
        int salary = scanner.nextInt();

        Employee employee = employeeManagement.addStaff(jobTitle,fullName,phone,salary);
        int password = employee.getPassword();
        String login = employee.getLogin();
        System.out.printf("Employee added. Employees password %d, login %s",password,login );
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

    protected void clientEnter() {
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

    protected void showMenuClient() {
        System.out.println("1. Add Ticket");
        System.out.println("2. Show info ");
        System.out.println("3. Cancel order");
        System.out.println("4. Take product");
        System.out.println("0. Exit");
    }

    protected void showMainMenuDirector() {
        System.out.println("1. Add an Employee");
        System.out.println("2. Fire an Employee");
        System.out.println("3. Find Employee by Name");
        System.out.println("4. Show Staff Info");
        System.out.println("5. Pay Salary");
        System.out.println("6. Pay tax");
        System.out.println("7. Make Payment");
        System.out.println("8. Find Transaction by Id");
        System.out.println("0. Exit");

    }

    public Scanner getScanner() {
        return scanner;
    }


}
