package control.issue;

import dal.ClassDAO;
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
import java.util.List;
import model.Semester;
import model.Subject;
import model.Class;
import model.Project;
import model.User;


@WebServlet(name = "ViewIssueDashboardServlet", urlPatterns = {"/view-issue-dashboard"})
public class ViewIssueDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Load comboboxes
        List<Semester> semesters = new SemesterDAO().getAllSemesters();
        request.setAttribute("semesters", semesters);
        
        List<Subject> subjects = new SubjectDAO().getAllSubjects();
        request.setAttribute("subjects", subjects);
        
        List<Class> classes = new ClassDAO().getAllClasses();
        request.setAttribute("classes", classes);     
        
        List<Project> projects = new ProjectDAO().getAllProjects();
        request.setAttribute("projects", projects);     
        
        List<User> users = new UserDAO().getAllStudents();
        request.setAttribute("users", users);    
        
        
        //Load statictis by week       

        // Forward to the JSP
        request.getRequestDispatcher("issue/issue_dashboard.jsp").forward(request, response);
    }
}
