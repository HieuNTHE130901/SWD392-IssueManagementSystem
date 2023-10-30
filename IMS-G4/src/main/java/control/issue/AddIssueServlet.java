package control.issue;

import dao.MilestoneDAO;
import dao.ProjectDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Issue;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Milestone;
import model.Project;
import model.User;
import service.IssueService;
import service.UserService;

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

        HttpSession session = request.getSession();

        ProjectDAO project = new ProjectDAO();
        List<Project> projectList = project.getProjectsForUser(((User) session.getAttribute("user")).getUserId());
        request.setAttribute("projectList", projectList);

        MilestoneDAO milestone = new MilestoneDAO();
        List<Milestone> milestoneList = milestone.getMilestonesForUser(((User) session.getAttribute("user")).getUserId());
        request.setAttribute("milestoneList", milestoneList);

        // Handle GET requests (e.g., redirect to the add_issue.jsp)
        request.getRequestDispatcher("issue/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve data from the form
        String project = request.getParameter("project");
        String milestone = request.getParameter("milestone");
        String description = request.getParameter("description");
        String issueType = request.getParameter("issueType");
        String issueStatus = request.getParameter("issueStatus");

        HttpSession session = request.getSession();
        int assigneeId = ((User) session.getAttribute("user")).getUserId(); // Assignee ID logic

        // Create a UserService instance
        UserService userService = new UserService();

        int teacherId = 0;
        try {
            teacherId = userService.getTeacherIdForUser(assigneeId);
        } catch (SQLException ex) {
            Logger.getLogger(AddIssueServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Create an Issue object
        Issue issue = new Issue();
        issue.setProjectId(Integer.parseInt(project));
        issue.setMilestoneId(Integer.parseInt(milestone));
        issue.setAssigneeId(assigneeId);
        issue.setAssignerId(teacherId);
        issue.setDescription(description);
        issue.setIssueType(issueType);
        issue.setIssueStatus(issueStatus);

        // Call a method to add the issue to the database (implement this in IssueService)
        boolean success = issueService.addIssue(issue, issueType, issueStatus);

        if (success) {
            // Issue added successfully
            response.sendRedirect("issue-list");
        } else {
            // Failed to add the issue
            response.sendRedirect("add-issue");
        }
    }

}
