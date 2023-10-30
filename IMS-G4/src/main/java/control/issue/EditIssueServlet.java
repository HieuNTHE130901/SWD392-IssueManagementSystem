/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.issue;

import dao.IssueDAO;
import dao.IssueSettingDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Issue;
import model.IssueSetting;

@WebServlet(name = "EditIssueServlet", urlPatterns = {"/edit-issue"})
public class EditIssueServlet extends HttpServlet {

    private IssueSettingDAO issueSettingDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        issueSettingDAO = new IssueSettingDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the issue ID parameter from the request
        String issueId = request.getParameter("issueId");

        // Check if the issue ID is valid
        if (issueId != null && !issueId.isEmpty()) {
            try {
                // Parse the issue ID into an integer
                int issueIdInt = Integer.parseInt(issueId);

                // Retrieve the issue details based on the issue ID from the data source
                IssueSetting issue = issueSettingDAO.getIssueSettingById(issueIdInt);

                if (issue != null) {
                    // Set the issue details as request attributes
                    request.setAttribute("issueId", issue.getIssueSettingId());
                    request.setAttribute("issueType", issue.getIssueType());
                    request.setAttribute("issueStatus", issue.getIssueStatus());
                    request.setAttribute("workingProcess", issue.getWorkingProcess());
                    request.setAttribute("issueComplexity", issue.getIssueComplexity());
                    

                    // Forward the request to the JSP page for displaying the edit form
                    request.getRequestDispatcher("issue/edit_issue.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                // Handle the NumberFormatException if the issueId cannot be parsed into an integer
                e.printStackTrace();
            }
        }

        // If the issue ID is invalid or the issue details are not found, redirect to an error page or a relevant URL
        response.sendRedirect("/IMS-G4/error");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the issue ID parameter from the request
        String issueId = request.getParameter("issueId");

        // Check if the issue ID is valid
        if (issueId != null && !issueId.isEmpty()) {
            try {
                // Parse the issue ID into an integer
                int issueIdInt = Integer.parseInt(issueId);

                // Retrieve the updated values from the request parameters
                String issueType = request.getParameter("issueType");
                String issueStatus = request.getParameter("issueStatus");
                String workingProcess = request.getParameter("workingProcess");                
                String issueComplexity = request.getParameter("issueComplexity");

                // Perform the necessary operations to save the updated issue setting to the data source
                boolean isUpdated = issueSettingDAO.updateIssueSetting(issueIdInt, issueType, issueStatus, workingProcess, issueComplexity);

                if (isUpdated) {
                    // Redirect to a success page or a relevant URL
                    response.sendRedirect("/IMS-G4/issue-list");
                    return;
                }
            } catch (NumberFormatException e) {
                // Handle the NumberFormatException if the issueId cannot be parsed into an integer
                e.printStackTrace();
            }
        }

        // If the issue ID is invalid or the update is not successful, redirect to an error page or a relevant URL
        response.sendRedirect("/IMS-G4/error");
    }
}
