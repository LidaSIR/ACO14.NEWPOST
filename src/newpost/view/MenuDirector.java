package newpost.view;

import newpost.model.money.Transaction;
import newpost.model.office.Employee;

public class MenuDirector extends Menu{
    private final Menu menu;

    public MenuDirector(Menu menu) {
        this.menu = menu;
    }

    protected void directorMenuRun() {
        while (true) {
               showMainMenuDirector();
            int choice = menu.getScanner().nextInt();
            if (choice == 1) {
               showAddStaffMenu(menu);
            } else if (choice == 2) {
               showRemoveStaffMenu(menu);
            } else if (choice == 3) {
                showFindStaffByNameMenu(menu);
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
            } else if (choice == 0) {
                break;
            }
        }
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

    protected void showAddStaffMenu(Menu menu) {
        System.out.println("Input job title");
        String jobTitle = menu.scanner.next();
        System.out.println("Input employees name");
        String name = menu.scanner.next();
        System.out.println("Input employees surname");
        String surname = menu.scanner.next();
        String fullName = name + surname;
        System.out.println("Input Employees telephone");
        String phone = menu.scanner.next();
        System.out.println("Input salary amount");
        int salary = menu.scanner.nextInt();

        Employee employee = menu.employeeManagement.addStaff(jobTitle,fullName,phone,salary);
        int password = employee.getPassword();
        String login = employee.getLogin();
        System.out.printf("Employee added. Employees password %d, login %s",password,login );
    }

    protected void showFindStaffByNameMenu(Menu menu) {
        System.out.println("Input employees name");
        String name = menu.scanner.next();
        System.out.println("Input employees surname");
        String surname = menu.scanner.next();
        String fullName = name + surname;

        Employee employee = menu.employeeManagement.findStaffByName(fullName);
        System.out.println(employee.toString());
    }

    protected void showRemoveStaffMenu(Menu menu) {
        System.out.println("Input employees name");
        String name = menu.scanner.next();
        System.out.println("Input employees surname");
        String surname = menu.scanner.next();
        String fullName = name + surname;

        menu.employeeManagement.removeStaff(fullName);
        System.out.println("Employee removed");
    }
    protected void showStaffInfo() {
        employeeManagement.showStaffInfo();
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

}