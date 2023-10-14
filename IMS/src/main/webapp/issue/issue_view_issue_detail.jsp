<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.IssueDAO" %>
<%@ page import="model.Issue" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>View Issue Details</title>
        <!-- Add your CSS styles here, if needed -->
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
        <!-- Header Area -->
        <header class="header" >
            <!-- Header Inner -->
            <div class="header-inner">
                <div class="container">
                    <div class="inner">
                        <div class="row">
                            <div class="col-lg-3 col-md-3 col-12">
                                <!-- Start Logo -->
                                <div class="logo">
                                    <a href="/IMS/student"><img src="CSS+JS/img/logo.png" alt="#" style="width: 120px; height: 50px;"></a>
                                </div>
                                <!-- End Logo -->
                            </div>
                            <div class="col-lg-7 col-md-9 col-12">
                                <!-- Main Menu -->
                                <div class="main-menu">
                                    <nav class="navigation">
                                        <ul class="nav menu">
                                            <li class="active"><a href="/IMS/student">Home </a>
                                            </li>                                           
                                        </ul>
                                    </nav>
                                </div>
                                <!--/ End Main Menu -->
                            </div>
                            <div class="col-lg-2 col-12">
                                <div class="get-quote">
                                    <a href="/IMS/sign-out" class="btn">Sign out</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--/ End Header Inner -->
        </header>
        <!-- End Header Area -->






        <%-- Retrieve the issueId parameter from the URL --%>
        <% String issueIdParam = request.getParameter("issueId");
           if (issueIdParam != null) {
               int issueId = Integer.parseInt(issueIdParam);
               IssueDAO issueDAO = new IssueDAO();
               Issue issue = issueDAO.getIssueById(issueId); // Implement this method in your DAO
               if (issue != null) {
        %>

        <div class="col-lg-2 mx-auto" style="background-color: #f5f5f5; padding: 20px; border: 1px solid #ccc; border-radius: 10px;">
            <h1 style="text-align: center;">Issue Details</h1>
            <br>
            <div style="text-align: center;">
                <!-- Edit issue form fields -->
                <label>Issue ID (integer): </label>
                <input type="text" value="<%= issue.getIssueId() %>" readonly style="text-align: center;">
                <br>
                <label>Project ID (integer): </label>
                <input type="text" value="<%= issue.getProjectId() %>" readonly style="text-align: center;">
                <br>
                <label>Issue Type: </label>
                <input type="text" value="<%= issue.getIssueType() %>" readonly style="text-align: center;">
                <br>
                <label>Issue Status: </label>
                <input type="text" value="<%= issue.getIssueStatus() %>" readonly style="text-align: center;">
                <br>
                <label>Issue Description: </label>
                <input type="text" value="<%= issue.getIssueDescription() %>" readonly style="text-align: center;">
                <br>
                <label>Created Date: </label>
                <input type="text" value="<%= issue.getCreatedDate() %>" readonly style="text-align: center;">
                <br>
                <label>Updated Date: </label>
                <input type="text" value="<%= issue.getUpdatedDate() %>" readonly style="text-align: center;">
                <br>
                <!-- Back Button -->
                <button class="btn-back" onclick="backToViewIssue()">Cancel</button>
                <script>
                    function backToViewIssue() {
                        window.location.href = "/IMS/manage-issue";
                    }
                </script>
                <!-- Back Button -->
            </div>
        </div>






        <% 
               } else {
                   // Handle the case where the issue with the given ID is not found
                   out.println("<p>Issue not found</p>");
               }
           } else {
               // Handle the case where issueId parameter is not provided
               out.println("<p>Issue ID not provided</p>");
           }
        %>

        <!-- Add any additional content or links as needed -->


    </body>
</html>
