<%-- 
    Document   : register
    Created on : 14 Oct 2023, 23:55:59
    Author     : trung
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <h1>Registration</h1>
        <form action="register" method="post">
            <label for="fullName">Full Name:</label>
            <input type="text" name="fullName" required><br>

            <label for="emailOrMobile">Email or Mobile:</label>
            <input type="text" name="emailOrMobile" required><br>

            <label for="password">Password:</label>
            <input type="password" name="password" required><br>

            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" name="confirmPassword" required><br>

            <input type="submit" value="Register">
            <!-- Add this code to display error messages -->
            <% String error = (String) request.getAttribute("error"); %>
            <% if (error != null) { %>
            <div class="alert alert-danger">
                <%= error %>
            </div>
            <% } %>
        </form>
    </body>
</html>
