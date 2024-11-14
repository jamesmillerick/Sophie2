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
}
