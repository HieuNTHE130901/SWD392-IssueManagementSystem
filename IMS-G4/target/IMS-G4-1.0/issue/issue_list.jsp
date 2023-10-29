<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Issue" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Issue List</title>
    </head>
    <body>
        <h1>Issue List </h1>

        <div>
            <button><a href="/IMS-G4/add-issue" class="button">Add New</a></button>
            <button>Import from Excel</button>
            <button>Export to Excel</button>
            <button>Sync to GitLab</button>

        </div>

        <c:if test="${empty issues}">
            <p>No issues found.</p>
        </c:if>

        <c:if test="${not empty issues}">
            <table border="1">
                <tr>
                    <th>Issue ID</th>                    
                    <th>Issue Type</th>
                    <th>Issue Status</th>  
                    <th>Project Code</th>
                    <th>Class Name</th>
                    <th>Subject Code</th>
                    <th>Assigner Name</th>
                    <th>Assignee Name</th>
                    <th>Created Date</th>
                    <th>Updated Date</th>                  
                    <th>Description</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${issues}" var="issue">
                    <tr>
                        <td>${issue.issueId}</td>                        
                        <td>${issue.issueType}</td>
                        <td>${issue.issueStatus}</td>  
                        <td>${issue.projectCode}</td>
                        <td>${issue.className}</td>
                        <td>${issue.subjectCode}</td>
                        <td>${issue.assignerName}</td>
                        <td>${issue.assigneeName}</td>
                        <td>${issue.createdDate}</td>
                        <td>${issue.updatedDate}</td>                      
                        <td>${issue.description}</td>
                        <td>
                            <a href="#">Edit</a>
                            <a href="#">View</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <a href="/IMS-G4/student">Go Back</a>
    </body>
</html>
