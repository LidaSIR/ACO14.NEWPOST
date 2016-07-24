package newpost.view;

import newpost.exceptions.LogException;
import newpost.exceptions.ValidationException;
import newpost.filter.Finder;
import newpost.model.office.PostTicket;

import java.util.List;

public class MenuManager  extends Menu {
    protected final Menu menu;

    public MenuManager(Menu menu) {
        this.menu = menu;
    }

    protected void managerMenuRun() throws ValidationException, LogException {
        while (true) {
            menu.showMainMenuManager();

            int choice = menu.getScanner().nextInt();

            if (choice == 1) {
                menu.showAddTicketMenu();
            } else if (choice == 2) {
                menu.showInfoMenu();
            } else if (choice == 3) {
                menu.showCancelTicketMenu();
            } else if (choice == 4) {
                menu.showAllLogs();
            } else if (choice == 5) {
                menu.showTicketByClientPhoneMenu();
            } else if (choice == 6) {
                menu.showGetClientMenu();
            } else if (choice == 7) {
                menu.showAddClientMenu();
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
        scanner.nextLine();

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

    private void showFindMenu() {
        System.out.println("1. Find by price");
        System.out.println("2. Find by address");
        System.out.println("3. Find by city");
        System.out.println("4. Find by name");
        System.out.println("5. Find by id");
        System.out.println("0. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                this.findByPrice();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 0:
                return;
        }
    }

    private void findByPrice() {
        System.out.println("Input price");
        int price = scanner.nextInt();
        scanner.nextLine();
        List<PostTicket> list = managerController.findByPrice(price);



    }
}