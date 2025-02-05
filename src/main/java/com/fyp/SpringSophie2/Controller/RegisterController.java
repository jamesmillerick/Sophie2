package com.fyp.SpringSophie2.Controller;

import com.fyp.SpringSophie2.Service.EmployeeService;
import com.fyp.SpringSophie2.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class RegisterController {

    private final EmployeeService employeeService;

    @Autowired
    public RegisterController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "register"; //This is the Thymeleaf template
    }

    @PostMapping("/register")
    public String registerEmployee(@ModelAttribute Employee employee) {
        // Explicitly set tasks as an empty list to prevent orphaned task reference
        employee.setTasks(new ArrayList<>());

        //Save the employee details in the database
        employeeService.createEmployee(employee);
        return "redirect:/login"; //Return to Log in after successful registration
    }
}
