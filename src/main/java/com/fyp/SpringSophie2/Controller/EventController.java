package com.fyp.SpringSophie2.Controller;

import com.fyp.SpringSophie2.Service.EventService;
import com.fyp.SpringSophie2.model.EventDTO;
import com.fyp.SpringSophie2.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/events") //local host source for events
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Render the dashboard with events
    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    // Endpoint to create a new event (form data)
    @PostMapping("/create")
    public ResponseEntity<EventDTO> createEvent(
            @RequestParam String eventID,       //@RequestParam annotation to map the individual form fields directly from the form submission.
            @RequestParam String eventName,
            @RequestParam String eventDate,
            @RequestParam String eventStatus) {

        EventDTO eventDTO = new EventDTO(
                eventID,
                eventName,
                LocalDate.parse(eventDate),
                eventStatus,
                0.0);

        EventDTO createdEvent = eventService.createEvent(eventDTO);
        return ResponseEntity.status(201).body(createdEvent); // Return created event
    }
/*
    // Create a new event with POST (accepting JSON from the front-end)
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        EventDTO createdEvent = eventService.createEvent(eventDTO);
        return ResponseEntity.status(201).body(createdEvent); // When creating a new event, a 201 Created HTTP status code is returned, which is the standard response for successful resource creation.
    }

 */

    // Assign tasks to an event
    @PostMapping("/{eventID}/tasks")
    public ResponseEntity<Task> assignTaskToEvent(
            @PathVariable String eventID,
            @RequestBody Task task
    ) {
        Task assignedTask = eventService.assignTaskToEvent(eventID, task);
        return ResponseEntity.ok(assignedTask);
    }
}

