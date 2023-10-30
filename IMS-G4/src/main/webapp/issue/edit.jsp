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

        <div class="col-lg-2 mx-auto" style="background-color: #f5f5f5; padding: 20px; border: 1px solid #ccc; border-radius: 10px; margin-top: 60px; ">
            <h1>Edit Issue</h1><br>

            <form method="post" action="/IMS-G4/edit-issue">
                <input type="hidden" name="issueId" value="<%= request.getAttribute("issueId") %>">


                <!-- Issue Type dropdown -->
                <label for="issueType">Issue Type:</label>
                <select id="issueType" name="issueType" required>
                    <option value="<%= request.getAttribute("issueType") %>"><%= request.getAttribute("issueType") %></option>
                    <option value="Q&A">Q&A</option>
                    <option value="Task">Task</option>
                    <option value="Defect">Defect</option>                
                    <option value="Other Issues">Other Issues</option>
                </select><br>

                <!-- Issue Status dropdown -->
                <label for="issueStatus">Issue Status:</label>
                <select id="issueStatus" name="issueStatus" required>
                    <option value="<%= request.getAttribute("issueStatus") %>"><%= request.getAttribute("issueStatus") %></option>
                    <option value="Open">Open</option>
                    <option value="To Do">To Do</option>
                    <option value="Doing">Doing</option>                
                    <option value="Done">Done</option>
                    <option value="Closed">Closed</option>
                </select><br>

                <!-- Issue comlexity dropdown -->
                <label for="issueComplexity">Issue Complexity: </label>
                <select id="issueComplexity" name="issueComplexity" required>
                    <option value="<%= request.getAttribute("issueComplexity") %>"><%= request.getAttribute("issueComplexity") %></option>
                    <option value="Complex">Complex</option>
                    <option value="Medium">Medium</option>
                    <option value="Simple">Simple</option>    
                </select><br>

                <label for="workingProcess">Working Process:</label>
                <input type="text" name="workingProcess"  value="<%= request.getAttribute("workingProcess") %>">
                <br>            
                <input type="submit" value="Update">
                <br>
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
