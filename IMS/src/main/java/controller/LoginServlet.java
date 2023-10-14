/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author trung
 */
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO; // Initialize the UserDAO

    @Override
    public void init() throws ServletException {
        // Initialize the UserDAO in the servlet's init() method
        userDAO = new UserDAO();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String emailOrMobile = request.getParameter("emailOrMobile");
    String password = request.getParameter("password");

    if (emailOrMobile == null || password == null) {
        // Handle the case where either emailOrMobile or password is null
        request.setAttribute("error", "Invalid input. Please provide both email/mobile and password.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("common/login.jsp");
        dispatcher.forward(request, response);
        return;
    }

    User user = null;

    if (emailOrMobile.contains("@") && userDAO.isEmailFromPermittedDomain(emailOrMobile)) {
        // Login with email
        user = userDAO.loginWithEmail(emailOrMobile, password);
    } else if (emailOrMobile.matches("\\d{10}")) {
        // Login with mobile
        user = userDAO.loginWithMobile(emailOrMobile, password);
    }

    if (user != null) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user); // Store the logged-in user in the session

        // Redirect based on user role
        String userRole = user.getUserRole();
        if ("Student".equals(userRole)) {
            response.sendRedirect(request.getContextPath() + "/student");
        } else if ("Teacher".equals(userRole)) {
            response.sendRedirect(request.getContextPath() + "/teacher");
        } else if ("Manager".equals(userRole)) {
            response.sendRedirect(request.getContextPath() + "/manager");
        } else if ("Admin".equals(userRole)) {
            response.sendRedirect(request.getContextPath() + "/admin");
        } else {
            request.setAttribute("error", "Unknown user role: " + userRole);
            RequestDispatcher dispatcher = request.getRequestDispatcher("common/login.jsp");
            dispatcher.forward(request, response);
        }
    } else {
        request.setAttribute("error", "Login failed. Incorrect email or password.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("common/login.jsp");
        dispatcher.forward(request, response);
    }
}



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
