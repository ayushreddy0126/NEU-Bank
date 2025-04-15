package Testing;

import Model.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import Controller.UserController;

import java.util.List;

/**
 * Test class for the {@code UserController} class, focusing on validating
 * its functionalities like user registration, login, deposits, withdrawals,
 * transaction history, and balance retrieval.
 *
 * <p>By conducting these tests, I aim to ensure the integrity of the
 * application's user and transaction management features, providing
 * confidence in its reliability and correctness.</p>
 */
public class UserControllerTest {

    private UserController userController;

    /**
     * Initializes the {@code UserController} instance before each test.
     */
    @Before
    public void setUp() {
        userController = new UserController();
    }

    /**
     * Tests the user registration process.
     *
     * <p>This test verifies that a new user can be successfully registered
     * and that the user details are correctly stored.</p>
     */
    @Test
    public void testRegisterUserSuccess() {
        User user = userController.registerUser("143", "Venkat Reddy", "Venkat@email.com", "password");
        assertNotNull("User should be registered successfully", user);
        assertEquals("Venkat Reddy", user.fetchName());
        assertEquals("Venkat@email.com", user.fetchEmail());
    }

    /**
     * Tests user login with valid credentials.
     *
     * <p>This test ensures that users can log in when they provide the
     * correct email and password.</p>
     */
    @Test
    public void testLoginSuccess() {
        userController.registerUser("143", "Venkat Reddy", "Venkat@email.com", "password");
        User loggedInUser = userController.loginUser("Venkat@email.com", "password");
        assertNotNull("User should be able to log in", loggedInUser);
        assertEquals("Venkat Reddy", loggedInUser.fetchName());
    }

    /**
     * Tests user login with invalid credentials.
     *
     * <p>This test verifies that the login process fails when incorrect
     * credentials are provided.</p>
     */
    @Test
    public void testLoginFailure() {
        userController.registerUser("143", "Venkat Reddy", "Venkat@email.com", "password");
        User loggedInUser = userController.loginUser("Venkat@email.com", "wrongpassword");
        assertNull("Login should fail with incorrect credentials", loggedInUser);
    }

    /**
     * Tests the deposit functionality.
     *
     * <p>This test validates that the deposit process updates the user's
     * balance correctly and records the transaction.</p>
     */
    @Test
    public void testDeposit() {
        userController.registerUser("143", "Venkat Reddy", "Venkat@email.com", "password");
        userController.loginUser("Venkat@email.com", "password");

        userController.deposit(200.0);
        double balance = userController.getCurrentBalance();
        assertEquals("Balance should be updated after deposit", 200.0, balance, 0.01);
    }

    /**
     * Tests the withdrawal functionality with sufficient balance.
     *
     * <p>This test ensures that withdrawals succeed when the user's balance
     * is sufficient and that the balance is updated accordingly.</p>
     */
    @Test
    public void testWithdrawSuccess() {
        userController.registerUser("143", "Venkat Reddy", "Venkat@email.com", "password");
        userController.loginUser("Venkat@email.com", "password");

        userController.deposit(200.0);
        boolean result = userController.withdraw(100.0);
        assertTrue("Withdrawal should succeed with sufficient balance", result);

        double balance = userController.getCurrentBalance();
        assertEquals("Balance should be updated after withdrawal", 100.0, balance, 0.01);
    }

    /**
     * Tests the withdrawal functionality with insufficient balance.
     *
     * <p>This test verifies that the withdrawal process fails when the
     * requested amount exceeds the user's current balance.</p>
     */
    @Test
    public void testWithdrawFailure() {
        userController.registerUser("143", "Venkat Reddy", "Venkat@email.com", "password");
        userController.loginUser("Venkat@email.com", "password");

        userController.deposit(100.0);
        boolean result = userController.withdraw(200.0);
        assertFalse("Withdrawal should fail with insufficient balance", result);

        double balance = userController.getCurrentBalance();
        assertEquals("Balance should remain unchanged after failed withdrawal", 100.0, balance, 0.01);
    }

    /**
     * Tests the transaction history retrieval functionality.
     *
     * <p>This test ensures that all user transactions (deposits and withdrawals)
     * are accurately recorded and retrievable.</p>
     */
    @Test
    public void testViewTransactionHistory() {
        userController.registerUser("143", "Venkat Reddy", "Venkat@email.com", "password");
        userController.loginUser("Venkat@email.com", "password");

        userController.deposit(200.0);
        userController.withdraw(100.0);

        List<String> transactions = userController.viewTransactionHistory();
        assertEquals("Transaction history should contain 2 entries", 2, transactions.size());
    }

    /**
     * Tests the current balance retrieval functionality.
     *
     * <p>This test validates that the current balance reflects all deposits
     * and withdrawals performed by the user.</p>
     */
    @Test
    public void testGetCurrentBalance() {
        userController.registerUser("143", "Venkat Reddy", "Venkat@email.com", "password");
        userController.loginUser("Venkat@email.com", "password");

        userController.deposit(150.0);
        double balance = userController.getCurrentBalance();
        assertEquals("Current balance should reflect deposited amount", 150.0, balance, 0.01);
    }
}
