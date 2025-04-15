package Model;

import java.util.Date;

/**
 * The {@code Transaction} class represents a financial transaction within the
 * banking system.
 *
 * <p>This class is designed to manage the essential details of a transaction, including:
 * <ul>
 *     <li>Transaction ID</li>
 *     <li>Amount involved</li>
 *     <li>Date of the transaction</li>
 *     <li>Type of transaction (e.g., Deposit, Withdrawal)</li>
 * </ul>
 * </p>
 *
 * <p>I created this class to ensure that all transaction-related data is encapsulated
 * and can be easily accessed when needed, such as for generating transaction histories
 * or analyzing user activity.</p>
 */
public class Transaction {
    private String transactionID; // Unique identifier for the transaction
    private double amount; // Amount involved in the transaction
    private Date date; // Date of the transaction
    private String type; // Type of transaction (e.g., Deposit, Withdrawal)

    /**
     * Constructs a {@code Transaction} object with the specified details.
     *
     * @param transactionID The unique identifier for the transaction.
     * @param amount        The amount involved in the transaction.
     * @param date          The date the transaction occurred.
     * @param type          The type of transaction (e.g., Deposit, Withdrawal).
     */
    public Transaction(String transactionID, double amount, Date date, String type) {
        this.transactionID = transactionID;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    /**
     * Fetches the unique identifier for the transaction.
     *
     * @return The transaction ID.
     */
    public String fetchTransactionID() {
        return transactionID;
    }

    /**
     * Retrieves the amount involved in the transaction.
     *
     * @return The transaction amount.
     */
    public double fetchAmount() {
        return amount;
    }

    /**
     * Retrieves the date the transaction occurred.
     *
     * @return The transaction date.
     */
    public Date fetchDate() {
        return date;
    }

    /**
     * Retrieves the type of the transaction.
     *
     * @return The type of transaction (e.g., Deposit, Withdrawal).
     */
    public String fetchType() {
        return type;
    }

    /**
     * Returns a string representation of the transaction.
     *
     * <p>This method is particularly useful when I need to display transaction
     * details in a user-friendly format, such as in transaction history views
     * or logs.</p>
     *
     * @return A formatted string containing transaction details.
     */
    @Override
    public String toString() {
        return "Transaction ID: " + transactionID +
                ", Type: " + type +
                ", Amount: " + amount +
                ", Date: " + date;
    }
}
