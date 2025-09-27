package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/OnlineBankingDB";
    private static final String USER = "root"; // change to your MySQL username
    private static final String PASSWORD = "72070"; // change to your MySQL password

    // Create account
    public static void createAccount(String name, String username, String password) {
        String sql = "INSERT INTO accounts(name, username, password, balance) VALUES (?, ?, ?, 0.0)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.executeUpdate();
            System.out.println("Account created successfully!");

        } catch (SQLException e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }

    // Login
    public static ResultSet login(String username, String password) {
        String sql = "SELECT * FROM accounts WHERE username=? AND password=?";
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            return ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
            return null;
        }
    }

    // Update balance
    public static void updateBalance(int accountId, double newBalance) {
        String sql = "UPDATE accounts SET balance=? WHERE account_id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, newBalance);
            ps.setInt(2, accountId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating balance: " + e.getMessage());
        }
    }
}
