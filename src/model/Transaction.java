package model;

import java.sql.Timestamp;

public class Transaction {
    private int id;
    private int accountId;       // foreign key to BankAccount
    private double amount;
    private String type;         // deposit / withdraw
    private Timestamp timestamp; // when transaction happened

    // Constructor
    public Transaction(int id, int accountId, double amount, String type, Timestamp timestamp) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    // To display transaction details easily
    @Override
    public String toString() {
        return "Transaction [ID=" + id +
               ", AccountID=" + accountId +
               ", Type=" + type +
               ", Amount=" + amount +
               ", Timestamp=" + timestamp + "]";
    }
}
