<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Details</title>
</head>
<body>
    <h1>Issue Details</h1>
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
    </c:if>
</body>
</html>