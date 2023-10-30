package dao;

import model.Issue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author trung
 */
public class IssueDAO extends BaseDAO{

    

    public List<Issue> getAllIssues() {
        List<Issue> issues = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String query = "SELECT "
                    + "i.issue_id, "
                    + "p.project_code, "
                    + "c.class_name, "
                    + "s.subject_code, "
                    + "ua.full_name AS assigner_name, "
                    + "ue.full_name AS assignee_name, "
                    + "i.created_date, "
                    + "i.updated_date, "
                    + "i.description "
                    + "FROM "
                    + "issue i "
                    + "JOIN project p ON i.project_id = p.project_id "
                    + "JOIN class c ON p.class_id = c.class_id "
                    + "JOIN subject s ON c.subject_id = s.subject_id "
                    + "JOIN user ua ON i.assigner_id = ua.user_id "
                    + "JOIN user ue ON i.assignee_id = ue.user_id";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Issue issue = new Issue();
                issue.setIssueId(rs.getInt("issue_id"));
                issue.setProjectCode(rs.getString("project_code"));
                issue.setClassName(rs.getString("class_name"));
                issue.setSubjectCode(rs.getString("subject_code"));
                issue.setAssignerName(rs.getString("assigner_name"));
                issue.setAssigneeName(rs.getString("assignee_name"));
                java.sql.Date createdDate = rs.getDate("created_date");
                java.sql.Date updatedDate = rs.getDate("updated_date");
                issue.setCreatedDate(createdDate);
                issue.setUpdatedDate(updatedDate);
                issue.setDescription(rs.getString("description"));

                issues.add(issue);
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

        return issues;
    }

    public List<Issue> getIssuesForCurrentUser(int userId) {
        List<Issue> issues = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String query = "SELECT\n"
                    + "    i.issue_id,\n"
                    + "    p.project_code,\n"
                    + "    c.class_name,\n"
                    + "    s.subject_code,\n"
                    + "    ua.full_name AS assigner_name,\n"
                    + "    ue.full_name AS assignee_name,\n"
                    + "    i.created_date,\n"
                    + "    i.updated_date,\n"
                    + "    ist.issue_type,\n"
                    + "    ist.issue_status,\n"
                    + "    i.description\n"
                    + "FROM\n"
                    + "    issue i\n"
                    + "    JOIN project p ON i.project_id = p.project_id\n"
                    + "    JOIN class c ON p.class_id = c.class_id\n"
                    + "    JOIN subject s ON c.subject_id = s.subject_id\n"
                    + "    JOIN `user` ua ON i.assigner_id = ua.user_id\n"
                    + "    JOIN `user` ue ON i.assignee_id = ue.user_id\n"
                    + "    JOIN issue_setting ist ON i.issue_id = ist.issue_id\n"
                    + "WHERE\n"
                    + "    p.project_id IN (\n"
                    + "        SELECT pm.project_id\n"
                    + "        FROM project_member pm\n"
                    + "        JOIN `user` u ON pm.member_id = u.user_id\n"
                    + "        WHERE u.user_id = ?\n"
                    + "    );";

            ps = conn.prepareStatement(query);
            ps.setInt(1, userId); // Set the user ID parameter

            rs = ps.executeQuery();

            while (rs.next()) {
                Issue issue = new Issue();
                issue.setIssueId(rs.getInt("issue_id"));
                issue.setProjectCode(rs.getString("project_code"));
                issue.setClassName(rs.getString("class_name"));
                issue.setSubjectCode(rs.getString("subject_code"));
                issue.setAssignerName(rs.getString("assigner_name"));
                issue.setAssigneeName(rs.getString("assignee_name"));
                java.sql.Date createdDate = rs.getDate("created_date");
                java.sql.Date updatedDate = rs.getDate("updated_date");
                issue.setCreatedDate(createdDate);
                issue.setUpdatedDate(updatedDate);
                issue.setIssueType(rs.getString("issue_type"));
                issue.setIssueStatus(rs.getString("issue_status"));
                issue.setDescription(rs.getString("description"));

                issues.add(issue);
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

        return issues;
    }

    public boolean addIssue(Issue issue) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();
            String query = "INSERT INTO issue (project_id, milestone_id, assigner_id, assignee_id, description, created_date, updated_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);

            // Set the values for the prepared statement
            ps.setInt(1, issue.getProjectId());
            ps.setInt(2, issue.getMilestoneId());
            ps.setInt(3, issue.getAssignerId());
            ps.setInt(4, issue.getAssigneeId());
            ps.setString(5, issue.getDescription());
            ps.setTimestamp(6, new Timestamp(issue.getCreatedDate().getTime()));
            ps.setTimestamp(7, new Timestamp(issue.getUpdatedDate().getTime()));

            int rowsAffected = ps.executeUpdate();

            // Check if the insert was successful
            if (rowsAffected > 0) {
                return true; // Issue added successfully
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        } finally {
            // Close resources in a finally block
            try {
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

        return false; // Insertion failed
    }

    public boolean insertNewIssue(Issue issue, String issueType, String issueStatus) {
        Connection connection = null;
        PreparedStatement issueStatement = null;
        PreparedStatement issueSettingStatement = null;

        try {
            // Get a database connection
            connection = getConnection();

            // Retrieve the maximum issue_id from the issue table
            String maxIssueIdQuery = "SELECT MAX(issue_id) FROM issue";
            Statement maxIssueIdStatement = connection.createStatement();
            ResultSet maxIssueIdResult = maxIssueIdStatement.executeQuery(maxIssueIdQuery);
            int maxIssueId = 0;
            if (maxIssueIdResult.next()) {
                maxIssueId = maxIssueIdResult.getInt(1);
            }
            int newIssueId = maxIssueId + 1;

            // Retrieve the maximum issue_setting_id from the issue_setting table
            String maxIssueSettingIdQuery = "SELECT MAX(issue_setting_id) FROM issue_setting";
            Statement maxIssueSettingIdStatement = connection.createStatement();
            ResultSet maxIssueSettingIdResult = maxIssueSettingIdStatement.executeQuery(maxIssueSettingIdQuery);
            int maxIssueSettingId = 0;
            if (maxIssueSettingIdResult.next()) {
                maxIssueSettingId = maxIssueSettingIdResult.getInt(1);
            }
            int newIssueSettingId = maxIssueSettingId + 1;

            // Insert the new issue into the issue table
            String issueQuery = "INSERT INTO issue (issue_id, project_id, milestone_id, assigner_id, assignee_id, description, created_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
            issueStatement = connection.prepareStatement(issueQuery);
            issueStatement.setInt(1, newIssueId);
            issueStatement.setInt(2, issue.getProjectId());
            issueStatement.setInt(3, issue.getMilestoneId());
            issueStatement.setInt(4, issue.getAssignerId());
            issueStatement.setInt(5, issue.getAssigneeId());
            issueStatement.setString(6, issue.getDescription());
            // Get the current timestamp
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            issueStatement.setTimestamp(7, currentTimestamp);
            int rowsAffected = issueStatement.executeUpdate();

            // Check if the insertion was successful
            if (rowsAffected == 1) {

                // Insert the issue setting into the issue_setting table
                String issueSettingQuery = "INSERT INTO issue_setting (issue_setting_id, issue_id, issue_type, issue_status) VALUES (?, ?, ?, ?)";
                issueSettingStatement = connection.prepareStatement(issueSettingQuery);
                issueSettingStatement.setInt(1, newIssueSettingId);
                issueSettingStatement.setInt(2, newIssueId);
                issueSettingStatement.setString(3, issueType);
                issueSettingStatement.setString(4, issueStatus);
                int rowsAffectedSetting = issueSettingStatement.executeUpdate();

                // Check if the insertion of the issue setting was successful
                if (rowsAffectedSetting == 1) {
                    return true; // Issue and issue setting inserted successfully
                }
            }
        } catch (SQLException e) {
            // Handle any exceptions
            e.printStackTrace();
        } finally {
            // Close the database resources
            try {
                if (issueStatement != null) {
                    issueStatement.close();
                }
                if (issueSettingStatement != null) {
                    issueSettingStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false; // Issue or issue setting insertion failed
    }

    public Issue getIssueById(String issueId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish a database connection
            connection = getConnection();

            // Prepare the SQL statement
            String sql = "SELECT * FROM issue WHERE issue_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, issueId);

            // Execute the query
            resultSet = statement.executeQuery();

            // Check if the issue was found
            if (resultSet.next()) {
                // Retrieve the issue data from the result set
                Issue issue = new Issue();
                issue.setIssueId(resultSet.getInt("issue_id"));
                // Set other properties of the issue object

                // Return the issue object
                return issue;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions
        } finally {
            // Close the database resources
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
                e.printStackTrace();
                // Handle any potential exceptions
            }
        }

        return null; // Issue not found
    }

    public Issue viewIssueDetails(int issueId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Issue issue = null;

        try {
            connection = getConnection();

            // Prepare the SQL query
            String query = "SELECT\n"
                    + "    i.issue_id,\n"
                    + "    issue_setting.issue_type,\n"
                    + "    issue_setting.issue_status,\n"
                    + "    p.project_code,\n"
                    + "    c.class_name,\n"
                    + "    s.subject_code,\n"
                    + "    sem.semester_name,\n"
                    + "    um.full_name AS manager_name,\n"
                    + "    ua.full_name AS assigner_name,\n"
                    + "    ue.full_name AS assignee_name,\n"
                    + "    i.created_date,\n"
                    + "    i.updated_date,\n"
                    + "    i.description,\n"
                    + "    issue_setting.work_process\n"
                    + "FROM\n"
                    + "    issue AS i\n"
                    + "    JOIN issue_setting ON i.issue_id = issue_setting.issue_id\n"
                    + "    JOIN project AS p ON i.project_id = p.project_id\n"
                    + "    JOIN class AS c ON p.class_id = c.class_id\n"
                    + "    JOIN subject AS s ON c.subject_id = s.subject_id\n"
                    + "    JOIN semester AS sem ON c.semester_id = sem.semester_id\n"
                    + "    JOIN user AS um ON s.manager_id = um.user_id\n"
                    + "    JOIN user AS ua ON i.assigner_id = ua.user_id\n"
                    + "    JOIN user AS ue ON i.assignee_id = ue.user_id\n"
                    + "WHERE\n"
                    + "    i.issue_id = ?;";
            statement = connection.prepareStatement(query);
            statement.setInt(1, issueId);

            // Execute the query
            resultSet = statement.executeQuery();

            // Process the result set
            if (resultSet.next()) {
                // Retrieve the issue details from the result set
                issue = new Issue();
                issue.setIssueId(resultSet.getInt("issue_id"));
                issue.setIssueType(resultSet.getString("issue_type"));
                issue.setIssueStatus(resultSet.getString("issue_status"));
                issue.setProjectCode(resultSet.getString("project_code"));
                issue.setClassName(resultSet.getString("class_name"));
                issue.setSubjectCode(resultSet.getString("subject_code"));
                issue.setSemesterName(resultSet.getString("semester_name"));
                issue.setManagerName(resultSet.getString("manager_name"));
                issue.setAssignerName(resultSet.getString("assigner_name"));
                issue.setAssigneeName(resultSet.getString("assignee_name"));
                java.sql.Date createdDate = resultSet.getDate("created_date");
                java.sql.Date updatedDate = resultSet.getDate("updated_date");
                issue.setCreatedDate(createdDate);
                issue.setUpdatedDate(updatedDate);
                issue.setDescription(resultSet.getString("description"));
                issue.setWorkProcess(resultSet.getString("work_process"));
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

        return issue; // Return the retrieved issue object, or null if no issue is found
    }
    
    
    public Map<String, Map<String, Integer>> getIssueStatusComplexity(int projectId) {
        String query = "SELECT iss.issue_status, " +
                "COUNT(CASE WHEN iss.issue_complexity = 'Complex' THEN 1 END) AS complex_value, " +
                "COUNT(CASE WHEN iss.issue_complexity = 'Medium' THEN 1 END) AS medium_value, " +
                "COUNT(CASE WHEN iss.issue_complexity = 'Simple' THEN 1 END) AS simple_value " +
                "FROM issue_setting AS iss " +
                "INNER JOIN issue AS i ON iss.issue_id = i.issue_id " +
                "WHERE i.project_id = ? " +
                "GROUP BY iss.issue_status";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, projectId);

            ResultSet rs = stmt.executeQuery();

            Map<String, Map<String, Integer>> result = new HashMap<>();
            while (rs.next()) {
                String issueStatus = rs.getString("issue_status");
                int complexValue = rs.getInt("complex_value");
                int mediumValue = rs.getInt("medium_value");
                int simpleValue = rs.getInt("simple_value");

                Map<String, Integer> complexityMap = new HashMap<>();
                complexityMap.put("Complex", complexValue);
                complexityMap.put("Medium", mediumValue);
                complexityMap.put("Simple", simpleValue);

                result.put(issueStatus, complexityMap);
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }

        return null;
    }
}
