/**
 * Project: Northeastern University Bank - Full Banking Application
 *
 * <p>This is the main entry point for the fully developed Northeastern University Bank application.
 * This application provides a complete online banking experience with functionalities like user
 * registration, login, transactions, bill payments, and account management.</p>
 *
 * <h2>Main Class Responsibilities:</h2>
 * <ul>
 *   <li>Launches the graphical user interface (GUI) through {@link view.MainApp}.</li>
 *   <li>Serves as the central initialization point for the entire application.</li>
 *   <li>Ensures smooth integration between different modules, including user management,
 *       transaction processing, and database operations.</li>
 * </ul>
 *
 * <h2>References:</h2>
 * <p>I referred to the following resources while designing this application:</p>
 * <ul>
 *   <li><a href="https://youtu.be/7v2OnUti2eM?si=MgA8-GCLm_76SV4h">
 *       Java Swing GUI Design</a> - for crafting the user interface.</li>
 *   <li><a href="https://youtu.be/Kmgo00avvEw?si=YZI8rm8DCY9OIz3q">
 *       Database Integration with Java</a> - for managing backend operations.</li>
 * </ul>
 *
 * <h2>Future Improvements:</h2>
 * <ul>
 *   <li>Add multi-threading to enhance performance for database operations.</li>
 *   <li>Implement a configuration management system for flexible application setup.</li>
 *   <li>Integrate advanced logging and error tracking for debugging.</li>
 * </ul>
 *
 * <p>This main class marks the starting point of a project I have poured my efforts into.
 * Every method and functionality reflects my dedication to building a robust, user-friendly
 * banking system.</p>
 */
public class Main {

    /**
     * The main method initializes the application and launches the GUI.
     * <p>
     * It creates an instance of the {@link view.MainApp} class, which handles
     * the primary user interface and workflow for the banking application.
     * </p>
     *
     * @param args Command-line arguments (currently unused but reserved for future expansion).
     */
    public static void main(String[] args) {
        new view.MainApp(); // Launch the GUI
    }
}
