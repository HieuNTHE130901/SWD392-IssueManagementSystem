package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

public class UserDAO {
    private DBContext dbContext;

    public UserDAO() {
        dbContext = new DBContext();
    }

    // User login with email
    public User loginWithEmail(String email, String password) {
        User user = null;

        try (Connection connection = dbContext.getConnection()) {
            String query = "SELECT * FROM User WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    // User login with mobile
    public User loginWithMobile(String mobile, String password) {
        User user = null;

        try (Connection connection = dbContext.getConnection()) {
            String query = "SELECT * FROM User WHERE mobile = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, mobile);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    // Extract user data from a ResultSet
    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setFullName(resultSet.getString("full_name"));
        user.setEmail(resultSet.getString("email"));
        user.setMobile(resultSet.getString("mobile"));
        user.setPassword(resultSet.getString("password"));
        user.setAvatarImage(resultSet.getString("avatar_image"));
        user.setVerificationCode(resultSet.getString("verification_code"));
        user.setIsVerified(resultSet.getBoolean("is_verified"));
        user.setUserRole(resultSet.getString("user_role"));
        return user;
    }

    // Register a new user
    public boolean register(User user) {
        try (Connection connection = dbContext.getConnection()) {
            String query = "INSERT INTO User (full_name, email, mobile, password, avatar_image, verification_code, is_verified, user_role) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getMobile());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getAvatarImage());
            preparedStatement.setString(6, user.getVerificationCode());
            preparedStatement.setBoolean(7, user.isIsVerified());
            preparedStatement.setString(8, user.getUserRole());

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean isEmailFromPermittedDomain(String email) {
    // Extract the domain from the email
    String[] parts = email.split("@");
    if (parts.length == 2) {
        String domain = parts[1];

        // Query the database to check if the domain is in the permitted domains
        String sql = "SELECT setting_value FROM systemsettings WHERE setting_name = 'permitted_domain' AND setting_value = ?";
        try (Connection connection = dbContext.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, "@" + domain);
            ResultSet resultSet = preparedStatement.executeQuery();

            // If a record is found, the domain is permitted
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any database errors here
        }
    }

    return false; // If the domain is not found in the database, it's not permitted
}

}
