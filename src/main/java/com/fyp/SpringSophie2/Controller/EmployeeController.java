package com.fyp.SpringSophie2.Controller;


import com.fyp.SpringSophie2.Service.EmployeeService;
import com.fyp.SpringSophie2.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    //Constructor-based dependency injection
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    //Get an employee by username
    @GetMapping("/{username}")
    public ResponseEntity<Employee> getEmployeeByUsername(@PathVariable String username) {
        Optional<Employee> employee = employeeService.getEmployeeByUsername(username);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //Create a new employee
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    //Update an existing employee
    @PutMapping("/{username}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String username, @RequestBody Employee updatedEmployee) {
        try {
            Employee employee = employeeService.updateEmployee(username, updatedEmployee);
            return ResponseEntity.ok(employee);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //Delete an employee by username
    @DeleteMapping("/{username}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable String username) {
        try {
            employeeService.deleteEmployee(username);
            return ResponseEntity.noContent().build(); //Return 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
