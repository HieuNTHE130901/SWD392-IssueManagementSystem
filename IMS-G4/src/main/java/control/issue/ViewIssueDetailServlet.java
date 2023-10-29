
package control.issue;

import dal.IssueDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Issue;

/**
 *
 * @author trung
 */
@WebServlet(name = "ViewIssueDetailServlet", urlPatterns = {"/issue-details"})
public class ViewIssueDetailServlet extends HttpServlet {

    private IssueDAO issueDAO; // Initialize and inject the IssueDAO dependency

    public void init() throws ServletException {
        // Perform any initialization tasks, such as creating the IssueDAO instance
        issueDAO = new IssueDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the issue ID from the request parameter
        int issueId = Integer.parseInt(request.getParameter("issueId"));

        // Retrieve the issue details from the IssueDAO
        Issue issue = issueDAO.viewIssueDetails(issueId);

        // Pass the issue details to the JSP for rendering
        request.setAttribute("issue", issue);
        request.getRequestDispatcher("issue/view_issue_details.jsp").forward(request, response);
    }
  
    public void destroy() {
        // Perform any cleanup tasks, such as closing database connections
        // Release any resources used by the servlet
    }
}