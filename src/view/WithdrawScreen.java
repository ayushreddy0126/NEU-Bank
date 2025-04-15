package view;

import Controller.UserController;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code WithdrawScreen} class handles the user interface for the withdrawal functionality.
 * I created this screen to make it simple and intuitive for users to withdraw money from their accounts.
 */
public class WithdrawScreen {
    private final JFrame frame;
    private final UserController userController;

    /**
     * Constructs a {@code WithdrawScreen} object and initializes the withdrawal UI.
     *
     * @param frame          The main application {@link JFrame}.
     * @param userController The {@link UserController} instance for managing withdrawal operations.
     */
    public WithdrawScreen(JFrame frame, UserController userController) {
        this.frame = frame;
        this.userController = userController;
        showWithdrawScreen(); // Render the screen upon instantiation
    }

    /**
     * Displays the withdrawal screen where users can enter the withdrawal amount.
     * The screen also validates the input and interacts with the controller to process the withdrawal.
     */
    private void showWithdrawScreen() {
        // Clear existing content in the frame
        frame.getContentPane().removeAll();

        // Create a panel with a GridBagLayout for precise alignment
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components

        // Add the label for withdrawal amount
        JLabel withdrawLabel = new JLabel("Enter Amount to Withdraw:");
        withdrawLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(withdrawLabel, gbc);

        // Add the input field for withdrawal amount
        JTextField withdrawField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(withdrawField, gbc);

        // Add the Submit button
        JButton submitButton = createStyledButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span the button across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center-align the button
        panel.add(submitButton, gbc);

        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Action listener for the Submit button
        submitButton.addActionListener(e -> {
            try {
                // Parse the input amount and process the withdrawal
                double amount = Double.parseDouble(withdrawField.getText());
                if (userController.withdraw(amount)) {
                    JOptionPane.showMessageDialog(frame, "Withdrew: $" + amount, "Success", JOptionPane.INFORMATION_MESSAGE);
                    new DashboardScreen(frame, userController); // Redirect to Dashboard
                } else {
                    JOptionPane.showMessageDialog(frame, "Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                // Handle invalid input
                JOptionPane.showMessageDialog(frame, "Invalid amount entered!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * Creates a styled button with consistent design for the application.
     *
     * @param text The text to display on the button.
     * @return A styled {@link JButton}.
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false); // Remove focus outline
        button.setBorderPainted(false); // Remove border
        button.setOpaque(true); // Ensure background color is applied
        return button;
    }
}
