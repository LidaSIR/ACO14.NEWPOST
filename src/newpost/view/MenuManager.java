package newpost.view;

import newpost.exceptions.LogException;
import newpost.exceptions.ValidationException;
import newpost.model.common.Address;
import newpost.model.office.PostTicket;
import java.util.List;


public class MenuManager  extends Menu {

    protected MenuManager() {
    }

    protected void managerMenuRun() throws ValidationException, LogException {
        while (true) {
            showMainMenuManager();

            int choice = scanner.nextInt();

            if (choice == 1) {
                showAddTicketMenu();
            } else if (choice == 2) {
                showInfoMenu();
            } else if (choice == 3) {
                showCancelTicketMenu();
            } else if (choice == 4) {
                showAllLogs();
            } else if (choice == 5) {
                showTicketByClientPhoneMenu();
            } else if (choice == 6) {
                showGetClientMenu();
            } else if (choice == 7) {
                showAddClientMenu();
            } else if(choice == 8) {
                this.showManagementOperationsMenu();
            } else if (choice == 0) {
                break;
            }
        }
    }

    public void showManagementOperationsMenu() {
        System.out.println();
        System.out.println("1. Find");
        System.out.println("2. Sort");
        System.out.println("0. Exit");

        int choice = scanner.nextInt();
        scanner.next();

        switch (choice) {
            case 1:
                showFindMenu();
                break;
            case 2:
                showSortMenu();
                break;
            case 0:
                return;
        }

    }

    private void showSortMenu() {

        System.out.println("1. Sort tickets by address");
        System.out.println("2. Sort clients by owner name");
        System.out.println("3. Sort actual tickets by price");
        System.out.println("4. Sort actual tickets by Id");
        System.out.println("0. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                managerController.sortTicketsByAddress();
                System.out.println("Done");
                break;
            case 2:
                managerController.sortClientsByName();
                System.out.println("Done");
                break;
            case 3:
                managerController.sortTicketsByPrice();
                System.out.println("Done");
                break;
            case 4:
                managerController.sortTicketsById();
                System.out.println("Done");
                break;
            case 0:
                return;
        }

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

    private void showFindMenu() {
        System.out.println("1. Find by price");
        System.out.println("2. Find by address");
        System.out.println("3. Find by city");
        System.out.println("4. Find by name");
        System.out.println("5. Find by id");
        System.out.println("0. Exit");

        int choice = scanner.nextInt();
        scanner.next();
        switch (choice) {
            case 1:
                this.findByPrice();
                break;
            case 2:
                this.findByAddress();
                break;
            case 3:
                this.findByCity();
                break;
            case 4:
                this.findByOwnerName();
                break;
            case 5:
                this.findById();
                break;
            case 0:
                return;
        }
    }

    private void findByPrice() {
        System.out.println("Input price");
        int price = scanner.nextInt();
        scanner.next();
        List<PostTicket> list = managerController.findByPrice(price);
        System.out.println(list.toString());

    }
    private void findByAddress (){
        System.out.println("Input City");
        String city = scanner.next();
        System.out.println("Input Street");
        String street =scanner.next();
        System.out.println("Input House Number");
        String houseNum = scanner.next();

        Address address = new Address(city,street,houseNum);

        List<PostTicket> list = managerController.findByAddress(address);
        System.out.println(list.toString());
    }
    private void findByCity(){
        System.out.println("Input City");
        String city = scanner.next();

        List<PostTicket> list = managerController.findByCity(city);
        System.out.println(list.toString());
    }
    private void findByOwnerName (){
        System.out.println("Input Name");
        String name = scanner.next();

        List<PostTicket> list = managerController.findByOwnerName(name);
        System.out.println(list.toString());
    }
    private void findById(){
        System.out.println("Input ID");
        String id = scanner.next();

        PostTicket postTicket = managerController.findById(id);
        System.out.println(postTicket.toString());
    }
}