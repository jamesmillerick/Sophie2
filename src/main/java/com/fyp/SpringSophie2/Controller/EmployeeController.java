package com.fyp.SpringSophie2.Controller;


import com.fyp.SpringSophie2.Service.EmployeeService;
import com.fyp.SpringSophie2.model.Employee;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    //Constructor-based dependency injection
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /*
    (Model model) - ui.Model and "addAttribute" comes from https://github.com/sprashantofficial/CRUDWithPostgresAndJSPExample/blob/main/src/main/java/com/example/jspdemo/controller/AnimeController.java
     */
    //Get all employees
    @GetMapping
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employeeList"; //ThymeLeaf template name for displaying all employees
    }

    //Code is kept in the event if I need it
    /*
    //Get an employee by username
    @GetMapping("/{username}")
    public ResponseEntity<Employee> getEmployeeByUsername(@PathVariable String username) {
        Optional<Employee> employee = employeeService.getEmployeeByUsername(username);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
     */

    //Show form to create a new employee
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee()); //Empty employee for the form
        return "createEmployee"; //ThymeLeaf template for creating an employee
    }

    //Create a new employee
    @PostMapping
    public String createEmployee(@ModelAttribute Employee employee) {
        employeeService.createEmployee(employee);
        return "redirect:/employees"; //Redirect to the employee list after creation
    }

    /*
    Code is adapted from ChatGPT - Prompt: "I have a way to display a form to create a new employee existing but how do I alter that code to show a form to edit an employee?"
     */
    //Show form to edit an employee
    @GetMapping("/{username}/edit")
    public String showEditForm(@PathVariable String username, Model model) {
        Optional<Employee> employee = employeeService.getEmployeeByUsername(username);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "editEmployee"; //ThymeLeaf template for editing an employee
        }
        return "redirect:/employees"; //If not found, redirect to the employee list
    }

    //Update an existing employee
    @PostMapping("/{username}/edit")
    public String updateEmployee(@PathVariable String username, @ModelAttribute Employee updatedEmployee) {
        employeeService.updateEmployee(username, updatedEmployee);
        return "redirect:/employees"; //Redirect to the employee list after updating
    }



    @GetMapping("/{username}")
    public ResponseEntity<Employee> getEmployeeByUsername(@PathVariable String username) {
        Employee employee = employeeService.findByUsername(username);
        if (employee != null) {
            return ResponseEntity.ok(employee); // Return employee details as JSON
        }
        return ResponseEntity.notFound().build();
    }


    //Delete an employee by username
    @GetMapping("/{username}/delete")
    public String deleteEmployee(@PathVariable String username) {
        employeeService.deleteEmployee(username);
        return "redirect:/employees"; //Redirect to the employee list after deleting
    }

    @GetMapping("/employees/current")
    @ResponseBody
    public ResponseEntity<Employee> getCurrentEmployee(HttpSession session) {
        String username = (String) session.getAttribute("loggedInUser");

        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Employee employee = employeeService.findByUsername(username);

        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(employee);
    }

    /*
    //Allows the event manager to search for an employee
    @GetMapping("/search")
    public String searchEmployees(@RequestParam("firstName") Optional<String> firstName,
                                  @RequestParam("lastName") Optional<String> lastName,
                                  Model model) {
        List<Employee> employees;

        if (firstName.isPresent() && lastName.isPresent()) {
            employees = employeeService.searchEmployeesByFirstAndLastName(firstName.get(), lastName.get());
        } else if (firstName.isPresent()) {
            employees = employeeService.searchEmployeesByFirstName(firstName.get());
        } else if (lastName.isPresent()) {
            employees = employeeService.searchEmployeesByLastName(lastName.get());
        } else {
            employees = employeeService.getAllEmployees(); // Return all employees if no query provided
        }

        model.addAttribute("employees", employees);
        return "employeeList"; // Returns the employee list
    }

     */

}
