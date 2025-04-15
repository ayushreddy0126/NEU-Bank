package Controller;

import Model.BillPayments;
import Model.DatabaseConfig;
import Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code UserController} class handles user operations like login, registration,
 * transactions, and bill payments. This is the backbone of the application, and I have
 * implemented it to ensure seamless interaction between the application and the database.
 */
public class UserController {
    private User currentUser; // Tracks the currently logged-in user

    /**
     * Logs in the user by validating their credentials.
     *
     * <p>This method authenticates the user based on their email and password. I ensured
     * that once the login is successful, the current user is tracked using {@code currentUser}.</p>
     *
     * @param email    The user's email address.
     * @param password The user's password.
     * @return The logged-in {@code User} object if successful, or {@code null} if the credentials are invalid.
     */
    public User loginUser(String email, String password) {
        User tempUser = new User(null, null, email, null);
        if (tempUser.login(email, password)) {
            currentUser = tempUser;
            return tempUser;
        } else {
            return null;
        }
    }

    /**
     * Registers a new user into the system.
     *
     * <p>During development, I made this method robust to handle errors during registration.
     * It creates a new {@code User} object and attempts to store their information in the database.</p>
     *
     * @param userId   The unique ID for the user.
     * @param name     The user's full name.
     * @param email    The user's email address.
     * @param password The user's chosen password.
     * @return The registered {@code User} object if successful, or {@code null} if registration fails.
     */
    public User registerUser(String userId, String name, String email, String password) {
        User user = new User(userId, name, email, password);
        if (user.registerUser(userId, name, email, password)) {
            System.out.println("User registered successfully: " + user.fetchName());
            return user;
        } else {
            System.out.println("User registration failed.");
            return null;
        }
    }

    /**
     * Handles deposit transactions for the currently logged-in user.
     *
     * <p>I designed this method to record the deposit both in the transactions table
     * and update the user's balance in the database.</p>
     *
     * @param amount The amount to deposit.
     */
    public void deposit(double amount) {
        String sqlTransaction = "INSERT INTO Transactions (userId, amount, date, type) VALUES (?, ?, ?, ?)";
        String sqlUpdateBalance = "UPDATE Users SET balance = balance + ? WHERE userId = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmtTransaction = conn.prepareStatement(sqlTransaction);
             PreparedStatement pstmtBalance = conn.prepareStatement(sqlUpdateBalance)) {

            pstmtTransaction.setInt(1, Integer.parseInt(currentUser.fetchUserId()));
            pstmtTransaction.setDouble(2, amount);
            pstmtTransaction.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmtTransaction.setString(4, "deposit");
            pstmtTransaction.executeUpdate();

            pstmtBalance.setDouble(1, amount);
            pstmtBalance.setInt(2, Integer.parseInt(currentUser.fetchUserId()));
            pstmtBalance.executeUpdate();

            System.out.println("Deposit successful. Amount: " + amount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles withdrawal transactions for the currently logged-in user.
     *
     * <p>When I implemented this, I focused on ensuring that the withdrawal only proceeds
     * if the user's balance is sufficient. Both the transactions table and balance are updated accordingly.</p>
     *
     * @param amount The amount to withdraw.
     * @return {@code true} if the withdrawal is successful, {@code false} otherwise.
     */
    public boolean withdraw(double amount) {
        String sqlTransaction = "INSERT INTO Transactions (userId, amount, date, type) VALUES (?, ?, ?, ?)";
        String sqlCheckBalance = "SELECT balance FROM Users WHERE userId = ?";
        String sqlUpdateBalance = "UPDATE Users SET balance = balance - ? WHERE userId = ?";

        try (Connection conn = DatabaseConfig.getConnection()) {
            double currentBalance = 0.0;

            try (PreparedStatement pstmtCheck = conn.prepareStatement(sqlCheckBalance)) {
                pstmtCheck.setInt(1, Integer.parseInt(currentUser.fetchUserId()));
                ResultSet rs = pstmtCheck.executeQuery();
                if (rs.next()) {
                    currentBalance = rs.getDouble("balance");
                }
            }

            if (currentBalance >= amount) {
                try (PreparedStatement pstmtTransaction = conn.prepareStatement(sqlTransaction);
                     PreparedStatement pstmtBalance = conn.prepareStatement(sqlUpdateBalance)) {

                    pstmtTransaction.setInt(1, Integer.parseInt(currentUser.fetchUserId()));
                    pstmtTransaction.setDouble(2, amount);
                    pstmtTransaction.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                    pstmtTransaction.setString(4, "withdrawal");
                    pstmtTransaction.executeUpdate();

                    pstmtBalance.setDouble(1, amount);
                    pstmtBalance.setInt(2, Integer.parseInt(currentUser.fetchUserId()));
                    pstmtBalance.executeUpdate();

                    System.out.println("Withdrawal successful. Amount: " + amount);
                    return true;
                }
            } else {
                System.out.println("Insufficient balance. Withdrawal failed.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves the transaction history for the currently logged-in user.
     *
     * <p>One of my goals with this method was to ensure that users could easily view
     * a history of their transactions in a clean and informative format.</p>
     *
     * @return A list of strings representing transaction details.
     */
    public List<String> viewTransactionHistory() {
        List<String> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transactions WHERE userId = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Integer.parseInt(currentUser.fetchUserId()));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String transaction = "Transaction ID: " + rs.getInt("transactionId") +
                        ", Amount: " + rs.getDouble("amount") +
                        ", Date: " + rs.getTimestamp("date") +
                        ", Type: " + rs.getString("type");
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    /**
     * Retrieves the current balance of the logged-in user.
     *
     * <p>This method was implemented to ensure users have quick access to their
     * account balance. I made sure it queries the database for real-time data to
     * keep the displayed balance accurate.</p>
     *
     * @return The current balance of the user as a double.
     */
    public double getCurrentBalance() {
        String sql = "SELECT balance FROM Users WHERE userId = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Integer.parseInt(currentUser.fetchUserId()));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    /**
     * Retrieves all bill payments for the currently logged-in user.
     *
     * <p>One of my priorities while designing this method was to allow users to view
     * their bill payment history conveniently. This method fetches all payment records
     * associated with the current user from the database.</p>
     *
     * @return A list of {@code BillPayments} objects representing the user's bill payments.
     */
    public List<BillPayments> getBillPaymentsForUser() {
        List<BillPayments> payments = new ArrayList<>();
        String sql = "SELECT * FROM BillPayments WHERE userId = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Integer.parseInt(currentUser.fetchUserId()));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                BillPayments payment = new BillPayments(
                        rs.getString("bill_type"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("date"),
                        rs.getString("status")
                );
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    /**
     * Adds a new bill payment record for the currently logged-in user.
     *
     * <p>I implemented this method to give users the ability to track their bill payments
     * directly from the application. It inserts the new payment into the database, ensuring
     * the information is persistent.</p>
     *
     * @param billType The type of the bill (e.g., electricity, internet).
     * @param amount   The amount to be paid.
     * @param status   The status of the payment (e.g., "Pending", "Paid").
     */
    public void addBillPayment(String billType, double amount, String status) {
        String sql = "INSERT INTO BillPayments (userId, bill_type, amount, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Integer.parseInt(currentUser.fetchUserId()));
            pstmt.setString(2, billType);
            pstmt.setDouble(3, amount);
            pstmt.setString(4, status);
            pstmt.executeUpdate();

            System.out.println("Bill payment added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetches the currently logged-in user.
     *
     * <p>I made this method simple yet essential. It allows other parts of the application
     * to retrieve the user who is currently logged in. This is particularly useful for
     * features like transactions and bill payments, where user-specific data is required.</p>
     *
     * @return The {@code User} object representing the currently logged-in user.
     */
    public User fetchCurrentUser() {
        return currentUser;
    }
}

