<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Issue</title>
    </head>
    <body>
        <h1>Edit Issue</h1>
        
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
            </select><br><br>

            <!-- Issue Status dropdown -->
            <label for="issueStatus">Issue Status:</label>
            <select id="issueStatus" name="issueStatus" required>
                <option value="<%= request.getAttribute("issueStatus") %>"><%= request.getAttribute("issueStatus") %></option>
                <option value="Open">Open</option>
                <option value="To Do">To Do</option>
                <option value="Doing">Doing</option>                
                <option value="Done">Done</option>
                <option value="Closed">Closed</option>
            </select><br><br>
            
             <!-- Issue comlexity dropdown -->
            <label for="issueComplexity">Issue Complexity: </label>
            <select id="issueComplexity" name="issueComplexity" required>
                <option value="<%= request.getAttribute("issueComplexity") %>"><%= request.getAttribute("issueComplexity") %></option>
                <option value="Complex">Complex</option>
                <option value="Medium">Medium</option>
                <option value="Simple">Simple</option>    
            </select><br><br>
            
            <label for="workingProcess">Working Process:</label>
            <input type="text" name="workingProcess" value="<%= request.getAttribute("workingProcess") %>">
            <br>            
            <input type="submit" value="Update">
        </form>
    </body>
</html>