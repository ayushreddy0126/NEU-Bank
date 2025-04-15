package view;

import Controller.UserController;

import javax.swing.*;

/**
 * The {@code BalanceScreen} class displays the user's current balance in a pop-up dialog.
 * I designed this screen to quickly fetch and show the balance, followed by a smooth
 * transition back to the dashboard for further actions.
 */
public class BalanceScreen {
    private final JFrame frame;
    private final UserController userController;

    /**
     * Constructs a {@code BalanceScreen} object and initiates the process of showing the balance.
     *
     * @param frame          The main {@link JFrame} used to render the screen.
     * @param userController The {@link UserController} instance used to fetch the user's balance.
     */
    public BalanceScreen(JFrame frame, UserController userController) {
        this.frame = frame;
        this.userController = userController;
        showBalanceScreen(); // Display the balance screen when this class is instantiated
    }

    /**
     * Fetches and displays the user's current balance in a dialog box.
     * After showing the balance, I redirect the user back to the dashboard
     * for seamless navigation and better user flow.
     */
    private void showBalanceScreen() {
        // Fetch the current balance from the UserController
        double balance = userController.getCurrentBalance();

        // Show the balance in a message dialog
        JOptionPane.showMessageDialog(
                frame,
                "Your current balance is: $" + balance,
                "Balance",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Navigate back to the dashboard after showing the balance
        new DashboardScreen(frame, userController);
    }
}
