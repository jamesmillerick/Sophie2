package com.fyp.SpringSophie2.Controller;

import com.fyp.SpringSophie2.model.Employee;
import com.fyp.SpringSophie2.model.Event;
import com.fyp.SpringSophie2.model.Task;
import com.fyp.SpringSophie2.Service.EmployeeService;
import com.fyp.SpringSophie2.Service.EventService;
import com.fyp.SpringSophie2.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


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
}

