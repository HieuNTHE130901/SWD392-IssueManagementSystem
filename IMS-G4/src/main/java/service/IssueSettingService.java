package service;

import dao.IssueSettingDAO;
import model.IssueSetting;

public class IssueSettingService {

    private IssueSettingDAO issueSettingDAO;

    public IssueSettingService() {
        issueSettingDAO = new IssueSettingDAO();
    }

    public IssueSetting getIssueSettingById(int issueId) {
        return issueSettingDAO.getIssueSettingById(issueId);
    }

    public boolean updateIssueSetting(int issueId, String issueType, String issueStatus, String workingProcess, String issueComplexity) {
        // Perform any necessary validation or business logic here
        // ...

        // Call the DAO method to update the issue setting
        return issueSettingDAO.updateIssueSetting(issueId, issueType, issueStatus, workingProcess, issueComplexity);
    }
}