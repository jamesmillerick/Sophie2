<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Event Dashboard</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<header>
    <h1>Event Dashboard</h1>
    <a href="createEvent.jsp" class="button">Create New Event</a>
</header>

<div class="event-table">
    <table>
        <thead>
        <tr>
            <th>Event ID</th>
            <th>Event Name</th>
            <th>Event Date</th>
            <th>Event Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <!-- Loop through the events list -->
        <c:forEach var="event" items="${events}">
            <tr>
                <td>${event.eventID}</td>
                <td>${event.eventName}</td>
                <td>${event.eventDate}</td>
                <td>${event.eventStatus}</td>
                <td>
                    <!-- Edit Button -->
                    <a href="editEvent.jsp?eventID=${event.eventID}" class="button">Edit</a>
                    <!-- Delete Button -->
                    <form action="/events" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete" />
                        <input type="hidden" name="eventID" value="${event.eventID}" />
                        <button type="submit" class="button delete">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

