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
        <%@include file="../common/header.jsp" %>

        <!-- BODY -->
        <section class="contact-us section">
            <div class="container">
                <h2>Common features</h2>
                <div class="contact-info">
                    <div class="row">
                        <div class="col-lg-6 col-12">
                            <a href="reset-pass">
                                <div class="single-info">
                                    <div class="content">
                                        <h3>Reset/Change the Password</h3>
                                        <p>Can't remember your password? Reset or change it here.</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-6 col-12">
                            <a href="view-profile">
                                <div class="single-info">
                                    <div class="content">
                                        <h3>View User Profile</h3>
                                        <p>Show your information.</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 30px">
                        <div class="col-lg-6 col-12">
                            <a href="view-issue-dashboard">
                                <div class="single-info">
                                    <div class="content">
                                        <h3>View Issue Tracking Dashboard</h3>
                                        <p>Show statistics of the project requirements, Q&A, Tasks, Defects, and other Project Issues.</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-6 col-12">
                            <a href="view-project-dashboard">
                                <div class="single-info">
                                    <div class="content">
                                        <h3>View Project Dashboard</h3>
                                        <p>Show the project newly created issues statistics and GitLab commits made by your team members.</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <br><br>
                    <% User user = (User) session.getAttribute("user");
if (user != null) {
    String userRole = user.getUserRole();
                    %>
                    <!-- Role specific features -->
                    <h2>Role specific features</h2>
                    <% if (userRole.equals("admin")) { %>
                    <!-- Admin features -->
                    <div class="row" style="padding-top: 30px">
                        <div class="col-lg-6 col-12">
                            <a href="manage-user">
                                <div class="single-info">
                                    <div class="content">
                                        <h3>Manage User</h3>
                                        <p>Manage users in IMS</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-6 col-12">
                            <a href="manage-system">
                                <div class="single-info">
                                    <div class="content">
                                        <h3>Manage System</h3>
                                        <p>Manage system settings and configurations.</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <% } else if (userRole.equals("student")) { %>
                    <!-- Student features -->
                    <div class="row" style="padding-top: 30px">
                        <div class="col-lg-6 col-12">
                            <a href="issue-list">
                                <div class="single-info">
                                    <div class="content">
                                        <h3>View Issue List</h3>
                                        <p>View issue of joined projects.</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <% } else if (userRole.equals("teacher")) { %>
                    <!-- Student features -->
                    <div class="row" style="padding-top: 30px">
                        <div class="col-lg-6 col-12">
                            <a href="class-list">
                                <div class="single-info">
                                    <div class="content">
                                        <h3>Manage Class</h3>
                                        <p>Manage assigned class</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-6 col-12">
                            <a href="project-list">
                                <div class="single-info">
                                    <div class="content">
                                        <h3>Manage project</h3>
                                        <p>Manage project of class</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <% } else if (userRole.equals("manager")) { %>
                    <!-- Manager features -->
                    <div class="row" style="padding-top: 30px">
                        <div class="col-lg-6 col-12">
                            <a href="class-list">
                                <div class="single-info">
                                    <div class="content">
                                        <h3>Manage Class</h3>
                                        <p>Manage assigned class</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-6 col-12">
                            <a href="subject-list">
                                <div class="single-info">
                                    <div class="content">
                                        <h3>Manage Subject</h3>
                                        <p>Manage assigned subject</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <% } %>
                    <% } %>
                </div>
            </div>
        </section>


        <%@include file="../common/footer.jsp" %>


        <!-- JS -->
        <script src="CSS+JS/js/vendor/modernizr-3.5.0.min.js"></script>
        <script src="CSS+JS/js/vendor/jquery-1.12.4.min.js"></script>
        <script src="CSS+JS/js/popper.min.js"></script>
        <script src="CSS+JS/js/bootstrap.min.js"></script>
        <script src="CSS+JS/js/jquery.slicknav.min.js"></script>
        <script src="CSS+JS/js/owl-carousel.js"></script>
        <script src="CSS+JS/js/slick.min.js"></script>
        <script src="CSS+JS/js/wow.js"></script>
        <script src="CSS+JS/js/animated.headline.js"></script>
        <script src="CSS+JS/js/jquery.magnific-popup.js"></script>
        <script src="CSS+JS/js/jquery.scrollUp.min.js"></script>
        <script src="CSS+JS/js/jquery.nice-select.min.js"></script>
        <script src="CSS+JS/js/jquery.sticky.js"></script>
        <script src="CSS+JS/js/contact.js"></script>
        <script src="CSS+JS/js/jquery.form.js"></script>
        <script src="CSS+JS/js/jquery.validate.min.js"></script>
        <script src="CSS+JS/js/mail-script.js"></script>
        <script src="CSS+JS/js/jquery.ajaxchimp.min.js"></script>
        <script src="CSS+JS/js/plugins.js"></script>
        <script src="CSS+JS/js/main.js"></script>
        <script src="CSS+JS/js/footer.js"></script>
        <script src="CSS+JS/js/customjs.js"></script>
    </body>
</html>