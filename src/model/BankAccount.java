package model;

public class BankAccount {
    private int accountId;
    private String name;
    private String username;
    private String password;
    private double balance;

    public BankAccount(int accountId, String name, String username, String password, double balance) {
        this.accountId = accountId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    // Getters and setters
    public int getAccountId() { return accountId; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName() { return name; }
}
