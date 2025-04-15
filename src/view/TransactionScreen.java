package view;

import Controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The {@code TransactionScreen} class displays the user's transaction history.
 * I designed this screen to provide users with a clear and detailed view of their past transactions.
 */
public class TransactionScreen {
    private final JFrame frame;
    private final UserController userController;

    /**
     * Constructs a {@code TransactionScreen} object and initializes the transaction history display.
     *
     * @param frame          The main application {@link JFrame}.
     * @param userController The {@link UserController} instance for accessing transaction data.
     */
    public TransactionScreen(JFrame frame, UserController userController) {
        this.frame = frame;
        this.userController = userController;
        showTransactionScreen(); // Render the screen immediately upon instantiation
    }

    /**
     * Displays the transaction history screen.
     * This method fetches the user's transaction history using the controller and renders it in a scrollable list.
     */
    private void showTransactionScreen() {
        // Clear any previous content in the frame
        frame.getContentPane().removeAll();

        // Create a panel with a BorderLayout to organize components
        JPanel panel = new JPanel(new BorderLayout());

        // Fetch transaction history
        List<String> transactions = userController.viewTransactionHistory();

        // Create a scrollable list for displaying transactions
        JList<String> transactionList = new JList<>(transactions.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(transactionList);

        // Add a title label
        JLabel titleLabel = new JLabel("Transaction History", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding

        // Add components to the panel
        panel.add(titleLabel, BorderLayout.NORTH); // Title at the top
        panel.add(scrollPane, BorderLayout.CENTER); // Transaction list in the center

        // Create and add a Back button
        JButton backButton = createStyledButton("Back");
        panel.add(backButton, BorderLayout.SOUTH);

        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Back button navigates to the Dashboard
        backButton.addActionListener(e -> new DashboardScreen(frame, userController));
    }

    /**
     * Creates a styled button with a consistent design for the application.
     *
     * @param text The text to display on the button.
     * @return A styled {@link JButton}.
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        return button;
    }
}
