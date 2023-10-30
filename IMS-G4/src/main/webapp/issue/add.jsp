<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Meta Tags -->
        <meta charset="utf-8">
        <!-- Title -->
        <title>Issue Management System</title>  
        <link href="https://fonts.googleapis.com/css?family=Poppins:200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="CSS+JS/css/bootstrap.min.css">
        <link rel="stylesheet" href="CSS+JS/css/nice-select.css">
        <link rel="stylesheet" href="CSS+JS/css/font-awesome.min.css">
        <link rel="stylesheet" href="CSS+JS/css/icofont.css">
        <link rel="stylesheet" href="CSS+JS/css/slicknav.min.css">
        <link rel="stylesheet" href="CSS+JS/css/owl-carousel.css">
        <link rel="stylesheet" href="CSS+JS/css/datepicker.css">
        <link rel="stylesheet" href="CSS+JS/css/animate.min.css">
        <link rel="stylesheet" href="CSS+JS/css/magnific-popup.css">      
        <link rel="stylesheet" href="CSS+JS/css/normalize.css">
        <link rel="stylesheet" href="CSS+JS/css/style.css">
        <link rel="stylesheet" href="CSS+JS/css/responsive.css">        
        <link rel="stylesheet" href="CSS+JS/css/customcss.css">

    </head>

    <body>
        <%@include file="../common/header.jsp" %>

        <div class="col-lg-2 mx-auto" style="background-color: #f5f5f5; padding: 20px; border: 1px solid #ccc; border-radius: 10px; ">
            <h2>Add New Issue</h2>
            <form action="/IMS-G4/add-issue" method="post">
                <!-- Load project names from the server and populate the select element -->
                <label for="project">Project:</label>
                <select id="project" name="project" required>
                    <option value="">Select a Project</option>
                    <c:forEach items="${projectList}" var="project">
                        <option value="${project.projectId}">${project.projectName}</option>
                    </c:forEach>
                </select>

                <!-- Load milestones based on the selected project -->
                <label for="milestone">Milestone: </label>
                <select id="milestone" name="milestone" required>
                    <option value="">Select a Milestone</option>
                    <c:forEach items="${milestoneList}" var="milestone">
                        <option value="${milestone.milestoneId}">${milestone.milestoneName}</option>
                    </c:forEach>
                </select>

                <!-- Assignee is the current user -->
                <label for="assignee">Assignee:</label>
                <input type="text" id="assignee" name="assignee" value="${user.fullName}" required readonly>

                <!-- Issue Type dropdown -->
                <label for="issueType">Issue Type:</label>
                <select id="issueType" name="issueType" required>
                    <option value="">Select an Issue Type</option>                
                    <option value="Q&A">Q&A</option>
                    <option value="Task">Task</option>
                    <option value="Defect">Defect</option>                
                    <option value="Other Issues">Other Issues</option>
                </select>

                <!-- Issue Status dropdown -->
                <label for="issueStatus">Issue Status:</label>
                <select id="issueStatus" name="issueStatus" required>
                    <option value="">Select an Issue Status</option>
                    <option value="Open">Open</option>
                    <option value="To Do">To Do</option>
                    <option value="Doing">Doing</option>                
                    <option value="Done">Done</option>
                    <option value="Closed">Closed</option>
                </select>

                <label for="description">Description:</label>
                <textarea id="description" name="description" rows="4" cols="50" required></textarea>
                <br>

                <input type="submit" value="Add Issue"><br>
                <button class="btn-back" onclick="backToViewIssue()">Cancel</button>
                <script>
                    function backToViewIssue() {
                        window.location.href = "issue-list";
                    }
                </script>
            </form>
        </div>
        <!--/ Body -->    
        <%@include file="../common/footer.jsp" %>
    </body>
</html>
