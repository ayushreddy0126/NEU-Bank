package view;

import Controller.UserController;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code DepositScreen} class provides a graphical interface for depositing funds into the user's account.
 * It allows the user to enter an amount, submit the deposit, and navigate back to the dashboard after completion.
 */
public class DepositScreen {
    private final JFrame frame;
    private final UserController userController;

    /**
     * Constructs a {@code DepositScreen} object.
     *
     * @param frame          The main {@link JFrame} to render the screen.
     * @param userController The {@link UserController} instance for handling backend deposit functionality.
     */
    public DepositScreen(JFrame frame, UserController userController) {
        this.frame = frame;
        this.userController = userController;
        showDepositScreen();
    }

    /**
     * Displays the deposit interface with a text field for the deposit amount and a submit button.
     * After a successful deposit, the user is redirected to the dashboard.
     */
    private void showDepositScreen() {
        // Clear previous content from the frame
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Label for the deposit field
        JLabel depositLabel = new JLabel("Enter Amount to Deposit:");
        depositLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(depositLabel, gbc);

        // Text field for entering the deposit amount
        JTextField depositField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(depositField, gbc);

        // Submit button
        JButton submitButton = createStyledButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);

        // Add the panel to the frame and make it visible
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Action listener for the submit button
        submitButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(depositField.getText());
                userController.deposit(amount); // Perform deposit operation
                JOptionPane.showMessageDialog(frame, "Deposited: $" + amount, "Success", JOptionPane.INFORMATION_MESSAGE);
                new DashboardScreen(frame, userController); // Redirecting to the dashboard
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount entered!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * Creates a styled button with a consistent design across the application.
     *
     * @param text The label text for the button.
     * @return A styled {@link JButton}.
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.RED); // Red background
        button.setForeground(Color.WHITE); // White text
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Bold font
        button.setFocusPainted(false); // No focus border
        button.setBorderPainted(false); // No border
        button.setOpaque(true); // Ensuring background color
        return button;
    }
}
