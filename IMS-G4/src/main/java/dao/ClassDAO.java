/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Class;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO extends BaseDAO{

  

    public List<Class> getClassesBySubject(String selectedSubject) {
        List<Class> classes = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String sql = "SELECT class_id, class_name FROM Class WHERE subject_id = (SELECT subject_id FROM Subject WHERE subject_name = ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, selectedSubject);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int classId = resultSet.getInt("class_id");
                String className = resultSet.getString("class_name");

                Class class1 = new Class(classId, className);
                classes.add(class1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential errors here
        }

        return classes;
    }
    public List<Class> getAllClasses() {
        List<Class> classes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish a database connection (replace these details with your actual database connection)
            conn = getConnection();

            // Create an SQL statement to retrieve all semesters
            String sql = "SELECT class_id, class_name FROM Class";
            stmt = conn.prepareStatement(sql);

            // Execute the SQL query
            rs = stmt.executeQuery();

            // Process the results and populate the list of semesters
            while (rs.next()) {
                Class class1 = new Class();
                class1.setClassId(rs.getInt("class_id"));
                class1.setClassName(rs.getString("class_name"));

                classes.add(class1);
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

        return classes;
    }

    public List<Class> getClassForTeacher(int userId) {
        List<Class> classes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String query = " SELECT * FROM class WHERE teacher_id = ? ";

            ps = conn.prepareStatement(query);
            ps.setInt(1, userId); // Set the user ID parameter

            rs = ps.executeQuery();

            while (rs.next()) {
                Class class1 = new Class();
               class1.setClassId(rs.getInt("class_id"));
               class1.setSemesterId(rs.getInt("semester_id"));
               class1.setTeacherId(rs.getInt("teacher_id"));
               class1.setSubjectId(rs.getInt("subject_id"));
               class1.setClassName(rs.getString("class_name"));               
               class1.setStatus(rs.getString("status"));               
               class1.setDescription(rs.getString("description"));                          

                classes.add(class1);
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

        return classes;
    }
   
}
