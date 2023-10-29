package dal;

import model.Issue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dal.dbutils.DBContext;
import java.sql.Timestamp;

/**
 *
 * @author trung
 */
public class IssueDAO {

    private DBContext dbContext;

    public IssueDAO() {
        dbContext = new DBContext();
    }

    public List<Issue> getAllIssues() {
        List<Issue> issues = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dbContext.getConnection();
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
            conn = dbContext.getConnection();
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
                    + "JOIN user ue ON i.assignee_id = ue.user_id "
                    + "WHERE p.team_leader_id = ?"; // Assuming team_leader_id corresponds to user_id

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
            conn = dbContext.getConnection();
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
}
