
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

        <div class="col-lg-2 mx-auto" style="background-color: #f5f5f5; padding: 20px; border: 1px solid #ccc; border-radius: 10px;  margin-top: 50px">
            <h1>Issue Details</h1><br>
            <c:if test="${empty issue}">
                <p>No issue found with the specified ID.</p>
            </c:if>
            <c:if test="${not empty issue}">
                <table>
                    <tr>
                        <td>Issue ID:</td>
                        <td>${issue.issueId}</td>
                    </tr>
                    <tr>
                        <td>Issue Type:</td>
                        <td>${issue.issueType}</td>
                    </tr>
                    <tr>
                        <td>Issue Status:</td>
                        <td>${issue.issueStatus}</td>
                    </tr>
                    <tr>
                        <td>Project Code:</td>
                        <td>${issue.projectCode}</td>
                    </tr>
                    <tr>
                        <td>Class Name:</td>
                        <td>${issue.className}</td>
                    </tr>
                    <tr>
                        <td>Subject Code:</td>
                        <td>${issue.subjectCode}</td>
                    </tr>
                    <tr>
                        <td>Semester:</td>
                        <td>${issue.semesterName}</td>
                    </tr>
                    <tr>
                        <td>Manager Name:</td>
                        <td>${issue.managerName}</td>
                    </tr>
                    <tr>
                        <td>Assigner Name:</td>
                        <td>${issue.assignerName}</td>
                    </tr>
                    <tr>
                        <td>Assignee Name:</td>
                        <td>${issue.assigneeName}</td>
                    </tr>
                    <tr>
                        <td>Created Date:</td>
                        <td>${issue.createdDate}</td>
                    </tr>
                    <tr>
                        <td>Updated Date:</td>
                        <td>${issue.updatedDate}</td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td>${issue.description}</td>
                    </tr>
                    <tr>
                        <td>Work Process:</td>
                        <td>${issue.workProcess}</td>
                    </tr>
                </table>
                    <button class="btn-back" onclick="backToViewIssue()" style="margin-left: 100px">Back</button>
                <script>
                    function backToViewIssue() {
                        window.location.href = "issue-list";
                    }
                </script>
            </c:if>
        </div>
        <!--/ Body -->    
        <%@include file="../common/footer.jsp" %>
    </body>
</html>
