/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.dbutils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Semester;

/**
 *
 * @author trung
 */
public class SemesterDAO {

    private DBContext dbContext;

    public SemesterDAO() {
        dbContext = new DBContext();
    }

    public List<Semester> getAllSemesters() {
        List<Semester> semesters = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish a database connection (replace these details with your actual database connection)
            conn = dbContext.getConnection();

            // Create an SQL statement to retrieve all semesters
            String sql = "SELECT semester_id, semester_name FROM Semester";
            stmt = conn.prepareStatement(sql);

            // Execute the SQL query
            rs = stmt.executeQuery();

            // Process the results and populate the list of semesters
            while (rs.next()) {
                Semester semester = new Semester();
                semester.setSemesterId(rs.getInt("semester_id"));
                semester.setSemesterName(rs.getString("semester_name"));

                semesters.add(semester);
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

        return semesters;
    }
   
}
