/*
package com.fyp.SpringSophie2.Controller;

import com.fyp.SpringSophie2.Repository.EmployeeRepository;
import com.fyp.SpringSophie2.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
RestController comes from "Java REST API with Spring Boot Tutorial | REST API CRUD Implementation"
by ThinkConstructive, published Oct 2023 - https://www.youtube.com/watch?v=FRT38sQeZ-w


@RestController
@RequestMapping("/employee")
public class EmployeeAPIService {

    /*
    @Autowired annotation came from ChatGPT, it was a more effective way to enable loose coupling between
    the interface and the controller class
     *
    @Autowired
    private EmployeeRepository employeeRepository;  //Injects the EmployeeRepository into my controller class (EmployeeAPIService)

    /*
    authenticate function comes from ChatGPT - assists in the servlet-based login format
     *

    public Employee authenticate(String username, String password) { // Query your database to verify credentials
        return employeeRepository.findByUsernameAndPassword(username, password);
    }





    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();  // Retrieves all employees from the database
    }
    //GetMapping annotation allows the user to fetch staff data from the PostgresSQL database.
    @GetMapping("{username}")
    public Employee getEmployeeDetails(@PathVariable String username) {
        return employeeRepository.findById(username).orElse(null); // Retrieves an employee by ID
    }


    /*
    Annotation comes from "Java REST API with Spring Boot Tutorial | REST API CRUD Implementation"
by ThinkConstructive, published Oct 2023 - https://www.youtube.com/watch?v=FRT38sQeZ-w
     *
    @PostMapping
    //PostMapping annotation allows the user to create a username and fill in rest of user credentials into the PostgresSQL database in the employee table
    public ResponseEntity<Employee> createEmployeeDetails(@RequestBody Employee employee) {
        Employee createdEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(createdEmployee);
    }


    /*
Annotation comes from "Java REST API with Spring Boot Tutorial | REST API CRUD Implementation"
by ThinkConstructive, published Oct 2023 - https://www.youtube.com/watch?v=FRT38sQeZ-w
 *
    @PutMapping("{username}") //@PutMapping annotation allows the user to update any username credentials in the PostgresSQL database in the employee table
    public ResponseEntity<Employee> updateEmployeeDetails(@PathVariable String username, @RequestBody Employee employeeDetails) {
        return employeeRepository.findById(username).map(employee -> {
            employee.setUsername(employeeDetails.getUsername());
            employee.setfName(employeeDetails.getfName());
            employee.setsName(employeeDetails.getsName());
            employee.setRole(employeeDetails.getRole());
            employee.setPassword(employeeDetails.getPassword());
            employee.setTasks(employeeDetails.getTasks());
            Employee updatedEmployee = employeeRepository.save(employee);
            return ResponseEntity.ok(updatedEmployee);
        }).orElse(ResponseEntity.notFound().build());
    }


    /*
    Annotation comes from "Java REST API with Spring Boot Tutorial | REST API CRUD Implementation"
by ThinkConstructive, published Oct 2023 - https://www.youtube.com/watch?v=FRT38sQeZ-w
     *
    @DeleteMapping("{username}") //@DeleteMapping annotation should allow the user to delete any staff from the PostgresSQL database in the employees table
    public ResponseEntity<Void> deleteEmployeeDetails(@PathVariable String username) {
        if (employeeRepository.existsById(username)) {
            employeeRepository.deleteById(username);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

 */
