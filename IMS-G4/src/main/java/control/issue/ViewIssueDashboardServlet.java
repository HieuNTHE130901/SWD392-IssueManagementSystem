package control.issue;

import dal.ClassDAO;
import dal.IssueDAO;
import dal.ProjectDAO;
import dal.SemesterDAO;
import dal.SubjectDAO;
import dal.UserDAO;
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
import model.Semester;
import model.Subject;
import model.Class;
import model.Project;
import model.User;

@WebServlet(name = "ViewIssueDashboardServlet", urlPatterns = {"/view-issue-dashboard"})
public class ViewIssueDashboardServlet extends HttpServlet {

    private final SemesterDAO semesterDAO;
    private final SubjectDAO subjectDAO;
    private final ClassDAO classDAO;
    private final ProjectDAO projectDAO;
    private final UserDAO userDAO;
    private final IssueDAO issueDAO;

    public ViewIssueDashboardServlet() {
        semesterDAO = new SemesterDAO();
        subjectDAO = new SubjectDAO();
        classDAO = new ClassDAO();
        projectDAO = new ProjectDAO();
        userDAO = new UserDAO();
        issueDAO = new IssueDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Load comboboxes
        List<Semester> semesters = semesterDAO.getAllSemesters();
        request.setAttribute("semesters", semesters);

        List<Subject> subjects = subjectDAO.getAllSubjects();
        request.setAttribute("subjects", subjects);

        List<Class> classes = classDAO.getAllClasses();
        request.setAttribute("classes", classes);

        List<Project> projects = projectDAO.getAllProjects();
        request.setAttribute("projects", projects);

        // Assuming you have a data access layer or service to retrieve data from the database
        int projectId = 2; // Replace with the actual project ID
        Map<String, Map<String, Integer>> issueStatusComplexity = issueDAO.getIssueStatusComplexity(projectId);

        // Convert the issue status complexity data to a format suitable for Google Charts
        List<List<Object>> chartData = new ArrayList<>();
        chartData.add(Arrays.asList("'Status'", "'Complex'", "'Medium'", "'Simple'"));

        for (Map.Entry<String, Map<String, Integer>> entry : issueStatusComplexity.entrySet()) {
            String issueStatus = entry.getKey();
            Map<String, Integer> complexityMap = entry.getValue();

            List<Object> rowData = new ArrayList<>();
            rowData.add("'"+issueStatus+"'");
            rowData.add(complexityMap.get("Complex"));
            rowData.add(complexityMap.get("Medium"));
            rowData.add(complexityMap.get("Simple"));
            chartData.add(rowData);
        }

        // Pass the chart data to the JSP file
        request.setAttribute("chartData", chartData);
        
        // Load statistics by week
        List<User> users = userDAO.getAllStudents();
        request.setAttribute("users", users);

        // Forward to the JSP
        request.getRequestDispatcher("issue/issue_dashboard.jsp").forward(request, response);
    }
}
