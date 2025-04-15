package view;

import Controller.UserController;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code AddBillPaymentScreen} class allows users to add a new bill payment.
 * I created this screen to ensure that users can easily input details for their bills,
 * including the type, amount, and payment status.
 */
public class AddBillPaymentScreen {
    private final JFrame frame;
    private final UserController userController;

    /**
     * Constructs an {@code AddBillPaymentScreen} object and initializes the screen.
     *
     * @param frame          The main {@link JFrame} used to render the screen.
     * @param userController The {@link UserController} instance to handle the logic for adding bill payments.
     */
    public AddBillPaymentScreen(JFrame frame, UserController userController) {
        this.frame = frame;
        this.userController = userController;
        showAddBillPaymentScreen(); // Display the screen immediately upon instantiation
    }

    /**
     * Renders the "Add Bill Payment" screen, allowing users to input and submit bill details.
     * This method provides an intuitive UI with fields for bill type, amount, and status.
     * Once submitted, the data is processed and saved using the controller.
     */
    private void showAddBillPaymentScreen() {
        // Clear previous components
        frame.getContentPane().removeAll();

        // Create a panel with a grid layout for form elements
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Create input fields
        JTextField billTypeField = new JTextField();
        JTextField amountField = new JTextField();
        JComboBox<String> statusComboBox = new JComboBox<>(new String[]{"Pending", "Paid"});

        // Add labels and fields to the panel
        panel.add(new JLabel("Bill Type:"));
        panel.add(billTypeField);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(new JLabel("Status:"));
        panel.add(statusComboBox);

        // Create and add the Submit button
        JButton submitButton = createStyledButton("Submit");
        panel.add(submitButton);

        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Action listener for the Submit button
        submitButton.addActionListener(e -> {
            try {
                String billType = billTypeField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String status = (String) statusComboBox.getSelectedItem();

                // Save the bill payment using the controller
                userController.addBillPayment(billType, amount, status);

                // Show success message and navigate back to the bill payments screen
                JOptionPane.showMessageDialog(
                        frame,
                        "Payment added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                new BillPaymentsScreen(frame, userController);
            } catch (NumberFormatException ex) {
                // Handle invalid input for the amount field
                JOptionPane.showMessageDialog(
                        frame,
                        "Please enter a valid amount.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    /**
     * Creates a styled button for consistency across the application.
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
