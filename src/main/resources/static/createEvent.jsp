<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create New Event</title>
</head>
<body>
<header>
    <h1>Create New Event</h1>
    <a href="/events" class="button">Back to Event Dashboard</a>
</header>

<form action="/events" method="post">
    <input type="hidden" name="action" value="create" />

    <label for="eventID">Event ID</label>
    <input type="text" name="eventID" required />

    <label for="eventName">Event Name</label>
    <input type="text" name="eventName" required />

    <label for="eventDate">Event Date</label>
    <input type="date" name="eventDate" required />

    <label for="eventStatus">Event Status</label>
    <select name="eventStatus" required>
        <option value="Pending">Pending</option>
        <option value="Completed">Completed</option>
        <option value="Cancelled">Cancelled</option>
    </select>

    <button type="submit" class="button">Create Event</button>
</form>
</body>
</html>
