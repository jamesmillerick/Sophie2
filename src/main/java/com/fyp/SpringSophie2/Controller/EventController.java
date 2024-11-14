package com.fyp.SpringSophie2.Controller;

import com.fyp.SpringSophie2.Service.EventService;
import com.fyp.SpringSophie2.model.EventDTO;
import com.fyp.SpringSophie2.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> events = eventService.getAllEventsWithTaskCompletion();
        return ResponseEntity.ok(events);
    }

    // Create a new event
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        EventDTO createdEvent = eventService.createEvent(eventDTO);
        return ResponseEntity.ok(createdEvent);
    }

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
