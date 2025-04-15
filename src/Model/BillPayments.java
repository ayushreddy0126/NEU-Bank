package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This {@code BillPayments} class handles the creation, storage, and retrieval of bill payment details.
 */
public class BillPayments {
    private String billType;
    private double amount;
    private Date paymentDate;
    private String status;

    public BillPayments(String billType, double amount, Date paymentDate, String status) {
        this.billType = billType;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }


    public String getBillType() {
        return billType;
    }

    public double getAmount() {
        return amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getStatus() {
        return status;
    }

    /**
     * Saves the current bill payment to the database.
     *
     * @param userId The ID of the logged-in user.
     */
    public void saveToDatabase(int userId) {
        String sql = "INSERT INTO BillPayments (userId, bill_type, amount, date, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, billType);
            pstmt.setDouble(3, amount);
            pstmt.setTimestamp(4, new java.sql.Timestamp(paymentDate.getTime()));
            pstmt.setString(5, status);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all bill payments for the specified user from the database.
     *
     * @param userId The ID of the logged-in user.
     * @return A list of {@code BillPayments} objects.
     */
    public static List<BillPayments> getPaymentsForUser(int userId) {
        List<BillPayments> payments = new ArrayList<>();
        String sql = "SELECT * FROM BillPayments WHERE userId = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                payments.add(new BillPayments(
                        rs.getString("bill_type"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("date"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    /**
     * Returns a human-readable representation of the bill payment.
     *
     * @return A string representation of the bill payment.
     */
    @Override
    public String toString() {
        return "Bill Type: " + billType +
                ", Amount: $" + String.format("%.2f", amount) +
                ", Payment Date: " + paymentDate +
                ", Status: " + status;
    }
}
