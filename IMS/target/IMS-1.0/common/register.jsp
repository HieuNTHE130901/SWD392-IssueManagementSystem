<%-- 
    Document   : signup
    Created on : 9 Oct 2023, 15:21:40
    Author     : trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Register page</h1>
        <!-- register.jsp -->
<form action="RegisterServlet" method="post">
    <div class="form-group">
        <label for="fullName">Full Name</label>
        <input type="text" name="fullName" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="emailOrMobile">Email or Mobile</label>
        <input type="text" name="emailOrMobile" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" name="password" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="verificationCode">Verification Code</label>
        <input type="text" name="verificationCode" class="form-control" required>
    </div>
    <button type="submit" class="btn btn-primary">Register</button>
</form>

    </body>
</html>
