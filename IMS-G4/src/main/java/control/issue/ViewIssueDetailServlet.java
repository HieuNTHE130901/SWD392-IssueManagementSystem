package control.issue;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Issue;
import service.IssueService;

@WebServlet(name = "ViewIssueDetailServlet", urlPatterns = {"/issue-details"})
public class ViewIssueDetailServlet extends HttpServlet {

    private IssueService issueService; // Initialize and inject the IssueService dependency

    public void init() throws ServletException {
        // Perform any initialization tasks, such as creating the IssueService instance
        issueService = new IssueService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the issue ID from the request parameter
        int issueId = Integer.parseInt(request.getParameter("issueId"));

        // Retrieve the issue details from the IssueService
        Issue issue = issueService.viewIssueDetails(issueId);

        // Pass the issue details to the JSP for rendering
        request.setAttribute("issue", issue);
        request.getRequestDispatcher("issue/details.jsp").forward(request, response);
    }
  
    public void destroy() {
    }
}