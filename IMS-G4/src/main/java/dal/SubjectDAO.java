
package dal;

import dal.dbutils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Subject;

/**
 *
 * @author trung
 */
public class SubjectDAO {

    
    
    private DBContext dbContext;

    public SubjectDAO() {
        dbContext = new DBContext();
    }
    public List<Subject> getSubjectsBySemester(String selectedSemester) {
    List<Subject> subjects = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = dbContext.getConnection();

        // Create an SQL statement to retrieve subjects based on the selected semester
        String sql = "SELECT subject_id, subject_name FROM Subject WHERE semester_id = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, selectedSemester);
        rs = stmt.executeQuery();

        while (rs.next()) {
            int subjectId = rs.getInt("subject_id");
            String subjectName = rs.getString("subject_name");

            // Create a Subject object and add it to the list
            Subject subject = new Subject(subjectId, subjectName);
            subjects.add(subject);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle exceptions appropriately
    } finally {
        // Close database resources
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

    return subjects;
}
    
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish a database connection (replace these details with your actual database connection)
            conn = dbContext.getConnection();

            // Create an SQL statement to retrieve all semesters
            String sql = "SELECT subject_id, subject_name FROM Subject";
            stmt = conn.prepareStatement(sql);

            // Execute the SQL query
            rs = stmt.executeQuery();

            // Process the results and populate the list of semesters
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));

                subjects.add(subject);
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

        return subjects;
    }
   

    
}
