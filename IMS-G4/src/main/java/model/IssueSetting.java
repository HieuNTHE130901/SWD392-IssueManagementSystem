
package model;

/**
 *
 * @author trung
 */
public class IssueSetting {
    private int issueSettingId;
    private String issueType;
    private String issueStatus;
    private String workingProcess;
    private String issueComplexity;
    

    public IssueSetting() {
        // Default constructor
    }

    public IssueSetting(int issueSettingId, String issueType, String issueStatus, String workingProcess, String issueComplexity) {
        this.issueSettingId = issueSettingId;
        this.issueType = issueType;
        this.issueStatus = issueStatus;
        this.workingProcess = workingProcess;
        this.issueComplexity = issueComplexity;
    }

    public String getIssueComplexity() {
        return issueComplexity;
    }

    public void setIssueComplexity(String issueComplexity) {
        this.issueComplexity = issueComplexity;
    }
    

    public int getIssueSettingId() {
        return issueSettingId;
    }

    public void setIssueSettingId(int issueSettingId) {
        this.issueSettingId = issueSettingId;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    public String getWorkingProcess() {
        return workingProcess;
    }

    public void setWorkingProcess(String workingProcess) {
        this.workingProcess = workingProcess;
    }
    

  
}