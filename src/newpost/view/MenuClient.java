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

      protected   void showMenuClient() {
            System.out.println("1. Add Ticket");
            System.out.println("2. Show info ");
            System.out.println("3. Cancel order");
            System.out.println("4. Take product");
            System.out.println("0. Exit");

        }


    protected void clientEnter(Menu menu) {
        while (true) {
//            System.out.println("Input: 1.I am already have account in Art Post ");
//            System.out.println("Input: 2. I am a new user "); //for receivers
//            int userAnswer = scanner.nextInt();
//            if (userAnswer != 1 && userAnswer != 2) System.out.println("Incorrect input");
//            if (userAnswer == 1) {
            System.out.println("Enter your login");
            String userLog = menu.scanner.next();
            System.out.println("Enter your password");
            String userPass = menu.scanner.next();
            break;
            //validation
            // if wrong System.out.println("Wrong login or password")

        }
    }
}