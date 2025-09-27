package service;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.DatabaseManager;
import model.BankAccount;

public class BankingService {

    // Deposit money
    public static void deposit(BankAccount account, double amount) {
        account.setBalance(account.getBalance() + amount);
        DatabaseManager.updateBalance(account.getAccountId(), account.getBalance());
        System.out.println("Deposit successful. New Balance: " + account.getBalance());
    }

    // Withdraw money
    public static void withdraw(BankAccount account, double amount) {
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            DatabaseManager.updateBalance(account.getAccountId(), account.getBalance());
            System.out.println("Withdrawal successful. New Balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    // Load account from DB
    public static BankAccount login(String username, String password) {
        try {
            ResultSet rs = DatabaseManager.login(username, password);
            if (rs != null && rs.next()) {
                return new BankAccount(
                        rs.getInt("account_id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
        return null;
    }
}
