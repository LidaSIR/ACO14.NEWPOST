package newpost.controller;

import newpost.db.AppDataContainer;
import newpost.model.Employee;
import newpost.model.Tax;
import newpost.model.Transaction;
import java.time.LocalDateTime;


/**
 * Created by Lida on 16.07.2016.
 */
public class MoneyController implements IMoneyController {
    public static final int TAX_RATE_PERCENTAGE = 18;
    private AppDataContainer appDataContainer;

    public MoneyController (AppDataContainer appDataContainer){
        this.appDataContainer = appDataContainer;
    }

    @Override
    public Transaction paySalary(Employee employee) {
        return null;
    }

    @Override
    public Tax payTax( Transaction transaction, int income) {
        Transaction taxPayment = new Transaction(transaction.getOurBankAccount(), transaction.getRecipientAccount(),
                transaction.getTransferAmount(), transaction.getPaymentPurpose());
        Tax tax = new Tax(income,taxPayment);
        return tax;
    }

    @Override
    public Transaction makePayment( int ourBankAccount, int recipientAccount,
                                   int transferAmount,String paymentPurpose) {
        Transaction transaction = new Transaction(ourBankAccount, recipientAccount, transferAmount, paymentPurpose);

        appDataContainer.getTransactions().add(transaction);

        return transaction;
    }

    @Override
    public Transaction findTransactionByDate(LocalDateTime transactionDate) {
        for(Transaction transaction: appDataContainer.getTransactions()){
            if(transaction.getTransactionDate().equals(String.valueOf(transactionDate))){
                return transaction;
            }
        }
        return null;
    }

    @Override
    public Transaction findTransactionByID(Transaction transactionId) {
        for(Transaction transaction: appDataContainer.getTransactions()) {
            if (transaction.getTransactionId().equals(String.valueOf(transactionId))) {
                return transaction;
            }
        }
        return null;
    }

}
