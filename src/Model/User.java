package Model;

import java.sql.*;
import Controller.UserController;

/**
 * The {@code User} class represents the user entity within the banking application.
 *
 * <p>This class is essential for handling user-related operations, including:
 * <ul>
 *   <li>Registration of new users</li>
 *   <li>Authentication (login)</li>
 *   <li>Updating user profiles</li>
 *   <li>Managing password changes</li>
 * </ul>
 * </p>
 *
 * <p>I built this class to ensure a clear representation of the user in the system,
 * providing methods that interact directly with the database for seamless user data management.</p>
 */
public class User {
    private String userId;    // Unique identifier for the user
    private String name;      // Full name of the user
    private String email;     // User's email address
    private String password;  // User's password (stored as plain text for simplicity)

    /**
     * Constructs a {@code User} object with the given attributes.
     *
     * @param userId   The unique identifier for the user.
     * @param name     The name of the user.
     * @param email    The email address of the user.
     * @param password The password for the user's account.
     */
    public User(String userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Retrieves the user's unique ID.
     *
     * @return The user ID.
     */
    public String fetchUserId() {
        return userId;
    }

    /**
     * Updates the user's unique ID.
     *
     * @param userId The new user ID.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the user's name.
     *
     * @return The user's name.
     */
    public String fetchName() {
        return name;
    }

    /**
     * Updates the user's name.
     *
     * @param name The new name for the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the user's email address.
     *
     * @return The user's email.
     */
    public String fetchEmail() {
        return email;
    }

    /**
     * Updates the user's email address.
     *
     * @param email The new email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the user's password.
     *
     * @return The user's password.
     */
    public String fetchPassword() {
        return password;
    }

    /**
     * Updates the user's password.
     *
     * @param password The new password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Registers a new user in the database.
     *
     * @param userId   The unique identifier for the user.
     * @param name     The name of the user.
     * @param email    The email address of the user.
     * @param password The password for the user's account.
     * @return {@code true} if registration is successful; {@code false} otherwise.
     */
    public boolean registerUser(String userId, String name, String email, String password) {
        String sql = "INSERT INTO Users (userId, name, email, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, password);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Authenticates the user based on the provided email and password.
     *
     * @param email    The email address of the user.
     * @param password The password for the user's account.
     * @return {@code true} if login is successful; {@code false} otherwise.
     */
    public boolean login(String email, String password) {
        String sql = "SELECT * FROM Users WHERE email = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (storedPassword.equals(password)) {
                    this.userId = rs.getString("userId");
                    this.name = rs.getString("name");
                    this.email = email;
                    this.password = storedPassword;
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Updates the user's profile with the provided name and email.
     *
     * @param name  The new name for the user.
     * @param email The new email address for the user.
     * @return {@code true} if the profile update is successful; {@code false} otherwise.
     */
    public boolean updateProfile(String name, String email) {
        String sql = "UPDATE Users SET name = ?, email = ? WHERE userId = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, this.userId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                this.name = name;
                this.email = email;
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Changes the user's password after validating the old password.
     *
     * @param oldPassword The current password of the user.
     * @param newPassword The new password for the user.
     * @return {@code true} if the password change is successful; {@code false} otherwise.
     */
    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            String sql = "UPDATE Users SET password = ? WHERE userId = ?";
            try (Connection conn = DatabaseConfig.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, newPassword);
                pstmt.setString(2, this.userId);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    this.password = newPassword;
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
