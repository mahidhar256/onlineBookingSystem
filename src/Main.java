import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import db.DatabaseManager;
import model.BankAccount;
import service.BankingService;

public class Main {
    public static void main(String[] args) {
        // Ensure MySQL JDBC driver is loaded
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Online Banking System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Username: ");
                    String uname = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String pwd = sc.nextLine();
                    DatabaseManager.createAccount(name, uname, pwd);
                    break;

                case 2:
                    System.out.print("Enter Username: ");
                    String loginUname = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String loginPwd = sc.nextLine();

                    try {
                        ResultSet rs = DatabaseManager.login(loginUname, loginPwd);
                        if (rs != null && rs.next()) {
                            BankAccount account = new BankAccount(
                                    rs.getInt("account_id"),
                                    rs.getString("name"),
                                    rs.getString("username"),
                                    rs.getString("password"),
                                    rs.getDouble("balance")
                            );
                            System.out.println("Login successful! Welcome, " + account.getName());

                            boolean logout = false;
                            while (!logout) {
                                System.out.println("\n1. Deposit");
                                System.out.println("2. Withdraw");
                                System.out.println("3. Check Balance");
                                System.out.println("4. Logout");
                                System.out.print("Choose an option: ");
                                int op = sc.nextInt();
                                sc.nextLine();

                                switch (op) {
                                    case 1:
                                        System.out.print("Enter amount to deposit: ");
                                        double depositAmount = sc.nextDouble();
                                        sc.nextLine();
                                        BankingService.deposit(account, depositAmount);
                                        break;
                                    case 2:
                                        System.out.print("Enter amount to withdraw: ");
                                        double withdrawAmount = sc.nextDouble();
                                        sc.nextLine();
                                        BankingService.withdraw(account, withdrawAmount);
                                        break;
                                    case 3:
                                        System.out.println("Current Balance: " + account.getBalance());
                                        break;
                                    case 4:
                                        logout = true;
                                        System.out.println("Logged out.");
                                        break;
                                    default:
                                        System.out.println("Invalid option!");
                                }
                            }
                        } else {
                            System.out.println("Login failed! Invalid username or password.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error during login: " + e.getMessage());
                    }
                    break;

                case 3:
                    exit = true;
                    System.out.println("Exiting system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }

        sc.close();
    }
}