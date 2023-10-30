<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Issue" %>


<!DOCTYPE html>
<html>
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
        <link rel="stylesheet" href="CSS+JS/css/customcss.css">

    </head>

    <body>
        <!-- Header Area -->
        <header class="header" st style="padding-bottom: 30px;">           
            <div class="header-inner">
                <div class="container">
                    <div class="inner">
                        <div class="row">
                            <div class="col-lg-3 col-md-3 col-12">
                                <!-- Start Logo -->
                                <div class="logo">
                                    <a href="student"><img src="CSS+JS/img/logo.png" alt="#" style="width: 120px; height: 50px;"></a>
                                </div>
                                <!-- End Logo -->
                            </div>
                            <div class="col-lg-7 col-md-9 col-12">
                                <!-- Main Menu -->
                                <div class="main-menu">
                                    <nav class="navigation">
                                        <ul class="nav menu">
                                            <li class="active"><a href="student">Home </a>
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
        </header>
        <!--/ End Header -->




        <!--/ Body -->        
        <div class="col-lg-10 col-12 mx-auto">
            <!-- Side bar -->
            <div class="main-sidebar">
                <!-- Single Widget -->
                <div class="single-widget">
                    <div style="display: flex; justify-content: center;">
                        <!-- Button group -->
                        <button class="btn btn-primary" style="margin-right: 10px;" onclick="openAddIssue()">Add New</button>
                        <button class="btn btn-primary" style="margin-right: 10px;" onclick="importFromExcel()">Import from Excel</button>
                        <button class="btn btn-primary" style="margin-right: 10px;" onclick="exportToExcel()">Export to Excel</button>
                        <button class="btn btn-primary" style="margin-right: 10px;" onclick="syncToGitLab()">Sync to GitLab</button>
                        <script>
                            function openAddIssue() {
                                window.location.href = "add-issue";
                            }

                            function importFromExcel() {
                                window.location.href = "import_from_excel.jsp";
                            }

                            function exportToExcel() {
                                window.location.href = "export_to_excel.jsp";
                            }

                            function syncToGitLab() {
                                window.location.href = "sync_to_gitlab.jsp";
                            }
                        </script>
                    </div>

                    <!-- Table -->
                    <div class="site-content" style="margin-top: 30px">
                        <c:if test="${empty issues}">
                            <p>No issues found.</p>
                        </c:if>

                        <c:if test="${not empty issues}">
                            <div class="table-responsive">
                                <table class="table table-bordered table-striped">
                                    <thead>
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
                                    </thead>
                                    <tbody>
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
                                                    <a href="/IMS-G4/edit-issue?issueId=${issue.issueId}" class="btn btn-primary action-button1">Edit</a>
                                                    <a href="/IMS-G4/issue-details?issueId=${issue.issueId}" class="btn btn-primary action-button2">View</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>
                    </div>
                    <!--/ Table -->
                </div>
                <!--/ End Single Widget -->
            </div>
            <!--/ End side bar -->
        </div>
        <!--/ Body -->

        <!-- Footer -->
        <footer id="footer" class="footer" style="padding-top: 100px">            
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

        <script src="CSS+JS/js/jquery.min.js"></script>
        <script src="CSS+JS/js/jquery-migrate-3.0.0.js"></script>
        <script src="CSS+JS/js/jquery-ui.min.js"></script>
        <script src="CSS+JS/js/easing.js"></script>
        <script src="CSS+JS/js/colors.js"></script>
        <script src="CSS+JS/js/popper.min.js"></script>
        <script src="CSS+JS/js/bootstrap-datepicker.js"></script>
        <script src="CSS+JS/js/jquery.nav.js"></script>
        <script src="CSS+JS/js/slicknav.min.js"></script>
        <script src="CSS+JS/js/jquery.scrollUp.min.js"></script>
        <script src="CSS+JS/js/niceselect.js"></script>
        <script src="CSS+JS/js/tilt.jquery.min.js"></script>
        <script src="CSS+JS/js/owl-carousel.js"></script>
        <script src="CSS+JS/js/jquery.counterup.min.js"></script>
        <script src="CSS+JS/js/steller.js"></script>
        <script src="CSS+JS/js/wow.min.js"></script>
        <script src="CSS+JS/js/jquery.magnific-popup.min.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/waypoints/2.0.3/waypoints.min.js"></script>
        <script src="CSS+JS/js/bootstrap.min.js"></script>
        <script src="CSS+JS/js/main.js"></script>
        <script>
                    // Get all the table rows
                    const tableRows = document.querySelectorAll('table tr');

                    // Set the number of rows to display per page
                    const rowsPerPage = 10;

                    // Calculate the total number of pages
                    const totalPages = Math.ceil(tableRows.length / rowsPerPage);

                    // Display the table rows for the specified page
                    function showPage(page) {
                        const startIndex = (page - 1) * rowsPerPage;
                        const endIndex = startIndex + rowsPerPage;

                        // Hide all table rows
                        tableRows.forEach((row, index) => {
                            if (index >= startIndex && index < endIndex) {
                                row.style.display = 'table-row'; // Display the row
                            } else {
                                row.style.display = 'none'; // Hide the row
                            }
                        });
                    }

                    // Create pagination links
                    function createPaginationLinks() {
                        const pagination = document.createElement('div');
                        pagination.className = 'pagination';

                        for (let i = 1; i <= totalPages; i++) {
                            const link = document.createElement('a');
                            link.href = '#';
                            link.innerText = i;

                            // Set the active class for the current page
                            if (i === 1) {
                                link.className = 'active';
                            }

                            // Add an event listener to each pagination link
                            link.addEventListener('click', function () {
                                // Remove the active class from all links
                                pagination.querySelectorAll('a').forEach((a) => {
                                    a.classList.remove('active');
                                });

                                // Set the active class for the clicked link
                                this.classList.add('active');

                                // Show the corresponding page
                                showPage(i);
                            });

                            pagination.appendChild(link);
                        }

                        return pagination;
                    }

                    // Show the first page initially
                    showPage(1);

                    // Create pagination links and append them to the document
                    const paginationContainer = document.querySelector('.site-content');
                    const paginationLinks = createPaginationLinks();
                    paginationContainer.appendChild(paginationLinks);
        </script>      
    </body>
</html>
