/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control.classs;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Class;
import model.User;
import dao.ClassDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ViewClassListServlet", urlPatterns = {"/class-list"})
public class ViewClassListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Create an instance of the IssueService
           ClassDAO class1 = new ClassDAO();

            // Fetch a list of issues from the database using the IssueService
            HttpSession session = request.getSession();

            // Retrieve the user ID attribute from the session
         
            List<Class> classes = class1.getClassForTeacher(((User) session.getAttribute("user")).getUserId());

            // Set the list of issues as a request attribute
            request.setAttribute("classes", classes);

            // Forward the request and response to a JSP page for rendering
            request.getRequestDispatcher("class/list.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle exceptions appropriately, e.g., log or display an error page
            e.printStackTrace();
            response.sendRedirect("common/error.jsp");
        }
    }
}
