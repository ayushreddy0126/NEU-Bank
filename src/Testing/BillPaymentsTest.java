package Testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import Model.BillPayments;

import java.util.Date;

public class BillPaymentsTest {
    private BillPayments billPayment;
    private Date paymentDate;

    @BeforeEach
    public void setUp() {
        paymentDate = new Date();
        billPayment = new BillPayments("Electricity", 100.0, paymentDate, "Pending");
    }

    @Test
    public void testGetBillType() {
        assertEquals("Electricity", billPayment.getBillType());
    }

    @Test
    public void testGetAmount() {
        assertEquals(100.0, billPayment.getAmount(), 0.001); // delta for double comparison
    }

    @Test
    public void testGetPaymentDate() {
        assertEquals(paymentDate, billPayment.getPaymentDate());
    }

    @Test
    public void testGetStatus() {
        assertEquals("Pending", billPayment.getStatus());
    }

    @Test
    public void testToString() {
        String expected = "Payee: Electricity, Account: null, Amount: 100.0, Payment Date: " + paymentDate.toString() + ", Payment Method: Pending";
        assertEquals(expected, billPayment.toString());
    }

    @Test
    public void testSaveToDatabase() {
        // Assume userId 143 corresponds to "Venkat Reddy"
        try {
            billPayment.saveToDatabase(143); // Assuming this saves without exceptions
            assertTrue(true); // If no exceptions, the test passes
        } catch (Exception e) {
            fail("Saving to the database failed with exception: " + e.getMessage());
        }
    }

    @Test
    public void testFetchPaymentsForUser() {
        // Assume userId 143 corresponds to "Venkat Reddy"
        try {
            var payments = BillPayments.getPaymentsForUser(143);
            assertNotNull(payments);
            assertTrue(payments.size() > 0, "Payments list should not be empty");
        } catch (Exception e) {
            fail("Fetching payments failed with exception: " + e.getMessage());
        }
    }
}
