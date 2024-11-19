package com.fyp.SpringSophie2.Service;

import com.fyp.SpringSophie2.Repository.EventRepository;
import com.fyp.SpringSophie2.Repository.TaskRepository;
import com.fyp.SpringSophie2.model.Event;
import com.fyp.SpringSophie2.model.EventDTO;
import com.fyp.SpringSophie2.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final TaskRepository taskRepository;

    public EventService(EventRepository eventRepository, TaskRepository taskRepository) {
        this.eventRepository = eventRepository;
        this.taskRepository = taskRepository;
    }

    //This function is asking to get all the events with the task completion percentage
    public List<EventDTO> getAllEventsWithTaskCompletion() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventDTOs = new ArrayList<>();

        for (Event event : events) {
            List<Task> tasks = taskRepository.findByEventID(event.getEventID());
            int completedTasks = (int) tasks.stream().filter(task -> task.getEventStatus().equals("Completed")).count();
            int totalTasks = tasks.size();
            double EventCompletionPercentage = totalTasks > 0 ? (completedTasks * 100.0 / totalTasks) : 0; // the maths to calculate the percentage value

            eventDTOs.add(new EventDTO(event.getEventID(), event.getEventName(), event.getEventDate(), event.getEventStatus(), EventCompletionPercentage));
        }

        return eventDTOs;
    }

    // Method to create a new event
    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setEventID(eventDTO.getEventID());
        event.setEventName(eventDTO.getEventName());
        event.setEventDate(eventDTO.getEventDate());
        event.setEventStatus("Pending"); //Set default status for a new event

        Event savedEvent = eventRepository.save(event);
        return new EventDTO(savedEvent.getEventID(), savedEvent.getEventName(), savedEvent.getEventDate(), savedEvent.getEventStatus(), 0.0);
    }

    // Method to assign a task to an event
    public Task assignTaskToEvent(String eventID, Task task) {
        Event event = eventRepository.findById(eventID)
                .orElseThrow(() -> new NoSuchElementException("Event not found"));

        task.setEventID(eventID);  // Set the event ID for the task
        task.setEventStatus("Pending"); // Default status when assigning a task

        Task savedTask = taskRepository.save(task);
        return savedTask;  // Return the saved task
    }

    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event event : events) {
            eventDTOs.add(new EventDTO(event.getEventID(), event.getEventName(), event.getEventDate(), event.getEventStatus(), 0.0));
        }
        return eventDTOs;
    }


}


/*
package com.fyp.SpringSophie2.Service;

import com.fyp.SpringSophie2.model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventService {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/FypSophie";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1234";

    public static List<Event> getAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();

        String query = "SELECT * FROM event";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Event event = new Event();
                event.setEventID(resultSet.getString("id"));
                event.setEventName(resultSet.getString("name"));
                event.setEventDate(resultSet.getDate("date").toLocalDate());
                event.setEventStatus(resultSet.getString("status"));
                events.add(event);
            }
        }
        return events;
    }

    public void addEvent(Event event) throws SQLException {
        String query = "INSERT INTO event (eventid, event_name, event_date, event_status) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, event.getEventID());
            preparedStatement.setString(2, event.getEventName());
            preparedStatement.setDate(3, Date.valueOf(event.getEventDate()));
            preparedStatement.setString(4, event.getEventStatus());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteEvent(String eventID) throws SQLException {
        String query = "DELETE FROM event WHERE eventid = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, eventID);
            preparedStatement.executeUpdate();
        }

    }
}

 */
