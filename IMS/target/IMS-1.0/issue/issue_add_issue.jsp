<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Issue" %>
<%@ page import="dao.DBContext" %>
<%@ page import="dao.IssueDAO" %>
<%@ page import="java.util.ArrayList" %>
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
         <!-- Header Area -->
         <header class="header" style="padding-bottom: 30px">
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

        <!--/ Body -->       
     

        <div class="col-lg-2 mx-auto"style="background-color: #f5f5f5; padding: 20px; border: 1px solid #ccc; border-radius: 10px; ">
            <h2>Add New Issue</h2>
            <form id="addIssueForm" action="issue/issue_submit_issue.jsp" method="POST">
                <!-- Add issue form fields -->
                <label for="projectID">Project ID (integer):</label>
                <input type="number" id="projectID" name="projectID" required min="1">
                <br>
                <label for="issueType">Issue Type:</label>
                <input type="text" id="issueType" name="issueType" required>
                <br>
                <label for="issueStatus">Issue Status:</label>
                <input type="text" id="issueStatus" name="issueStatus" required>
                <br>
                <label for="issueDescription">Issue Description:</label>
                <input type="text" id="issueDescription" name="issueDescription" required>
                <br>
                <label for="createdBy">Created By (user ID):</label>
                <input type="number" id="createdBy" name="createdBy" required min="1">
                <br>
                <input type="submit" value="Submit">
                <br>
                 <!-- Back Button -->
                <button class="btn-back" onclick="backToViewIssue()">Cancel</button>
                <script>
                    function backToViewIssue() {
                        window.location.href = "/IMS/manage-issue";
                    }
                </script>
                <!-- Back Button -->
                
                
            </form>
            
        </div>
        <!--/ Body -->    

        <!-- Footer -->
        <footer id="footer" class="footer" style="padding-top: 100px">
            <!-- ... (your footer code) ... -->
            <div class="copyright">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-12">
                            <div class="copyright-content">
                                <p>© Copyright 2023 | All Rights Reserved by <a href="" target="_blank">Group 4 - SWD392</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Footer -->

       

    </body>
</html>
