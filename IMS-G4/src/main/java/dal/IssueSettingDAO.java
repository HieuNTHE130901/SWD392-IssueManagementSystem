package dal;

import dal.dbutils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.IssueSetting;

public class IssueSettingDAO {

    private DBContext dbContext;

    public IssueSettingDAO() {
        dbContext = new DBContext();
    }

    public boolean updateIssueSetting(int issueId, String newIssueType, String newIssueStatus, String newWorkProcess) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dbContext.getConnection(); // Obtain your database connection from your DBContext

            // Prepare the SQL statement
            String query = "UPDATE issue_setting SET issue_type = ?, issue_status = ?, work_process = ? WHERE issue_id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, newIssueType);
            statement.setString(2, newIssueStatus);
            statement.setString(3, newWorkProcess);
            statement.setInt(4, issueId);

            // Execute the update
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Update the updated_date in the issue table
                String updateIssueQuery = "UPDATE issue SET updated_date = NOW() WHERE issue_id = ?";
                statement = connection.prepareStatement(updateIssueQuery);
                statement.setInt(1, issueId);
                statement.executeUpdate();
            }

            return rowsAffected > 0; // Return true if at least one row is affected, indicating a successful update
        } catch (SQLException e) {
            // Handle any exceptions
            e.printStackTrace();
        } finally {
            // Close the database resources (connection and statement) in the reverse order of their creation
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Handle any exceptions that may occur during closing
                e.printStackTrace();
            }
        }

        return false; // Return false if the update is not successful
    }

    public IssueSetting getIssueSettingById(int issueId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dbContext.getConnection(); // Obtain your database connection from your DBContext

            // Prepare the SQL statement
            String query = "SELECT * FROM issue_setting WHERE issue_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, issueId);

            // Execute the query
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Retrieve the values from the result set
                String issueType = resultSet.getString("issue_type");
                String issueStatus = resultSet.getString("issue_status");
                String workProcess = resultSet.getString("work_process");

                // Create and return the IssueSetting object
                return new IssueSetting(issueId, issueType, issueStatus, workProcess);
            } else {
                // No issue setting found with the given issueId
                return null;
            }
        } catch (SQLException e) {
            // Handle any exceptions
            e.printStackTrace();
        } finally {
            // Close the database resources in the reverse order of their creation
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Handle any exceptions that may occur during closing
                e.printStackTrace();
            }
        }

        return null; // Return null if an error occurs
    }
}
