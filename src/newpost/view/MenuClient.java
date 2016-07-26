package newpost.view;

import newpost.exceptions.ValidationException;

public class MenuClient extends Menu {
    private final Menu menu;

    public MenuClient(Menu menu) {
        this.menu = menu;
    }

    protected void clientMenuRun() throws ValidationException {
        while (true) {
            showMenuClient();

            int clientChoice = menu.getScanner().nextInt();

            if (clientChoice == 1) {
                menu.showAddTicketMenu();
            } else if (clientChoice == 2) {
                menu.showInfoMenu();
            } else if (clientChoice == 3) {
                menu.showCancelTicketMenu();
            } else if (clientChoice == 4) {
                menu.showTakeProductMenu();
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
        System.out.println("0. Exit");

    }


}