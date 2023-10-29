package control.issue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Issue;
import java.io.IOException;
import service.issue.IssueService;

@WebServlet(name = "AddIssueServlet", urlPatterns = {"/add-issue"})
public class AddIssueServlet extends HttpServlet {

    private IssueService issueService;

    @Override
    public void init() throws ServletException {
        super.init();
        issueService = new IssueService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle GET requests (e.g., redirect to the add_issue.jsp)
        request.getRequestDispatcher("issue/add_issue.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve data from the form
//        String project = request.getParameter("project");
//        String milestone = request.getParameter("milestone");
//        String assigner = request.getParameter("assigner");
//        String assignee = request.getParameter("assignee");
//        String description = request.getParameter("description");
//
//        // Create an Issue object
        Issue issue = new Issue();
//        issue.setProjectId(project);
//        issue.setMilestone(milestone);
//        issue.setAssigner(assigner);
//        issue.setAssignee(assignee);
//        issue.setDescription(description);

        // Call a method to add the issue to the database (implement this in IssueService)
        issueService.addIssue(issue);

        // Redirect to a success page or the issue list page
        response.sendRedirect("issue-list");
    }
}
