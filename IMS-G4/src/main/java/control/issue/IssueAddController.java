package control.issue;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Issue;
import java.io.IOException;
import java.util.List;
import model.Milestone;
import model.Project;
import model.User;
import service.IssueService;

@WebServlet(name = "AddIssueServlet", urlPatterns = {"/add-issue"})
public class IssueAddController extends HttpServlet {

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
        
        //Load project list for user to add       
        List<Project> projectList = issueService.getProjectsForUser(((User) session.getAttribute("user")).getUserId());        
        request.setAttribute("projectList", projectList);

        //Load milestone list for user to add       
        List<Milestone> milestoneList = issueService.getMilestoneForUser(((User) session.getAttribute("user")).getUserId());
        request.setAttribute("milestoneList", milestoneList);
        
        //Load student list assign      
        List<User> studentList = issueService.getStudentList();
        request.setAttribute("studentList", studentList);       
        
        
        request.getRequestDispatcher("issue/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve data from the form
        String project = request.getParameter("project");
        String milestone = request.getParameter("milestone");
        String assignee = request.getParameter("assignee");
        String description = request.getParameter("description");
        String issueType = request.getParameter("issueType");
        String issueStatus = request.getParameter("issueStatus");

        HttpSession session = request.getSession();
        int assignerId = ((User) session.getAttribute("user")).getUserId(); // Assigner ID logic          

        // Create an Issue object
        Issue issue = new Issue();
        issue.setProjectId(Integer.parseInt(project));
        issue.setMilestoneId(Integer.parseInt(milestone));
        issue.setAssigneeId(Integer.parseInt(assignee));
        issue.setAssignerId(assignerId);
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
