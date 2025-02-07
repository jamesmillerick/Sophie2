package com.fyp.SpringSophie2.Controller;

import com.fyp.SpringSophie2.model.Employee;
import com.fyp.SpringSophie2.model.Event;
import com.fyp.SpringSophie2.model.Task;
import com.fyp.SpringSophie2.Service.EmployeeService;
import com.fyp.SpringSophie2.Service.EventService;
import com.fyp.SpringSophie2.Service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;


//This is the main dashboard for the event manager to view

/*
Code for this came from ChatGPT prompt - Query: I want to create a way for the event manager to be able to see all three dashboards on one page, acting as a master dashboard
Code was edited to suit my application
 */

@Controller
public class ManagerController {

    private final TaskService taskService;
    private final EmployeeService employeeService;
    private final EventService eventService;

    @Autowired
    public ManagerController(TaskService taskService, EmployeeService employeeService, EventService eventService) {
        this.taskService = taskService;
        this.employeeService = employeeService;
        this.eventService = eventService;
    }

    @GetMapping("/index")
    public String showIndexPage(Model model) {
        // Fetch only upcoming and ongoing events
        List<Event> upcomingEvents = eventService.getUpcomingEventsForNextWeek();

        // Create a map to store tasks per event
        Map<String, List<Task>> eventTasks = new HashMap<>();


        for (Event event : upcomingEvents) {
            List<Task> incompleteTasks = taskService.getIncompleteTasksByEvent(event.getEventID());

            // Sort tasks: "Not Started" first, then "Started"
            incompleteTasks.sort(Comparator.comparing(task -> {
                if ("Not Started".equals(task.getTaskStatus())) return 0;
                if ("Started".equals(task.getTaskStatus())) return 1;
                return 2; // Just in case other statuses exist
            }));

            eventTasks.put(event.getEventID(), incompleteTasks);
        }

        // Fetch all employees (optional, if needed elsewhere)
        List<Employee> employees = employeeService.getAllEmployees();

        // Count all tasks by roles for events
        Map<String, Long> roleTaskCounts = taskService.getTaskCountByRole();

        // Get event counts by month for 2025
        List<Map<String, Object>> eventCountsByMonth = eventService.getEventCountsByMonth();

        System.out.println("Event counts by month: " + eventCountsByMonth);

        // Add attributes to the model for Thymeleaf
        model.addAttribute("events", upcomingEvents);
        model.addAttribute("employees", employees);
        model.addAttribute("eventTasks", eventTasks);
        model.addAttribute("roleTaskCounts", roleTaskCounts);
        model.addAttribute("eventCountsByMonth", eventCountsByMonth);

        // Return the Thymeleaf view
        return "index"; // Points to the index.html file in Thymeleaf templates folder
        }

    // Display account settings page with user details
    @GetMapping("/accountSettings")
    public String accountSettings(HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("loggedInEmployee");

        if (employee == null) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }

        model.addAttribute("employee", employee);
        return "accountSettings"; // Renders accountSettings.html
    }
}

/*
@GetMapping("/ManagerDashboard")
    public String showManagerDashboard(Model model) {
        // Fetch all tasks
        List<Task> tasks = taskService.getAllTasks();

        // Fetch all employees
        List<Employee> employees = employeeService.getAllEmployees();

        // Fetch all events
        List<Event> events = eventService.getAllEvents();

        // Add attributes to the model for Thymeleaf
        model.addAttribute("tasks", tasks);
        model.addAttribute("employees", employees);
        model.addAttribute("events", events);

        // Return the Thymeleaf view
        return "ManagerDashboard";
    }
 */

