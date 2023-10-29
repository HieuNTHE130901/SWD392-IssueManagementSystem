

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="login" method="post">
            <div class="form-group">
                <label for="emailOrMobile">Email or Mobile</label>
                <input type="text" name="emailOrMobile" class="form-control" id="emailOrMobile" placeholder="Email or Mobile">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" name="password" class="form-control" id="password" placeholder="Password">
            </div>

            <button type="submit" class="btn btn-primary">Login</button>
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
