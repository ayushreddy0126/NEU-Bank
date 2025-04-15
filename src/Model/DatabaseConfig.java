package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The {@code DatabaseConfig} class provides utility methods to establish a connection
 * to the MySQL database used by the Banking System application.
 *
 * <p>In this project, I wanted to ensure that all database interactions are centralized,
 * and having this configuration class has made the application more maintainable. By
 * abstracting the database connection logic here, the rest of the application doesn't
 * need to worry about connection details or initialization.</p>
 */
public class DatabaseConfig {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/BankingSystem";
    private static final String USER = "root"; // Username for the database
    private static final String PASSWORD = "password"; // Password for the database

    /**
     * Establishes and returns a connection to the database.
     *
     * <p>This method loads the MySQL JDBC driver and connects to the database using the
     * provided URL, username, and password. If the connection fails, an appropriate error
     * message is logged to the console.</p>
     *
     * <p>I wanted to ensure that this method is straightforward to use, so it hides all
     * complexity from the caller and just returns a {@code Connection} object.</p>
     *
     * @return A {@code Connection} object if the connection is successful, or {@code null} if it fails.
     */
    public static Connection getConnection() {
        try {
            // Explicitly loading the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed! Check output console.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Main method to test the database connection.
     *
     * <p>Before integrating this class into the rest of the application, I wanted a quick
     * way to verify that the database connection works correctly. This main method does
     * exactly that by attempting to connect to the database and printing the result to
     * the console.</p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Test connection
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Database connected successfully!");
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
