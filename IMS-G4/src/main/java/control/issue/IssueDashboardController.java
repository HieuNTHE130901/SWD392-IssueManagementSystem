package control.issue;

import dao.ClassDAO;
import dao.IssueDAO;
import dao.ProjectDAO;
import dao.SubjectDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import model.Subject;
import model.Class;
import model.Project;
import model.User;

@WebServlet(name = "ViewIssueDashboardServlet", urlPatterns = {"/view-issue-dashboard"})
public class IssueDashboardController extends HttpServlet {

    private final SubjectDAO subjectDAO;
    private final ClassDAO classDAO;
    private final ProjectDAO projectDAO;
    private final UserDAO userDAO;
    private final IssueDAO issueDAO;

    public IssueDashboardController() {
        subjectDAO = new SubjectDAO();
        classDAO = new ClassDAO();
        projectDAO = new ProjectDAO();
        userDAO = new UserDAO();
        issueDAO = new IssueDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Load comboboxes

        List<Subject> subjects = subjectDAO.getAllSubjects();
        request.setAttribute("subjects", subjects);

        List<Class> classes = classDAO.getAllClasses();
        request.setAttribute("classes", classes);

        List<Project> projects = projectDAO.getAllProjects();
        request.setAttribute("projects", projects);

        List<User> users = userDAO.getAllStudents();
        request.setAttribute("users", users);
        // Get the selected project ID from the request parameters
        String projectId = request.getParameter("projectId");
        //int id = Integer.parseInt(projectId);

        int id =2;
        Map<String, Map<String, Integer>> issueStatusComplexity = issueDAO.getIssueStatusComplexity(id);
        // Convert the issue status complexity data to a format suitable for Google Charts
        List<List<Object>> chartData = new ArrayList<>();
        chartData.add(Arrays.asList("'Status'", "'Complex'", "'Medium'", "'Simple'"));

        for (Map.Entry<String, Map<String, Integer>> entry : issueStatusComplexity.entrySet()) {
            String issueStatus = entry.getKey();
            Map<String, Integer> complexityMap = entry.getValue();

            List<Object> rowData = new ArrayList<>();
            rowData.add("'" + issueStatus + "'");
            rowData.add(complexityMap.get("Complex"));
            rowData.add(complexityMap.get("Medium"));
            rowData.add(complexityMap.get("Simple"));
            chartData.add(rowData);
        }

        // Pass the chart data to the JSP file
        request.setAttribute("chartData", chartData);

        // Forward to the JSP
        request.getRequestDispatcher("issue/dashboard.jsp").forward(request, response);
    }
}
