package com.fyp.SpringSophie2.Controller;

import com.fyp.SpringSophie2.Service.EmployeeService;
import com.fyp.SpringSophie2.model.Employee;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    private final EmployeeService employeeService;

    @Autowired
    public LoginController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Handle GET requests to display the login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Return the login page view (login.html)
    }

    // Handle login authentication
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {
        Optional<Employee> employeeOptional = employeeService.getEmployeeByUsername(username);

        //Validate Credentials
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            if (employee.getPassword().equals(password)) {
                // Store the logged-in employee in session
                session.setAttribute("loggedInEmployee", employee);

                //Redirect based on role
                if (isManagerRole(employee.getRole())) {
                    return "redirect:/index"; //Redirects to the Manager Dashboard
                } else {
                    //Pass username as a query parameter to TaskAssigned
                    return "redirect:/tasks/TaskAssigned?username=" + username; //Redirects to the Assigned Task page
                }
            }
        }

        //Invalid Credentials - show error message
        model.addAttribute("error", "Invalid username or password");
        return "login";  //Redirect back to the login page
    }

    // API Endpoint to get the logged-in employee details
    @PostMapping("/account/details")
    public ResponseEntity<Employee> getEmployeeDetails(HttpSession session) {
        Employee employee = (Employee) session.getAttribute("loggedInEmployee");

        if (employee == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(employee);
    }

    /*
    Boolean function was adapted from https://www.geeksforgeeks.org/java-string-equalsignorecase-method-with-examples/
    because I needed to have a way to return certain values but exploit others
     */
    // Function to check if the role is a manager role
    private boolean isManagerRole(String role) {
        return role.equalsIgnoreCase("event manager")
                || role.equalsIgnoreCase("general manager")
                || role.equalsIgnoreCase("hotel manager")
                || role.equalsIgnoreCase("wedding coordinator");
    }
}
