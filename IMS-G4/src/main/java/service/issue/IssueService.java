package service.issue;

import model.Issue;

import dal.IssueDAO;
import java.util.List;

public class IssueService {

    private IssueDAO issueDAO;

    public IssueService() {
        issueDAO = new IssueDAO();
    }

    public List<Issue> getAllIssues() {
        // You can add any additional business logic or validation here
        return issueDAO.getAllIssues();
    }

    public List<Issue> getIssuesForCurrentUser(int userId) {
        // You can add any additional business logic or validation here
        return issueDAO.getIssuesForCurrentUser(userId);
    }

    public boolean addIssue(Issue issue) {
        // You can add any additional business logic or validation here
        return issueDAO.addIssue(issue);
    }

}
