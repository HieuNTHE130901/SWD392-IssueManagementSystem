package control.issue;

import dal.SubjectDAO;
import model.Subject;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "FetchSubjectsServlet", urlPatterns = {"/fetch-subjects"})
public class FetchSubjectsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Fetch the selected semester parameter from the request
        String selectedSemester = request.getParameter("semester");
        // Create an instance of the SubjectDAO
        SubjectDAO subjectDAO = new SubjectDAO();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // You should have a method to fetch subjects based on the selected semester
            List<Subject> subjects = subjectDAO.getSubjectsBySemester(selectedSemester);

            // Create a JSON string from the list of subjects
            Gson gson = new Gson();
            String jsonSubjects = gson.toJson(subjects);

            response.getWriter().write(jsonSubjects);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Set 500 status for internal server error
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
