package com.fyp.SpringSophie2.Controller;


import com.fyp.SpringSophie2.Service.EventService;
import com.fyp.SpringSophie2.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    //Constructor-based dependency injection
    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /*
    (Model model) - ui.Model and "addAttribute" comes from https://github.com/sprashantofficial/CRUDWithPostgresAndJSPExample/blob/main/src/main/java/com/example/jspdemo/controller/AnimeController.java
     */

    //Get all events and render them with ThymeLeaf
    @GetMapping
    public String getAllEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "EventDashboard"; //Ensure this matches the file name of the ThymeLeaf template
    }

    /*
    //Get an event by eventID
    @GetMapping("/{eventID}")
    public ResponseEntity<Event> getEventById(@PathVariable String eventID) {
        Optional<Event> event = eventService.getEventById(eventID);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

     */
    // Create a new event
    @PostMapping
    public String createEvent(@ModelAttribute Event event) {
        eventService.createEvent(event);
        return "redirect:/events";  // Redirect to the list of events
    }

    //Show the form for creating an event
    @GetMapping("/create")
    public String showCreateEventForm(Model model) {
        model.addAttribute("event", new Event());  // Create a new empty event object for the form
        return "createEvent";  // This should match the name of your Thymeleaf template
    }

    //Show the form for editing an event
    @GetMapping("/{eventID}/edit")
    public String showEditForm(@PathVariable String eventID, Model model) {
        Optional<Event> event = eventService.getEventById(eventID);
        if (event.isPresent()) {
            model.addAttribute("event", event.get());
            return "editEvent"; //Make sure this view exists for editing
        }
        return "redirect:/events";

    }

    //Update an existing event
    @PostMapping("/{eventID}/edit")
    public String updateEvent(@PathVariable String eventID, @ModelAttribute Event updatedEvent) {
        eventService.updateEvent(eventID, updatedEvent);
        return "redirect:/events"; //Redirect to the event list after update
    }

/*
Adapted code for "delete by ID" from https://github.com/sprashantofficial/CRUDWithPostgresAndJSPExample/blob/main/src/main/java/com/example/jspdemo/controller/AnimeController.java
 */
    //Delete an event by its ID
    @GetMapping("/{eventID}/delete")
    public String deleteEvent(@PathVariable String eventID) {
        eventService.deleteEvent(eventID);
        return "redirect:/events"; //Redirect to the event list after delete
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

