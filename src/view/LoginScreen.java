package view;

import Controller.UserController;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code LoginScreen} class represents the login interface for the Northeastern University Bank application.
 * It provides fields for user credentials and navigation to the dashboard or registration screen.
 *
 * <p>This screen serves as the entry point for authenticated users and allows new users to register.</p>
 */
public class LoginScreen {
    private final JFrame frame;
    private final UserController userController;

    /**
     * Constructs a {@code LoginScreen} object.
     *
     * @param frame          The main {@link JFrame} to render the screen.
     * @param userController The {@link UserController} instance for backend operations like login.
     */
    public LoginScreen(JFrame frame, UserController userController) {
        this.frame = frame;
        this.userController = userController;
        showLoginScreen();
    }

    /**
     * Displays the login interface with fields for email and password and buttons for login and registration.
     */
    private void showLoginScreen() {
        // Clear the frame and set up the login panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Email label and field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(emailField, gbc);

        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passwordField, gbc);

        // Login button
        JButton loginButton = createStyledButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        // Register button
        JButton registerButton = createStyledButton("Register");
        gbc.gridy++;
        panel.add(registerButton, gbc);

        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Login button action
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            if (userController.loginUser(email, password) != null) {
                new DashboardScreen(frame, userController);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Register button action
        registerButton.addActionListener(e -> new RegisterScreen(frame, userController));
    }

    /**
     * Creates a styled button with consistent design for the application.
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
        button.setOpaque(true); // Ensure background color
        return button;
    }
}
