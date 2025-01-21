package com.fyp.SpringSophie2.Controller;


import com.fyp.SpringSophie2.Service.EmployeeService;
import com.fyp.SpringSophie2.Service.EventService;
import com.fyp.SpringSophie2.Service.TaskService;
import com.fyp.SpringSophie2.model.Employee;
import com.fyp.SpringSophie2.model.Event;
import com.fyp.SpringSophie2.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {


    private final TaskService taskService;
    private final EmployeeService employeeService;
    private final EventService eventService;


    //Constructor-based dependency
    public TaskController(TaskService taskService, EmployeeService employeeService, EventService eventService) {
        this.taskService = taskService;
        this.employeeService = employeeService;
        this.eventService = eventService;
    }

    //Get all tasks
    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "TaskDashboard"; //Ensure this matches the ThymeLeaf template
    }

    //Get a task by its ID
    @GetMapping("/{taskId}")
    public String getTaskById(@PathVariable Long taskId, Model model) {
        Optional<Task> task = taskService.getTaskById(taskId);
        task.ifPresent(t -> model.addAttribute("task", t));
        return "viewTask"; //View task details template
    }

    //Create a new Task Form
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("employees", employees);
        model.addAttribute("events", events);
        model.addAttribute("task", new Task()); //Empty task to fill out
        return "createTask"; //Create task form template
    }

    //Create a new task
    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task) {
        taskService.createTask(task);
        return "redirect:/tasks"; //Redirect to task dashboard after creation
    }

    //Update task form
    @GetMapping("/{taskId}/edit")
    public String showEditForm(@PathVariable Long taskId, Model model) {
        Optional<Task> task = taskService.getTaskById(taskId);
        if (task.isPresent()) {
            List<Employee> employees = employeeService.getAllEmployees();
            List<Event> events = eventService.getAllEvents();
            model.addAttribute("employees", employees);
            model.addAttribute("events", events);
            model.addAttribute("task", task.get());
            return "editTask"; //Edit task form template
        }
        return "redirect:/tasks"; //Redirect to task dashboard if task not found
    }

    //Update an existing task
    @PostMapping("/{taskId}/edit")
    public String updateTask(@PathVariable Long taskId, @ModelAttribute Task updatedTask) {
        taskService.updateTask(taskId, updatedTask);
        return "redirect:/tasks"; // Redirect to task dashboard after update
    }


    // Delete a task
    @GetMapping("/{taskId}/delete")
    public String deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/tasks"; // Redirect to task dashboard after delete
    }

    // Get all tasks assigned to the logged-in user
    @GetMapping("/TaskAssigned")
    public String showTasks(@RequestParam("username") String username, Model model) {
        List<Task> tasks = taskService.getTasksByAssignedEmployeeUsername(username);
        model.addAttribute("tasks", tasks);
        return "TaskAssigned"; //Redirect to TaskAssigned.html and should display only the tasks assigned to the individual based on their username
    }

    // Update the status of each task
    @PostMapping("/{taskId}/updateStatus")
    public String updateTaskStatus(@PathVariable Long taskId, @RequestParam("status") String status) {
        Optional<Task> task = taskService.getTaskById(taskId);
        if (task.isPresent()) {
            Task updatedTask = task.get();
            updatedTask.setTaskStatus(status);
            taskService.updateTask(taskId, updatedTask);
        }
        return "redirect:/tasks"; // Redirect back to the task list after updating status
    }

}
