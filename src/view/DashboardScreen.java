package view;

import Controller.UserController;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code DashboardScreen} class represents the main menu of the application where users
 * can perform key banking operations like deposit, withdrawal, viewing transactions, checking balance,
 * and managing bill payments. This is the central hub of the application, and I made sure it is intuitive
 * and visually aligned.
 */
public class DashboardScreen {
    private final JFrame frame;
    private final UserController userController;

    /**
     * Constructs a {@code DashboardScreen} object.
     *
     * @param frame          The main {@link JFrame} to render the dashboard.
     * @param userController The {@link UserController} instance to handle backend interactions.
     */
    public DashboardScreen(JFrame frame, UserController userController) {
        this.frame = frame;
        this.userController = userController;
        showDashboard(); // Displaying the dashboard screen when this class is instantiated
    }

    /**
     * Displays the dashboard with buttons for different banking functionalities.
     * I wanted to ensure the layout is clean, so I used GridBagLayout to neatly align the buttons.
     */
    private void showDashboard() {
        // Clearing previous components from the frame
        frame.getContentPane().removeAll();

        // Using a panel with GridBagLayout for precise button alignment
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        // Creating buttons for each functionality
        JButton depositButton = createStyledButton("Deposit");
        JButton withdrawButton = createStyledButton("Withdraw");
        JButton viewTransactionsButton = createStyledButton("View Transactions");
        JButton checkBalanceButton = createStyledButton("Check Balance");
        JButton billPaymentsButton = createStyledButton("Bill Payments");
        JButton logoutButton = createStyledButton("Logout");

        // Adding the  buttons to the panel with appropriate spacing
        gbc.gridy = 0;
        panel.add(depositButton, gbc);

        gbc.gridy++;
        panel.add(withdrawButton, gbc);

        gbc.gridy++;
        panel.add(viewTransactionsButton, gbc);

        gbc.gridy++;
        panel.add(checkBalanceButton, gbc);

        gbc.gridy++;
        panel.add(billPaymentsButton, gbc);

        gbc.gridy++;
        panel.add(logoutButton, gbc);

        // Adding the panel to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();


        depositButton.addActionListener(e -> new DepositScreen(frame, userController));
        withdrawButton.addActionListener(e -> new WithdrawScreen(frame, userController));
        viewTransactionsButton.addActionListener(e -> new TransactionScreen(frame, userController));
        checkBalanceButton.addActionListener(e -> new BalanceScreen(frame, userController));
        billPaymentsButton.addActionListener(e -> new BillPaymentsScreen(frame, userController));
        logoutButton.addActionListener(e -> new LoginScreen(frame, userController));
    }

    /**
     * Creates a styled button to maintain a consistent look and feel across the application.
     * I chose red for the background to match the application's theme.
     *
     * @param text The label text for the button.
     * @return A styled {@link JButton}.
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Bold font
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(200, 40));
        return button;
    }
}
