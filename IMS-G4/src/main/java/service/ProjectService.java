
package service;

import dao.ProjectDAO;
import dao.UserDAO;
import java.util.ArrayList;
import java.util.List;
import model.Project;

public class ProjectService {

    ProjectDAO projectDAO;
    UserDAO userDAO;

    public ProjectService() {
        projectDAO = new ProjectDAO();
        userDAO = new UserDAO();
    }

    public List<Project> getProjectsForUser(int userId) {
        String userRole = userDAO.getUserRoleById(userId);
        if ("admin".equals(userRole)) {
            // Logic for admin user
            return projectDAO.getAllProjects();
        } else if ("manager".equals(userRole)) {
            // Logic for manager user
            return projectDAO.getAllProjects();
        } else if ("teacher".equals(userRole)) {
            // Logic for teacher user
            return projectDAO.getAllProjects();
        } else if ("student".equals(userRole)) {
            // Logic for student user
            return projectDAO.getProjectsForStudent(userId);
        }
        return new ArrayList<>(); // Return an empty list
    }

}
