package com.fyp.SpringSophie2.Service;


import com.fyp.SpringSophie2.Repository.EventRepository;
import com.fyp.SpringSophie2.Repository.TaskRepository;
import com.fyp.SpringSophie2.model.Event;
import com.fyp.SpringSophie2.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    //Get all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    //Get an event by its ID
    public Optional<Event> getEventById(String eventID) {
        return eventRepository.findById(eventID);
    }

    //Create a new event
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    //Update an event using AJAX
    public void updateEvent(String eventID, Event updatedEvent) {
        Event existingEvent = eventRepository.findById(eventID)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        existingEvent.setEventName(updatedEvent.getEventName());
        existingEvent.setEventDate(updatedEvent.getEventDate());
        existingEvent.setEventStatus(updatedEvent.getEventStatus());

        eventRepository.save(existingEvent);
    }

    //Delete an event by its ID
    public void deleteEvent(String eventID) {
        if (eventRepository.existsById(eventID)) {
            eventRepository.deleteById(eventID);
        } else {
            throw new RuntimeException("Event not found with ID: " + eventID);
        }
    }

    public List<Event> getUpcomingEventsForNextWeek() {
        LocalDate today = LocalDate.now(); // Get today's date
        LocalDate nextWeek = today.plusDays(7); // Get the date one week from today
        return eventRepository.findByEventDateBetween(today, nextWeek);
    }

    // Fetch all events and count them per month (No repository query needed)
    public List<Map<String, Object>> getEventCountsByMonth() {
        List<Event> events = eventRepository.findAll(); // Fetch all events

        // Create a map to store the count of events for each month (Jan to Dec)
        Map<Integer, Long> eventCountMap = Arrays.stream(Month.values())
                .collect(Collectors.toMap(
                        Month::getValue,  // Get month as an integer (1 = Jan, 2 = Feb, etc.)
                        month -> 0L       // Initialize with 0 events
                ));

        // Process events and count them per month
        for (Event event : events) {
            int month = event.getEventDate().getMonthValue(); // Extract month number (1-12)
            eventCountMap.put(month, eventCountMap.getOrDefault(month, 0L) + 1);
        }

        // Convert map to a List of Maps for easy Thymeleaf/JavaScript consumption
        List<Map<String, Object>> eventCounts = new ArrayList<>();
        for (Map.Entry<Integer, Long> entry : eventCountMap.entrySet()) {
            Map<String, Object> monthData = new HashMap<>();
            monthData.put("month", entry.getKey());
            monthData.put("eventCount", entry.getValue());
            eventCounts.add(monthData);
        }

        return eventCounts;
    }
}


/*
 //Get all events by month
    public List<Map<String, Object>> getEventCountsByMonth() {
        List<Object[]> results = eventRepository.getEventCountsPerMonth();
        List<Map<String, Object>> eventCounts = new ArrayList<>();
        for (Object[] result : results) {
            Map<String, Object> monthData = new HashMap<>();
            monthData.put("month", result[0]);
            monthData.put("eventCount", result[1]);
            eventCounts.add(monthData);
        }
        return eventCounts;
    }
 */





















/*
    //Update an existing event
    public Event updateEvent(String eventID, Event updatedEvent) {
        return eventRepository.findById(eventID)
                .map(event -> {
                    event.setEventName(updatedEvent.getEventName());
                    event.setEventDate(updatedEvent.getEventDate());
                    event.setEventStatus(updatedEvent.getEventStatus());
                    return eventRepository.save(event);
                })
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventID));
    }

 */





/*
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

            /*
            Code to calculate "EventCompletionPercentage" comes from ChatGPT - Query: I want to be able to return a percentage of tasks completed for an event to display
             on my event dashboard, given my current code, what is the best way to do that?
             */
            /*
            double EventCompletionPercentage = totalTasks > 0 ? (completedTasks * 100.0 / totalTasks) : 0; // the maths to calculate the percentage value

             *



            eventDTOs.add(new EventDTO(event.getEventID(), event.getEventName(), event.getEventDate(), event.getEventStatus()));
        }

        return eventDTOs;
    }

    // The createEvent method creates a new event by mapping details from an EventDTO object to an Event entity
    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setEventID(eventDTO.getEventID());
        event.setEventName(eventDTO.getEventName());
        event.setEventDate(eventDTO.getEventDate());
        event.setEventStatus("Pending"); //Set default status for a new event

        //Returns the saved event as a new EventDTO
        Event savedEvent = eventRepository.save(event);
        return new EventDTO(savedEvent.getEventID(), savedEvent.getEventName(), savedEvent.getEventDate(), savedEvent.getEventStatus());
    }

    // The assignTaskToEvent method assigns a new task to an event by associating the event ID with the task and setting its default status to "Pending" before saving it to the database.
    public Task assignTaskToEvent(String eventID, Task task) {
        Event event = eventRepository.findById(eventID)
                .orElseThrow(() -> new NoSuchElementException("Event not found"));

        task.setEventID(eventID);  // Set the event ID for the task
        task.setEventStatus("Pending"); // Default status when assigning a task

        return taskRepository.save(task);  // Return the saved task
    }

    //The getAllEvents method retrieves all events from the database, converts them into a list of EventDTO objects, and returns this list.
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event event : events) {
            eventDTOs.add(new EventDTO(event.getEventID(), event.getEventName(), event.getEventDate(), event.getEventStatus()));
        }
        return eventDTOs;
    }


}
*/



















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
