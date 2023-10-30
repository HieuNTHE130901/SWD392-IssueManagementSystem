/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import model.Milestone;

/**
 *
 * @author trung
 */
public class MilestoneDAO extends BaseDAO{



    public List<Milestone> getMilestonesForProject(int projectId) {
        List<Milestone> milestones = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "SELECT milestone_id, milestone_name FROM Milestone WHERE project_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, projectId); // Set the project ID as a parameter

            rs = stmt.executeQuery();

            while (rs.next()) {
                Milestone milestone = new Milestone();
                milestone.setMilestoneId(rs.getInt("milestone_id"));
                milestone.setMilestoneName(rs.getString("milestone_name"));

                milestones.add(milestone);
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

        return milestones;
    }

    public List<Milestone> getMilestonesForUser(int userId) {
        List<Milestone> milestones = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "SELECT m.milestone_id, m.milestone_name "
                    + "FROM Milestone m "
                    + "INNER JOIN project p ON m.project_id = p.project_id "                    
                    + "INNER JOIN project_member pm ON p.project_id = pm.project_id "
                    + "WHERE pm.member_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId); // Set the user ID as a parameter

            rs = stmt.executeQuery();

            while (rs.next()) {
                Milestone milestone = new Milestone();
                milestone.setMilestoneId(rs.getInt("milestone_id"));
                milestone.setMilestoneName(rs.getString("milestone_name"));

                milestones.add(milestone);
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

        return milestones;
    }
}
