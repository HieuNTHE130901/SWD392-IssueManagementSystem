package control;

import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import service.UserService;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private UserService userService;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle GET requests (e.g., redirect to the login page)
        request.getRequestDispatcher("common/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emailOrMobile = request.getParameter("emailOrMobile");
        String password = request.getParameter("password");

        // Authenticate the user and get their role
        User user = authenticateUser(emailOrMobile, password);

        if (user != null) {
            // Set the user object in the session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("user-home");

        } else {
            // Authentication failed, show an error message or redirect to the login page
            request.setAttribute("error", "Authentication failed. Please try again.");
            request.getRequestDispatcher(request.getContextPath() + "/login").forward(request, response);
        }
    }

    private User authenticateUser(String emailOrMobile, String password) {
        // Assuming you have a method to retrieve a user by email or mobile
        User user = userDAO.getUserByEmailOrMobile(emailOrMobile);

        if (user != null) {
            // Check if the provided password matches the user's stored password
            if (user.getPassword().equals(password)) {
                // Authentication successful
                return user;
            }
        }

        // Authentication failed
        return null;
    }
}
