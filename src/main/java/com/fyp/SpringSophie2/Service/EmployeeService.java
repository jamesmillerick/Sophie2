package com.fyp.SpringSophie2.Service;

import com.fyp.SpringSophie2.Repository.EmployeeRepository;
import com.fyp.SpringSophie2.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //Get an employee by username
    public Optional<Employee> getEmployeeByUsername(String username) {
        return employeeRepository.findById(username);
    }

    //Create a new employee
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    //Update an existing employee
    public Employee updateEmployee(String username, Employee updatedEmployee) {
        return employeeRepository.findById(username)
                .map(employee -> {
                    employee.setFirstName(updatedEmployee.getFirstName());
                    employee.setLastName(updatedEmployee.getLastName());
                    employee.setRole(updatedEmployee.getRole());
                    employee.setPassword(updatedEmployee.getPassword());
                    return employeeRepository.save(employee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with username: " + username));
    }

    //Delete an employee by username
    public void deleteEmployee(String username) {
        if (employeeRepository.existsById(username)) {
            employeeRepository.deleteById(username);
        } else {
            throw new RuntimeException("Employee not found with username: " + username);
        }
    }
}
