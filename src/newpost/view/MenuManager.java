package newpost.view;

import newpost.exceptions.LogException;
import newpost.exceptions.ValidationException;

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
            } else if (choice == 0) {
                break;
            }
        }

    }
}