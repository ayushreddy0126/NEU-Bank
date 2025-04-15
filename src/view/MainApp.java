package view;

import Controller.UserController;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code MainApp} class serves as the entry point for the Northeastern University Bank application.
 * It initializes the main frame, manages navigation between screens, and provides a consistent title bar.
 *
 * <p>This class uses a {@link JFrame} to render the application's user interface and incorporates
 * the {@code UserController} for backend functionality such as user authentication and transactions.</p>
 *
 * <p>Features include:</p>
 * <ul>
 *   <li>Dynamic screen rendering (e.g., LoginScreen).</li>
 *   <li>A persistent title bar with branding and a logo.</li>
 *   <li>Integration with the application's controller layer.</li>
 * </ul>
 */
public class MainApp {
    private final JFrame frame;
    private final UserController userController;

    /**
     * Constructs the {@code MainApp} instance, initializing the main application window
     * and rendering the initial login screen.
     */
    public MainApp() {
        // Initializing the main frame
        frame = new JFrame("Northeastern University Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Initializing the user controller
        userController = new UserController();

        // Shows the initial screen
        showLoginScreen();
    }

    /**
     * Adds a title bar to the frame with a black background, red text, and the Northeastern University logo.
     *
     * <p>The title bar is positioned at the top (NORTH) of the frame and contains:</p>
     * <ul>
     *   <li>A scaled logo on the left.</li>
     *   <li>The application title in the center.</li>
     * </ul>
     */
    private void addTitleBar() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.BLACK); // Black background
        titlePanel.setPreferredSize(new Dimension(frame.getWidth(), 50));

        // Loads the Northeastern University logo
        ImageIcon icon = new ImageIcon("/Users/ayushreddy/Downloads/Midsem/src/NortheasternLogo.png");
        Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
        titlePanel.add(iconLabel, BorderLayout.WEST);

        // Title text
        JLabel titleLabel = new JLabel("Northeastern University Bank", SwingConstants.CENTER);
        titleLabel.setForeground(Color.RED); // Red text color
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // Adding the title panel to the frame's NORTH region
        frame.add(titlePanel, BorderLayout.NORTH);
    }

    /**
     * Clears all components from the frame's content pane and re-adds the title bar.
     *
     * <p>This method ensures a consistent title bar while dynamically updating the displayed screen.</p>
     */
    public void clearFrame() {
        frame.getContentPane().removeAll();
        addTitleBar();
        frame.repaint();
        frame.revalidate();
    }

    /**
     * Displays the {@code LoginScreen} by clearing existing components and rendering the login interface.
     *
     * <p>This method acts as a navigation point to the initial user authentication screen.</p>
     */
    public void showLoginScreen() {
        clearFrame();
        new LoginScreen(frame, userController);
    }

    /**
     * The entry point for the Northeastern University Bank application.
     *
     * <p>Initializes the {@code MainApp} and renders the login screen.</p>
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new MainApp();
    }
}
