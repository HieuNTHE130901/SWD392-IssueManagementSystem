<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>View/Edit Profile</title>
        <!-- Include your CSS and JavaScript files here -->
    </head>
    <body>
        <h1>View/Edit Profile</h1>
        <form action="view-profile" method="post">
            <div class="form-group">
                <label for="full_name">Full Name</label>
                <input type="text" name="full_name" value="<%= request.getAttribute("userFullName") %>" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" name="email" value="<%= request.getAttribute("userEmail") %>" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="text" name="phone" value="<%= request.getAttribute("userPhone") %>" class="form-control">
            </div>
            <div class="form-group">
                <label for="user_role">User Role</label>
                <input type="text" name="user_role" value="<%= request.getAttribute("userRole") %>" class="form-control" readonly>
            </div>
            <button type="submit" class="btn btn-primary">Save Changes</button>
        </form>
        <!-- Display success or failure messages here -->
        <div class="message">
            <% String message = (String) request.getAttribute("message");
               if (message != null) { %>
            <p><%= message %></p>
            <% } %>
        </div>
        <!-- Include your JavaScript and other footer content here -->
    </body>
</html>
