package Testing;

import Model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

/**
 * Test class for the {@code Transaction} class.
 * This class ensures the correctness of the {@code Transaction} model's behavior by validating
 * the functionality of its methods such as fetching transaction details and formatting its string representation.
 *
 * <p>By running these tests, I can confirm that the {@code Transaction} class handles
 * transaction data consistently and as expected.</p>
 */
public class TransactionTest {
    private Transaction transaction;
    private Date date;

    /**
     * Sets up a {@code Transaction} object and a sample {@code Date} instance before each test.
     * This ensures a consistent testing environment for all test cases.
     */
    @BeforeEach
    public void setUp() {
        date = new Date(); // Current date for testing
        transaction = new Transaction("TXN123", 500.0, date, "Deposit");
    }

    /**
     * Tests the {@code fetchTransactionID()} method to ensure it retrieves the correct transaction ID.
     */
    @Test
    public void testFetchTransactionID() {
        assertEquals("TXN123", transaction.fetchTransactionID(), "Transaction ID should match the expected value");
    }

    /**
     * Tests the {@code fetchAmount()} method to ensure it retrieves the correct transaction amount.
     * <p>This test uses a delta for comparing floating-point values to account for any rounding differences.</p>
     */
    @Test
    public void testFetchAmount() {
        assertEquals(500.0, transaction.fetchAmount(), 0.001, "Transaction amount should match the expected value");
    }

    /**
     * Tests the {@code fetchDate()} method to ensure it retrieves the correct transaction date.
     */
    @Test
    public void testFetchDate() {
        assertEquals(date, transaction.fetchDate(), "Transaction date should match the expected value");
    }

    /**
     * Tests the {@code fetchType()} method to ensure it retrieves the correct transaction type.
     */
    @Test
    public void testFetchType() {
        assertEquals("Deposit", transaction.fetchType(), "Transaction type should match the expected value");
    }

    /**
     * Tests the {@code toString()} method to ensure it provides the expected string representation of a transaction.
     *
     * <p>The string format is verified to ensure it follows the expected structure and includes
     * all relevant transaction details (ID, type, amount, and date).</p>
     */
    @Test
    public void testToString() {
        String expectedOutput = "Transaction ID: TXN123, Type: Deposit, Amount: 500.0, Date: " + date.toString();
        assertEquals(expectedOutput, transaction.toString(), "String representation of transaction should match the expected format");
    }
}
