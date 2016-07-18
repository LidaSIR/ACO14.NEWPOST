package newpost.model;

/**
 * Created by Lida on 16.07.2016.
 */
public class Tax extends Transaction {
    public static final int TAX_RATE_PERCENTAGE = 18;
    private int taxRate;
    private int income;
    private Transaction transaction;

    public Tax(int income, Transaction transaction) {
        this.taxRate =TAX_RATE_PERCENTAGE ;
        this.income = income;
        this.transaction = transaction;
    }


    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "Tax{" +
                "taxRate=" + taxRate +
                ", income=" + income +
                ", transaction=" + transaction +
                '}';
    }
}
