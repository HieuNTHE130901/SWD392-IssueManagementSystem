package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAO extends BaseDAO {

    

    // User login with email
    public User loginWithEmail(String email, String password) {
        User user = null;

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM user WHERE email = ? AND password = ?";
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

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM user WHERE mobile = ? AND password = ?";
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

// The rest of your methods remain the same
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
        user.setUserRole(resultSet.getString("user_role"));
        return user;
    }

    public boolean registerByEmail(User user) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false); // Start a transaction

            // Calculate the new user_id using a subquery
            String selectMaxUserIdQuery = "SELECT COALESCE(MAX(user_id), 0) + 1 AS new_user_id FROM user";
            PreparedStatement selectStatement = connection.prepareStatement(selectMaxUserIdQuery);
            ResultSet resultSet = selectStatement.executeQuery();

            int newUserId = 1; // Default value if no user exists in the table

            if (resultSet.next()) {
                newUserId = resultSet.getInt("new_user_id");
            }

            // Now, insert the user data with the calculated user_id
            String insertQuery = "INSERT INTO user (user_id, full_name, email, password, user_role) VALUES (?, ?, ?, ?, student)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, newUserId);
            preparedStatement.setString(2, user.getFullName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                connection.commit(); // Commit the transaction if the insertion is successful
                return true;
            } else {
                connection.rollback(); // Rollback the transaction if there's an issue with the insertion
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean registerByMobile(User user) {
        try (Connection connection = getConnection()) {
            // Calculate the new user_id using a subquery
            String selectMaxUserIdQuery = "SELECT COALESCE(MAX(user_id), 0) + 1 AS new_user_id FROM user";
            PreparedStatement selectStatement = connection.prepareStatement(selectMaxUserIdQuery);
            ResultSet resultSet = selectStatement.executeQuery();

            int newUserId = 1; // Default value if no user exists in the table

            if (resultSet.next()) {
                newUserId = resultSet.getInt("new_user_id");
            }
            // Now, insert the user data with the calculated user_id
            String insertQuery = "INSERT INTO user (user_id, full_name, mobile, password, user_role) VALUES (?, ?, ?, ?, student)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, newUserId);
            preparedStatement.setString(2, user.getFullName());
            preparedStatement.setString(3, user.getMobile());
            preparedStatement.setString(4, user.getPassword());

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePassword(User user) {
        try (Connection connection = getConnection()) {
            String query = "UPDATE user SET password = ? WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setInt(2, user.getUserId());

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user) {
        try (Connection connection = getConnection()) {
            String query = "UPDATE user SET full_name = ?, email = ?, mobile = ? WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getMobile());
            preparedStatement.setInt(4, user.getUserId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllStudents() {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish a database connection (replace these details with your actual database connection)
            conn = getConnection();

            // Create an SQL statement to retrieve all semesters
            String sql = "SELECT user_id, full_name FROM User WHERE user_role = 'Student'";
            stmt = conn.prepareStatement(sql);

            // Execute the SQL query
            rs = stmt.executeQuery();

            // Process the results and populate the list of semesters
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        } finally {
            // Close database resources in the reverse order of their creation
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions appropriately
            }
        }

        return users;
    }

    public User getUserByEmailOrMobile(String emailOrMobile) {
        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM user WHERE email = ? OR mobile = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, emailOrMobile);
                ps.setString(2, emailOrMobile);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        User user = new User();
                        user.setUserId(rs.getInt("user_id"));
                        user.setFullName(rs.getString("full_name"));
                        user.setEmail(rs.getString("email"));
                        user.setMobile(rs.getString("mobile"));
                        user.setPassword(rs.getString("password"));
                        user.setUserRole(rs.getString("user_role"));
                        // Set other user properties as needed
                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }

        return null;
    }
    
    public int getTeacherIdForUser(int userId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            String query = "SELECT teacher_id FROM class WHERE class_id IN (SELECT class_id FROM class_member WHERE user_id = ?)";

            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("teacher_id");
            }

            // If no teacher is found, you can return a default value or throw an exception.
            // For now, let's return -1 if no teacher is found.
            return -1;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    public String getUserRoleById(int userId) {
        String userRole = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String query = "SELECT `user_role` FROM `user` WHERE `user_id` = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId); // Set the user ID parameter

            rs = ps.executeQuery();

            if (rs.next()) {
                userRole = rs.getString("user_role");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        } finally {
            // Close resources in a finally block
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception properly in your application
            }
        }
        return userRole;
    }
    
    


   

}
