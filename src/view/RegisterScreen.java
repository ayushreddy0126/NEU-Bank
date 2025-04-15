package view;

import Controller.UserController;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code RegisterScreen} class handles the user registration view.
 * It allows users to create an account by entering their details.
 */
public class RegisterScreen {
    private final JFrame frame;
    private final UserController userController;

    /**
     * Constructs a new RegisterScreen instance.
     *
     * @param frame          the main application frame
     * @param userController the user controller for handling business logic
     */
    public RegisterScreen(JFrame frame, UserController userController) {
        this.frame = frame;
        this.userController = userController;
        showRegisterScreen();
    }

    /**
     * Displays the registration screen, allowing users to register their details.
     * Clears the frame content and sets up a new registration form.
     */
    private void showRegisterScreen() {
        frame.getContentPane().removeAll(); // Clear existing components from the frame
        frame.repaint();
        frame.revalidate();

        // Create the main registration panel with a grid layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 rows, 2 columns with spacing

        // Create form fields and labels
        JLabel userIdLabel = new JLabel("User ID:");
        JTextField userIdField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        // Create buttons for registration and going back
        JButton registerButton = createStyledButton("Register");
        JButton backButton = createStyledButton("Back");

        // Add components to the panel
        panel.add(userIdLabel);
        panel.add(userIdField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(registerButton);
        panel.add(backButton);

        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Revalidate and repaint to ensure the UI updates correctly
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);

        // Action listener for the Register button
        registerButton.addActionListener(e -> {
            userController.registerUser(
                    userIdField.getText(),
                    nameField.getText(),
                    emailField.getText(),
                    new String(passwordField.getPassword())
            );
            JOptionPane.showMessageDialog(frame, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Navigate to the login screen after successful registration
            navigateToLogin();
        });

        // Action listener for the Back button
        backButton.addActionListener(e -> navigateToLogin());
    }

    /**
     * Navigates to the login screen and clears any lingering components.
     */
    private void navigateToLogin() {
        frame.getContentPane().removeAll(); // Clear all components
        frame.repaint();
        frame.revalidate();
        new LoginScreen(frame, userController); // Navigate to the login screen
    }

    /**
     * Creates a styled button with a consistent design.
     *
     * @param text the text to display on the button
     * @return a styled JButton instance
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
