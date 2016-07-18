package newpost.test;

import newpost.controller.MoneyController;
import newpost.db.AppDataContainer;
import newpost.model.Salary;
import newpost.model.Tax;
import newpost.model.Transaction;

import java.time.LocalDateTime;

/**
 * Created by Lida on 17.07.2016.
 */
public class TestMoneyController {
    public static void main(String[] args) {

        MoneyController moneyController = new MoneyController(new AppDataContainer());

        testPayTax(moneyController);
        testMakePayment(moneyController);
        testPaySalary(moneyController);
       // testFindTransactionByID(moneyController);


    }
    public static void testPayTax (MoneyController moneyController){
        int in1 = 453434;
        Transaction  in2 = new Transaction(547488,468387, 6737646, "Tax");
        Tax expected = new Tax(453434,new Transaction(547488,468387, 6737646, "Tax"));
        Tax actual = moneyController.payTax(in2, in1);
        System.out.printf("%s, test pay tax in1 %d,\n expected %s,\n actual %s\n",
                actual.toString().equals(expected.toString()), in1,  expected.toString(), actual.toString());
        System.out.println();

    }
    public  static void testMakePayment (MoneyController moneyController){
        int ourBankAccount = 7699745;
         int recipientAccount = 8746993;
         int transferAmount = 87845;
         String paymentPurpose = "Pay for water";
        Transaction expected = new Transaction(7699745, 8746993,87845, "Pay for water");
        Transaction actual = moneyController.makePayment(ourBankAccount,recipientAccount,transferAmount, paymentPurpose);

        System.out.printf("%s test make payment, expected %s\n, actual %s\n",
                actual.toString().equals(expected.toString()),  expected.toString(), actual.toString());
        System.out.println();
    }

    public  static void testPaySalary(MoneyController moneyController){
       String employeeName = "Vasiia";
        String employeeSurname = "Ivanov";
        int salaryAmount = 7000;
        Transaction transaction = new Transaction(8979435,8743975, 7000, "Salary");

        Transaction actual = moneyController.paySalary(employeeName,employeeSurname,salaryAmount,transaction);
        Salary expected = new Salary(new Transaction(8979435,8743975, 7000,"Salary"),7000,"Vasiia","Ivanov");

        System.out.printf("%s test pay salary, expected %s\n, actual %s\n",
                expected.toString().equals(actual.toString()),expected,actual);
    }

    public static void testFindTransactionByID (MoneyController moneyController){
        Transaction expected = moneyController.makePayment(56788635,979345935,7564,"Rent");
        String id = expected.getTransactionId();
        Transaction actual = moneyController.findTransactionByID("id");
        System.out.printf("%s test find transaction by Id, expected %s\n, actual %s\n",
                expected.getTransactionId().equals(actual.getTransactionId()),expected,actual);
    }
}
