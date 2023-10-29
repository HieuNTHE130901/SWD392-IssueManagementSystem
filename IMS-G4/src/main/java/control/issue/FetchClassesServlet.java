/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control.issue;

import dal.ClassDAO;
import model.Class;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "FetchClassesServlet", urlPatterns = {"/fetch-classes"})
public class FetchClassesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Fetch the selected subject parameter from the request
        String selectedSubject = request.getParameter("subject");
        // Create an instance of the ClassDAO
        ClassDAO classDAO = new ClassDAO();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            List<Class> classes = classDAO.getClassesBySubject(selectedSubject);
            out.print("[");
            boolean firstClass = true;

            for (Class class1 : classes) {
                if (firstClass) {
                    firstClass = false;
                } else {
                    out.print(",");
                }

                out.print("{");
                out.print("\"classId\":" + class1.getClassId() + ",");
                out.print("\"className\":\"" + class1.getClassName() + "\"");
                out.print("}");
            }

            out.print("]");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Set 500 status for internal server error
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
