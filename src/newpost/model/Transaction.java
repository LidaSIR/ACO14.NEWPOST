package newpost.model;

import java.time.LocalDateTime;

/**
 * Created by Lida on 16.07.2016.
 */
public class Transaction {


    private final String transactionId;
    private static int nextTransactionId = 0;
    private int ourBankAccount;
    private int recipientAccount;
    private int transferAmount;
    private String paymentPurpose;
    private LocalDateTime transactionDate;


    public Transaction(int ourBankAccount, int recipientAccount, int transferAmount, String paymentPurpose) {

        this.transactionId = String.valueOf(nextTransactionId++);
        this.ourBankAccount = ourBankAccount;
        this.recipientAccount = recipientAccount;
        this.transferAmount = transferAmount;
        this.paymentPurpose = paymentPurpose;
        LocalDateTime localDate = LocalDateTime.now();
        this.transactionDate =localDate;

    }

    public int getOurBankAccount() {
        return ourBankAccount;
    }

    public void setOurBankAccount(int ourBankAccount) {
        this.ourBankAccount = ourBankAccount;
    }

    public int getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(int recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(int transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", ourBankAccount=" + ourBankAccount +
                ", recipientAccount=" + recipientAccount +
                ", transferAmount=" + transferAmount +
                ", paymentPurpose='" + paymentPurpose + '\'' +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
