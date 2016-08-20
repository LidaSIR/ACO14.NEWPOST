package newpost.view.console;

import newpost.exceptions.ValidationException;
import newpost.model.office.PostTicket;

public class MenuClient extends Menu {

    public MenuClient() {
    }

    protected void clientMenuRun(){
        while (true) {
            showMenuClient();

            int clientChoice = getScanner().nextInt();

            if (clientChoice == 1) {
                showAddTicketMenu();
            } else if (clientChoice == 2) {
                showFindById();
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

    private void showFindById()  {
        System.out.println("Input ID");
        String id = scanner.next();

        PostTicket postTicket = null;
        try {
            postTicket = managerController.filterTicketById(id);
            System.out.println(postTicket.toString());
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }
}
