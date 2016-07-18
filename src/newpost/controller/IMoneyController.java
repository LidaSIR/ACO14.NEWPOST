package newpost.controller;

import newpost.model.Employee;
import newpost.model.Tax;
import newpost.model.Transaction;

import java.time.LocalDateTime;

/**
 * Created by Lida on 16.07.2016.
 */
public interface IMoneyController {
    Transaction paySalary(String employeeName, String employeeSurname,
                          int salaryAmount,Transaction transaction);
    Tax payTax(Transaction transaction, int income);
    Transaction makePayment(int ourBankAccount, int recipientAccount,
                            int transferAmount,String paymentPurpose);
    Transaction findTransactionByID (String transactionId);




}
