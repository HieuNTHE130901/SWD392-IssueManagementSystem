<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Issue</title>
</head>
<body>
    <h1>Add Issue</h1>
    <form action="/IMS-G4/add-issue" method="post">
        <!-- Load project names from the server and populate the select element -->
        <label for="project">Project:</label>
        <select id="project" name="project" required>
            <option value="">Select a Project</option>
            <%-- Iterate through the list of projects and create an option for each --%>
            <c:forEach items="${projectList}" var="project">
                <option value="${project.projectId}">${project.projectName}</option>
            </c:forEach>
        </select><br><br>
       <!-- Load project names from the server and populate the select element -->
        <label for="milestone">Milestones: </label>
        <select id="milestone" name="milestone" required>
            <option value="">Select a Milestone</option>
            <%-- Iterate through the list of projects and create an option for each --%>
            <c:forEach items="${milestoneList}" var="project">
                <option value="${milestone.milestoneId}">${milestone.milestoneName}</option>
            </c:forEach>
        </select><br><br>
        <label for="assigner">Assigner:</label>
        <input type="text" id="assigner" name="assigner" ><br><br>        
        <label for="assignee">Assignee:</label>
        <input type="text" id="assignee" name="assignee" value="${user.fullName}" required readonly><br><br>        
        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="4" cols="50" required></textarea><br><br>        
        <input type="submit" value="Add Issue">
    </form>
</body>
</html>
