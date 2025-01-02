package com.fyp.SpringSophie2.Controller;


import com.fyp.SpringSophie2.Service.EventService;
import com.fyp.SpringSophie2.model.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    //Constructor-based dependency injection
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    //Get all events
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    //Get an event by eventID
    @GetMapping("/{eventID}")
    public ResponseEntity<Event> getEventById(@PathVariable String eventID) {
        Optional<Event> event = eventService.getEventById(eventID);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //Create a new event
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    //Update an existing event
    @PutMapping("/{eventID}")
    public ResponseEntity<Event> updateEvent(@PathVariable String eventID, @RequestBody Event updatedEvent) {
        try {
            Event event = eventService.updateEvent(eventID, updatedEvent);
            return ResponseEntity.ok(event);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //Delete an event by its ID
    @DeleteMapping("/{eventID}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String eventID) {
        try {
            eventService.deleteEvent(eventID);
            return ResponseEntity.noContent().build(); //Return 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}























/*
package com.fyp.SpringSophie2.Controller;


import com.fyp.SpringSophie2.Service.EventService;
import com.fyp.SpringSophie2.model.EventDTO;
import com.fyp.SpringSophie2.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/*
CRUD operation (RequestMapping, GetMapping and PostMapping annotations) comes from "Java REST API with Spring Boot Tutorial | REST API CRUD Implementation"
by ThinkConstructive, published Oct 2023 - https://www.youtube.com/watch?v=FRT38sQeZ-w
 *
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
                eventStatus);

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

 *

    // Assign tasks to an event
    @PostMapping("/{eventID}/assign-task")
    public ResponseEntity<Task> assignTaskToEvent(
            @PathVariable String eventID,
            @RequestParam int taskID,
            @RequestParam String taskDescription,
            @RequestParam String dueDate,
            @RequestParam String eventStatus)
    {
        Task task = new Task();
        task.setTaskID(taskID);
        task.setTaskDescription(taskDescription);
        task.setDueDate(LocalDate.parse(dueDate));
        task.setEventStatus(eventStatus);

        Task assignedTask = eventService.assignTaskToEvent(eventID, task);
        return ResponseEntity.ok(assignedTask);
    }
}

 */

