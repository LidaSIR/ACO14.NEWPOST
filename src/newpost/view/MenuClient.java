package newpost.view;

import newpost.exceptions.ValidationException;

public class MenuClient extends Menu {

    public MenuClient() {
    }

    protected void clientMenuRun() throws ValidationException {
        while (true) {
            showMenuClient();

            int clientChoice = getScanner().nextInt();

            if (clientChoice == 1) {
                showAddTicketMenu();
            } else if (clientChoice == 2) {
                showInfoMenu();
            } else if (clientChoice == 3) {
                showCancelTicketMenu();
            } else if (clientChoice == 4) {
                showTakeProductMenu();
            } else if (clientChoice == 5) {
                showAllPostOffices();
            } else if (clientChoice == 0) {
                break;
            }
        }
    }

    protected void showMenuClient() {
        System.out.println("1. Add Ticket");
        System.out.println("2. Show info ");
        System.out.println("3. Cancel order");
        System.out.println("4. Take product");
        System.out.println("5. Show all post offices");
        System.out.println("0. Exit");

    }


}