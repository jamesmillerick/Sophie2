<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Event</title>
</head>
<body>
<header>
    <h1>Edit Event</h1>
    <a href="/events" class="button">Back to Event Dashboard</a>
</header>

<form action="updateEvent" method="post">
    <input type="hidden" name="eventID" value="${param.eventID}" />

    <label for="eventName">Event Name</label>
    <input type="text" name="eventName" value="${event.eventName}" required />

    <label for="eventDate">Event Date</label>
    <input type="date" name="eventDate" value="${event.eventDate}" required />

    <label for="eventStatus">Event Status</label>
    <select name="eventStatus" required>
        <option value="Pending" ${event.eventStatus == 'Pending' ? 'selected' : ''}>Pending</option>
        <option value="Completed" ${event.eventStatus == 'Completed' ? 'selected' : ''}>Completed</option>
        <option value="Cancelled" ${event.eventStatus == 'Cancelled' ? 'selected' : ''}>Cancelled</option>
    </select>

    <button type="submit" class="button">Update Event</button>
</form>
</body>
</html>
