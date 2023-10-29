package control;

import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;

@WebServlet(name = "ViewProfileServlet", urlPatterns = {"/view-profile"})
public class ViewProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle GET requests (e.g., retrieve and display user information)
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            // Display user information in the form
            request.setAttribute("userFullName", user.getFullName());
            request.setAttribute("userEmail", user.getEmail());
            request.setAttribute("userPhone", user.getMobile());
            request.setAttribute("userRole", user.getUserRole());

            // Check if there's a message attribute in the session
            String message = (String) session.getAttribute("message");
            if (message != null) {
                request.setAttribute("message", message);
                session.removeAttribute("message"); // Remove the message from the session
            }

            request.getRequestDispatcher("common/profile.jsp").forward(request, response);
        } else {
            // Redirect to login page or display an error
            response.sendRedirect("common/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle POST requests (e.g., update user information)
        String fullName = request.getParameter("full_name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            user.setFullName(fullName);
            user.setEmail(email);
            user.setMobile(phone);

            UserDAO userDAO = new UserDAO();
            boolean updated = userDAO.updateUser(user);

            if (updated) {
                // Set a success message in the session
                session.setAttribute("message", "User information updated successfully.");
            } else {
                // Set an error message in the session
                session.setAttribute("message", "Failed to update user information.");
            }

            // Redirect back to the profile page
            response.sendRedirect("view-profile");
        } else {
            // Redirect to login page or display an error
            response.sendRedirect("common/login.jsp");
        }
    }
}
