package control;

import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/reset-pass"})
public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if the user is logged in
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            // Forward to the password reset JSP
            request.getRequestDispatcher("common/reset_password.jsp").forward(request, response);
        } else {
            // If not logged in, redirect to the login page
            response.sendRedirect("common/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if the user is logged in
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            // Get the user from the session
            User user = (User) session.getAttribute("user");

            // Get the new password from the request
            String newPassword = request.getParameter("newPassword");

            // Update the user's password in the database
            UserDAO userDAO = new UserDAO();
            user.setPassword(newPassword); // Set the new password
            boolean passwordUpdated = userDAO.updatePassword(user);

            if (passwordUpdated) {
                // Password updated successfully
                request.setAttribute("message", "Password updated successfully.");
            } else {
                // Password update failed, handle the error
                request.setAttribute("error", "Password reset failed.");
            }

            // Forward to the reset_password.jsp with the message
            request.getRequestDispatcher("common/reset_password.jsp").forward(request, response);
        } else {
            // If not logged in, redirect to the login page
            response.sendRedirect("login");
        }
    }
}
