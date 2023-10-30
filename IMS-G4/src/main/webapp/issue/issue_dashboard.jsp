<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Issue Dashboard</title>

    </head>
    <body>
        <h1>Issue Tracker Dashboard</h1>

        <div>
            <div style="display: flex;">
                <div>
                    <label for="semesterCombobox">Semester:</label>
                    <select id="semesterCombobox">
                        <option value="">Select Semester</option>
                        <c:forEach items="${semesters}" var="semester">
                            <option value="${semester.semesterId}">${semester.semesterName}</option>
                        </c:forEach>
                    </select>

                    <label for="subjectCombobox">Subject:</label>
                    <select id="subjectCombobox">
                        <option value="">Select Subject</option>
                        <c:forEach items="${subjects}" var="subject">
                            <option value="${subject.subjectId}">${subject.subjectName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div>
                    <label for="classCombobox">Class:</label>
                    <select id="classCombobox">
                        <option value="">Select class</option>
                        <c:forEach items="${classes}" var="class1">
                            <option value="${class1.classId}">${class1.className}</option>
                        </c:forEach>
                    </select>

                    <label for="projectCombobox">Project:</label>
                    <select id="projectCombobox">
                        <option value="">Select project</option>
                        <c:forEach items="${projects}" var="project">
                            <option value="${project.projectId}">${project.projectName}</option>
                        </c:forEach>
                    </select>
                    <button onclick="searchChart()">Search</button>
                </div>
            </div>
            <div id="stackedColumnChart"></div>
        </div>

        <div>
            <h2>Project Issues Statistics by week</h2>
            <br><br>
            <label for="weekCombobox">Choose week:</label>
            <select id="weekCombobox">
            </select>

            <label for="studentCombobox">Student:</label>
            <select id="studentCombobox">
                <option value="">Select student</option>
                <c:forEach items="${users}" var="user">
                    <option value="${user.userId}">${user.fullName}</option>
                </c:forEach>
            </select>
        </div>

        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
                        // Load the Google Charts library
                        google.charts.load('current', {'packages': ['corechart']});

                        // Draw the chart when the library is loaded
                        google.charts.setOnLoadCallback(drawStackedColumnChart);

                        // Function to draw the stacked column chart
                        
                        function searchChart() {
                            // Get the selected project ID
                            var projectSelect = document.getElementById("projectCombobox");
                            var selectedProjectId = projectSelect.value;

                            // Call the drawStackedColumnChart function with the selected project ID
                            drawStackedColumnChart(selectedProjectId);
                        }

                        // Function to draw the stacked column chart
                        function drawStackedColumnChart(projectId) {
                            // Get the chart data from the server-side code
                            var chartData = ${chartData};

                            // Create a DataTable using the chartData
                            var data = google.visualization.arrayToDataTable(chartData);

                            // Set chart options
                            var options = {
                                title: 'Project Requirement',
                                isStacked: true,
                                seriesType: 'bars'
                            };

                            // Create a new stacked column chart and draw it
                            var chart = new google.visualization.ComboChart(document.getElementById('stackedColumnChart'));
                            chart.draw(data, options);
                        }
                        
        </script>
        
    </body>
</html>
