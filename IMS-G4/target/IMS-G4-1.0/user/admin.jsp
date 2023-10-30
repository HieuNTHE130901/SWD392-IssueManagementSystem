<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html class="no-js" lang="zxx">
    <head>
        <!-- Meta Tags -->
        <meta charset="utf-8">
        <!-- Title -->
        <title>Issue Management System</title>        
        <!--  CSS -->
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
        <link rel="stylesheet" href="CSS+JS/css/footer.css">        
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
                                    <a href="admin"><img src="CSS+JS/img/logo.png" alt="#" style="width: 120px; height: 50px;"></a>
                                </div>
                                <!-- End Logo -->
                            </div>
                            <div class="col-lg-7 col-md-9 col-12">
                                <!-- Main Menu -->
                                <div class="main-menu">
                                    <nav class="navigation">
                                        <ul class="nav menu">
                                            <li class="active"><a href="admin">Home </a>
                                            </li>                                           
                                        </ul>
                                    </nav>
                                </div>
                                <!--/ End Main Menu -->
                            </div>
                            <div class="col-lg-2 col-12">
                                <div class="get-quote">
                                    <a href="logout" class="btn">Sign out</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--/ End Header Inner -->
        </header>
        <!-- End Header Area -->

        <!-- BODY -->
        <section class="contact-us section">
            <div class="container">
                <div class="contact-info">
                    <div class="row">
                        <div class="col-lg-6 col-12" id="resetPassword">
                            <div class="single-info">
                                <div class="content">
                                    <h3>Reset/Change the Password</h3>
                                    <p>Can't remember your password? Reset or change it here.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-12" id="viewUserProfile">
                            <div class="single-info">
                                <div class="content">
                                    <h3>View User Profile</h3>
                                    <p>Show your information.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 30px">
                        <div class="col-lg-6 col-12"  id="viewIssueTrackingDashboard">
                            <div class="single-info">
                                <div class="content">
                                    <h3>View Issue Tracking Dashboard</h3>
                                    <p>Show statistics of the project requirements, Q&A, Tasks, Defects, and other Project Issues.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-12" id="viewProjectDashboard">
                            <div class="single-info">
                                <div class="content">
                                    <h3>View Project Dashboard</h3>
                                    <p>Show the project newly created issues statistics and GitLab commits made by your team members.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 30px">
                        <div class="col-lg-6 col-12"  id="manageUser">
                            <div class="single-info">
                                <div class="content">
                                    <h3>Manage User</h3>
                                    <p>Manage users in IMS</p>
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-lg-6 col-12"  id="manageSystem">
                            <div class="single-info">
                                <div class="content">
                                    <h3>Manage System</h3>
                                    <p>Manage IMS system setting</p>
                                </div>
                            </div>
                        </div>
                       
                    </div>

                    <script>
                        document.getElementById("resetPassword").onclick = function () {
                            // Redirect to the Reset Password 
                            window.location.href = "reset-pass";
                        };

                        document.getElementById("viewUserProfile").onclick = function () {
                            // Redirect to the View User Profile 
                            window.location.href = "view-profile";
                        };

                        document.getElementById("viewIssueTrackingDashboard").onclick = function () {
                            // Redirect to the View Issue Tracking Dashboard 
                            window.location.href = "view-issue-dashboard";
                        };

                        document.getElementById("viewProjectDashboard").onclick = function () {
                            // Redirect to the View Project Dashboard 
                            window.location.href = "view-project-dashboard";
                        };

                        document.getElementById("manageUser").onclick = function () {
                            // Redirect to the Manage Project Issue 
                            window.location.href = "manage-user";
                        };
                       
                         document.getElementById("manageSystem").onclick = function () {
                            // Redirect to the Manage subject
                            window.location.href = "manage-system";
                        };
                        
                    </script>

                </div>
            </div>
        </section>


        <!-- Footer -->
        <footer id="footer" class="footer" style="padding-top: 30px">
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


    </body>
</html>

