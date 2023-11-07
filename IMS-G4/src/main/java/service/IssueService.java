package service;

import model.Issue;

import dao.IssueDAO;
import dao.UserDAO;
import java.util.ArrayList;
import java.util.List;

public class IssueService {

    private IssueDAO issueDAO;
    private UserDAO userDAO;

    public IssueService() {
        issueDAO = new IssueDAO();
        userDAO = new UserDAO();

    }

    public List<Issue> getAllIssues() {
        return issueDAO.getAllIssues();
    }

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

    public boolean addIssue(Issue issue, String issueType, String issueStatus) {

        return issueDAO.insertNewIssue(issue, issueType, issueStatus);
    }

    public Issue viewIssueDetails(int issueId) {
        return issueDAO.viewIssueDetails(issueId);
    }

}
