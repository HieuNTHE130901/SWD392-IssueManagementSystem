<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Reset Password</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <form action="reset-pass" method="post">
            <!-- Form fields for entering the new password -->
            <div class="form-group">
                <label for="newPassword">New Password</label>
                <input type="password" name="newPassword" class="form-control" id="newPassword" required>
            </div>

            <!-- Display success message -->
            <% if (request.getAttribute("message") != null) { %>
                <p class="text-success"><%= request.getAttribute("message") %></p>
            <% } %>

            <!-- Display error message -->
            <% if (request.getAttribute("error") != null) { %>
                <p class="text-danger"><%= request.getAttribute("error") %></p>
            <% } %>

            <button type="submit" class="btn btn-primary">Reset Password</button>
        </form>
    </body>
</html>
