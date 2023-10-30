<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <c:forEach items="${projectList}" var="project">
                    <option value="${project.projectId}">${project.projectName}</option>
                </c:forEach>
            </select><br><br>


            <!-- Load milestones based on the selected project -->
            <label for="milestone">Milestone: </label>
            <select id="milestone" name="milestone" required>
                <option value="">Select a Milestone</option>
                <c:forEach items="${milestoneList}" var="milestone">
                    <option value="${milestone.milestoneId}">${milestone.milestoneName}</option>
                </c:forEach>
            </select><br><br>



            <!-- Assignee is the current user -->
            <label for="assignee">Assignee:</label>
            <input type="text" id="assignee" name="assignee" value="${user.fullName}" required readonly><br><br>

            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4" cols="50" required></textarea><br><br>

            <!-- Issue Type dropdown -->
            <label for="issueType">Issue Type:</label>
            <select id="issueType" name="issueType" required>
                <option value="">Select an Issue Type</option>                
                <option value="Q&A">Q&A</option>
                <option value="Task">Task</option>
                <option value="Defect">Defect</option>                
                <option value="Other Issues">Other Issues</option>
            </select><br><br>

            <!-- Issue Status dropdown -->
            <label for="issueStatus">Issue Status:</label>
            <select id="issueStatus" name="issueStatus" required>
                <option value="">Select an Issue Status</option>
                <option value="Open">Open</option>
                <option value="To Do">To Do</option>
                <option value="Doing">Doing</option>                
                <option value="Done">Done</option>
                <option value="Closed">Closed</option>
            </select><br><br>

            <input type="submit" value="Add Issue">
        </form>
        


    </body>
</html>
