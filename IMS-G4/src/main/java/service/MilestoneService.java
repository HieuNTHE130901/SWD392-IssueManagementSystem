
package service;

import dao.MilestoneDAO;
import dao.UserDAO;
import java.util.ArrayList;
import java.util.List;
import model.Milestone;

/**
 *
 * @author trung
 */
public class MilestoneService {

    MilestoneDAO milestoneDAO;
    UserDAO userDAO;

    public MilestoneService() {
        milestoneDAO = new MilestoneDAO();
        userDAO = new UserDAO();
    }

    public List<Milestone> getMilestoneForUser(int userId) {
        String userRole = userDAO.getUserRoleById(userId);
        if ("admin".equals(userRole)) {
            // Logic for admin user
            return milestoneDAO.getAllMilestones();
        } else if ("manager".equals(userRole)) {
            // Logic for manager user
            return milestoneDAO.getAllMilestones();
        } else if ("teacher".equals(userRole)) {
            // Logic for teacher user
            return milestoneDAO.getAllMilestones();
        } else if ("student".equals(userRole)) {
            // Logic for student user
            return milestoneDAO.getMilestonesForStudent(userId);
        }
        return new ArrayList<>(); // Return an empty list
    }
    
    public List<Milestone> getMilestonesForProject(int projectId) {
        return milestoneDAO.getMilestonesForProject(projectId);
    }

}
