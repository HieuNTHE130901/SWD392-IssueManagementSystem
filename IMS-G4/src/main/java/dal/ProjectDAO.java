/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;


import dal.dbutils.DBContext;
import model.Class;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;

public class ProjectDAO {

    private DBContext dbContext;

    public ProjectDAO() {
        dbContext = new DBContext();
    }
    
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish a database connection (replace these details with your actual database connection)
            conn = dbContext.getConnection();

            // Create an SQL statement to retrieve all semesters
            String sql = "SELECT project_id, project_name FROM Project";
            stmt = conn.prepareStatement(sql);

            // Execute the SQL query
            rs = stmt.executeQuery();

            // Process the results and populate the list of semesters
            while (rs.next()) {
                Project project = new Project();
                project.setProjectId(rs.getInt("project_id"));
                project.setProjectName(rs.getString("project_name"));

                projects.add(project);
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

        return projects;
    }
}