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
            <h2>Project Issues Statistics by week</h2>
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

            <br><br>

            <label for="classCombobox">Class:</label>
            <select id="subjectCombobox">
                <option value="">Select class</option>
                <c:forEach items="${classes}" var="class1">
                    <option value="${class1.classId}">${class1.className}</option>
                </c:forEach>
            </select>

            <label for "projectCombobox">Project:</label>
            <select id="projectCombobox">
                <option value="">Select project</option>
                <c:forEach items="${projects}" var="project">
                    <option value="${project.projectId}">${project.projectName}</option>
                </c:forEach>
            </select>
        </div>


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

        <script>
            // Get the current date
            var currentDate = new Date();

            // Calculate the start date for the two-month range
            var startDate = new Date(currentDate);
            startDate.setMonth(currentDate.getMonth() - 2);

            // Adjust the start date to the previous Monday
            startDate.setDate(startDate.getDate() - (startDate.getDay() + 6) % 7 );

            // Create an array to store the week options
            var weekOptions = [];

            // Generate the week options for the past two months
            while (startDate <= currentDate) {
                // Calculate the end date for the current week (Sunday)
                var endDate = new Date(startDate);
                endDate.setDate(startDate.getDate() + 6);

                // Format the start and end dates of the week as "dd/mm/yyyy"
                var formattedStartDate = ("0" + startDate.getDate()).slice(-2) + "/" + ("0" + (startDate.getMonth() + 1)).slice(-2) + "/" + startDate.getFullYear();
                var formattedEndDate = ("0" + endDate.getDate()).slice(-2) + "/" + ("0" + (endDate.getMonth() + 1)).slice(-2) + "/" + endDate.getFullYear();

                // Create the option element with the formatted date range
                var option = document.createElement("option");
                option.value = formattedStartDate + " - " + formattedEndDate;
                option.textContent = formattedStartDate + " - " + formattedEndDate;

                // Add the option element to the weekOptions array
                weekOptions.push(option);

                // Move to the next week
                startDate.setDate(startDate.getDate() + 7);
            }

            // Get the select element for weeks
            var weekSelect = document.getElementById("weekCombobox");

            // Add the sorted option elements to the select dropdown
            for (var i = 0; i < weekOptions.length; i++) {
                weekSelect.appendChild(weekOptions[i]);
            }

            // Find the current week and set it as the selected option
            var currentWeekStart = new Date(currentDate);
            currentWeekStart.setDate(currentDate.getDate() - (currentDate.getDay() + 6) % 7 + 1);
            var currentWeekEnd = new Date(currentWeekStart);
            currentWeekEnd.setDate(currentWeekStart.getDate() + 6);
            var currentWeekRange = currentWeekStart.toLocaleDateString('en-GB', {day: '2-digit', month: '2-digit', year: 'numeric'}).replace(/\//g, '-') + " - " + currentWeekEnd.toLocaleDateString('en-GB', {day: '2-digit', month: '2-digit', year: 'numeric'}).replace(/\//g, '-');

            for (var i = 0; i < weekSelect.options.length; i++) {
                if (weekSelect.options[i].value === currentWeekRange) {
                    weekSelect.selectedIndex = i;
                    break;
                }
            }
        </script>





    </body>
</html>
