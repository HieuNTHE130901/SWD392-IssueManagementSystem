package service;

import model.Issue;

import dao.IssueDAO;
import dao.MilestoneDAO;
import dao.ProjectDAO;
import dao.UserDAO;
import java.util.ArrayList;
import java.util.List;
import model.Milestone;
import model.Project;
import model.User;

public class IssueService {

    private IssueDAO issueDAO;
    private UserDAO userDAO;
    private MilestoneDAO milestoneDAO;
    private ProjectDAO projectDAO;

    public IssueService() {
        issueDAO = new IssueDAO();
        userDAO = new UserDAO();
        milestoneDAO = new MilestoneDAO();
        projectDAO = new ProjectDAO();

    }

    // Get list of project for user
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

    //Get list of milestone for user
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

    //get Student list
    public List<User> getStudentList() {
        return userDAO.getAllStudents();
    }

    //Get all issues on system
    public List<Issue> getAllIssues() {
        return issueDAO.getAllIssues();
    }

    //Get issues list for each type of user
    public List<Issue> getIssuesForCurrentUser(int userId) {

        String userRole = userDAO.getUserRoleById(userId);

        if ("admin".equals(userRole)) {
            // Logic for admin user
            return issueDAO.getAllIssues();
        } else if ("manager".equals(userRole)) {
            // Logic for manager user
            return issueDAO.getAllIssues();
        } else if ("teacher".equals(userRole)) {
            // Logic for teacher user
            return issueDAO.getIssuesForTeacher(userId);
        } else if ("student".equals(userRole)) {
            // Logic for student user
            return issueDAO.getIssuesForStudent(userId);
        }
        return new ArrayList<>(); // Return an empty list
    }

    //Add new issue to project
    public boolean addIssue(Issue issue, String issueType, String issueStatus) {
        return issueDAO.insertNewIssue(issue, issueType, issueStatus);
    }

    //View issue's details
    public Issue viewIssueDetails(int issueId) {
        return issueDAO.viewIssueDetails(issueId);
    }

}
