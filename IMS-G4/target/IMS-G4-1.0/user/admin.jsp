<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
    <h1>Welcome, <%= ((User) session.getAttribute("user")).getFullName() %>!</h1>
    <div>
        <h2>Common features</h2>
        <p>Reset your password here! <a href="reset-pass">Reset password</a></p>
        <p>View your profile here! <a href="view-profile">View profile</a></p>
        <p>View issue tracking dashboard here! <a href="view-issue-dashboard">View issue tracking dashboard</a></p>
        <p>View project dashboard here! <a href="view-project-dashboard">View project dashboard</a></p>
    </div>

    <div>
        <h2>Role-specific features</h2>
       
                <p>You are an Admin. Here are the admin features:</p>
                <!-- Include admin-specific content or links here -->
                <p>Edit System Setting <a href="#">System Setting</a></p>
                <p>Manage User <a href="#">Manage User</a></p>
           
    </div>

    <a href="logout">Logout</a>
</body>
</html>
