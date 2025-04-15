package view;

import Controller.UserController;
import Model.BillPayments;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The {@code BillPaymentsScreen} class displays the user's bill payments
 * and provides options to add a new payment or navigate back to the dashboard.
 * I designed this screen to ensure simplicity and a user-friendly interface
 * for managing bill payments.
 */
public class BillPaymentsScreen {
    private final JFrame frame;
    private final UserController userController;

    /**
     * Constructs a {@code BillPaymentsScreen} object.
     *
     * @param frame          The main {@link JFrame} to render the screen.
     * @param userController The {@link UserController} instance to fetch user-related data.
     */
    public BillPaymentsScreen(JFrame frame, UserController userController) {
        this.frame = frame;
        this.userController = userController;
        showBillPaymentsScreen(); // Display the screen when this class is instantiated
    }

    /**
     * Renders the bill payments screen with a list of payments and action buttons.
     * I aimed for a clean design with clear navigation options.
     */
    private void showBillPaymentsScreen() {
        frame.getContentPane().removeAll(); // Clear previous components from the frame

        // Main panel with BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Bill Payments", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Bold font for emphasis
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding for better appearance
        panel.add(titleLabel, BorderLayout.NORTH);

        // List to display bill payments
        List<BillPayments> payments = userController.getBillPaymentsForUser(); // Fetch user-specific payments
        DefaultListModel<String> paymentListModel = new DefaultListModel<>();
        for (BillPayments payment : payments) {
            paymentListModel.addElement(payment.toString()); // Add each payment to the list model
        }

        JList<String> paymentList = new JList<>(paymentListModel); // Create a JList with the payment data
        JScrollPane scrollPane = new JScrollPane(paymentList); // Add scrolling capability for long lists
        panel.add(scrollPane, BorderLayout.CENTER);

        // Button panel at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center-align buttons with spacing
        JButton addPaymentButton = createStyledButton("Add Payment"); // Button to add a new payment
        JButton backButton = createStyledButton("Back"); // Button to navigate back
        buttonPanel.add(addPaymentButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom

        // Add the main panel to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Action listener for the Add Payment button
        addPaymentButton.addActionListener(e -> new AddBillPaymentScreen(frame, userController));

        // Action listener for the Back button
        backButton.addActionListener(e -> new DashboardScreen(frame, userController));
    }

    /**
     * Creates a styled button to maintain a consistent look across the application.
     * I decided to use a fixed size and bold colors to make the buttons stand out.
     *
     * @param text The label text for the button.
     * @return A styled {@link JButton}.
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.RED); // Red background for contrast
        button.setForeground(Color.WHITE); // White text for readability
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Bold font for emphasis
        button.setFocusPainted(false); // Remove focus border
        button.setBorderPainted(false); // Remove button border
        button.setOpaque(true); // Ensure the background color is visible
        button.setPreferredSize(new Dimension(150, 40)); // Set a fixed button size for uniformity
        return button;
    }
}
