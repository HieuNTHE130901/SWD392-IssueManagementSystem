package control.issue;

import model.Issue;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import service.IssueService; 

@WebServlet(name = "ViewIssueListServlet", urlPatterns = {"/issue-list"})
public class ViewIssueListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Create an instance of the IssueService
            IssueService issueService = new IssueService();

            // Fetch a list of issues from the database using the IssueService
            HttpSession session = request.getSession();

            // Retrieve the user ID attribute from the session
         
            List<Issue> issues = issueService.getIssuesForCurrentUser( ((User) session.getAttribute("user")).getUserId());

            // Set the list of issues as a request attribute
            request.setAttribute("issues", issues);

            // Forward the request and response to a JSP page for rendering
            request.getRequestDispatcher("issue/list.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle exceptions appropriately, e.g., log or display an error page
            e.printStackTrace();
            response.sendRedirect("common/error.jsp");
        }
    }
}
