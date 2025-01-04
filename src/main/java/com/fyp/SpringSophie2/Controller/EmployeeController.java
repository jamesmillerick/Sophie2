package com.fyp.SpringSophie2.Controller;


import com.fyp.SpringSophie2.Service.EmployeeService;
import com.fyp.SpringSophie2.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
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

    //Get all employees
    @GetMapping
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employeeList"; //ThymeLeaf template name for displaying all employees
    }

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


    //Delete an employee by username
    @GetMapping("/{username}/delete")
    public String deleteEmployee(@PathVariable String username) {
        employeeService.deleteEmployee(username);
        return "redirect:/employees"; //Redirect to the employee list after deleting
    }

}
